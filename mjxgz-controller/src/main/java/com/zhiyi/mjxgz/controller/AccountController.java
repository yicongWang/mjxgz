   package com.zhiyi.mjxgz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.constants.InfoState;
import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.controller.common.AccessRequired;
import com.zhiyi.mjxgz.controller.common.CurrentRedisUserData;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.model.Account;
import com.zhiyi.mjxgz.model.LoginLog;
import com.zhiyi.mjxgz.qo.LoginQo;
import com.zhiyi.mjxgz.service.AccountJpushService;
import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.service.LoginLogService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.JwtHelper;
import com.zhiyi.mjxgz.util.Md5Utils;
import com.zhiyi.mjxgz.util.NetworkUtil;
import com.zhiyi.mjxgz.util.RedisUtil;
import com.zhiyi.mjxgz.util.ServiceUtil;
import com.zhiyi.mjxgz.util.UserSettings;
import com.zhiyi.mjxgz.util.ValidatorUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户信息管理接口
 * Created by DW on 2017/6/27.
 */
@Api(description = "账户信息管理接口")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountService accountService;
  
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private UserSettings userSettings;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AccountJpushService accountJpushService;
    
    /**
     * 用户登录
     *
     * @param accountDTO 用户对象
     * @return 请求响应
     */
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse userLogin(@RequestBody LoginQo loginDTO,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    Device device) {
        //默认用户名或密码错误，
        CommonResponse commonResponse = new CommonResponse(ResponseCode.SOURCE_NOT_EXIST_ERROR, "用户名或密码错误！");
        try {
            Map<String, String> errorMap = new HashMap<String, String>();
            if (ValidatorUtil.validParameterAlert(loginDTO.getAccount(), "account", true, 1, 36, 4, errorMap) > 0) {
                logger.info("Parameter [account] check failed.");
            }
            if (ValidatorUtil.validParameterAlert(loginDTO.getPassword(), "password", true, 1, 20, 4, errorMap) > 0) {
                logger.info("Parameter [password] check failed.");
            }
            if (!errorMap.isEmpty()) {
                return new CommonResponse(ResponseCode.PARAMETER_ERROR, "参数校验未通过", errorMap);
            }
            Account account = new Account();
            account.setMobilePhone(loginDTO.getAccount());
            List<Account> list = accountService.findAccounts(account);
            if(CollectionUtils.isNotEmpty(list)){
            	account = list.get(0);
            	if(null != account ){
            		  if (account.getStatus().equals(InfoState.BLOCKED)) {
                          commonResponse.setMsg("该用户已锁定！");
                      } else if (account.getStatus().equals(InfoState.NORMAL)) {
                          String inputPwd = Md5Utils.encryptPassword(account.getMobilePhone(), loginDTO.getPassword(), account.getSalt());
                          if (account.getPassword().equals(inputPwd)) {
                              Date currentDate = DateUtil.now();
                              //使用Redis 保存用户token 菜单，和权限信息
                              String accessToken = ServiceUtil.CreateAccessToken(account.getMobilePhone(), String.valueOf(System.currentTimeMillis()));
                              RedisUserData redisUserData = new RedisUserData();
                              redisUserData.setAccessToken(accessToken);
                              BeanUtils.copyProperties(account, redisUserData);
                              response.setHeader("access_token", accessToken);
                              System.out.println(accessToken);
                              
                              Long invalid = Long.parseLong(userSettings.getSessionInvalid()) * 60;
                              redisUserData.setLoginTime(DateUtil.dateToStr(currentDate,DateUtil.DATETIME_DEFAULT_FORMAT));
                              //设置请求ip地址和访问终端类型
                              try{
                                  redisUserData.setRequestIP(NetworkUtil.getIpAddress(request));
                                  redisUserData.setTerminalType(jwtHelper.generateAudience(device));
                              }catch (Exception e){
                                  e.printStackTrace();
                              }
                              //将 redisUserData对象 保存到redis中
                              boolean isWrite = redisUtil.set(accessToken, redisUserData, invalid);
                              if (isWrite && this.addLoginLog(redisUserData,currentDate)) {
                                  commonResponse.setMsg("account login success");
                                  commonResponse.setCode(ResponseCode.SUCCESS);
                                  redisUserData.setRequestIP(null);
                                  redisUserData.setTerminalType(null);
                                  commonResponse.setData(redisUserData);
                              } else {
                                  commonResponse.setMsg("account login failed, userData is write failed");
                                  commonResponse.setCode(ResponseCode.SERVER_ERROR);
                              }
                          }else{
                        	 return commonResponse;
                          }
                      } else {
                          commonResponse.setMsg("用户状态错误！");
                          return commonResponse;
                      }
            	}
            }
            logger.info(commonResponse.getMsg());
        } catch (Exception e) {
            logger.error("account login failed", e);
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("service error");
        }
        return commonResponse;
    }

    /**
     * 用户退出登录
     *
     * @param redisUserData
     * @return
     */
    @ApiOperation(value = "用户退出登录")
    @AccessRequired
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public CommonResponse logout(@CurrentRedisUserData RedisUserData redisUserData,@RequestHeader String access_token) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            //移除accessToken
            redisUtil.delKey(redisUserData.getAccessToken());
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setMsg("account [" + redisUserData.getAccount() + "] logout success");
            logger.info(commonResponse.getMsg());
        } catch (Exception e) {
            logger.error("account [" + redisUserData.getAccount() + "] logout failed", e);
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("service error");
        }
        return commonResponse;
    }
    


    /**
     * 添加登录日志
     * @param redisUserData redisUserData
     * @param loginTime  登录时间
     * @return  true表示成功，false表示失败
     */
    private boolean addLoginLog(RedisUserData redisUserData,Date loginTime){
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginUser(redisUserData.getId());
        loginLog.setIp(redisUserData.getRequestIP());
        loginLog.setTerminalType(redisUserData.getTerminalType());
        loginLog.setLoginTime(loginTime);
        loginLog.setRemark("登录成功！");
        return loginLogService.add(loginLog) > 0 ? true : false;
    }

    
}

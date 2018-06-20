package com.zhiyi.mjxgz.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.controller.common.AccessRequired;
import com.zhiyi.mjxgz.controller.common.CurrentRedisUserData;
import com.zhiyi.mjxgz.dao.ex.AccountMapperExt;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.model.Account;
import com.zhiyi.mjxgz.model.LoginLog;
import com.zhiyi.mjxgz.service.AccountCouponService;
import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.service.ActivationCodeService;
import com.zhiyi.mjxgz.service.BusinessShopService;
import com.zhiyi.mjxgz.service.LoginLogService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.HttpRequest;
import com.zhiyi.mjxgz.util.JwtHelper;
import com.zhiyi.mjxgz.util.NetworkUtil;
import com.zhiyi.mjxgz.util.RandomStr;
import com.zhiyi.mjxgz.util.RedisUtil;
import com.zhiyi.mjxgz.util.ServiceUtil;
import com.zhiyi.mjxgz.util.StdRandom;
import com.zhiyi.mjxgz.util.UserSettings;
import com.zhiyi.mjxgz.vo.ActiveInfoVO;
import com.zhiyi.mjxgz.vo.UserInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 账户信息管理接口 Created by DW on 2017/6/27.
 */
@Api(description = "账户信息管理接口")
@RestController
@RequestMapping("/accounts")
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountMapperExt accountMapperExt;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private UserSettings userSettings;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private AccountCouponService accountCouponService;

	@Autowired
	private ActivationCodeService activationCodeService;
	@Autowired
	private BusinessShopService businessShopService;
	/**
	 * 用户登录
	 *
	 * @param accountDTO
	 *            用户对象
	 * @return 请求响应
	 */
	/*
	 * @ApiOperation(value = "用户登录")
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public
	 * CommonResponse userLogin(@RequestBody LoginQo loginDTO,
	 * HttpServletRequest request, HttpServletResponse response, Device device)
	 * { //默认用户名或密码错误， CommonResponse commonResponse = new
	 * CommonResponse(ResponseCode.SOURCE_NOT_EXIST_ERROR, "用户名或密码错误！"); try {
	 * Map<String, String> errorMap = new HashMap<String, String>(); if
	 * (ValidatorUtil.validParameterAlert(loginDTO.getAccount(), "account",
	 * true, 1, 36, 4, errorMap) > 0) { logger.info(
	 * "Parameter [account] check failed."); } if
	 * (ValidatorUtil.validParameterAlert(loginDTO.getPassword(), "password",
	 * true, 1, 20, 4, errorMap) > 0) { logger.info(
	 * "Parameter [password] check failed."); } if (!errorMap.isEmpty()) {
	 * return new CommonResponse(ResponseCode.PARAMETER_ERROR, "参数校验未通过",
	 * errorMap); } Account account = new Account();
	 * account.setMobilePhone(loginDTO.getAccount()); List<Account> list =
	 * accountService.findAccounts(account);
	 * if(CollectionUtils.isNotEmpty(list)){ account = list.get(0); if(null !=
	 * account ){ if (account.getStatus().equals(InfoState.BLOCKED)) {
	 * commonResponse.setMsg("该用户已锁定！"); } else if
	 * (account.getStatus().equals(InfoState.NORMAL)) { String inputPwd =
	 * Md5Utils.encryptPassword(account.getMobilePhone(),
	 * loginDTO.getPassword(), account.getSalt()); if
	 * (account.getPassword().equals(inputPwd)) { Date currentDate =
	 * DateUtil.now(); //使用Redis 保存用户token 菜单，和权限信息 String accessToken =
	 * ServiceUtil.CreateAccessToken(account.getMobilePhone(),
	 * String.valueOf(System.currentTimeMillis())); RedisUserData redisUserData
	 * = new RedisUserData(); redisUserData.setAccessToken(accessToken);
	 * BeanUtils.copyProperties(account, redisUserData);
	 * response.setHeader("access_token", accessToken);
	 * System.out.println(accessToken);
	 * 
	 * Long invalid = Long.parseLong(userSettings.getSessionInvalid()) * 60;
	 * redisUserData.setLoginTime(DateUtil.dateToStr(currentDate,DateUtil.
	 * DATETIME_DEFAULT_FORMAT)); //设置请求ip地址和访问终端类型 try{
	 * redisUserData.setRequestIP(NetworkUtil.getIpAddress(request));
	 * redisUserData.setTerminalType(jwtHelper.generateAudience(device)); }catch
	 * (Exception e){ e.printStackTrace(); } //将 redisUserData对象 保存到redis中
	 * boolean isWrite = redisUtil.set(accessToken, redisUserData, invalid); if
	 * (isWrite && this.addLoginLog(redisUserData,currentDate)) {
	 * commonResponse.setMsg("account login success");
	 * commonResponse.setCode(ResponseCode.SUCCESS);
	 * redisUserData.setRequestIP(null); redisUserData.setTerminalType(null);
	 * commonResponse.setData(redisUserData); } else { commonResponse.setMsg(
	 * "account login failed, userData is write failed");
	 * commonResponse.setCode(ResponseCode.SERVER_ERROR); } }else{ return
	 * commonResponse; } } else { commonResponse.setMsg("用户状态错误！"); return
	 * commonResponse; } } } logger.info(commonResponse.getMsg()); } catch
	 * (Exception e) { logger.error("account login failed", e);
	 * commonResponse.setCode(ResponseCode.SERVER_ERROR); commonResponse.setMsg(
	 * "service error"); } return commonResponse; }
	 */

	// appId:wxd42ab7a49b945f43
	// app_secret:cbd13f4f4cb4f63e041a7ccc423f84ed
	/**
	 * 用户登录
	 *
	 * @param accountDTO
	 *            用户对象
	 * @return 请求响应
	 */
	@ApiOperation(value = "微信授权登录", response = RedisUserData.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CommonResponse loginByWeixin(
			@ApiParam(name = "jsCode", value = "授权code") @RequestParam(name = "jsCode") String jsCode,
			HttpServletRequest request, HttpServletResponse response, Device device) throws Exception {
		CommonResponse commonResponse = new CommonResponse(ResponseCode.SOURCE_NOT_EXIST_ERROR, "授权失败！");
		logger.info("Start getSessionKey");
		String appId = "wxd42ab7a49b945f43";
		String appSecret = "cbd13f4f4cb4f63e041a7ccc423f84ed";
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		// String httpUrl = url + "?appid=" + appId + "&secret=" + appSecret +
		// "&js_code=" + jsCode
		// + "&grant_type=authorization_code";
		try {
			String ret = HttpRequest.sendGet(url, "appid=" + appId + "&secret=" + appSecret + "&js_code=" + jsCode
					+ "&grant_type=authorization_code");
			// String ret = HttpRequest.sendGetRequest(httpUrl);
			logger.info("微信返回的结果 {}", ret);
			if (ret == null || "".equals(ret)) {
				commonResponse.setMsg("网络超时");
				logger.info("网络超时");
			} else {
				JSONObject obj = JSONObject.parseObject(ret);
				if (obj.containsKey("errcode")) {
					String errcode = obj.get("errcode").toString();
					logger.info("微信返回的错误码{}", errcode);
					commonResponse.setMsg(obj.get("errmsg").toString());
				} else if (obj.containsKey("session_key")) {
					logger.info("调微信成功");
					// 开始处理userInfo
					String openId = obj.get("openid").toString();
					System.out.println("openId==" + openId);
					Date currentDate = DateUtil.now();
					RedisUserData redisUserData = new RedisUserData();
					Long invalid = Long.parseLong(userSettings.getSessionInvalid());// 要小于30天session_key
					// String accessToken =
					// ServiceUtil.CreateAccessToken(obj.get("session_key").toString(),
					// String.valueOf(System.currentTimeMillis()));
					String accessToken = ServiceUtil.CreateAccessToken(obj.get("session_key").toString(),
							obj.get("openid").toString());
					redisUtil.set(openId, accessToken,invalid);// 记录openId的TOKEN
					Account searchAccount = new Account();
					searchAccount.setOpenid(openId);
					List<Account> list = accountService.findAccounts(searchAccount);
					Account account = null;
					if (CollectionUtils.isNotEmpty(list)) {
						logger.info("openId已经存在，不需要插入");
						account = list.get(0);
						redisUserData.setVipCode(account.getVipCode());
						redisUserData.setRoleIdentity(account.getRoleType() + "");
						redisUserData.setRoleName(
								(account.getRoleType() == null || account.getRoleType() == 0) ? "普通用户" : "店长");
						if(null != account.getExpireTime()){
							redisUserData.setExpireTime(DateUtil.dateToStr(account.getExpireTime(), "yyyy-MM-dd HH:mm:ss"));
						}
					} else {
						// 创建授权用户
						account = new Account();
						account.setOpenid(openId);
						account.setVipCode("V"+RandomStr.randomStr(3)+DateUtil.dateToStr(new Date(), "HHmmss"));
						account.setId(StdRandom.getUUID());
						account.setStatus(0);
						account.setCreateTime(new Date());
						accountService.insertAccounts(account);
						redisUserData.setRoleIdentity("0");
						redisUserData.setVipCode(account.getVipCode());
						redisUserData.setRoleName("普通用户");
					}
					redisUserData.setAccessToken(accessToken);
					BeanUtils.copyProperties(account, redisUserData);
					redisUserData.setAvatarUrl(account.getHeardImg());
					redisUserData.setOpenid(openId);
					redisUserData.setLoginTime(DateUtil.dateToStr(currentDate, DateUtil.DATETIME_DEFAULT_FORMAT));
					// 设置请求ip地址和访问终端类型
					try {
						redisUserData.setRequestIP(NetworkUtil.getIpAddress(request));
						redisUserData.setTerminalType(jwtHelper.generateAudience(device));
					} catch (Exception e) {
						logger.error("请求ip地址和访问终端类型:" + e.getMessage());
						e.printStackTrace();
					}
					boolean isWrite = redisUtil.set(accessToken, redisUserData, invalid);
					if (isWrite && this.addLoginLog(redisUserData, currentDate)) {
						commonResponse.setMsg("account login success");
						commonResponse.setCode(ResponseCode.SUCCESS);
						redisUserData.setRequestIP(null);
						redisUserData.setTerminalType(null);
						commonResponse.setData(redisUserData);
					} else {
						commonResponse.setMsg("account login failed, userData is write failed");
						commonResponse.setCode(ResponseCode.SERVER_ERROR);
					}
				}
			}
		} catch (Exception e) {
			logger.error("account login failed", e);
			commonResponse.setCode(ResponseCode.SERVER_ERROR);
			commonResponse.setMsg("service error");
		}
		return commonResponse;
	}

	@ApiOperation(value = "获取用户信息")
	@AccessRequired
	@RequestMapping(value = "/findUserInfo", method = RequestMethod.GET)
	public CommonResponse findUserInfo(@CurrentRedisUserData RedisUserData redisUserData, @RequestHeader String access_token) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			Account account = accountMapperExt.selectByPrimaryKey(redisUserData.getId());
			if(null != account.getExpireTime()){
				redisUserData.setExpireTime(DateUtil.dateToStr(account.getExpireTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			
			redisUserData.setVipCode(account.getVipCode());
			redisUserData.setRoleIdentity(account.getRoleType() + "");
			redisUserData.setRoleName((account.getRoleType() == null || account.getRoleType() == 0) ? "普通用户" : "店长");
			
			commonResponse.setData(redisUserData);
			commonResponse.setCode(ResponseCode.SUCCESS);
			commonResponse.setMsg("success");
			logger.info(commonResponse.getMsg());
		} catch (Exception e) {
			logger.error("findUserInfo failed", e);
			commonResponse.setCode(ResponseCode.SERVER_ERROR);
			commonResponse.setMsg("service error");
		}
		return commonResponse;
	}
	
	@ApiOperation(value = "更新用户信息")
	@AccessRequired
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public CommonResponse updateUserInfo(@RequestBody UserInfoVO userInfoVO,
			@CurrentRedisUserData RedisUserData redisUserData, @RequestHeader String access_token) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			// 移除accessToken
			accountService.updateAccount(userInfoVO, redisUserData.getOpenid());
			commonResponse.setCode(ResponseCode.SUCCESS);
			commonResponse.setMsg("success");
			logger.info(commonResponse.getMsg());
		} catch (Exception e) {
			logger.error("updateUserInfo failed", e);
			commonResponse.setCode(ResponseCode.SERVER_ERROR);
			commonResponse.setMsg("service error");
		}
		return commonResponse;
	}

	@ApiOperation(value = "使用激活码续费会员", notes = "使用激活码续费会员")
	@RequestMapping(value = "/useActiveCode", method = RequestMethod.POST)
	public CommonResponse useActiveCode(@RequestBody ActiveInfoVO activeInfoVO,
			@CurrentRedisUserData RedisUserData redisUserData, @RequestHeader String access_token) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			activationCodeService.useActiveCode(activeInfoVO, redisUserData.getId());
			commonResponse.setCode(ResponseCode.SUCCESS);
			commonResponse.setMsg("success");
			logger.info(commonResponse.getMsg());
		} catch (Exception e) {
			logger.error("useActiveCode failed", e);
			commonResponse.setCode(ResponseCode.SERVER_ERROR);
			commonResponse.setMsg("service error");
		}
		return commonResponse;
	}

	@ApiOperation(value = "獲取店鋪信息", notes = "獲取店鋪信息")
	@RequestMapping(value = "/getShopInfo", method = RequestMethod.POST)
	public CommonResponse getShopInfo(@CurrentRedisUserData RedisUserData redisUserData, @RequestHeader String access_token) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			commonResponse.setData(businessShopService.getShopInfo(redisUserData.getId()));
			commonResponse.setCode(ResponseCode.SUCCESS);
			commonResponse.setMsg("success");
			logger.info(commonResponse.getMsg());
		} catch (Exception e) {
			logger.error("useActiveCode failed", e);
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
	public CommonResponse logout(@CurrentRedisUserData RedisUserData redisUserData,
			@RequestHeader String access_token) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			// 移除accessToken
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
	 * 
	 * @param redisUserData
	 *            redisUserData
	 * @param loginTime
	 *            登录时间
	 * @return true表示成功，false表示失败
	 */
	private boolean addLoginLog(RedisUserData redisUserData, Date loginTime) {
		LoginLog loginLog = new LoginLog();
		loginLog.setLoginUser(redisUserData.getId());
		loginLog.setIp(redisUserData.getRequestIP());
		loginLog.setTerminalType(redisUserData.getTerminalType());
		loginLog.setLoginTime(loginTime);
		loginLog.setRemark("登录成功！");
		return loginLogService.add(loginLog) > 0 ? true : false;
	}

}

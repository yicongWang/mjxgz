package com.zhiyi.mjxgz.service.common.log;


import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.model.OperationLog;
import com.zhiyi.mjxgz.service.OperationLogService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.RedisUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 操作日志切面
 * Created by DW on 2016/10/14.
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {
    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 当前用户操作对象
     */
    private RedisUserData redisUserData;

    /**
     * 操作日志对象
     */
    private OperationLog operationLog;

    ThreadLocal<Long> startTime = new ThreadLocal<>();

//    @Pointcut("execution(public * com.zhiyi.mjxgz.controller..*.*(..))")
    @Pointcut("@annotation(com.zhiyi.mjxgz.service.common.log.OperationnLogAnnotation)")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));


        //保存操作日志
        this.assemblyOperationLogData(joinPoint,request);
        operationLogService.add(operationLog);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()) +"ms");
    }

    /**
     * 组装操作日志对象数据
     * @param joinPoint
     * @param request
     * @throws Exception
     */
    public void assemblyOperationLogData(JoinPoint joinPoint,HttpServletRequest request) throws Exception {
        operationLog = new OperationLog();
        operationLog.setOperationTime(DateUtil.now());
        //操作日志对象是否已保存用户信息 （默认否）
        Boolean flag = false;
        //获取当前用户对象
        String accessToken = request.getHeader("access_token");
        if(!StringUtils.isEmpty(accessToken)) {
            redisUserData = (RedisUserData) redisUtil.get(accessToken);
            flag = this.setOperationLogByRedisUser();
        }

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        if(!flag) {
            for (Object obj : arguments) {
                if (obj instanceof RedisUserData) {
                    redisUserData = (RedisUserData) obj;
                    flag = this.setOperationLogByRedisUser();
                    break;
                }
            }
        }
        if(!flag){
            throw new DataNotExistsException("当用户对象（RedisUserData）为空，保存操作记录日志失败！");
        }
        operationLog.setOperationIp(redisUserData.getRequestIP());
        operationLog.setTerminalType(redisUserData.getTerminalType());
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    operationLog.setOperationDesc(method.getAnnotation(OperationnLogAnnotation.class).description());
                    operationLog.setModuleName(method.getAnnotation(OperationnLogAnnotation.class).moduleName());
                    operationLog.setOperationType(method.getAnnotation(OperationnLogAnnotation.class).operationLogType().getOperationName());
//                    operationLog.setTerminalType(method.getAnnotation(OperationnLogAnnotation.class).terminalType());
                    break;
                }
            }
        }
    }

    /**
     * 根据RedisUserData 设置操作日志对象属性
     */
    private boolean setOperationLogByRedisUser(){
        boolean flag = false;
        if(redisUserData!=null) {
            operationLog.setOperationUser(redisUserData.getAccount());
            flag = true;
        }
        return flag;
    }

}

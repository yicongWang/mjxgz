package com.zhiyi.mjxgz.common.response;

/**
 * Created by szl on 2016/2/22.
 */
public class ResponseCode {
    /**
     * 表示返回成功
     */
    public static final int SUCCESS = 200;


   /**
     * 该用户无权限访问API
     */
    public static final int ACCESS_FAILED=403;

   
    /**
     * 令牌失效,请重新登录.
     */
    public static final int ACCESS_INVALID=402;

    /**
     * 表示未通过鉴权,请求令牌有误.
     */
    public static final int AUTHORIZE_FAILED = 401;

    /**
     * 发送命令道设备失败.
     */
    public static final int SEND_COMMAND_ERROR =  412;

    /**
     * 导入文件类型错误
     */
    public static final int IMPORT_FILE_TYPE_ERROR = 419;

    /**
     * 表示http请求错误
     */
    public static final int HTTP_METHOD_ERROR=415;
    
    /**
    * 表示资源已存在的错误
    */
   public static final int SOURCE_EXIST_ERROR = 411;
    
    /**
     * 表示资源不存在，被删除或失效
     */
    public static final int SOURCE_NOT_EXIST_ERROR=410;
    /**
     * 表示API调用时的参数有误
     */
    public static final int PARAMETER_ERROR = 422;

    /**
     * 表示其他服务端错误
     */
    public static final int SERVER_ERROR = 500;


}

package com.zhiyi.mjxgz.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.zhiyi.mjxgz.common.exception.DataAlreadyExistsException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.common.exception.ParametersException;
import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionController {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    /**
     * 2017/2/16
     * 参数传递错误异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ParametersException.class)
    @ResponseBody
    public CommonResponse handleParametersException(HttpServletRequest request,ParametersException e){
        e.printStackTrace();
        handleLog(request, e);
        CommonResponse commonResponse = new CommonResponse(ResponseCode.PARAMETER_ERROR, e.getMessage(), e.getErrorMap());
        //commonResponse.setData(e.getData());
        return commonResponse;
    }


    /**
     * 2017/2/10
     * 处理资源已存在的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(DataAlreadyExistsException.class)
    @ResponseBody
    public CommonResponse handleDataAlreadyExistsException(HttpServletRequest request,Exception e){
        e.printStackTrace();
        handleLog(request, e);
        return new CommonResponse(ResponseCode.SOURCE_EXIST_ERROR, e.getMessage());
    }

    /**
     * 2017/2/10
     * 处理资源不存在
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(DataNotExistsException.class)
    @ResponseBody
    public CommonResponse handleDataNotExistsException(HttpServletRequest request,Exception e){
        e.printStackTrace();
        handleLog(request, e);
        return new CommonResponse(ResponseCode.SOURCE_NOT_EXIST_ERROR, e.getMessage());
    }



    /**
     * 2017/2/10
     * 处理必传参数缺失异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResponse handleMissingServletRequestParameterException(HttpServletRequest request, HttpServletResponse response,
                                             MissingServletRequestParameterException ex) {
        handleLog(request, ex);
        CommonResponse cr=new CommonResponse();
        cr.setCode(ResponseCode.PARAMETER_ERROR);
        cr.setMsg("parameter error:"+ex.getMessage());
        return cr;
    }

    /**
     * 2017/2/21
     * 处理方-法参数类型不匹配
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public CommonResponse handleMethodArgumentTypeMismatchException(HttpServletRequest request, HttpServletResponse response,
                                                                    MethodArgumentTypeMismatchException ex) {
        handleLog(request, ex);
        CommonResponse cr=new CommonResponse();
        cr.setCode(ResponseCode.HTTP_METHOD_ERROR);
        cr.setMsg("parameter error:"+ex.getMessage()+" 请检查方法参数类型是否匹配或者请求方式是否正确。");
        return cr;
    }



    /**
     * 2017/2/20
     * 捕获异常 HttpMessageNotReadableException
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public CommonResponse handleHttpMessageNotReadableException(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        String method = request.getMethod();
        handleLog(request, ex);
        CommonResponse cr=new CommonResponse();
        String message = ex.getMessage();

        cr.setCode(ResponseCode.PARAMETER_ERROR);
        cr.setMsg("参数传递错误");
        return cr;
    }

    /**
     * 捕获和处理 MethodArgumentNotValidException 方法参数无效的异常信息
     *
     * @param request 请求
     * @param e 异常
     * @return 返回异常信息状态码和异常信息
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleBindException(HttpServletRequest request, MethodArgumentNotValidException e) {
        Map<String, String> data = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new CommonResponse(ResponseCode.PARAMETER_ERROR, "参数校验未通过", data);
    }

    /**
     * 2017/2/10
     * 捕获异常 Exception
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse handleAllException(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        String method = request.getMethod();
        handleLog(request, ex);
        CommonResponse cr=new CommonResponse();
        String message = ex.getMessage();
        String nowMessage = "Request method '"+method+"' not supported";
        if(message.equals(nowMessage)){
            cr.setCode(ResponseCode.HTTP_METHOD_ERROR);
            cr.setMsg("http method error,please choose correct method!");
        }else {
            cr.setCode(ResponseCode.SERVER_ERROR);
            cr.setMsg("server inernal error,please contact the administrator!");
        }
       return cr;
    }


    /**
     * 处理日志
     * @param request
     * @param ex
     */
    private void handleLog(HttpServletRequest request, Exception ex) {
        Map parameter = request.getParameterMap();
        StringBuffer logBuffer = new StringBuffer();
        logger.error(ex.getMessage());
        if (request != null) {
            logBuffer.append("  request method=" + request.getMethod());
            logBuffer.append("  url=" + request.getRequestURL());
        }
        if (ex != null) {
            logBuffer.append("  exception:" + ex);
        }
        logger.error(logBuffer.toString());
    }
}

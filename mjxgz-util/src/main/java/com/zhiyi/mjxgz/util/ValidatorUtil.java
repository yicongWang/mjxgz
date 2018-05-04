package com.zhiyi.mjxgz.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiyi.mjxgz.common.exception.ParametersException;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author sxt 2016/07/13
 */
public class ValidatorUtil {
    private static Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);


    /**
     * 验证必填
     * @param value 验证的值
     * @param parameterName 参数名称
     * @param required 是否必填；true：必填；false：不必填
     * @param errorMap 存放错误消息
     * @return true: 验证通过；false：未通过
     */
    public static boolean validParameterRequired(Object value,String parameterName,boolean required,Map<String,String> errorMap){
        if(errorMap == null){
            errorMap = new HashMap<>();
        }
        if(required){
            if(value instanceof Date){
                if(value == null){
                    errorMap.put(parameterName,"不能为空");
                    return false;
                }
            }else{
                String v = String.valueOf(value);
                if (value == null || v.length() == 0 || "".equals(v.trim())) {
                    errorMap.put(parameterName,"不能为空");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证是一个日期
     * @param date 验证的值
     * @param parameterName 参数名称
     * @param required 是否必填；true：必填；false：不必填
     * @param errorMap 存放错误消息
     * @return true: 验证通过；false：未通过
     */
    public static boolean validParameterDateAlert(Object date,String parameterName,boolean required,Map<String,String> errorMap){
        if(errorMap == null){
            errorMap = new HashMap<>();
        }
        if(!validParameterRequired(date, parameterName, required, errorMap)){
            return false;
        }
        if(!(date instanceof Date) && null != date){
            String v = String.valueOf(date);
            //说明已填写：验证是否为日期格式
            if(null != v && v.length()>0){
                if(!isDate(v)){
                    errorMap.put(parameterName,"不是一个日期格式；格式：(例如：2017-02-27)");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 验证是否在指定日期之前
     * @param targetDate 目标日期
     * @param beforeDate 之前的日期
     * @param parameterName 参数名称
     * @param required 是否必填；true：必填；false：不必填
     * @param errorMap 存放错误消息
     * @return true: 验证通过；false：未通过
     */
    public static boolean validParameterDateBeforeAlert(Object targetDate,Object beforeDate,String parameterName,boolean required,Map<String,String> errorMap){
        if(errorMap == null){
            errorMap = new HashMap<>();
        }
        if(!validParameterDateAlert(beforeDate,parameterName,required,errorMap)){
            return false;
        }

        if(!(targetDate instanceof Date)){
            String before = String.valueOf(beforeDate);
            //说明已填写:验证是否在指定日期之前
            if(null == before || before.length()<=0 || !isDate(beforeDate)){
                return true;
            }
        }

        if(!isDateBefore(targetDate, beforeDate)){
            errorMap.put(parameterName,"[ "+dateFormat(beforeDate)+" ] 不是 [ "+dateFormat(targetDate)+" ]之前的一个日期。");
            return false;
        }

        return true;
    }

    /**
     * 验证是否在指定日期之后
     * @param targetDate 目标日期
     * @param afterDate 之后的日期
     * @param parameterName 参数名称
     * @param required 是否必填；true：必填；false：不必填
     * @param errorMap 存放错误消息
     * @return true: 验证通过；false：未通过
     */
    public static boolean validParameterDateAfterAlert(Object targetDate,Object afterDate,String parameterName,boolean required,Map<String,String> errorMap){
        if(errorMap == null){
            errorMap = new HashMap<>();
        }
        if(!validParameterDateAlert(afterDate,parameterName,required,errorMap)){
            return false;
        }
        if(!(afterDate instanceof Date)){
            String after = String.valueOf(afterDate);
            //说明已填写:验证是否在指定日期之后
            if(null == after || after.length()<=0 || !isDate(afterDate)){
                return true;
            }
        }
        if(!isDateAfter(targetDate, afterDate)){
            errorMap.put(parameterName,"[ "+dateFormat(afterDate)+" ]不是[ "+dateFormat(targetDate)+" ]之后的一个日期。");
            return false;
        }
        return true;
    }

    /**
     * 验证日期是否在指定的开始日期-结束日期之间（不包含开始日期、结束日期）
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param date 比较的日期
     * @param parameterName 参数名称
     * @param required 是否必填；true：必填；false：不必填
     * @param errorMap 存放错误消息
     * @return
     */
    public static boolean validParameterBetweenDateAlert(Object beginDate,Object endDate,Object date,String parameterName,boolean required,Map<String,String> errorMap){
        if(errorMap == null){
            errorMap = new HashMap<>();
        }
        if(!validParameterDateAlert(date,parameterName,required,errorMap)){
            return false;
        }

        if(!(date instanceof Date)){
            String d = String.valueOf(date);
            if(null == d || d.length()<=0 || !isDate(date)){
                return true;
            }
        }

        if(!isDateBetween(beginDate, endDate,date)){
            errorMap.put(parameterName,"[ "+dateFormat(date)+" ]必须在[ "+dateFormat(beginDate)+" ]-[ "+dateFormat(endDate)+" ]之间。");
            return false;
        }
        return true;
    }

    /**
     * 日期格式化 yyyy-MM-dd
     * @param date 日期
     * @return
     */
    private static String dateFormat(Object date){
        if(date instanceof Date){
            return DateFormatUtils.format((Date) date,"yyyy-MM-dd");
        }
        return String.valueOf(date);
    }


    /**
     * 校验参数方法.
     *
     * @param target    需要校验的目标字符串
     * @param fieldName  错误信息绑定的属性名
     * @param isrequired 是否必填
     * @param minLength  最小长度
     * @param maxLength  最大长度
     * @param dateType   data type
     *                   1.字符串为数字,
     *                   2. 字符串为“0”---”9”, ”a”---”z”,”A”---”Z” ；
     *                   3.字符串为values中的值
     *                   4.任意字符串
     *                   5.IP
     *                   6.Email地址
     *                   7.电话号码（移动电话:18000000000、固定电话：023-6500000、400电话）
     *                   8：电话号码不符合要求：移动号码
     *                   9：电话号码不符合要求：座机(固定电话)
     *                   10：电话号码不符合要求：4000000000
     *                   11：日期格式：2017-02-27
     *                   12: 数字或者小数：1 或者 -1 或者 0.1 或者 -0.1
     * @param errorMap   错误信息Map
     * @return true：success；false: failed
     * @version
     */
    public static boolean validParameterAlert2(Object target, String fieldName, boolean isrequired,
                                          int minLength, int maxLength, int dateType, Map<String,String> errorMap) {
        return (validParameterAlert(target, fieldName, isrequired, minLength, maxLength, dateType, null, errorMap)==0);
    }


    /**
     * 校验参数方法.
     *
     * @param target    需要校验的目标字符串
     * @param fieldName  错误信息绑定的属性名
     * @param isrequired 是否必填
     * @param minLength  最小长度
     * @param maxLength  最大长度
     * @param dataType   data type
     *                   1.字符串为数字,
     *                   2. 字符串为“0”---”9”, ”a”---”z”,”A”---”Z” ；
     *                   3.字符串为values中的值
     *                   4.任意字符串
     *                   5.IP
     *                   6.Email地址
     *                   7.电话号码（移动电话:18000000000、固定电话：023-6500000、400电话）
     *                   8：电话号码不符合要求：移动号码
     *                   9：电话号码不符合要求：座机(固定电话)
     *                   10：电话号码不符合要求：4000000000
     *                   11：日期格式：2017-02-27
     *                   12: 数字或者小数：1 或者 -1 或者 0.1 或者 -0.1
     * @param errorMap   错误信息Map
     * @return int 0:success, other failure
     * @version
     */
    public static int validParameterAlert(Object target, String fieldName, boolean isrequired,
                                   int minLength, int maxLength, int dataType, Map<String,String> errorMap) {
        return validParameterAlert(target, fieldName, isrequired, minLength, maxLength, dataType, null, errorMap);
    }

    /**
     * 校验参数方法.
     *
     * @param target    需要校验的目标字符串
     * @param fieldName  错误信息绑定的属性名
     * @param required 是否必填
     * @param minLength  最小长度
     * @param maxLength  最大长度
     * @param dataType   data type
     *                   1.字符串为数字,
     *                   2. 字符串为“0”---”9”, ”a”---”z”,”A”---”Z” ；
     *                   3.字符串为values中的值
     *                   4.任意字符串
     *                   5.IP
     *                   6.Email地址
     *                   7.电话号码（移动电话:18000000000、固定电话：023-6500000、400电话）
     *                   8：电话号码不符合要求：移动号码
     *                   9：电话号码不符合要求：座机(固定电话)
     *                   10：电话号码不符合要求：4000000000
     *                   11：日期格式：2017-02-27
     *                   12: 数字或者小数：1 或者 -1 或者 0.1 或者 -0.1
     * @param errorMap   错误信息Map
     * @return int 0:success, other failure
     * @version
     */
    public static int validParameterAlert(Object target, String fieldName, boolean required,
                                          int minLength, int maxLength, int dataType, String[] values, Map<String,String> errorMap) {
        int resultCode = 0;
        try {
            resultCode = ValidatorUtil.validParameter(target, required, minLength, maxLength, dataType, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /**
         * 1：必填但没填写
         * 2:不符合要求的长度范围，限制最小长度
         * 3:包含单双引号
         * 4:非数字
         * 5 不符合handletype为2所要求的值
         * 6不符合handletype为3所要求的值
         * 7：不符合要求的ip地址
         * 8 大于要求的最大长度，不限制最小长度
         * 9 不符合要求的email地址
         */
        switch (resultCode) {
            case 0:
                return resultCode;
            case 1:
                errorMap.put(fieldName,  "不能为空");
                break;
            case 2:
                if (minLength != maxLength) {
                    errorMap.put(fieldName, "不能少于" + minLength
                            + "且不能超过" + maxLength + "个字符");
                } else {
                    errorMap.put(fieldName, "应该为" + minLength + "个字符");
                }
                break;
            case 3:
                errorMap.put(fieldName, "不允许带有单引号或双引号");
                break;
            case 4:
                errorMap.put(fieldName, "必须是数字");
                break;
            case 5:
                errorMap.put(fieldName, "不能超过" + maxLength + "个字符，并且只能是数字和字母");
                break;
            case 6:// no this
                if (null != values && values.length > 0) {
                    String str = "";
                    for (String v : values) {
                        str += str.equals("") ? v : ", " + v;
                    }
                    errorMap.put(fieldName, "只能是 [" + str + "] 范围的值");
                }
                break;
            case 7:
                errorMap.put(fieldName, "请填写"  + "正确的IP地址");
                break;
            case 8:
                errorMap.put(fieldName, "不能少于" + maxLength + "个字符");
                break;
            case 9:
                errorMap.put(fieldName, "请填写正确的电子邮箱地址");
                break;
            case 10:
                errorMap.put(fieldName, "请填写正确的电话号码；格式：移动号码(例如：18000000000) 或者 座机(固定电话)(例如：023-6666666) 或者 400 电话号码(例如：4000000000)");
                break;
            case 11:
                errorMap.put(fieldName, "请填写正确的电话号码；格式：移动号码(例如：18000000000)");
                break;
            case 12:
                errorMap.put(fieldName, "请填写正确的电话号码；格式：座机(固定电话)(例如：023-6666666)");
                break;
            case 13:
                errorMap.put(fieldName, "请填写正确的电话号码；格式：400 电话号码(例如：4000000000)");
                break;
            case 14:
                errorMap.put(fieldName, "请填写正确的日期；格式：(例如：2017-02-01)");
                break;
            case 15:
                errorMap.put(fieldName, "请填写正确的数值；整数或者小数 格式：（1 或者 -1 或者 0.1 或者 -0.1）");
                break;
            case 16:
                errorMap.put(fieldName, "格式不正确；必须同时包含大小写字母与数字");
                break;
            default:// hand type is not correct
                resultCode = 99;
                break;
        }
        return resultCode;
    }

    /**
     * 预处理参数，默认值、长度等
     *
     * @param target   参数
     * @param required  必填
     * @param minLength 是已经填写了值的情况下的参数最小参数，即最少填写1
     * @param maxLength 参数最大长度
     * @param dataType  数据类型
     *                   1.字符串为数字,
     *                   2. 字符串为“0”---”9”, ”a”---”z”,”A”---”Z” ；
     *                   3.字符串为values中的值
     *                   4.任意字符串
     *                   5.IP
     *                   6.Email地址
     *                   7.电话号码（移动电话:18000000000、固定电话：023-6500000、400电话）
     *                   8：电话号码不符合要求：移动号码
     *                   9：电话号码不符合要求：座机(固定电话)
     *                   10：电话号码不符合要求：4000000000
     *                   11：日期格式：2017-02-27
     *                   12: 数字或者小数：1 或者 -1 或者 0.1 或者 -0.1
     * @param values    参数列表值
     * @return int      0：ok
     *                  1：必填但没填写
     *                  2：不符合要求的长度范围，限制最小长度
     *                  3：包含单双引号
     *                  4：非数字
     *                  5：不符合handletype为2所要求的值
     *                  6：不符合handletype为3所要求的值
     *                  7：不符合要求的ip地址
     *                  8：大于要求的最大长度，不限制最小长度
     *                  9：不符合要求的email地址
     *                  10: 电话号码不符合要求：移动号码 或者 座机 或者 400 电话号码
     *                  11：电话号码不符合要求：移动号码
     *                  12：电话号码不符合要求：座机(固定电话)
     *                  13：电话号码不符合要求：4000000000
     *                  14: 验证是日期格式:2017-02-27
     *                  15: 数字或者小数：1 或者 -1 或者 0.1 或者 -0.1
     * @throws Exception the exception
     */
    public static int validParameter(Object target, boolean required, int minLength, int maxLength, int dataType, String[] values) throws Exception {
        int resultCode = 0;
        String targetStr = "";
        if(null != target){
            targetStr = String.valueOf(target);
        }
        // 判断是否必填
        if (required && (null == target || targetStr.length() == 0 || "".equals(targetStr.trim()))) {
            return 1;
        }else if (null == target || targetStr.length() == 0 || "".equals(targetStr.trim())) {
            return 0;
        }

        //不是Date(日期)类型
        if(!(target instanceof Date)){
            int len = targetStr.length(); //默认 0
            if (minLength > 0 && (len < minLength || len > maxLength)) {
                return 2;
            }else if (len > maxLength) {
                return 8;
            }
            // check whether contains ' and " character
            if (targetStr.indexOf('\'') >= 0 || targetStr.indexOf('\"') >= 0) {
                return 3;
            }
        }
        switch (dataType) { // 1.字符串为数字 ； 2. 字符串为“0”---”9”, ”a”---”z”,
        // ”A”---”Z” ；3 .字符串为values中的值 4.任意字符串
        case 1:
            if (!isNumeric(targetStr)) {
                resultCode = 4;
            }
            break;
        case 2:
            if (!isNumOrChar(targetStr)) {
                resultCode = 5;
            }
            break;
        case 3:
            resultCode = 6;
            if (values == null) {
                break;
            }
            for (int i = 0; i < values.length; i++) {
                String tmp = values[i];
                if (targetStr.toLowerCase().equals(tmp.toLowerCase())) { // 都转化为小写进行比较
                    resultCode = 0;
                    break;
                }
            }
            break;
        case 4:
            break;
        case 5:
            if (!isIp(targetStr)) {
                resultCode = 7;
            }
            break;
        case 6:
            if (!isEmail(targetStr)) {
                resultCode = 9;
            }
            break;
        case 7:
            if(!isPhoneNumber(targetStr)){
                resultCode = 10;
            }
            break;
        case 8: //验证移动电话号码
            if(!isMobilePhone(targetStr)){
                resultCode = 11;
            }
            break;
        case 9: //验证固定电话号码
            if(!isTelephone(targetStr)){
                resultCode = 12;
            }
            break;
        case 10: //验证400电话
            if(!is400Phone(targetStr)){
                resultCode = 13;
            }
            break;
        case 11: //验证是日期格式:2017-02-27
            if(!isDate(target)){
                resultCode = 14;
            }
            break;
        case 12: //验证是数字或者小数（包含负数）
            if(!isNumberOrDecimal(targetStr)){
                resultCode = 15;
            }
            break;
        case 13: //验证由数字和大小字母组成，并且要同时含有数字和大小字母字母
            if(!isNumerAndChar(targetStr)){
                resultCode = 16;
            }
            break;
        default:
            resultCode = 99;
            break;
        }
        return resultCode;
    }



    /**
     * 正则表达式验证
     *
     * @param deststr 被检查字符串
     * @param regex   正则表达式
     * @return boolean ，true_符合正则表达式
     */
    public static boolean isRegex(String deststr, String regex) {
        if (deststr == null || deststr.trim().length() == 0) {
            return false;
        }
        return deststr.matches(regex);
    }

    /**
     * 数字验证
     *
     * @param deststr 被检查字符串
     * @return boolean ，true_数字
     */
    public static boolean isNumeric(String deststr) {
        return deststr.matches("\\d+");
    }

    /**
     * 判断是数字或者小数（包含负数）
     * @param str 检查的字符串
     * @return
     */
    public static boolean isNumberOrDecimal(String str){
        return str.matches("(-?\\d+)|(-?\\d+\\.\\d+)");
    }
    /**
     * 邮箱验证
     *
     * @param deststr 被检查字符串
     * @return boolean ，true_邮箱
     */
    public static boolean isEmail(String deststr) {
        return deststr.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

    /**
     * 字符串是否由0--9,a--z,A--Z组成
     *
     * @param deststr 被检查字符串
     * @return boolean ，true_字符串由0--9,a--z,A--Z组成
     */
    public static boolean isNumOrChar(String deststr) {
        return deststr.matches("\\w+");
    }


    /**
     * 得到字符的长度，统一用UTF-8，汉字算三
     *
     * @param tmp string
     * @return int string length -1_表示返回错误，其他为字段长度
     */
    public static int getCharLength(String tmp) {
        int len = 1;
        try {
            len = tmp.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            len = -1;
        }
        return len;
    }


    /**
     * IP检验
     *
     * @param ip ip address
     * @return boolean true:yes false:no
     */
    public static boolean isIp(String ip) {
        return ip.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|23[0-2])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
    }

    /**
     * 判断字符串是否为合法手机号 11位 13 14 15 18开头
     *
     * @param str the str
     * @return boolean boolean
     */
    public static boolean isMobile(String str){
        if(isEmpty(str))
            return false;
        return str.matches("^(13|14|15|18|17)\\d{9}$");
    }

    /**
     * 判断是否为数字
     *
     * @param str the str
     * @return boolean boolean
     */
    public static boolean isNumber(String str) {
        try{
            Integer.parseInt(str);
            return true;
        }catch(Exception ex){
            return false;
        }
    }


    
    /**
     * 判断字符串是否为非空(包含null与"")
     *
     * @param str the str
     * @return boolean boolean
     */
    public static boolean isNotEmpty(String str){
        if(str == null || "".equals(str))
            return false;
        return true;
    }

    /**
     * 判断字符串是否为非空(包含null与"","    ")
     *
     * @param str the str
     * @return boolean boolean
     */
    public static boolean isNotEmptyIgnoreBlank(String str){
        if(str == null || "".equals(str) || "".equals(str.trim()))
            return false;
        return true;
    }

    /**
     * 判断字符串是否为空(包含null与"")
     *
     * @param str the str
     * @return boolean boolean
     */
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str))
            return true;
        return false;
    }

    /**
     * 判断字符串是否为空(包含null与"","    ")
     *
     * @param str the str
     * @return boolean boolean
     */
    public static boolean isEmptyIgnoreBlank(String str){
        if(str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }

    /**
     * 判断是否为浮点数或者整数
     *
     * @param str the str
     * @return true Or false
     */
    public static boolean isNumerOrFloat(String str){
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    
    /**
     * 由数字和大小字母组成，并且要同时含有数字和大小字母字母
     * @param str
     * @return
     */
    public static boolean isNumerAndChar(String str){
        Pattern pattern = Pattern.compile("^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,20}$");
        Matcher isSuccess = pattern.matcher(str);
        if( !isSuccess.matches() ){
            return false;
        }
        return true;
    }
    
    

    /**
     * 验证字符串是否是时间格式.
     *
     * @param dateStr 时间字符串
     * @param format  时间格式 默认为：yyyy-MM-dd HH:mm:ss
     * @return 返回值 true:是时间格式  false:不是时间格式
     */
    public static boolean isValidDate(String dateStr, String format) {
        if(dateStr == null || dateStr.trim().length() == 0) {
            return false;
        }
        if(format == null || format.trim().length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证电话号码
     * 匹配格式：17,13,15(除154),18 开头的移动号码
     * 匹配格式：座机电话如：023-600000000
     * 匹配格式：400电话；如：4000000000
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber){
        if(null == phoneNumber){
            return false;
        }
        String regExp ="^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$|(0[0-9]{2,3}-[0-9]{7,8})$|(4\\d{9})$";
        Pattern p = Pattern.compile(regExp);
        return p.matcher(phoneNumber).find();
    }


    /**
     * 验证移动电话
     * 匹配格式：17,13,15(除154),18 开头的移动号码
     * @param mobilePhone 电话号码
     * @return
     */
    public static boolean isMobilePhone(String mobilePhone){
        if(null == mobilePhone){
            return false;
        }
        return mobilePhone.matches("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    }

    /**
     * 验证固定电话号码
     * 匹配格式：座机电话如：023-600000000
     * @param telephone 电话号码
     * @return
     */
    public static boolean isTelephone(String telephone){
        if(null == telephone){
            return false;
        }
        return telephone.matches("^0[0-9]{2,3}-[0-9]{7,8}$");
    }


    /**
     * 400电话号码
     * 匹配格式：400电话；如：4000000000
     * @param phoneNumber
     * @return
     */
    public static boolean is400Phone(String phoneNumber){
        if(null == phoneNumber){
            return false;
        }
        String regExp ="^4\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        return p.matcher(phoneNumber).find();
    }


    /**
     * 验证是否为日期格式
     * 格式：如：2017-02-27
     * 年：1000-9999
     * 月：01-12
     * 日：01-31
     * @param date 日期的字符串
     * @return true：是；false：否
     */
    public static boolean isDate(Object date){
        if(date == null){
            return false;
        }
        if(!(date instanceof Date)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);   //这个的功能是不把2017-02-29 转换为2017-03-01(严格模式-平年的2月没有29日)
            try {
                Date parse = format.parse(String.valueOf(date));
                return true;
            } catch (ParseException e) {
//            e.printStackTrace();
                return false;
            }
        }
        return true;
//        return date.matches("^([^0,\\D])\\d{3}-((0[1-9]{1})|(1[0-2]{1}))-((0[1-9]{1})|([1-2]{1}[0-9]{1})|3[0-1]{1})$");
    }


    /**
     * 判断在某个日期之后
     * @param targetDate 目标日期 2017-02-27
     * @param afterDate 之后的日期 2017-02-28
     * @return true：是；false：否
     */
    public static boolean isDateAfter(Object targetDate,Object afterDate){
        if(isDate(targetDate) && isDate(afterDate)){
            Date target = null;
            Date after = null;
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            try {
                if(targetDate instanceof Date){
                    target = simpleDateFormat.parse(simpleDateFormat.format((Date) targetDate));
                }else{
                    target = simpleDateFormat.parse(String.valueOf(targetDate));
                }
                if(afterDate instanceof Date){
                    after = simpleDateFormat.parse(simpleDateFormat.format((Date) afterDate));
                }else{
                    after = simpleDateFormat.parse(String.valueOf(afterDate));
                }
                //after 在某个日期之后
                return after.after(target);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 验证在某日期之后
     * @param targetDate 目标日期 2017-02-21
     * @param beforeDate 之前的日期 2017-02-11
     * @return true：是；false：否
     */
    public static boolean isDateBefore(Object targetDate,Object beforeDate){
        if(isDate(targetDate) && isDate(beforeDate)){
            Date target = null;
            Date before = null;
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
            try {
                if(targetDate instanceof Date){
                    target = (Date) targetDate;
                }else{
                    target = simpleDateFormat.parse(String.valueOf(targetDate));
                }
                if(beforeDate instanceof Date){
                    before = (Date) beforeDate;
                }else{
                    before = simpleDateFormat.parse(String.valueOf(beforeDate));
                }
                //before 在 目标日期 之前
                return before.before(target);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 判断在指定日期之间(不包含开始、结束日期)
     * @param beginDate 开始日期 2017-01-01
     * @param endDate 结束日期 2017-03-10
     * @param date 比较日期 2017-02-10
     * @return
     */
    public static boolean isDateBetween(Object beginDate,Object endDate,Object date){
        return (isDateAfter(beginDate,date) && isDateBefore(endDate,date));
    }


    //禁止实例化
    private ValidatorUtil(){}

    /**
     * 验证参数
     * @param errorMap
     */
    public static void validateParameter(Map<String,String> errorMap){
        if(!errorMap.isEmpty()){
            throw new ParametersException(errorMap);
        }
    }

}

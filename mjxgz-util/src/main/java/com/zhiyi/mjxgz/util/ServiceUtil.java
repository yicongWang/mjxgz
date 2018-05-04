package com.zhiyi.mjxgz.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 在处理业务是对参数进行处理的类
 * Created by szl on 2016/3/1.
 */
public class ServiceUtil {

    /**
     * 把以‘,’号分隔的区域转换为区域数组
     * 如把"重庆#北京#上海"转换为一个列表，包含三个元素，重庆、北京、上海
     *
     * @param areas 形如"重庆#北京#上海"的字符串
     * @return 链表
     */
    public static List<String> getAreas(String areas) {
        if (isEmpty(areas)) {
            return null;
        }
        List<String> areasList = null;
        String[] areaArray = areas.split(",");
        if (areaArray.length > 0) {
            areasList = new ArrayList<String>();
            for (String s : areaArray) {
                areasList.add(s);
            }
        }
        return areasList;
    }

    /**
     * 把以‘,’号分隔的区域转换为数据库Province In (?) 中问号中应有的形式
     *
     * @param areas
     * @return
     */
    public static String getAreasAsString(String areas) {
        if (isEmpty(areas)) {
            return null;
        }

        StringBuilder builder = new StringBuilder(256);
        String[] areaArray = areas.split(",");
        boolean bFirst = true;
        for (String s : areaArray) {
            if (!bFirst) {
                builder.append(",");
            }
            builder.append("\'");
            builder.append(s);
            builder.append("\'");
            bFirst = false;
        }
        return builder.toString();
    }

    public static boolean isEmpty(String str){
        if (str ==null ||"".equals(str))
            return true;
        return false;
    }

    /**
     * 生成随机字符串
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@$%^*()";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static Date getCurrentDay(){
        Calendar day=Calendar.getInstance();
        day.set(Calendar.HOUR_OF_DAY,0);
        day.set(Calendar.MINUTE,0);
        day.set(Calendar.SECOND,0);
        day.set(Calendar.MILLISECOND,0);
        return day.getTime();
    }


    public static String CreateAccessToken(String userId,String timestamp){
        String[] paramArr = new String[]{userId,timestamp};
        Arrays.sort(paramArr);
        String content = paramArr[0].concat(paramArr[1]);
        String access_token = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            access_token = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return access_token;
    }

    private static String byteToStr(byte[] byteArray){
        String strDigest="";
        for(int i=0;i<byteArray.length;i++){
            strDigest +=byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte){
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}

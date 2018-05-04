package com.zhiyi.mjxgz.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * Created by Huangchen on 2016/7/13.
 */
public class RandCodeUtil {


    //随机密码字符数组
    private static char[] passwordSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    //验证码字符数组
    private static char[] smsCodeSequence = "0123456789".toCharArray();

    private static Random random = new Random();

    // 随机生成一个字符
    private static String getRandomChar(char[] charSequence) {
        int index = random.nextInt(charSequence.length);
        return String.valueOf(charSequence[index]);
    }

    // 生成短信验证码
    public static String getRandomSmsCode(int codeNum){
        StringBuilder sRand = new StringBuilder(codeNum);
        for (int i = 0; i < codeNum; i++) {
            // 取得一个随机字符
            String tmp = getRandomChar(smsCodeSequence);
            sRand.append(tmp);
        }
        return sRand.toString();
    }

    // 生成随机密码
    public static String getRandomPwd(int pwdNum){
        StringBuilder sRand = new StringBuilder(pwdNum);
        for (int i = 0; i < pwdNum; i++) {
            // 取得一个随机字符
            String tmp = getRandomChar(passwordSequence);
            sRand.append(tmp);
        }
        return sRand.toString();
    }


    /**
     * 生成10位数的随机密码种子
     *
     * @return 返回随机的密码种子
     */
    public static String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

}


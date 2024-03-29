package com.cxhello.admin.utils;

import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author cxhello
 * @create 2019/11/10 16:54
 */
public class MD5Utils {
    /**
     * 对字符串进行Md5加密
     *
     * @param input 原文
     * @return md5后的密文
     */
    public static String md5(String input) {
        byte[] code = null;
        try {
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
    }

    /**
     * 对字符串进行Md5加密
     *
     * @param input 原文
     * @param salt 随机数
     * @return string
     */
    public static String generatePasswordMD5(String input, String salt) {
        if (!StringUtils.hasLength(salt)) {
            salt = "";
        }
        return md5(salt + md5(input));
    }

    public static void main(String[] args) {
        System.out.println(md5("111111"));;
    }

}

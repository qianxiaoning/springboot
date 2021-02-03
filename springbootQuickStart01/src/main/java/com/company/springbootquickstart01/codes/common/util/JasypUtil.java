package com.company.springbootquickstart01.codes.common.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName:   JasypUtil
 * @Description: Jasyp加解密工具类
 * @Author:      Rambo
 * @CreateDate:  2020/7/13 10:12
 * @UpdateUser:  Rambo
 * @UpdateDate:  2020/7/13 10:12
 * @Version:     1.0.0
 */
@Component
public class JasypUtil {

    private static final String PBEWITHHMACSHA512ANDAES_256 = "PBEWITHHMACSHA512ANDAES_256";
    //盐值
    private static String factor;
    @Value("${qxn.jasypt.factor}")
    public void setFactor(String factor){
        this.factor = factor;
    }

    //加密
    public static String encryptWithSHA512(String plainText) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(factor);
        config.setAlgorithm(PBEWITHHMACSHA512ANDAES_256);
        // 为减少配置文件的书写，以下都是 Jasyp 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations( "1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // 3. 加密
        return encryptor.encrypt(plainText);
    }
    //解密
    public static String decryptWithSHA512(String encryptedText) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(factor);
        config.setAlgorithm(PBEWITHHMACSHA512ANDAES_256);
        // 为减少配置文件的书写，以下都是 Jasyp 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations( "1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // 3. 解密
        return encryptor.decrypt(encryptedText);
    }
    public static void main() {
        System.out.println(factor);
        //需要加密的明文
        String plainText = "123456";
        String encryptWithSHA512Str = encryptWithSHA512(plainText);
        String decryptWithSHA512Str = decryptWithSHA512(encryptWithSHA512Str);
        System.out.println();
        System.out.println("采用SHA512加密前原文密文：" + encryptWithSHA512Str);
        System.out.println("采用SHA512解密后密文原文:" + decryptWithSHA512Str);
    }
}

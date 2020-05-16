package com.atom.mybatis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Atom
 */
@Slf4j
public final class AesUtil {

    /**
     * 加密解密，默认密码
     */
    private static final String KEY = "atom123456";
    /**
     * 加密算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加密算法/加密模式/填充类型 本例采用AES加密，CBC加密模式，PKCS5Padding填充
     */
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";
    /**
     * 必须是16个字节，密钥默认偏移，可更改
     */
    private static final String IVPARAMETER = "qwertyuikjh6789l";
    /**
     * 设置加密密码处理长度。 不足此长度补0；
     */
    private static final int PWD_SIZE = 16;

    private AesUtil() {
    }

    private static byte[] encrypt(String cmp, SecretKey sk, IvParameterSpec iv, byte[] msg) {
        try {
            Cipher c = Cipher.getInstance(cmp);
            c.init(Cipher.ENCRYPT_MODE, sk, iv);
            return c.doFinal(msg);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            log.warn(e.getLocalizedMessage(), e);
        }
        return null;

    }

    public static byte[] encrypt(byte[] clearTextBytes, byte[] pwdBytes) throws Exception {
        byte[] aesKey = new byte[16];
        if (pwdBytes.length < 16) {
            throw new Exception("Length of AES key SHOULD BE 16 bytes");
        } else {
            for (int i = 0; i < 16; i++) {
                aesKey[i] = pwdBytes[i];
            }
        }
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, ALGORITHM);
        return encrypt(CIPHER_MODE, keySpec, generateIV(IVPARAMETER.getBytes(UTF_8)).getParameterSpec(IvParameterSpec.class), clearTextBytes);
    }


    /**
     * BASE64加密
     *
     * @param clearText 明文，待加密的内容
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptBase64UseDefaultPWD(String clearText) {
        try {
            byte[] passwordByte = pwdHandler(KEY);
            // 1 获取加密密文字节数组
            byte[] cipherTextBytes = encrypt(clearText.getBytes(UTF_8), passwordByte);
            // 2 对密文字节数组进行BASE64 encoder 得到 BASE6输出的密文,返回BASE64输出的密文
            return Base64.getEncoder().encodeToString(cipherTextBytes);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        // 加密错误 返回null
        return null;
    }

    /**
     * BASE64加密
     *
     * @param clearText 明文，待加密的内容
     * @param password  密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptBase64(String clearText, String password) {
        try {
            byte[] passwordByte = pwdHandler(password);
            // 1 获取加密密文字节数组
            byte[] cipherTextBytes = encrypt(clearText.getBytes(UTF_8), passwordByte);
            // 2 对密文字节数组进行BASE64 encoder 得到 BASE6输出的密文,返回BASE64输出的密文
            return Base64.getEncoder().encodeToString(cipherTextBytes);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        // 加密错误 返回null
        return null;
    }

    /**
     * HEX加密
     *
     * @param clearText 明文，待加密的内容
     * @param password  密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptHex(String clearText, String password) {
        try {
            byte[] passwordByte = pwdHandler(password);
            // 1 获取加密密文字节数组
            byte[] cipherTextBytes = encrypt(clearText.getBytes(UTF_8), passwordByte);
            // 2 对密文字节数组进行 转换为 HEX输出密文,返回 HEX输出密文
            return byte2hex(cipherTextBytes);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        // 加密错误返回null
        return null;
    }

    private static byte[] decrypt(String cmp, SecretKey sk, IvParameterSpec iv, byte[] cipherText) {
        try {
            Cipher c = Cipher.getInstance(cmp);
            c.init(Cipher.DECRYPT_MODE, sk, iv);
            return c.doFinal(cipherText);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    public static byte[] decrypt(byte[] cipherTextBytes, byte[] pwdBytes) throws Exception {
        byte[] aesKey = new byte[PWD_SIZE];
        if (pwdBytes.length < PWD_SIZE) {
            throw new Exception("Length of AES key SHOULD BE 16 bytes");
        } else {
            for (int i = 0; i < PWD_SIZE; i++) {
                aesKey[i] = pwdBytes[i];
            }
        }
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, ALGORITHM);
        return decrypt(CIPHER_MODE, keySpec, generateIV(IVPARAMETER.getBytes(UTF_8)).getParameterSpec(IvParameterSpec.class), cipherTextBytes);
    }

    public static String decryptBase64UseDefaultPWD(String cipherText) {
        try {
            byte[] passwordByte = pwdHandler(KEY);
            // 1 对 BASE64输出的密文进行BASE64 decodebuffer 得到密文字节数组
            byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText);
            // 2 对密文字节数组进行解密 得到明文字节数组
            byte[] clearTextBytes = decrypt(cipherTextBytes, passwordByte);
            // 3 根据 CHARACTER 转码，返回明文字符串
            return new String(clearTextBytes, UTF_8);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        // 解密错误返回null
        return null;
    }

    public static String decryptBase64(String cipherText, String password) {
        try {
            byte[] passwordByte = pwdHandler(password);
            // 1 对 BASE64输出的密文进行BASE64 decodebuffer 得到密文字节数组
            byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText);
            // 2 对密文字节数组进行解密 得到明文字节数组
            byte[] clearTextBytes = decrypt(cipherTextBytes, passwordByte);
            // 3 根据 CHARACTER 转码，返回明文字符串
            return new String(clearTextBytes, UTF_8);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        // 解密错误返回null
        return null;
    }

    /**
     * HEX解密
     *
     * @param cipherText 密文，带解密的内容
     * @param password   密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptHex(String cipherText, String password) {
        try {
            byte[] passwordByte = pwdHandler(password);
            // 1 将HEX输出密文 转为密文字节数组
            byte[] cipherTextBytes = hex2byte(cipherText);
            // 2 将密文字节数组进行解密 得到明文字节数组
            byte[] clearTextBytes = decrypt(cipherTextBytes, passwordByte);
            // 3 根据 CHARACTER 转码，返回明文字符串
            return new String(clearTextBytes, UTF_8);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将hex字符串转换成字节数组
     *
     * @param str
     * @return
     */
    public static byte[] hex2byte(String str) {
        if (str == null || str.length() < 2) {
            return new byte[0];
        }
        str = str.toLowerCase();
        int l = str.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = str.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }

    /**
     * 字节数组转成16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        String tmp;
        for (int n = 0; n < bytes.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

    /**
     * 密码处理方法 如果加解密出问题， 请先查看本方法，排除密码长度不足补"0",导致密码不一致
     *
     * @param password 待处理的密码
     * @return
     */
    private static byte[] pwdHandler(String password) {
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Argument sKey is null.");
        }
        StringBuilder sb = new StringBuilder(PWD_SIZE);
        sb.append(password);
        while (sb.length() < PWD_SIZE) {
            sb.append("0");
        }
        if (sb.length() > PWD_SIZE) {
            sb.setLength(PWD_SIZE);
        }
        return sb.toString().getBytes(UTF_8);
    }

    /**
     * 生成iv
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        byte[] ivByte = new byte[16];
        new SecureRandom().nextBytes(ivByte);
        params.init(new IvParameterSpec(iv));
        return params;
    }


    public static void main(String[] args) {
        String atom = encryptBase64("atom", "123");
        String atom2 = encryptHex("atom", "123");

        System.err.println(atom);
        System.err.println(atom2);
        String s = decryptBase64(atom, "123");
        System.err.println(s);
    }

}

package com.sly.medicineshop.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

/**
 * @author SLY
 * @time 2019/9/16
 */
public class JwtDesUtils {
    /**
     * 偏移变量，固定占8位字节
     */
    private final static String IV_PARAMETER = "12345678";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * 默认编码
     */
    private static final String CHARSET = "utf-8";

    /**
     * 加密key
     */
    private static final String ENCODE_KEY = "C91BB568D82911E9801800FFA796B0DB";

    /**
     * 生成key
     *
     * @return
     * @throws Exception
     * @author sly
     * @time 2019年9月16日
     */
    private static Key generateKey() throws Exception {
        DESKeySpec dks = new DESKeySpec(ENCODE_KEY.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }


    /**
     * DES加密字符串
     *
     * @param data 待加密字符串
     * @return 加密后内容
     */
    public static String encrypt(String data) {
        if (data == null) {
            return null;
        }
        try {
            Key secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));

            return new String(Base64.getEncoder().encode(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DES解密字符串
     *
     * @param data 待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String data) {
        if (data == null) {
            return null;
        }
        try {
            Key secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

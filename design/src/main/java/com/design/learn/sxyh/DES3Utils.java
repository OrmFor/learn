package com.design.learn.sxyh;


import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;

import static com.design.learn.sxyh.DESEncrypt.ALLCHAR;


/**
 * 3DES算法
 */
public class DES3Utils {
    private static final String Algorithm = "DESede";//定义 加密算法
    public static final String CITIZEN_SIGN_KEY = "qhkiG9w0";

    /**
     * 与前端交互关键数据3DES加密
     *
     * @param data 明文字符串
     * @param key  3DES密钥
     * @return
     */
    public static String encryptByDES3(String data, String key) {
        try {
            byte[] returnJsonByte = DES3Utils.des3EncodeECB(data.getBytes("UTF-8"), key);
            return Base64.toBase64String(returnJsonByte);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 3DES加密
     * @param data
     * @param key 密钥
     * @return
     */
    public static byte[] des3EncodeECB(byte[] data, String key) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(Algorithm);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     * 与前端交互关键数据3DES解密
     *
     * @param data 密文字符串
     * @param key  3DES密钥
     * @return
     */
    public static String decryptByDES3(String data, String key) throws Exception {
        try {
            //16进制的加密
            byte[] returnByte = DES3Utils.ees3DecodeECB(Base64.decode(data), key);
            return new String(returnByte, "utf-8");
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 3DES解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] ees3DecodeECB(byte[] data, String key)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(Algorithm);
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);
        return bOut;

    }

    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    //


    public static void main1(String[] args) throws Exception {
        try {
            String dec_key=generateString(24).toUpperCase();
            System.out.println("1:"+dec_key);
            RSAPublicKey rsaPublickey= DESEncrypt.loadPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQ/DOV4sn6I83QBjAUXmfhD02FtPEepFhWpS1nwzR6WVzRmeYxsX58oVINIVek8SptF3TpVfUq28DCDSGyAuOkCmmPIOP3zjy8q6Rht2KN9fZyCTKdwrHPZaw/h3HHXbUOSkywQE3Y372DdRxXoZN/I/QHDSKRRO8z9ESfxOBrDQIDAQAB");
            dec_key=DESEncrypt.encryptByPublicKey(dec_key.getBytes(), rsaPublickey);
            System.out.println("2:"+dec_key);
            RSAPrivateKey rsaPrivateKey = DESEncrypt.loadPrivateKey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAND8M5XiyfojzdAGMBReZ+EPTYW08R6kWFalLWfDNHpZXNGZ5jGxfnyhUg0hV6TxKm0XdOlV9SrbwMINIbIC46QKaY8g4/fOPLyrpGG3Yo319nIJMp3Csc9lrD+HccddtQ5KTLBATdjfvYN1HFehk38j9AcNIpFE7zP0RJ/E4GsNAgMBAAECgYEAyaahC/6yT5gerpxZ5j2V1JdUgTCv/JYt2yU048t6ppf1tRXgBZEz8IQkzSCgFjrSakNY/xF/KKS0oUnUepTJGzCujqnuBgxzMwHlNZljZQ5gkkXmXLtiiVOClog1eY7B2+Qv7heCTOY7Y78pKrryAOSWTfSO0HqagwwXkxyFwQ0CQQDr8HHXuinoX/lu0JZ59rkOhE2NAuaQ0f8WcwG9Pq4GaMo4XA14HNk5QDyW6O1++wSGbVlLjwiLIWv+VQnsJmUTAkEA4sEQEf6toJVbh2EWoutWhr4Suj5iSqgaSYg29x10MPFncIdDM2NHy9ycBV00izU2UJBS1CR/iNgcEKCq7O2TXwJAdBlwW6Be6Nr5Sb9YnJF8xJjz9FSeEltTc7+J9o62Ya8E+zDI6/b+KT7j0bqyy05IDsIWM6khoh0DIDbGWC8YgwJAfekkakmX0e59l4vizO6O6l0K+x3+6GO1zRT1KHzTcwXpIUQKnbNIP7wtapEZxx7yHQQ26hOmOa9+zgDZSJyXUwJBAKq+Ye4p4U/qctt3vsL2eF9yII1eyM/EQpeIb52TZQB5DbfvGjA2sS/q+Dvsc7GodQUET9AqI/Gal4DRJjiMVHY=");
            dec_key=DESEncrypt.decryptByPrivateKey(Base64.decode(dec_key), rsaPrivateKey);
            System.out.println("3:"+dec_key);
            String tr_amt="123456";
            String result =DES3Utils.encryptByDES3(tr_amt, dec_key);
            System.out.println(result);
            String result1 = DES3Utils.decryptByDES3(result, dec_key);
            System.out.println(result1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static final String CITIZEN_CARD_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQ/DOV4sn6I83QBjAUXmfhD02FtPEepFhWpS1nwzR6WVzRmeYxsX58oVINIVek8SptF3TpVfUq28DCDSGyAuOkCmmPIOP3zjy8q6Rht2KN9fZyCTKdwrHPZaw/h3HHXbUOSkywQE3Y372DdRxXoZN/I/QHDSKRRO8z9ESfxOBrDQIDAQAB";
    public static void main(String[] args) throws Exception {
        String decKey = "1234567890ACDFRTGY6589IK"; //RSAUtils.generateString(24).toUpperCase();
        JSONObject request = new JSONObject();
        RSAPublicKey rsaPublickey= DESEncrypt.loadPublicKey(CITIZEN_CARD_PUBLIC_KEY);
        //公钥加密
        String certNoWithEncrypt = DES3Utils.encryptByDES3("350583198702220732", decKey);
        String mobileWithEncrypt = DES3Utils.encryptByDES3("15566667777", decKey);

        String decKeyWithEncrypt = DESEncrypt.encryptByPublicKey(decKey.getBytes(),rsaPublickey);

        //关键字解密密钥
        request.put("dec_key",decKeyWithEncrypt);
        request.put("cert_no",certNoWithEncrypt);
        request.put("mobile",mobileWithEncrypt);
        request.put("name","test0732");
        request.put("channel","21");
        //加签字段
        JSONObject addsign = DESEncrypt.addSign(request,"qhkiG9w0");
        String sign = addsign.getString("sign");
        request.put("sign",sign);
        request.put("key","cert_no,channel,dec_key,mobile,name");

        System.out.println(request.toJSONString());
    }


}
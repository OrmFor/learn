package com.design.learn.sxyh;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Random;

public class DESEncrypt {

    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static final String CITIZEN_SIGN_KEY = "qhkiG9w0";

    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 从字符串中加载公钥
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {

            byte[] buffer= Base64.decode(publicKeyStr);
            KeyFactory keyFactory= KeyFactory.getInstance(ALGORITHM);
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        }  catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */

//    public static String encryptByPublicKey(byte[] data, RSAPublicKey publicKey)
//            throws Exception {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        int inputLen = data.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段加密
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(data, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_ENCRYPT_BLOCK;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        String mi = new String(org.bouncycastle.util.encoders.Base64.encode(encryptedData));
//        return mi;
//    }

    public static String encryptByPublicKey(byte[] data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        String mi = new String(Base64.encode(encryptedData));
        return mi;
    }

    /**
     * 从字符串中加载私钥
     * @param privateKeyStr 私钥数据字符串
     * @throws Exception 加载私钥时产生的异常
     */
    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            KeyFactory keyFactory= KeyFactory.getInstance(ALGORITHM);
            byte[] buffer= Base64.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
            return  (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        }  catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String mi = new String(decryptedData);
        return mi;
    }


    public static boolean verifySign(JSONObject jsonObject){
        try {
            Iterator ite = jsonObject.keySet().iterator();
            StringBuffer old_key=new StringBuffer();//
            while(ite.hasNext()) {
                String e= (String)ite.next();
                if (jsonObject.get(e) instanceof JSONArray) {
                    JSONArray a = jsonObject.getJSONArray(e);
                    for (int i=0;i < a.size();i++) {
                        JSONObject a_e = a.getJSONObject(i);
                        if (!verifySign(a_e)) {
                            return false;
                        }
                    }
                } else {
                    if(!e.equals("key") && !e.equals("sign")) {
                        old_key.append(e+",");
                    }
                }
            }
            if (old_key.length()>0) {
                old_key = new StringBuffer(old_key.substring(0,old_key.length()-1));
            }

            if (!jsonObject.containsKey("key")||!jsonObject.containsKey("sign")) {
                return false;
            }
            if (old_key.length()!=Tools.processNull(jsonObject.get("key")).length()) {
                return false;
            }

            String[] key=Tools.processNull(jsonObject.get("key")).split(",");
            StringBuffer sb = new StringBuffer();
            for (int i=0;i < key.length;i++) {
                sb.append(Tools.processNull(jsonObject.get(key[i])));
            }
            if(!SHA1Utils.encryptSHA(MD5.crypt(sb.toString() + CITIZEN_SIGN_KEY)
                    .toUpperCase()).toUpperCase().equals(Tools.processNull(jsonObject.get("sign")))){
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static JSONObject addSign(JSONObject obj, String md5key) {
        JSONObject jsonObject = new JSONObject();
        obj.remove("key");
        obj.remove("sign");
        jsonObject = obj;
        int count = 0;
        Iterator ite = jsonObject.keySet().iterator();
        StringBuffer old_key = new StringBuffer();
        StringBuffer old_content = new StringBuffer();
        while (ite.hasNext()) {
            count++;
            String e = (String) ite.next();
            if (jsonObject.get(e) instanceof JSONArray) {
                JSONArray a = jsonObject.getJSONArray(e);
                for (int i = 0; i < a.size(); i++) {
                    JSONObject a_e = a.getJSONObject(i);
                    addSign(a_e, md5key);
                }
            } else {
                old_key.append(e + ",");
                old_content.append(jsonObject.get(e));
            }
        }
        if (old_key.length() > 0) {
            old_key = new StringBuffer(old_key.substring(0, old_key.length() - 1));
        }
        if (old_key.length() > 0) {
            jsonObject.put("key", old_key);
        }
        if (old_content.length() > 0) {
            if (jsonObject.keySet().size() - 1 == count) {
                jsonObject.put("sign",
                        SHA1Utils.getDigestOfString(
                                MD5.crypt(old_content.append(md5key).toString()).toUpperCase().getBytes())
                                .toUpperCase());
            }
        }
        return jsonObject;
    }


}

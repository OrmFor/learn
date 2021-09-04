package com.design.learn.sxyh;

import java.security.MessageDigest;


public class SHA1Utils {
    /**
     * 定义加密方式
     */
    private final static String KEY_SHA = "SHA";
    private final static String KEY_SHA1 = "SHA-1";
    /**
     * 全局数组
     */
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 构造函数
     */
    public SHA1Utils() {

    }

    /**
     * SHA 加密
     * @param data 需要加密的字节数组
     * @return 加密之后的字节数组
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        // 创建具有指定算法名称的信息摘要
//        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);
        // 使用指定的字节数组对摘要进行最后更新
        sha.update(data);
        // 完成摘要计算并返回
        return sha.digest();
    }

    /**
     * SHA 加密
     * @param data 需要加密的字符串
     * @return 加密之后的字符串
     * @throws Exception
     */
    public static String encryptSHA(String data) throws Exception {
        // 验证传入的字符串
        if (data==null || data.isEmpty()) {
            return "";
        }
        // 创建具有指定算法名称的信息摘要
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        // 使用指定的字节数组对摘要进行最后更新
        sha.update(data.getBytes());
        // 完成摘要计算
        byte[] bytes = sha.digest();
        // 将得到的字节数组变成字符串返回
        return byteArrayToHexString(bytes);
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        //System.out.println("ret = " + ret);
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    /**
     * 转换字节数组为十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String key = "123";
        System.out.println(encryptSHA(key));
    }
    // 从主席SHA1类中复制过来,用于签名--lcg->
    private static final int[] a = { 1732584193, -271733879, -1732584194, 271733878, -1009589776 };
    private static int[] b = new int[5];
    private static int[] c = new int[80];

    private static int a(int i, int i_0_) {
        return i << i_0_ | i >>> 32 - i_0_;
    }

    public static byte[] getDigestOfBytes(byte[] is) {
        byte[] is_1_ = is;
        System.arraycopy(a, 0, b, 0, a.length);
        int i;
        int i_2_;
        int i_3_;
        if ((i = (i_2_ = (is_1_ = is_1_).length) % 64) < 56) {
            i_3_ = 55 - i;
            i = i_2_ - i + 64;
        } else if (i == 56) {
            i_3_ = 63;
            i = i_2_ + 8 + 64;
        } else {
            i_3_ = 63 - i + 56;
            i = i_2_ + 64 - i + 64;
        }
        byte[] is_4_ = new byte[i];
        System.arraycopy(is_1_, 0, is_4_, 0, i_2_);
        int i_5_ = i_2_;
        byte[] is_6_ = is_4_;
        int i_7_ = i_2_;
        i_5_++;
        is_6_[i_7_] = (byte) -128;
        for (int i_8_ = 0; i_8_ < i_3_; i_8_++)
            is_4_[i_5_++] = (byte) 0;
        long l;
        byte i_9_ = (byte) (int) ((l = (long) i_2_ << 3) & 0xffL);
        byte i_10_ = (byte) (int) (l >> 8 & 0xffL);
        byte i_11_ = (byte) (int) (l >> 16 & 0xffL);
        byte i_12_ = (byte) (int) (l >> 24 & 0xffL);
        byte i_13_ = (byte) (int) (l >> 32 & 0xffL);
        byte i_14_ = (byte) (int) (l >> 40 & 0xffL);
        byte i_15_ = (byte) (int) (l >> 48 & 0xffL);
        byte i_16_ = (byte) (int) (l >> 56);
        is_4_[i_5_++] = i_16_;
        is_4_[i_5_++] = i_15_;
        is_4_[i_5_++] = i_14_;
        is_4_[i_5_++] = i_13_;
        is_4_[i_5_++] = i_12_;
        is_4_[i_5_++] = i_11_;
        is_4_[i_5_++] = i_10_;
        is_4_[i_5_] = i_9_;
        byte[] is_18_;
        int i_17_ = (is_18_ = is_4_).length / 64;
        for (i_5_ = 0; i_5_ < i_17_; i_5_++) {
            for (i = 0; i < 16; i++) {
                int[] is_19_ = c;
                int i_20_ = i;
                byte[] is_21_ = is_18_;
                i_3_ = (i_5_ << 6) + (i << 2);
                is_19_[i_20_] = (((is_1_ = is_21_)[i_3_] & 0xff) << 24 | (is_1_[i_3_ + 1] & 0xff) << 16
                        | (is_1_[i_3_ + 2] & 0xff) << 8 | is_1_[i_3_ + 3] & 0xff);
            }
            for (int i_22_ = 16; i_22_ <= 79; i_22_++)
                c[i_22_] = a((c[i_22_ - 3] ^ c[i_22_ - 8] ^ c[i_22_ - 14] ^ c[i_22_ - 16]), 1);
            int[] is_23_ = new int[5];
            for (i_3_ = 0; i_3_ < is_23_.length; i_3_++)
                is_23_[i_3_] = b[i_3_];
            for (i_3_ = 0; i_3_ <= 19; i_3_++) {
                int i_24_ = a(is_23_[0], 5);
                int i_25_ = is_23_[1];
                int i_26_ = is_23_[2];
                int i_27_ = is_23_[3];
                i = i_26_;
                i = (i_24_ + ((i_2_ = i_25_) & i | (i_2_ ^ 0xffffffff) & i_27_) + is_23_[4] + c[i_3_] + 1518500249);
                is_23_[4] = is_23_[3];
                is_23_[3] = is_23_[2];
                is_23_[2] = a(is_23_[1], 30);
                is_23_[1] = is_23_[0];
                is_23_[0] = i;
            }
            for (i_3_ = 20; i_3_ <= 39; i_3_++) {
                int i_28_ = a(is_23_[0], 5);
                int i_29_ = is_23_[1];
                int i_30_ = is_23_[2];
                int i_31_ = is_23_[3];
                i_2_ = i_30_;
                i = (i_28_ + ((i = i_29_) ^ i_2_ ^ i_31_) + is_23_[4] + c[i_3_] + 1859775393);
                is_23_[4] = is_23_[3];
                is_23_[3] = is_23_[2];
                is_23_[2] = a(is_23_[1], 30);
                is_23_[1] = is_23_[0];
                is_23_[0] = i;
            }
            for (i_3_ = 40; i_3_ <= 59; i_3_++) {
                int i_32_ = a(is_23_[0], 5);
                int i_33_ = is_23_[1];
                int i_34_ = is_23_[2];
                int i_35_ = is_23_[3];
                i = i_34_;
                i = (i_32_ + ((i_2_ = i_33_) & i | i_2_ & i_35_ | i & i_35_) + is_23_[4] + c[i_3_] + -1894007588);
                is_23_[4] = is_23_[3];
                is_23_[3] = is_23_[2];
                is_23_[2] = a(is_23_[1], 30);
                is_23_[1] = is_23_[0];
                is_23_[0] = i;
            }
            for (i_3_ = 60; i_3_ <= 79; i_3_++) {
                int i_36_ = a(is_23_[0], 5);
                int i_37_ = is_23_[1];
                int i_38_ = is_23_[2];
                int i_39_ = is_23_[3];
                i_2_ = i_38_;
                i = (i_36_ + ((i = i_37_) ^ i_2_ ^ i_39_) + is_23_[4] + c[i_3_] + -899497514);
                is_23_[4] = is_23_[3];
                is_23_[3] = is_23_[2];
                is_23_[2] = a(is_23_[1], 30);
                is_23_[1] = is_23_[0];
                is_23_[0] = i;
            }
            for (i_3_ = 0; i_3_ < is_23_.length; i_3_++)
                b[i_3_] = b[i_3_] + is_23_[i_3_];
            for (i_3_ = 0; i_3_ < c.length; i_3_++)
                c[i_3_] = 0;
        }
        if (20 != 0) {
            /* empty */
        }
        is = new byte[20];
        for (i = 0; i < b.length; i++) {
            int i_40_ = b[i];
            byte[] is_41_ = is;
            i_17_ = i << 2;
            is_18_ = is_41_;
            int i_42_ = i_40_;
            is_18_[i_17_] = (byte) (i_42_ >> 24);
            is_18_[i_17_ + 1] = (byte) (i_42_ >>> 16);
            is_18_[i_17_ + 2] = (byte) (i_42_ >>> 8);
            is_18_[i_17_ + 3] = (byte) i_42_;
        }
        return is;
    }

    public static String getDigestOfString(byte[] is) {
        is = getDigestOfBytes(is);
        String string = "";
        for (int i = 0; i < is.length; i++) {
            StringBuilder stringbuilder = new StringBuilder(string);
            int i_43_ = is[i];
            char[] cs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            char[] cs_44_;
            (cs_44_ = new char[2])[0] = cs[i_43_ >>> 4 & 0xf];
            cs_44_[1] = cs[i_43_ & 0xf];
            string = stringbuilder.append(string = new String(cs_44_)).toString();
        }
        return string;
    }
}

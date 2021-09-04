package com.design.learn.sxyh;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5 {

    public static String crypt(String arg) {
        StringBuffer arg3 = new StringBuffer();

        if (arg != null && arg.length() != 0) {
            MessageDigest arg0;
            try {
                (arg0 = MessageDigest.getInstance("MD5")).update(arg.getBytes(StandardCharsets.UTF_8));
                byte[] arg2 = arg0.digest();
                for (int arg1 = 0; arg1 < arg2.length; ++arg1) {
                    if ((256 & arg2[arg1]) < 16) {
                        arg3.append("0" + Integer.toHexString(255 & arg2[arg1]));
                    } else {
                        arg3.append(Integer.toHexString(255 & arg2[arg1]));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return arg3.toString();
        } else {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
    }
}

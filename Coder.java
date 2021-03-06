package fudandb;

import java.sql.*;

import java.security.MessageDigest;  
  
import javax.crypto.KeyGenerator;  
import javax.crypto.Mac;  
import javax.crypto.SecretKey;  
  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  

public class Coder {
    public Coder() {
    }
    public static String passwordDigest(String myinfo) {
        String newPwd = "";
        try {
            //java.security.MessageDigest alga=java.security.MessageDigest.getInstance("MD5");
            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1");
            alga.update(myinfo.getBytes());
            byte[] digesta = alga.digest();
            newPwd = byte2hex(digesta);
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println("密码加密异常：非法摘要算法"+e);
        }
        return newPwd;
    }
    public static String byte2hex(byte[] b) // 二行制转字符串
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1){
                hs = hs + "0" + stmp;
            }else{
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

}


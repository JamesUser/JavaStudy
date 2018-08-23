package org.study.md5;
 
import sun.misc.BASE64Encoder;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * md5工具类
 * @auth shijing
 * @date 2016-01-07 14:33:46
 */
public class MD5Utils {
 
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
 
 
 
    /**
     * 加密
     * @param seq
     * @return
     */
    public static String md5Digest(String seq) {
        try {
            MessageDigest md5Code =MessageDigest.getInstance("md5");
            byte[] bTmp=md5Code.digest(seq.getBytes());
            BASE64Encoder base64=new BASE64Encoder();
            String str=base64.encode(bTmp);
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }
 
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
 
        return resultSb.toString();
    }
 
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
//    	String md5Digest = MD5Utils.md5Digest("aaaBBBBB");
//    	System.out.println(md5Digest);
    	
    	String md5Encode = MD5Utils.MD5Encode("阳石屏", "UTF-8");
    	System.out.println(md5Encode);
	}
    
}

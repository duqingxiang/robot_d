package com.demo.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/** 
 
 * MD5工具类，提供字符串MD5加密（校验）、文件MD5值获取（校验）功能。 
 
 */  
  
public class MD5Util  
  
{  
  
    /** 
 
     * 16进制字符集 
 
     */  
  
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};  
  
  
  
    /** 
 
     * 指定算法为MD5的MessageDigest 
 
     */  
  
    //private static MessageDigest messageDigest = null;  
  
  
  
    /** 
 
     * 初始化messageDigest的加密算法为MD5 
 
     */  
  
/*    static  
  
    {  
  
        try  
  
        {  
  
            messageDigest = MessageDigest.getInstance("MD5");  
  
        }  
  
        catch(NoSuchAlgorithmException e)  
  
        {  
  
            Logs.geterrorLogger().error(e.getMessage(),e);  
  
        }  
  
    } */ 
  
  
  
    /** 
 
     * 获取文件的MD5值 
 
     * @param file 目标文件 
 
     * @return MD5字符串 
 
     */  
  
    public static String getFileMD5String(File file)  
  
    {  
  
        String ret = "";  
  
        FileInputStream in = null;  
  
        FileChannel ch = null;  
  
  
  
        try  
  
        {  
        	MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
            in = new FileInputStream(file);  
  
            ch = in.getChannel();  
  
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());  
  
            messageDigest.update(byteBuffer);  
  
            ret = bytesToHex(messageDigest.digest());  
  
        }  
  
        catch(Exception e)  
  
        {  
        	Logs.geterrorLogger().error(e.getMessage(),e); 
        }  
  
        finally  
        {  
            if(in != null)  
            {  
                try  
                {  
                    in.close();  
                }  
                catch(IOException e)  
                {  
                	Logs.geterrorLogger().error(e.getMessage(),e); 
                }  
            }  
            if(ch != null)  
            {  
                try  
                {  
                    ch.close();  
                }  
                catch(IOException e)  
                {  
                	Logs.geterrorLogger().error(e.getMessage(),e);  
                }  
            }  
        }  
  
        return ret;  
  
    }  
  
      
  
    /** 
 
     * 获取文件的MD5值 
 
     * @param fileName 目标文件的完整名称 
 
     * @return MD5字符串 
 
     */  
  
    public static String getFileMD5String(String fileName)  
  
    {  
  
        return getFileMD5String(new File(fileName));  
  
    }  
  
  
  
    /** 
 
     * MD5加密字符串 
 
     * @param str 目标字符串 
 
     * @return MD5加密后的字符串 
 
     */  
  
    public static String getMD5String(String str)  
  
    {  
  
        return getMD5String(str.getBytes());  
  
    }  
  
  
  
    /** 
 
     * MD5加密以byte数组表示的字符串 
 
     * @param bytes 目标byte数组 
 
     * @return MD5加密后的字符串 
 
     */  
  
    public static String getMD5String(byte[] bytes)  
    
    {  
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);  
			return bytesToHex(messageDigest.digest()); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			Logs.geterrorLogger().error(e.getMessage(),e); 
		}  
  
        return "";  
  
    }  
  
      
  
    /** 
 
     * 校验密码与其MD5是否一致 
 
     * @param pwd 密码字符串 
 
     * @param md5 基准MD5值 
 
     * @return 检验结果 
 
     */  
  
    public static boolean checkPassword(String pwd, String md5)  
  
    {  
  
        return getMD5String(pwd).equalsIgnoreCase(md5);  
  
    }  
  
      
  
    /** 
 
     * 校验密码与其MD5是否一致 
 
     * @param pwd 以字符数组表示的密码 
 
     * @param md5 基准MD5值 
 
     * @return 检验结果 
 
     */  
  
    public static boolean checkPassword(char[] pwd, String md5)  
  
    {  
  
        return checkPassword(new String(pwd), md5);  
  
    }  
  
      
  
    /** 
 
     * 检验文件的MD5值 
 
     * @param file 目标文件 
 
     * @param md5 基准MD5值 
 
     * @return 检验结果 
 
     */  
  
    public static boolean checkFileMD5(File file, String md5)  
  
    {  
  
        return getFileMD5String(file).equalsIgnoreCase(md5);  
  
    }  
  
      
  
    /** 
 
     * 检验文件的MD5值 
 
     * @param fileName 目标文件的完整名称 
 
     * @param md5 基准MD5值 
 
     * @return 检验结果 
 
     */  
  
    public static boolean checkFileMD5(String fileName, String md5)  
  
    {  
  
        return checkFileMD5(new File(fileName), md5);  
  
    }  
  
  
  
    /** 
 
     * 将字节数组转换成16进制字符串 
 
     * @param bytes 目标字节数组 
 
     * @return 转换结果 
 
     */  
  
    public static String bytesToHex(byte bytes[])  
  
    {  
  
        return bytesToHex(bytes, 0, bytes.length);  
  
    }  
  
  
  
    /** 
 
     * 将字节数组中指定区间的子数组转换成16进制字符串 
 
     * @param bytes 目标字节数组 
 
     * @param start 起始位置（包括该位置） 
 
     * @param end 结束位置（不包括该位置） 
 
     * @return 转换结果 
 
     */  
  
    public static String bytesToHex(byte bytes[], int start, int end)  
  
    {  
  
        StringBuilder sb = new StringBuilder();  
  
  
  
        for(int i = start; i < start + end; i++)  
  
        {  
  
            sb.append(byteToHex(bytes[i]));  
  
        }  
  
  
  
        return sb.toString();  
  
    }  
  
  
  
    /** 
 
     * 将单个字节码转换成16进制字符串 
 
     * @param bt 目标字节 
 
     * @return 转换结果 
 
     */  
  
    public static String byteToHex(byte bt)  
  
    {  
  
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];  
  
    } 
    
    public static long byteToInt(String plainText){
    	MessageDigest messageDigest=null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			Logs.geterrorLogger().error(e.getMessage(),e); 
		}  
    	messageDigest.update(plainText.getBytes());
    	byte b[] = messageDigest.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toOctalString(i));
		}
		System.out.println(buf.toString());
		String result = buf.toString().substring(8, 24);
		return StringUtil.toLong(result , 0L);
    }
  
  
  
    /*public static void main(String[] args) throws IOException  
  
    {  
  
        long begin = System.currentTimeMillis();  
  
        String md5 = getFileMD5String(new File("d://1.pdf"));  
  
        long end = System.currentTimeMillis();  
  
        System.out.println("MD5:\t" + md5 + "\nTime:\t" + (end - begin) + "ms");  
        
        begin = System.currentTimeMillis();  
        
        md5 = getFileMD5String(new File("d://2.pdf"));  
  
        end = System.currentTimeMillis();  
  
        System.out.println("MD5:\t" + md5 + "\nTime:\t" + (end - begin) + "ms");  
        
        begin = System.currentTimeMillis();  
        
        md5 = getFileMD5String(new File("d://3.pdf"));  
  
        end = System.currentTimeMillis();  
  
        System.out.println("MD5:\t" + md5 + "\nTime:\t" + (end - begin) + "ms");  
 
        begin = System.currentTimeMillis();  
        
        md5 = getFileMD5String(new File("d://4.pdf"));  
  
        end = System.currentTimeMillis();  
  
        System.out.println("MD5:\t" + md5 + "\nTime:\t" + (end - begin) + "ms");  
  
        
        
    }  */
    
    /**
	 * encode string
	 * @param str
	 * @return String
	 */
	public static String encodeBySha1(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) { 			
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	
    public static String byteArrayToString(String text) {
    	try{
    		MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
    		byte[] b = messageDigest.digest(text.getBytes());
            StringBuffer resultSb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
              resultSb.append(byteToNumString(b[i]));//使用本函数则返回加密结果的10进制数字字串，即全数字形式
            }
            return resultSb.toString();
    	}catch(Exception e){
    		Logs.geterrorLogger().error(e.getMessage(),e); 
    		return "0";
    	}
    	
	}
    
    private static String byteToNumString(byte b) {
        int _b = b;
        if (_b < 0) {
          _b = 256 + _b;
        }
        return String.valueOf(_b);
	}
    
    public static final int byteArrayToShort(byte [] b) {
    	return (b[0] << 8) + (b[1] & 0xFF);
	}
    
    public static Long test(String text){
    	try {
    		MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
			messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(text.getBytes());
			byte[] b = messageDigest.digest();
			System.out.println(bytesToHex(b));
			
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(text.getBytes());
			b = null;
			b = messageDigest.digest();
			System.out.println(bytesToHex(b));
		} catch (Exception e) {
			Logs.geterrorLogger().error(e.getMessage(),e); 
		}
    	return 0L;
    }

    public static String getMD5String16(String plainText) {
        String result = getMD5String(plainText);
        if (result != null && result.length() > 24)
            return result.substring(8, 24);
        return result;
    }

    public static String getMD5String64(String plainText) {
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("SHA-256");
            md5.update(plainText.getBytes());
            return bytesToHex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
    	String str = "ccccasdfedafde";
    	System.out.println(MD5Util.getMD5String(str));
    	System.out.println(MD5Util.getMD5String16(str));
    	System.out.println(MD5Util.getMD5String64(str));
    }
}  

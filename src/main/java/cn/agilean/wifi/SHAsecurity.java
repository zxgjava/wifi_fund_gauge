package cn.agilean.wifi;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class SHAsecurity {
	

	public static final String SHA1 = "sha1";
	public static final String SHA256 = "sha256";
	
	public static String encryptSHA(String data,String key) {
        MessageDigest md;
        String result="";
			try {
				md = MessageDigest.getInstance(key);
				 md.update(data.getBytes("utf-8"));
				 result = bytes2Hex(md.digest()); // to HexString
				 
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
        return result;
    }
	
	 public static String bytes2Hex(byte[] bts) {
	        String des = "";
	        String tmp = null;
	        for (int i = 0; i < bts.length; i++) {
	            tmp = (Integer.toHexString(bts[i] & 0xFF));
	            if (tmp.length() == 1) {
	                des += "0";
	            }
	            des += tmp;
	        }
	        return des;
	 }
	// 进行SHA-1加密
	   public static String SHAEncode(String message) {
	      String resultString = null;

	      try {
	         MessageDigest md = MessageDigest.getInstance("SHA");
	         md.update(message.getBytes());
	         resultString = bytes2HexString(md.digest());
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return resultString;
	   }

	   // 将Bytes数据转换成16进制字符串格式
	   public static String bytes2HexString(byte[] bts) {
	      String des = "";
	      String tmp = null;
	      for (int i = 0; i < bts.length; i++) {
	         tmp = (Integer.toHexString(bts[i] & 0xFF));
	         if (tmp.length() == 1) {
	            des += "0";
	         }
	         des += tmp;
	      }
	      return des;
	   }
	 public static void main(String[] args) {
		    //System.out.println("连接失败安抚活动签名");
		 	//generalSign_connfail();
//		 	System.out.println("优酷");
			generalSign_youku();
//			generalSign();
//			generatBusinessid();
	 }
	 public static void generalSign_youku(){
//		 String appId = "YOUKU";
		    String appId = "AIQIYI";
			String nonce = "8412";
			long timestamp = System.currentTimeMillis();
			System.out.println(timestamp);
			String code = "fdrtEGQibG";
			//F007-tMzf-ecdq-SEEX 
			//F007-7Ex5-PbQf-QKEy 
			String phoneNumber = "13070632601";
			String appKey = "279ebe019f7a12e4badb001b217763b0";
			if(StringUtils.isEmpty(phoneNumber)){
				String[] sortArr = {appId,appKey,nonce,String.valueOf(timestamp)};
				Arrays.sort(sortArr);
				String sha1msg = StringUtils.join(sortArr);
				//SHA加密
				String newsignatrue = SHAsecurity.encryptSHA(sha1msg, "SHA1");
				System.out.println(newsignatrue);
			}else{
				String[] sortArr = {appId,appKey,nonce,String.valueOf(timestamp),code,phoneNumber};
				Arrays.sort(sortArr);
				String sha1msg = StringUtils.join(sortArr);
				//SHA加密
				String newsignatrue = SHAsecurity.encryptSHA(sha1msg, "SHA1");
				System.out.println(newsignatrue);
		 }
	 }
	 public static void generalSign_connfail(){
		 long timestamp = System.currentTimeMillis();
			String nonce = "OxcE";
			System.out.println(timestamp);
			String securityKey = "108cb800a5fa11e48ccf001b217763b0";
			String[] sortArr = {String.valueOf(timestamp), nonce, securityKey};
			Arrays.sort(sortArr);
			String sha1msg = StringUtils.join(sortArr);
			//SHA加密
			String newsignatrue = SHAsecurity.encryptSHA(sha1msg, "SHA1");
			System.out.println(newsignatrue);
	 }
	 private static void generalSign(){
			String nonce = "4321";
			long timestamp = System.currentTimeMillis();
			System.out.println(timestamp);
			String securityKey = "e05d5224ed7f11e4a1cf001b217763b0";
			String[] sortArr = {String.valueOf(timestamp), nonce, securityKey};
			Arrays.sort(sortArr);
			String sha1msg = StringUtils.join(sortArr);
			//SHA加密
			String newsignatrue = SHAsecurity.encryptSHA(sha1msg, "SHA1");
			System.out.println(newsignatrue);
	 }
	 private static void generatBusinessid(){
			Random ran = new Random();
			int ranNum = 1000+ran.nextInt(9000);
			String transNum = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"0100"+ ranNum;
			System.out.println( "CUST"+transNum);
		}

}

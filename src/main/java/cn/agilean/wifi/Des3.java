package cn.agilean.wifi;

import java.net.URLEncoder;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author xuwei130
 * @date 2012-10-11
 */
public class Des3 {
	// 密钥
	private static String secretKey = "5462249415ed84d7768ab5ae2e2f0abd";
	// private static String secretKey = "";
	// 向量
	private static String iv = "01234567";
	private static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, String key) throws Exception {
		Key deskey = null;
		if (key == null) {
			key = secretKey;
		}
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText, String key)
			throws Exception {
		Key deskey = null;
		if (key == null) {
			key = secretKey;
		}
		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

		return new String(decryptData, encoding);
	}

	// wifi01234567899876543210
	public static void main(String[] args) throws Exception {

		Des3 aa = new Des3();
		// System.out.println("解密串 1 = " + aa.decode("TCgxl136nLA=",
		// "wifi01234567899876543210"));
		// System.out.println("解密串2 = " +
		// aa.decode("1rUXRcwRCcqgvqdSRryzh9V8wX8FHFZFXNX9xAhWbfhodlY9hJhls4FUhh+1r7ktVuQ6inwfy9W8YTvdefQ26eGkPuf1vwc5",
		// "2HIgDvEG5cFQvShdx3TcAGB7"));
		// System.out.println("加密1 = " + URLEncoder.encode(aa.encode("4280",
		// "wifi20150901884578625886")));
		// System.out.println("加密1 = " + URLEncoder.encode(aa.encode("4280",
		// "wifi20150901894644578958")));
		// System.out.println("解密1 = " +
		// aa.decode(URLDecoder.decode("ItHmD6OFiyO05BWaZFAYpA%3D%3D"),
		// "wifi20150901894644578958"));
		// System.out.println("国际wifi解密 = " +
		// aa.decode("l+sEqqt1c36hE5GXeY5bvoW7h0bOlX5FyIjwJGmdy6sU6I5SL3JS1LWGjb8WGMe0pZv7CNAh3R3y7yfCQYaTJQbeYq07VuzXMvkgH9Ezc4E4ADYUr49Gw/fHPrvdXZnDAPDhCqXDawkScoUPqHJ2oBEi83t/bgW/Q4NqNMcZEmQ+/wm0yit+8MK6O7sOZu0eyOYwMYhz+YvOTffZmbpmLEl1t8o+7rNdN8uhM9LOtkLv4cIF0Zevp4lqPPG+dtNp+KVzIoOUxxzJ6TsIUqiBieaZBO57rKH1CuW5rQDCCJsDJ+oHKXuO9lo1g3+FncRRvRQOXp/u2YuwtS4zicKIwIgldejNpitbKTIy/yZxhU2VX1SX/zmGIUzpTgeyvRxwCNRRQzX5DDbLPUZPvdGfPBcakJeX5ridLmCaPRPaysU=",
		// "UWmQesQkubhuJUV32psgMGyM"));   System.currentTimeMillis()+
//		 System.out.println("快传传输记录加密= " + URLEncoder.encode(aa.encode("[{actId:\"10001\",uid:\"1010002695001\",reuid:\"1010002695001\",\"filename\":\"123\",timestamp:\"1444291131000\"}]"
//,"12d4161d94b957ea313aeb04"),"UTF-8"));
//		 List<Map<String,String>> list =new ArrayList<Map<String,String>>();
//		 Map<String,String> map = new HashMap<String,String>();
//		 map.put("actId", "10011123455556666");
//		 map.put("uid", "1010008188376");
//		 map.put("reUid", "1010002695001");
//		 map.put("filename", "1234");
//		 map.put("timestamp", System.currentTimeMillis()+"");
//		 list.add(map);
//		 String aaaaa = JSONArray.fromObject(list).toString();
		 JSONArray arr = new JSONArray();
		 JSONObject json = new JSONObject();
		 json.put("actId", "10011123455556666");
		 json.put("uid", "1010008188376");
		 json.put("reUid", "1010002695001");
		 json.put("filename", "1234");
		 json.put("timestamp", System.currentTimeMillis()+"");
		 arr.add(json);
		 System.out.println("jsonArrStr:" + arr.toJSONString());
		 System.out.println("快传传输记录加密= " + URLEncoder.encode(Des3.encode(arr.toJSONString(),"12d4161d94b957ea313aeb04"),"UTF-8"));
		 
		 
	}
}

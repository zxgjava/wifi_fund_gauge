/**
 * 
 */
package cn.agilean.wifi;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author ex-huangruifeng001
 * 
 */
public class RSAHelper {

	// 加解密统一使用的编码方弄
	private static String encoding = "utf-8";

	/**
	 * 得到公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 使用公钥进行rsa加密操作
	 * 
	 * @param data
	 *            需要加密的数据
	 * @param publicKey
	 *            公钥字符
	 * @return
	 */
	public static String encryptByPublicKey(String data, String publicKey) {
		if (StringUtil.isEmpty(data) || StringUtil.isEmpty(publicKey)) {
			System.err
					.println("RSAHelper.decryptByPrivateKey() date or publicKey is null.");
			return "";
		}
		String encryptStr = "";
		try {
			// 根据模和指数获取公钥对象进行加密操作
			PublicKey publicKeyObj = getPublicKey(publicKey);
//			System.out.println("publicKeyObj:"+publicKeyObj);
			// 加解密类
			Cipher cipher = Cipher.getInstance("RSA");
			// 明文
			byte[] plainText = data.getBytes(encoding);
			// 加密
			cipher.init(Cipher.ENCRYPT_MODE, publicKeyObj);
			byte[] enBytes = cipher.doFinal(plainText);
			encryptStr = Base64.encode(enBytes);
		} catch (Exception e) {
			System.err
					.println("RSAHelper.encryptByPublicKey() is faild. Exception:"
							+ e.toString());
		}
		return encryptStr;
	}

	/**
	 * 使用私钥进行解码操作
	 * 
	 * @param data
	 *            解密数据
	 * @param privateKey
	 *            私钥字符
	 * @return
	 */
	public static String decryptByPrivateKey(String data, String privateKey) {
		String decryptStr = "";
		if (StringUtil.isEmpty(data) || StringUtil.isEmpty(privateKey)) {
			System.err
					.println("RSAHelper.decryptByPrivateKey() date or privateKey is null.");
			return "";
		}
		try {
			PrivateKey privateKeyObj = getPrivateKey(privateKey);
			// 加解密类
			Cipher cipher = Cipher.getInstance("RSA");
			// 解密
			cipher.init(Cipher.DECRYPT_MODE, privateKeyObj);
			BASE64Decoder dec = new BASE64Decoder();
			byte[] messageBytes = dec.decodeBuffer(data);
			byte[] deBytes = cipher.doFinal(messageBytes);
			decryptStr = new String(deBytes, encoding);
		} catch (Exception e) {
			System.err
					.println("RSAHelper.decryptByPrivateKey() is faild. Exception:"
							+ e.toString());
		}
		return decryptStr;
	}
	
	public static void main(String[] args) {
//		String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDeWaoBYR3Z1x9jnS7IKrmYaCs38rE5GBW19tADLs+Ui2jG1jy8Pt/yn5t9LaMr1v+T9HPfjqlDZHJkE3DUy7cK6u1UskCdlISjlB/uBja6Usjg/Wdvfc8Mn7Ize53rlUdk+3U3T+yr8rVJZkpTBinjGAq83XT+Kcl2mUGv4Yw13QIDAQAB";
//		String privateKeyString = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN5ZqgFhHdnXH2OdLsgquZhoKzfysTkYFbX20AMuz5SLaMbWPLw+3/Kfm30toyvW/5P0c9+OqUNkcmQTcNTLtwrq7VSyQJ2UhKOUH+4GNrpSyOD9Z299zwyfsjN7neuVR2T7dTdP7KvytUlmSlMGKeMYCrzddP4pyXaZQa/hjDXdAgMBAAECgYBxrx6A/gqpKphd8ivNJCsohXozbN639lyDYOIR/okbxfuYTklXZj5SatCxO/hFnfOmzkHAd8yLLHxgNqBl7JJ1CozevSZJd9vbPJLfsjd90aAfWhnY6X9pK1rmR1B60Bo+ruShZ3Nc/fwsWRI31SpglCmhtU5lPA3/vpC+Z45eAQJBAPMuiVD/aB5V3ZmTwvD9QuK3Iiequ4NP5GLY+W/WQfs6mjadMPdA6BfHBJyKtlDtP9GMZETJwiw+ZSSi+UkZ3T0CQQDqEgeZFGsrZIEnk59yUOfb5ncYCvlyMn4ezsNGAYbR92jQ2+8mm6gYtsEQx9NqMgz+O65Dhz73b+bpclU6voUhAkBxIuNrtYehJV2Vh7MYHxuxEnZncf0PGGouurdBmrLTyO3aHsObJK1V8pdopPgxKk+Yk/JKNnuJzfWMV+4WXlwZAkEA2+UUPjtwctN4gjWtFTfsmilouK2WqjHFSiv0R1An2BkV3yC/CwYwZQdLWPhhJOLgUfa/P7FO9iWqSKWgbA+QAQJAJ0HbRPa3lN9wRxjZKAdgm3dx3lQNwrod9Pw+10xNr0PQh/4TDdbaJoPtzo/TyTEvBCoZVkIa7aGHy62TH4em2A==";
//		String date = "你好＄1�7";
//		String encode = encryptByPublicKey(date,publicKeyString);
//		System.out.println(encode);
//		String deCode = decryptByPrivateKey(encode,privateKeyString);
//		System.out.println(deCode);
//		
//		String appid ="abc";
//		String openid ="vvv";
//		String openkey ="ccc";
//		String token ="aa";
//		String timestamp ="0987654321";
//		String nonce ="1234";
//		
//		// 将参数信息放入list丄1�7
//	       List<String> data = new ArrayList<String>();
//	       data.add(appid!=null?appid:"");
//	       data.add(openid!=null?openid:"");
//	       data.add(openkey!=null?openkey:"");
//	       //双方约定好的token
//	       data.add(token!=null?token:"");
//	       data.add(timestamp!=null?timestamp:"");
//	       data.add(nonce!=null?nonce:"");
//	       Collections.sort(data);
//	       // 将六个参数拼接成丄1�7个字符串
//	       StringBuffer paicString= new StringBuffer();
//	       for(int i=0;i<data.size();i++)
//	       {
//	           paicString=paicString.append(data.get(i));
//	       }
//	       String signStr = paicString.toString();
//	       System.out.println(signStr);
		
		String prive_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN5ZqgFhHdnXH2OdLsgquZhoKzfysTkYFbX20AMuz5SLaMbWPLw+3/Kfm30toyvW/5P0c9+OqUNkcmQTcNTLtwrq7VSyQJ2UhKOUH+4GNrpSyOD9Z299zwyfsjN7neuVR2T7dTdP7KvytUlmSlMGKeMYCrzddP4pyXaZQa/hjDXdAgMBAAECgYBxrx6A/gqpKphd8ivNJCsohXozbN639lyDYOIR/okbxfuYTklXZj5SatCxO/hFnfOmzkHAd8yLLHxgNqBl7JJ1CozevSZJd9vbPJLfsjd90aAfWhnY6X9pK1rmR1B60Bo+ruShZ3Nc/fwsWRI31SpglCmhtU5lPA3/vpC+Z45eAQJBAPMuiVD/aB5V3ZmTwvD9QuK3Iiequ4NP5GLY+W/WQfs6mjadMPdA6BfHBJyKtlDtP9GMZETJwiw+ZSSi+UkZ3T0CQQDqEgeZFGsrZIEnk59yUOfb5ncYCvlyMn4ezsNGAYbR92jQ2+8mm6gYtsEQx9NqMgz+O65Dhz73b+bpclU6voUhAkBxIuNrtYehJV2Vh7MYHxuxEnZncf0PGGouurdBmrLTyO3aHsObJK1V8pdopPgxKk+Yk/JKNnuJzfWMV+4WXlwZAkEA2+UUPjtwctN4gjWtFTfsmilouK2WqjHFSiv0R1An2BkV3yC/CwYwZQdLWPhhJOLgUfa/P7FO9iWqSKWgbA+QAQJAJ0HbRPa3lN9wRxjZKAdgm3dx3lQNwrod9Pw+10xNr0PQh/4TDdbaJoPtzo/TyTEvBCoZVkIa7aGHy62TH4em2A==";
		String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDeWaoBYR3Z1x9jnS7IKrmYaCs38rE5GBW19tADLs+Ui2jG1jy8Pt/yn5t9LaMr1v+T9HPfjqlDZHJkE3DUy7cK6u1UskCdlISjlB/uBja6Usjg/Wdvfc8Mn7Ize53rlUdk+3U3T+yr8rVJZkpTBinjGAq83XT+Kcl2mUGv4Yw13QIDAQAB";
		String randomnum = Createnonce.nonce(10);
		String data = "{\"mobile\":\"13600010001\",\"key\":\"" + randomnum +"\"}";
		System.out.println("data = " + data);
		String parma = RSAHelper.encryptByPublicKey(data, public_key);
		
		System.out.println("parma = " + parma);
		System.out.println(">>>>>>>>>>>>." +  RSAHelper.decryptByPrivateKey(parma,prive_key));
	}
}
 
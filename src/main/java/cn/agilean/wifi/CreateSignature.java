package cn.agilean.wifi;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class CreateSignature {

	public static String createsignature(String... strArray) {
		String signature = "";

		List<String> data = new ArrayList();
		for (String str : strArray) {
			data.add(str != null ? str : "");
		}
		Collections.sort(data);

		StringBuffer paicString = new StringBuffer();
		for (int i = 0; i < data.size(); i++) {
			paicString = paicString.append(data.get(i));
		}
		try {
			MessageDigest md = null;
			md = MessageDigest.getInstance("SHA-1");

			byte[] digest = md.digest(paicString.toString().getBytes());

			signature = CreateSign.bytesToHexString(digest);
			//System.out.println("signature = " + signature);
			return signature;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void aaaa(){
		String appId = "AIQIYI";
        String nonce = "8412";
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String code = "Aqy1-gVcC-eYFU-ukGt";
        //F007-tMzf-ecdq-SEEX 
        //F007-7Ex5-PbQf-QKEy 
        String phoneNumber = "13100000001";
        String appKey = "279ebe019f7a12e4badb001b217763b0";//密钥
        
           String[] sortArr = {appId,appKey,nonce,String.valueOf(timestamp),code,phoneNumber};
           Arrays.sort(sortArr);
           String sha1msg = StringUtils.join(sortArr);
           //SHA加密
           String newsignatrue = SHAsecurity.encryptSHA(sha1msg, "SHA1");
           System.out.println(newsignatrue);

	}

	public static void main(String args[]) {
		CreateSignature aa = new CreateSignature();
//		aa.createsignature("AIQIYI","8412","1439285815445","Aqy1-gVcC-eYFU-ukGt","13100000001","279ebe019f7a12e4badb001b217763b0");
		CreateSignature.createsignature("15028376254","GSrr6vp3BDx4T7y42791hR66Yq65n0Z2D","android","1446549740110","923561052435819507877974","register","87b9eec990b74dd3bfbb58cf180a1974");
	}

}

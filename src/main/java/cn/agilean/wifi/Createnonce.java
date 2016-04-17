package cn.agilean.wifi;

import java.util.Random;

public class Createnonce {
	
	public static String nonce(int length) {
        Random r = new Random();
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            s.append(r.nextInt(10));
        }
        return s.toString();
    }
	
	public static String randoms(){
		Random random = new Random();
		String mobile = "158" + random.nextInt(100000000);
		return mobile;
	}
	
	public static void main(String[] args){
		Createnonce aa = new Createnonce();
//		System.out.println("nonce = " + aa.nonce(8));
		System.out.println("mobile = " + Createnonce.randoms());
	}


}

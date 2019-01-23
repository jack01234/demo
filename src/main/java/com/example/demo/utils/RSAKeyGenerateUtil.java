package com.example.demo.utils;

import org.bouncycastle.util.encoders.Base64;

import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wm.li on 2017/01/17.
 */
public class RSAKeyGenerateUtil {

	public static String ALGORITHM="RSA";

	public  static PrivateKey pkey ;

	public	static PublicKey pubkey ;

	public static String PUBLIC_KEY="PublicKey";
	public static String PRIVATE_KEY="PrivateKey";

	public static Map<String,String> genKey() {

		KeyPairGenerator kpg = null;
		try {
			kpg = KeyPairGenerator.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		kpg.initialize(1024);
		KeyPair kep = kpg.generateKeyPair();
		Provider p  = kpg.getProvider();
		System.out.println(p.getName());
		pkey = kep.getPrivate();
		pubkey = kep.getPublic();
		System.out.println("���ɵĹ�Կ:"+new String(Base64.encode(pubkey.getEncoded())));
		System.out.println("====================================");
		System.out.println("���ɵ�˽Կ:"+new String(Base64.encode(pkey.getEncoded())));

		Map<String,String> param=new HashMap<String,String>();
		param.put(PUBLIC_KEY, new String(Base64.encode(pubkey.getEncoded())));
		param.put(PRIVATE_KEY, new String(Base64.encode(pkey.getEncoded())));

		return param;
	}


	public static void main(String[] args) throws Exception{
		genKey();
	}

}

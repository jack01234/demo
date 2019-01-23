package com.example.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 
 * @author yuanqy
 *
 */
@SuppressWarnings("restriction")
public class SecurityUtil {
	/***
	 * MD5 ����
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		if (str == null)
			return null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte[] digest = md5.digest();
			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				strTemp = Integer.toHexString((digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
			return hexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// ==Base64�ӽ���==================================================================
	/**
	 * Base64����
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String Base64Encode(String str) throws UnsupportedEncodingException {
		return new BASE64Encoder().encode(str.getBytes("UTF-8"));
	}

	/**
	 * ����
	 * 
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static String Base64Decode(String str) throws UnsupportedEncodingException, IOException {
		str = str.replaceAll(" ", "+");
		return new String(new BASE64Decoder().decodeBuffer(str), "UTF-8");
	}

	// ==Aes�ӽ���==================================================================
	/**
	 * aes����-128λ
	 * 
	 * @param encryptContent
	 *            (16����) ����
	 * @param password
	 *            ��Կ
	 * @return ԭ��
	 */
	public static String AesDecrypt(String encryptContent, String password) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			keyGen.init(128, secureRandom);
			SecretKey secretKey = keyGen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(hex2Bytes(encryptContent)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * aes����-128λ
	 * 
	 * @param content
	 *            ԭ��
	 * @param password
	 *            ��Կ
	 * @return 16��������
	 */
	public static String AesEncrypt(String content, String password) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			keyGen.init(128, secureRandom);
			SecretKey secretKey = keyGen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return byte2Hex(cipher.doFinal(content.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��byte[] ת�����ַ���
	 */
	public static String byte2Hex(byte[] srcBytes) {
		StringBuilder hexRetSB = new StringBuilder();
		for (byte b : srcBytes) {
			String hexString = Integer.toHexString(0x00ff & b);
			hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
		}
		return hexRetSB.toString();
	}

	/**
	 * ��16�����ַ���תΪת�����ַ���
	 */
	public static byte[] hex2Bytes(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return sourceBytes;
	}

	// ==3Des�ӽ���==================================================================
	/**
	 * DES����
	 * 
	 * @throws Exception
	 */
	public static String desEncrypt(String source, String desKey) throws Exception {
		try {
			// ��ԭʼ�ܳ����ݴ���DESKeySpec����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			// Cipher����ʵ����ɼ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			// ���ڣ���ȡ���ݲ�����
			byte[] destBytes = cipher.doFinal(source.getBytes());
			StringBuilder hexRetSB = new StringBuilder();
			for (byte b : destBytes) {
				String hexString = Integer.toHexString(0x00ff & b);
				hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
			}
			return hexRetSB.toString();
		} catch (Exception e) {
			throw new Exception("DES���ܷ�������", e);
		}
	}

	/**
	 * DES����
	 * 
	 * @param source
	 * @param desKey
	 * @return
	 * @throws Exception 
	 */
	public static String desDecrypt(String source, String desKey) throws Exception {
		// ��������
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return desDecrypt(sourceBytes, desKey);
	}

	/**
	 * DES����
	 * 
	 * @param source
	 * @param desKey
	 * @return
	 * @throws Exception
	 */
	public static String desDecrypt(byte[] source, String desKey) throws Exception {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.DECRYPT_MODE, securekey);
			// ���ڣ���ȡ���ݲ�����
			byte[] destBytes = cipher.doFinal(source);
			return new String(destBytes);
		} catch (Exception e) {
			throw new Exception("DES���ܷ�������", e);
		}
	}

	/**
	 * 3DES����
	 * 
	 * @param keybyte
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static byte[] threeDesEncrypt(byte[] keybyte, byte[] src) throws Exception {
		try {
			// ������Կ
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// ����
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new Exception("3DES���ܷ�������", e);
		}
	}

	/**
	 * 3DES����
	 * 
	 * @param keybyte
	 *            ������Կ������Ϊ24�ֽ�
	 * @param src
	 *            ���ܺ�Ļ�����
	 * @return
	 * @throws Exception
	 */
	public static byte[] threeDesDecrypt(byte[] keybyte, byte[] src) throws Exception {
		try {
			// ������Կ
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// ����
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new Exception("3DES���ܷ�������", e);
		}
	}

	/**
	 * 3DES����
	 * 
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String threeDesEncrypt(String src,String key) throws Exception {
		return byte2Hex(threeDesEncrypt(key.getBytes(), src.getBytes()));
	}

	/**
	 * 3DES����
	 * 
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String threeDesDecrypt(String src,String key) throws Exception {
		return new String(threeDesDecrypt(key.getBytes(), hex2Bytes(src)));
	}

	public static void main(String[] args) throws Exception {
		String str = "���ݼ��ܵĻ������̾��Ƕ�ԭ��Ϊ���ĵ��ļ������ݰ�ĳ���㷨���д���ʹ���Ϊ���ɶ���һ�δ��룬ͨ����Ϊ�����ġ���"
				+ "ʹ��ֻ����������Ӧ����Կ֮�������ʾ���������ݣ�ͨ��������;�����ﵽ�������ݲ����Ƿ�����ȡ���Ķ���Ŀ�ġ� "
				+ "�ù��̵������Ϊ���ܣ������ñ�����Ϣת��Ϊ��ԭ�����ݵĹ��̡�";
		String PWD = "SecurityUtil.PWD";
		System.out.println("ԭ��:[" + str.length() + "]" + str);
		System.out.println("==MD5===============");
		System.out.println(MD5(str));
		System.out.println("==Base64============");
		String strBase64 = Base64Encode(str);
		System.out.println("����:[" + strBase64.length() + "]" + strBase64);
		System.out.println("����:" + Base64Decode(strBase64));
		System.out.println("==Aes============");
		String strAes = AesEncrypt(str, PWD);
		System.out.println("����:[" + strAes.length() + "]" + strAes);
		System.out.println("����:" + AesDecrypt(strAes, PWD));
		System.out.println("==Des==============");
		String strDes = desEncrypt(str, PWD);
		System.out.println("����:[" + strDes.length() + "]" + strDes);
		System.out.println("����:" + desDecrypt(strDes, PWD));
		System.out.println("==3Des==============");
		String str3Des = threeDesEncrypt(str, PWD);
		System.out.println("����:[" + str3Des.length() + "]" + str3Des);
		System.out.println("����:" + threeDesDecrypt(str3Des, PWD));
	}

 
}

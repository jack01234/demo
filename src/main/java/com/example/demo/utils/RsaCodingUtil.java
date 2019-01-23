package com.example.demo.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <b>Rsa鍔犺В瀵嗗伐鍏�</b><br>
 * <br>
 * 鍏挜閲囩敤X509,Cer鏍煎紡鐨�<br>
 * 绉侀挜閲囩敤PKCS12鍔犲瘑鏂瑰紡鐨凱FX绉侀挜鏂囦欢<br>
 * 鍔犲瘑绠楁硶涓�1024浣嶇殑RSA锛屽～鍏呯畻娉曚负PKCS1Padding<br>
 * 
 * @author 琛岃��
 * @version 4.1.0
 */
public final class RsaCodingUtil {

	// ======================================================================================
	// 鍏挜鍔犲瘑绉侀挜瑙ｅ瘑娈�
	// ======================================================================================

	/**
	 * 鎸囧畾Cer鍏挜璺緞鍔犲瘑
	 * 
	 * @param src
	 * @param pubCerPath
	 * @return hex涓�
	 */
	public static String encryptByPubCerFile(String src, String pubCerPath) {

		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubCerPath);
		if (publicKey == null) {
			return null;
		}
		return encryptByPublicKey(src, publicKey);
	}

	/**
	 * 鐢ㄥ叕閽ュ唴瀹瑰姞瀵�
	 * 
	 * @param src
	 * @param pubKeyText
	 * @return hex涓�
	 */
	public static String encryptByPubCerText(String src, String pubKeyText) {
		PublicKey publicKey = RsaReadUtil.getPublicKeyByText(pubKeyText);
		if (publicKey == null) {
			return null;
		}
		return encryptByPublicKey(src, publicKey);
	}

	/**
	 * 鍏挜鍔犲瘑杩斿洖
	 */
	public static String encryptByPublicKey(String src, PublicKey publicKey) {
		byte[] destBytes = rsaByPublicKey(src.getBytes(), publicKey, Cipher.ENCRYPT_MODE);

		if (destBytes == null) {
			return null;
		}

		return FormatUtil.byte2Hex(destBytes);

	}

	/**
	 * 鏍规嵁绉侀挜鏂囦欢瑙ｅ瘑
	 */
	public static String decryptByPriPfxFile(String src, String pfxPath, String priKeyPass) {
		if (FormatUtil.isEmpty(src) || FormatUtil.isEmpty(pfxPath)) {
			return null;
		}
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(pfxPath, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return decryptByPrivateKey(src, privateKey);
	}

	/**
	 * 鏍规嵁绉侀挜鏂囦欢娴佽В瀵�
	 */
	public static String decryptByPriPfxStream(String src, byte[] pfxBytes, String priKeyPass) {
		if (FormatUtil.isEmpty(src)) {
			return null;
		}
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyByStream(pfxBytes, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return decryptByPrivateKey(src, privateKey);
	}

	/**
	 * 绉侀挜瑙ｅ瘑
	 */
	public static String decryptByPrivateKey(String src, PrivateKey privateKey) {
		if (FormatUtil.isEmpty(src)) {
			return null;
		}
		try {
			byte[] destBytes = rsaByPrivateKey(FormatUtil.hex2Bytes(src), privateKey, Cipher.DECRYPT_MODE);
			if (destBytes == null) {
				return null;
			}
			return new String(destBytes, RsaConst.ENCODE);
		} catch (UnsupportedEncodingException e) {
			// //log.error("瑙ｅ瘑鍐呭涓嶆槸姝ｇ‘鐨刄TF8鏍煎紡:", e);
		} catch (Exception e) {
			// //log.error("瑙ｅ瘑鍐呭寮傚父", e);
		}

		return null;
	}

	// ======================================================================================
	// 绉侀挜鍔犲瘑鍏挜瑙ｅ瘑
	// ======================================================================================

	/**
	 * 鏍规嵁绉侀挜鏂囦欢鍔犲瘑
	 */
	public static String encryptByPriPfxFile(String src, String pfxPath, String priKeyPass) {

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(pfxPath, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return encryptByPrivateKey(src, privateKey);
	}

	/**
	 * 鏍规嵁绉侀挜鏂囦欢娴佸姞瀵�
	 */
	public static String encryptByPriPfxStream(String src, byte[] pfxBytes, String priKeyPass) {
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyByStream(pfxBytes, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return encryptByPrivateKey(src, privateKey);
	}

	/**
	 * 鏍规嵁绉侀挜鍔犲瘑
	 */
	public static String encryptByPrivateKey(String src, PrivateKey privateKey) {

		byte[] destBytes = rsaByPrivateKey(src.getBytes(), privateKey, Cipher.ENCRYPT_MODE);

		if (destBytes == null) {
			return null;
		}
		return FormatUtil.byte2Hex(destBytes);

	}

	/**
	 * 鎸囧畾Cer鍏挜璺緞瑙ｅ瘑
	 */
	public static String decryptByPubCerFile(String src, String pubCerPath) {
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubCerPath);
		if (publicKey == null) {
			return null;
		}
		return decryptByPublicKey(src, publicKey);
	}

	/**
	 * 鏍规嵁鍏挜鏂囨湰瑙ｅ瘑
	 */
	public static String decryptByPubCerText(String src, String pubKeyText) {
		PublicKey publicKey = RsaReadUtil.getPublicKeyByText(pubKeyText);
		if (publicKey == null) {
			return null;
		}
		return decryptByPublicKey(src, publicKey);
	}

	/**
	 * 鏍规嵁鍏挜瑙ｅ瘑
	 */
	public static String decryptByPublicKey(String src, PublicKey publicKey) {

		try {
			byte[] destBytes = rsaByPublicKey(FormatUtil.hex2Bytes(src), publicKey, Cipher.DECRYPT_MODE);
			if (destBytes == null) {
				return null;
			}
			return new String(destBytes, RsaConst.ENCODE);
		} catch (UnsupportedEncodingException e) {
			// //log.error("瑙ｅ瘑鍐呭涓嶆槸姝ｇ‘鐨刄TF8鏍煎紡:", e);
		}
		return null;
	}

	// ======================================================================================
	// 鍏閽ョ畻娉�
	// ======================================================================================
	/**
	 * 鍏挜绠楁硶
	 * 
	 * @param srcData
	 *            婧愬瓧鑺�
	 * @param publicKey
	 *            鍏挜
	 * @param mode
	 *            鍔犲瘑 OR 瑙ｅ瘑
	 * @return
	 */
	public static byte[] rsaByPublicKey(byte[] srcData, PublicKey publicKey, int mode) {
		try {
			Cipher cipher = Cipher.getInstance(RsaConst.RSA_CHIPER);
			cipher.init(mode, publicKey);
			// 鍒嗘鍔犲瘑
			int blockSize = (mode == Cipher.ENCRYPT_MODE) ? RsaConst.ENCRYPT_KEYSIZE : RsaConst.DECRYPT_KEYSIZE;
			byte[] encryptedData = null;
			for (int i = 0; i < srcData.length; i += blockSize) {
				// 娉ㄦ剰瑕佷娇鐢�2鐨勫�嶆暟锛屽惁鍒欎細鍑虹幇鍔犲瘑鍚庣殑鍐呭鍐嶈В瀵嗘椂涓轰贡鐮�
				byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
				encryptedData = addAll(encryptedData, doFinal);
			}
			return encryptedData;

		} catch (NoSuchAlgorithmException e) {
			// //log.error("鍏挜绠楁硶-涓嶅瓨鍦ㄧ殑瑙ｅ瘑绠楁硶:", e);
		} catch (NoSuchPaddingException e) {
			// //log.error("鍏挜绠楁硶-鏃犳晥鐨勮ˉ浣嶇畻娉�:", e);
		} catch (IllegalBlockSizeException e) {
			// //log.error("鍏挜绠楁硶-鏃犳晥鐨勫潡澶у皬:", e);
		} catch (BadPaddingException e) {
			// //log.error("鍏挜绠楁硶-琛ヤ綅绠楁硶寮傚父:", e);
		} catch (InvalidKeyException e) {
			// //log.error("鍏挜绠楁硶-鏃犳晥鐨勭閽�:", e);
		}
		return null;
	}

	/**
	 * 绉侀挜绠楁硶
	 * 
	 * @param srcData
	 *            婧愬瓧鑺�
	 * @param privateKey
	 *            绉侀挜
	 * @param mode
	 *            鍔犲瘑 OR 瑙ｅ瘑
	 * @return
	 */
	public static byte[] rsaByPrivateKey(byte[] srcData, PrivateKey privateKey, int mode) {
		try {
			Cipher cipher = Cipher.getInstance(RsaConst.RSA_CHIPER);
			cipher.init(mode, privateKey);
			// 鍒嗘鍔犲瘑
			int blockSize = (mode == Cipher.ENCRYPT_MODE) ? RsaConst.ENCRYPT_KEYSIZE : RsaConst.DECRYPT_KEYSIZE;
			byte[] decryptData = null;
			for (int i = 0; i < srcData.length; i += blockSize) {
				byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
				decryptData = addAll(decryptData, doFinal);
			}
			return decryptData;
		} catch (NoSuchAlgorithmException e) {
			// //log.error("绉侀挜绠楁硶-涓嶅瓨鍦ㄧ殑瑙ｅ瘑绠楁硶:", e);
		} catch (NoSuchPaddingException e) {
			// log.error("绉侀挜绠楁硶-鏃犳晥鐨勮ˉ浣嶇畻娉�:", e);
		} catch (IllegalBlockSizeException e) {
			// log.error("绉侀挜绠楁硶-鏃犳晥鐨勫潡澶у皬:", e);
		} catch (BadPaddingException e) {
			// log.error("绉侀挜绠楁硶-琛ヤ綅绠楁硶寮傚父:", e);
		} catch (InvalidKeyException e) {
			// log.error("绉侀挜绠楁硶-鏃犳晥鐨勭閽�:", e);
		}
		return null;
	}

	// /////////////==========================
	public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		}
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		}
		if (endIndexExclusive > array.length) {
			endIndexExclusive = array.length;
		}
		int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return new byte[0];
		}

		byte[] subarray = new byte[newSize];
		System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static byte[] addAll(byte[] array1, byte[] array2) {
		if (array1 == null) {
			return clone(array2);
		} else if (array2 == null) {
			return clone(array1);
		}
		byte[] joinedArray = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static byte[] clone(byte[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}
}

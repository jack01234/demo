package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

//import com.jweb.//log.Logger;

/**
 * <b>鍏閽ヨ鍙栧伐鍏�</b><br>
 * <br>
 * 
 * @author 琛岃��
 * @version 4.1.0
 */
@Slf4j
public final class RsaReadUtil {

	/**
	 * 鏍规嵁Cer鏂囦欢璇诲彇鍏挜
	 * 
	 * @param pubCerPath
	 * @return
	 */
	public static PublicKey getPublicKeyFromFile(String pubCerPath) {
		FileInputStream pubKeyStream = null;
		try {
			pubKeyStream = new FileInputStream(pubCerPath);
			byte[] reads = new byte[pubKeyStream.available()];
			pubKeyStream.read(reads);
			return getPublicKeyByText(new String(reads));
		} catch (FileNotFoundException e) {
			// //log.error("鍏挜鏂囦欢涓嶅瓨鍦�:", e);
		} catch (IOException e) {
			// log.error("鍏挜鏂囦欢璇诲彇澶辫触:", e);
		} finally {
			if (pubKeyStream != null) {
				try {
					pubKeyStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 鏍规嵁鍏挜Cer鏂囨湰涓茶鍙栧叕閽�
	 * 
	 * @param pubKeyText
	 * @return
	 */
	public static PublicKey getPublicKeyByText(String pubKeyText) {
		try {
			CertificateFactory certificateFactory = CertificateFactory.getInstance(RsaConst.KEY_X509);
			BufferedReader br = new BufferedReader(new StringReader(pubKeyText));
			String line = null;
			StringBuilder keyBuffer = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("-")) {
					keyBuffer.append(line);
				}
			}
			Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(keyBuffer.toString())));
			return certificate.getPublicKey();
		} catch (Exception e) {
			// log.error("瑙ｆ瀽鍏挜鍐呭澶辫触:", e);
		}
		return null;
	}

	/**
	 * 鏍规嵁绉侀挜璺緞璇诲彇绉侀挜
	 * 
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	public static PrivateKey getPrivateKeyFromFile(String pfxPath, String priKeyPass) {
		InputStream priKeyStream = null;
		try {
			priKeyStream = new FileInputStream(pfxPath);
			byte[] reads = new byte[priKeyStream.available()];
			priKeyStream.read(reads);
			return getPrivateKeyByStream(reads, priKeyPass);
		} catch (Exception e) {
//			 log.error("瑙ｆ瀽鏂囦欢锛岃鍙栫閽ュけ璐�:", e);
			e.printStackTrace();
		} finally {
			if (priKeyStream != null) {
				try {
					priKeyStream.close();
				} catch (Exception e) {
					//
				}
			}
		}
		return null;
	}

	/**
	 * 鏍规嵁PFX绉侀挜瀛楄妭娴佽鍙栫閽�
	 * 
	 * @param pfxBytes
	 * @param priKeyPass
	 * @return
	 */
	public static PrivateKey getPrivateKeyByStream(byte[] pfxBytes, String priKeyPass) {
		try {
			KeyStore ks = KeyStore.getInstance(RsaConst.KEY_PKCS12);
			char[] charPriKeyPass = priKeyPass.toCharArray();
			ks.load(new ByteArrayInputStream(pfxBytes), charPriKeyPass);
			Enumeration<String> aliasEnum = ks.aliases();
			String keyAlias = null;
			if (aliasEnum.hasMoreElements()) {
				keyAlias = aliasEnum.nextElement();
			}
			return (PrivateKey) ks.getKey(keyAlias, charPriKeyPass);
		} catch (IOException e) {
			// 鍔犲瘑澶辫触
			// log.error("瑙ｆ瀽鏂囦欢锛岃鍙栫閽ュけ璐�:", e);
		} catch (KeyStoreException e) {
			// log.error("绉侀挜瀛樺偍寮傚父:", e);
		} catch (NoSuchAlgorithmException e) {
			// log.error("涓嶅瓨鍦ㄧ殑瑙ｅ瘑绠楁硶:", e);
		} catch (CertificateException e) {
			// log.error("璇佷功寮傚父:", e);
		} catch (UnrecoverableKeyException e) {
			// log.error("涓嶅彲鎭㈠鐨勭閽ュ紓甯�", e);
		}
		return null;
	}

	/**
	 * 解析证书文件
	 *
	 * @param path 路径
	 * @return 结果
	 */
	public static String getCertId(String path){
		InputStream inStream = null;
		String certId = null;
		try {
			File file = new File(path);
			inStream = new FileInputStream(file);
			// 创建X509工厂类
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			// 创建证书对象
			X509Certificate oCert = (X509Certificate) cf
					.generateCertificate(inStream);
			certId = oCert.getSerialNumber().toString();
		} catch (Exception e) {
			log.error("fail execute getCertId {}",e);
		}finally {
			release(inStream);
		}
		return certId;
	}

	/**
	 * 输入流关闭
	 *
	 * @param inStream 输入流
	 */
	private static void release(InputStream inStream){
		try {
			if (null != inStream){
				inStream.close();
			}
		} catch (Exception e) {
			log.error("fail execute release {}",e);
		}
	}

	public static void main(String[] args) {
		String certId = getCertId("E://设备指纹相关/证书/xinyantest_pub.cer");
		System.out.println(certId);
	}
}

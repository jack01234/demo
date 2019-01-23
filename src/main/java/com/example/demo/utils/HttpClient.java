package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

public class HttpClient {
	/**
	 * Ŀ���ַ
	 */
	private URL url;

	/**
	 * ͨ�����ӳ�ʱʱ��
	 */
	private int connectionTimeout;

	/**
	 * ͨ�Ŷ���ʱʱ��
	 */
	private int readTimeOut;

	/**
	 * ͨ�Ž��
	 */
	private String result;

	/**
	 * ��ȡͨ�Ž��
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * ����ͨ�Ž��
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * ���캯��
	 * @param url Ŀ���ַ
	 * @param connectionTimeout HTTP���ӳ�ʱʱ��
	 * @param readTimeOut HTTP��д��ʱʱ��
	 */
	public HttpClient(String url, int connectionTimeout, int readTimeOut) {
		try {
			this.url = new URL(url);
			this.connectionTimeout = connectionTimeout;
			this.readTimeOut = readTimeOut;
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ������Ϣ�������
	 * @param data
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public int send(Map<String, String> data, String encoding) throws Exception {
		try {
			HttpURLConnection httpURLConnection = createConnection(encoding);
			if (null == httpURLConnection) {
				throw new Exception("Create httpURLConnection Failure");
			}
			String sendData = this.getRequestParamString(data, encoding);
			//		String sendData = JSONObject.toJSONString(data);
			System.out.println("������:[" + sendData + "]");
			this.requestServer(httpURLConnection, sendData,
					encoding);
			this.result = this.response(httpURLConnection, encoding);
			System.out.println("Response message:[" + result + "]");
			return httpURLConnection.getResponseCode();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * ������Ϣ������� GET��ʽ
	 * @param data
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public int sendGet(String encoding) throws Exception {
		try {
			HttpURLConnection httpURLConnection = createConnectionGet(encoding);
			if(null == httpURLConnection){
				throw new Exception("��������ʧ��");
			}
			this.result = this.response(httpURLConnection, encoding);
			System.out.println("ͬ�����ر���:[" + result + "]");
			return httpURLConnection.getResponseCode();
		} catch (Exception e) {
			throw e;
		}
	}

	
	/**
	 * HTTP Post������Ϣ
	 *
	 * @param connection
	 * @param message
	 * @throws IOException
	 */
	private void requestServer(final URLConnection connection, String message, String encoder)
			throws Exception {
		PrintStream out = null;
		try {
			connection.connect();
			out = new PrintStream(connection.getOutputStream(), false, encoder);
			out.print(message);
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**
	 * ��ʾResponse��Ϣ
	 *
	 * @param connection
	 * @param CharsetName
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private String response(final HttpURLConnection connection, String encoding)
			throws URISyntaxException, IOException, Exception {
		InputStream in = null;
		StringBuilder sb = new StringBuilder(1024);
		StringBuffer resultBuffer = new StringBuffer(); 
		BufferedReader br = null;
		try {
			if (200 == connection.getResponseCode()) {
				in = connection.getInputStream();
				br = new BufferedReader(new InputStreamReader(in,encoding)); 
				String tempLine = null; 
				while ((tempLine = br.readLine()) != null) {  
					resultBuffer.append(tempLine);  
				}  
				sb.append(new String(read(in), encoding));
				System.out.println("=========="+resultBuffer.toString());
			} else {
				in = connection.getErrorStream();
				sb.append(new String(read(in), encoding));
			}
			System.out.println("HTTP Return Status-Code:["
					+ connection.getResponseCode() + "]");
			return resultBuffer.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != br) {
				br.close();
			}
			if (null != in) {
				in.close();
			}
			if (null != connection) {
				connection.disconnect();
			}
		}
	}
	
	public static byte[] read(InputStream in) throws IOException {
		byte[] buf = new byte[1024];
		int length = 0;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		while ((length = in.read(buf, 0, buf.length)) > 0) {
			bout.write(buf, 0, length);
		}
		bout.flush();
		return bout.toByteArray();
	}
	
	/**
	 * ��������
	 *
	 * @return
	 * @throws ProtocolException
	 */
	private HttpURLConnection createConnection(String encoding) throws ProtocolException {
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage()+e);
			return null;
		}
		httpURLConnection.setConnectTimeout(this.connectionTimeout);// ���ӳ�ʱʱ��
		httpURLConnection.setReadTimeout(this.readTimeOut);// ��ȡ�����ʱʱ��
		httpURLConnection.setDoInput(true); // �ɶ�
		httpURLConnection.setDoOutput(true); // ��д
		httpURLConnection.setUseCaches(false);// ȡ������
		//application/x-www-form-urlencoded
		httpURLConnection.setRequestProperty("Content-type",
				"application/x-www-form-urlencoded;charset=" + encoding);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.addRequestProperty("X-HPTerminalUserID", "123456789");
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("contentType", "utf-8");
		if ("https".equalsIgnoreCase(url.getProtocol())) {
			HttpsURLConnection husn = (HttpsURLConnection) httpURLConnection;
			//�Ƿ���֤https֤�飬���Ի���������false�����������������ȳ���true��������false
//			if(!SDKConfig.getConfig().isIfValidateRemoteCert()){
//				husn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
//				husn.setHostnameVerifier(new TrustAnyHostnameVerifier());//������ڷ�����֤�����⵼��HTTPS�޷����ʵ����
//			}
			return husn;
		}
		return httpURLConnection;
	}

	/**
	 * ��������
	 *
	 * @return
	 * @throws ProtocolException
	 */
	private HttpURLConnection createConnectionGet(String encoding) throws ProtocolException {
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage()+e);
			return null;
		}
		httpURLConnection.setConnectTimeout(this.connectionTimeout);// ���ӳ�ʱʱ��
		httpURLConnection.setReadTimeout(this.readTimeOut);// ��ȡ�����ʱʱ��
		httpURLConnection.setUseCaches(false);// ȡ������
		httpURLConnection.setRequestProperty("Content-type",
				"application/x-www-form-urlencoded;charset=" + encoding);
		httpURLConnection.setRequestMethod("GET");
		if ("https".equalsIgnoreCase(url.getProtocol())) {
			HttpsURLConnection husn = (HttpsURLConnection) httpURLConnection;
			//�Ƿ���֤https֤�飬���Ի���������false�����������������ȳ���true��������false
//			if(false){
//				husn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
//				husn.setHostnameVerifier(new TrustAnyHostnameVerifier());//������ڷ�����֤�����⵼��HTTPS�޷����ʵ����
//			}
			return husn;
		}
		return httpURLConnection;
	}
	
	/**
	 * ��Map�洢�Ķ���ת��Ϊkey=value&key=value���ַ�
	 *
	 * @param requestParam
	 * @param coder
	 * @return
	 */
	private String getRequestParamString(Map<String, String> requestParam, String coder) {
		if (null == coder || "".equals(coder)) {
			coder = "UTF-8";
		}
		StringBuffer sf = new StringBuffer("");
		String reqstr = "";
		if (null != requestParam && 0 != requestParam.size()) {
			for (Entry<String, String> en : requestParam.entrySet()) {
				try {
					sf.append(en.getKey()
							+ "="
							+ (null == en.getValue() || "".equals(en.getValue()) ? "" : URLEncoder
									.encode(en.getValue(), coder)) + "&");
				} catch (UnsupportedEncodingException e) {
					System.out.println(e.getMessage()+e);
					return "";
				}
			}
			reqstr = sf.substring(0, sf.length() - 1);
		}
		System.out.println("Request Message:[" + reqstr + "]");
		return reqstr;
	}

}

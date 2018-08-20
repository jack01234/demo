package com.example.demo.utils;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * http请求工具类
 *
 * @author thank_wd
 */
@Slf4j
public class ApiPostUtil {

    private URL url;

    private int connectionTimeout;

    private int readTimeOut;

    private String result;

    public ApiPostUtil(String url, int connectionTimeout, int readTimeOut) {
        try {
            this.url = new URL(url);
            this.connectionTimeout = connectionTimeout;
            this.readTimeOut = readTimeOut;
        } catch (MalformedURLException e) {
            log.error("error ApiPostUtil 初始化连接错误：{}", Throwables.getStackTraceAsString(e));
        }
    }

    /**
     * GET、POST请求
     * @param requestMethod  GET、POST
     * @param encoding  UTF-8、GBK编码格式
     * @throws Exception 异常
     */
    public String sendHttps(String message,String requestMethod,String encoding) throws Exception{
        HttpsURLConnection httpsURLConnection = createHttpsConnection(requestMethod,encoding);
        if(null == httpsURLConnection){
            throw new Exception("error");
        }
        this.request(httpsURLConnection, message,  encoding);
        this.result = this.response(httpsURLConnection, encoding);
        return result;
    }

    /**
     * GET、POST请求
     * @param requestMethod  GET、POST
     * @param encoding  UTF-8、GBK编码格式
     * @throws Exception 异常
     */
    public String sendHttp(String message,String requestMethod,String encoding) throws Exception{
        HttpURLConnection httpURLConnection = createHttpConnection(requestMethod,encoding);
        if(null == httpURLConnection){
            throw new Exception("error");
        }
        this.request(httpURLConnection, message,  encoding);
        this.result = this.response(httpURLConnection, encoding);
        return result;
    }

    /**
     * 发送请求
     * @param connection 连接
     * @param message   请求报文
     * @param encoder   编码格式
     * @throws Exception 异常
     */
    private void request(final URLConnection connection, String message, String encoder)
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
     * 响应结果处理
     * @param connection
     * @param encoding
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws Exception
     */
    private String response(final HttpURLConnection connection, String encoding)
            throws URISyntaxException, IOException, Exception {
        InputStream in = null;
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
                resultBuffer.append(new String(read(in), encoding));
            } else {
                in = connection.getErrorStream();
                resultBuffer.append(new String(read(in), encoding));
            }
            return resultBuffer.toString();
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

    /**
     * 文件流处理
     *
     * @param in 传入
     * @return 结果
     * @throws IOException io异常
     */
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
     * http连接
     *
     * @param requestMethod POST、GET
     * @param encoding UFT-8、GBK
     * @return 连接
     * @throws ProtocolException 异常
     */
    private HttpURLConnection createHttpConnection(String requestMethod,String encoding) throws ProtocolException {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println(e.getMessage()+e);
            return null;
        }
        httpURLConnection.setConnectTimeout(this.connectionTimeout);
        httpURLConnection.setReadTimeout(this.readTimeOut);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestMethod(requestMethod);
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        return httpURLConnection;
    }

    /**
     * https连接
     *
     * @param requestMethod POST、GET
     * @param encoding UFT-8、GBK
     * @return 连接
     * @throws ProtocolException 异常
     */
    private HttpsURLConnection createHttpsConnection(String requestMethod,String encoding) throws ProtocolException {
        HttpsURLConnection httpsURLConnection = null;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0],
                    new TrustManager[] { new DefaultTrustManager() },
                    new SecureRandom());
            SSLContext.setDefault(ctx);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(ctx.getSocketFactory());
        } catch (Exception e) {
            System.out.println(e.getMessage()+e);
            log.info("error 创建http连接出现异常：{}" , Throwables.getStackTraceAsString(e));
        }
        httpsURLConnection.setConnectTimeout(this.connectionTimeout);
        httpsURLConnection.setReadTimeout(this.readTimeOut);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setRequestProperty("Content-type", "application/json");
        httpsURLConnection.setRequestMethod(requestMethod);
        httpsURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpsURLConnection.setHostnameVerifier((hostname, session) -> true);
        return httpsURLConnection;
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1){
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1){
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
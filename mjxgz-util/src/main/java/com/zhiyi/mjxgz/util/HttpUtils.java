package com.zhiyi.mjxgz.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * HTTP服务工具类
 *
 * Created by WUXB on 2016-10-31.
 */
public class HttpUtils {

    /** 日志信息 */
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 发送 GET 请求，默认UTF-8编码格式
     *
     * @param url 请求地址
     * @param property 请求的 Headers 参数
     * @param parameters 请求参数
     * @return 远程响应结果
     */
    public static String sendHttpGetRequest(String url, Map<String, String> property, Map<String, Object> parameters) {
        String encode = "UTF-8";
        return sendHttpGetRequest(url, property, parameters, encode);
    }

    /**
     * 发送 GET 请求
     *
     * @param url 请求地址
     * @param property 请求的 Headers 参数
     * @param parameters 请求参数
     * @param encode 请求的编码格式
     * @return 远程响应结果
     */
    public static String sendHttpGetRequest(String url, Map<String, String> property, Map<String, Object> parameters, String encode) {
        encode = StringUtils.isEmpty(encode) ? "UTF-8" : encode;
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer params = new StringBuffer();// 编码之后的参数
        try {
            if (null != parameters && !parameters.isEmpty()) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    if (params.length() > 0) {
                        params.append("&");
                    }
                    if (null != entry.getValue()) {
                        params.append(entry.getKey()).append("=");
                        params.append(URLEncoder.encode(entry.getValue().toString(), encode));
                    }
                }
            }

            String fullUrl = url + "?" + params;
            logger.info("sendHttpGetRequest Full Url : {}", fullUrl);

            // 创建URL对象
            HttpURLConnection httpConn = (HttpURLConnection) new URL(fullUrl).openConnection();

            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            if (property != null && !property.isEmpty()) {
                for (Map.Entry<String, String> entry : property.entrySet()) {
                    if (null != entry.getValue()) {
                        httpConn.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
            }

            // 建立实际的连接
            httpConn.connect();

            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), encode));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result ;
    }

    public static String sendHttpPostRequest(String url, Map<String, String> property, String params, String encode) {
        String result = "";
        HttpURLConnection connection = null;
        try {
            URL connectionUrl = new URL(url);
            connection = (HttpURLConnection) connectionUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(3000); //链接超时时间
            connection.setDoInput(true); //从服务器获取数据
            connection.setDoOutput(true); //向服务器写入数据

            if (property != null && !property.isEmpty()) {
                for (Map.Entry<String, String> entry : property.entrySet()) {
                    if (null != entry.getValue()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
            }
            connection.connect();

            // 获得输出流，向服务器输出数据
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(params.getBytes());

            // 获得服务器响应的结果和状态码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // 获得输入流，从服务器端获得数据
                result = changeInputStream(connection.getInputStream(), encode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                connection.disconnect();
            }
        }
        return result;
    }

    /**
     * 把从输入流InputStream按指定编码格式encode变成字符串String
     *
     * @param inputStream
     * @param encode
     * @return
     */
    private static String changeInputStream(InputStream inputStream, String encode) {

        // ByteArrayOutputStream 一般叫做内存流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len;
        String result = "";
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    byteArrayOutputStream.write(data, 0, len);
                }
                result = new String(byteArrayOutputStream.toByteArray(), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

package com.zhiyi.mjxgz.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	 private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	 /**
	  * 下载文件
	  * @param response
	  * @param file
	  * @param fileName
	  * @return
	  */
	 public static String downloadFile(HttpServletResponse response,File file,String fileName){
	    	try {
	    	        if (fileName != null) {
	    	            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
	    	            if (file.exists()) {
	    	                response.setContentType("application/force-download");// 设置强制下载不打开
	    	                response.addHeader("Content-Disposition",
	    	                        "attachment;fileName=" +  fileName);// 设置文件名
	    	                byte[] buffer = new byte[1024];
	    	                FileInputStream fis = null;
	    	                BufferedInputStream bis = null;
	    	                try {
	    	                    fis = new FileInputStream(file);
	    	                    bis = new BufferedInputStream(fis);
	    	                    OutputStream os = response.getOutputStream();
	    	                    int i = bis.read(buffer);
	    	                    while (i != -1) {
	    	                        os.write(buffer, 0, i);
	    	                        i = bis.read(buffer);
	    	                    }
	    	                    System.out.println("success");
	    	                } catch (Exception e) {
	    	                    e.printStackTrace();
	    	                    logger.error("-----downloadFile---error："+e.getMessage(),e);
	    	                } finally {
	    	                    if (bis != null) {
	    	                        try {
	    	                            bis.close();
	    	                        } catch (IOException e) {
	    	                            e.printStackTrace();
	    	                        }
	    	                    }
	    	                    if (fis != null) {
	    	                        try {
	    	                            fis.close();
	    	                        } catch (IOException e) {
	    	                            e.printStackTrace();
	    	                        }
	    	                    }
	    	                }
	    	            }
	    	        }
			} catch (Exception e) {
				logger.error("-----downloadFile---error："+e.getMessage(),e);
			}
	        return null;
	    }
	 
	 public static String downloadExcelFile(HttpServletResponse response,File file,String fileName){
	    	try {
	    	        if (fileName != null) {
	    	            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
	    	            if (file.exists()) {
	    	               /*
*/	    	            	//response.setCharacterEncoding("UTF-8");
	    	            	 response.setContentType("application/octet-stream");
	    	            	 //response.setContentType("application/force-download");// 设置强制下载不打开
	    	        		//response.setHeader("content-Type", "application/vnd.ms-excel");
	    	                // 告诉浏览器用什么软件可以打开此文件
	    				    //response.setHeader("content-Type", "application/vnd.ms-excel");
	    	                response.addHeader("Content-Disposition","attachment;fileName=" +URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
	    	                byte[] buffer = new byte[1024];
	    	                FileInputStream fis = null;
	    	                BufferedInputStream bis = null;
	    	                try {
	    	                    fis = new FileInputStream(file);
	    	                    bis = new BufferedInputStream(fis);
	    	                    OutputStream os = response.getOutputStream();
	    	                    int i = bis.read(buffer);
	    	                    while (i != -1) {
	    	                        os.write(buffer, 0, i);
	    	                        i = bis.read(buffer);
	    	                    }
	    	                    System.out.println("success");
	    	                } catch (Exception e) {
	    	                    e.printStackTrace();
	    	                    logger.error("-----downloadFile---error："+e.getMessage(),e);
	    	                } finally {
	    	                    if (bis != null) {
	    	                        try {
	    	                            bis.close();
	    	                        } catch (IOException e) {
	    	                            e.printStackTrace();
	    	                        }
	    	                    }
	    	                    if (fis != null) {
	    	                        try {
	    	                            fis.close();
	    	                        } catch (IOException e) {
	    	                            e.printStackTrace();
	    	                        }
	    	                    }
	    	                }
	    	            }
	    	        }
			} catch (Exception e) {
				logger.error("-----downloadFile---error："+e.getMessage(),e);
			}
	        return null;
	    }
}

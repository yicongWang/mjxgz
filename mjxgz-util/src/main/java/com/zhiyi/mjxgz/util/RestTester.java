package com.zhiyi.mjxgz.util;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by wyc.
 * time on 2018/02/22 14:37
 * description:测试工具类
 */
public class RestTester {

    /**
     * @param url      绝对地址
     * @param paramMap 请求参数
     * @param method   请求方式
     * @param bodyType 返回类型
     * @param <T>      返回类型
     * @return 返回结果(响应体)
     */
    public static <T> T exchange(String url,Map paramMap, HttpMethod method, Class<T> bodyType) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        if(MapUtils.isNotEmpty(paramMap) && paramMap.containsKey("access_token")){
        	headers.set("access_token", paramMap.get("access_token").toString());
        }
        // 请求体
        headers.setContentType(mediaType);
        //提供json转化功能
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            if (MapUtils.isNotEmpty(paramMap)) {
            	jsonStr = mapper.writeValueAsString(paramMap);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(jsonStr, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType);
        return resultEntity.getBody();
    }
}
package com.zhiyi.mjxgz.common.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Floki on 2016/12/22.
 */
public class YMDCNDateStringSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (StringUtils.isEmpty(value)) {
            gen.writeString("");
        } else {
            String result = "";
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                result = new SimpleDateFormat("yyyy年MM月dd日").format(date);
            } catch (Exception e) {
            }
            gen.writeString(result);
        }
    }
}
package com.zhiyi.mjxgz.common.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Floki on 2016/12/22.
 */
public class YMDCNDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (null != value) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
            String formattedDate = formatter.format(value);
            gen.writeString(formattedDate);
        }
    }
}

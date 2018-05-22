package cn.lfungame.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Auther: xuke
 * @Date: 2018/5/22 15:10
 * @Description:
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String beanToJson(Object obj) throws Exception {
        return mapper.writeValueAsString(obj);
    }

    public static Object jsonToBean(String json, Class clazz) throws Exception {
        return mapper.readValue(json, clazz);
    }
}

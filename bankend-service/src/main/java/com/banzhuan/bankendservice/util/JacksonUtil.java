package com.banzhuan.bankendservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description json工具类,依赖jackson
 * @author linyoulong
 * @version V1.0
 */
public class JacksonUtil {
    private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper INSTANCE = new ObjectMapper();

    private JacksonUtil() {}

    /**
     * @param obj 准备转换对象
     * @return
     * @description 对象转换成json字符串
     * @version V1.0
     */
    public static String toJsonStr(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("toJsonStr error:",e);
        }
        return null;
    }

    /**
     * javaBean、列表数组转换为json字符串,忽略空值
     */
    public static String toJsonStrIgnoreNull(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * @param json 准备转换json
     * @param type 转换类型
     * @return
     * @throws Exception 转换异常
     * @description json字符串转换成对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2pojo(String json, String type) {
        try {
            return (T) json2pojo(json, Class.forName(type));
        } catch (ClassNotFoundException e) {
            logger.error("json2pojo error:",e);
        } catch (Exception e) {
            logger.error("json2pojo error:",e);
        }
        return null;
    }

    /**
     * @param json 准备转换json
     * @param clazz 转换类型
     * @return
     * @description json字符串转换成对象
     */
    public static <T> T json2pojo(String json, Class<T> clazz) {
        try {
            return (T) INSTANCE.readValue(json, clazz);
        } catch (JsonParseException e) {
            logger.error("json2pojo error:",e);
        } catch (JsonMappingException e) {
            logger.error("json2pojo error:",e);
        } catch (IOException e) {
            logger.error("json2pojo error:",e);
        }
        return null;
    }

    /**
     * @param json 准备转换json
     * @param clazz 转换类型
     * @return
     * @description json字符串转换成对象，当遇到没有的字段，则忽略
     */
    public static <T> T json2pojoCompatible(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return (T) mapper.readValue(json, clazz);

        } catch (JsonParseException e) {
            logger.error("json2pojoCompatible error:",e);
        } catch (JsonMappingException e) {
            logger.error("json2pojoCompatible error:",e);
        } catch (IOException e) {
            logger.error("json2pojoCompatible error:",e);
        }
        return null;
    }
    /**
     * @param json 准备转换json
     * @param type 转换类型
     * @return
     * @description json字符串转换成对象，当遇到没有的字段，则忽略
     */
    public static <T> T json2pojoCompatible(String json, TypeReference<T> type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return (T) mapper.readValue(json, type);

        } catch (JsonParseException e) {
            logger.error("json2pojoCompatible error:",e);
        } catch (JsonMappingException e) {
            logger.error("json2pojoCompatible error:",e);
        } catch (IOException e) {
            logger.error("json2pojoCompatible error:",e);
        }
        return null;
    }

    /**
     * json字符串转换为map
     */
    public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, Map.class);
    }

    /**
     * json字符串转换为map
     */
    public static <T> Map<String, T> json2map(String jsonString, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = INSTANCE.readValue(jsonString, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * 深度转换json成map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2mapDeeply(String json) throws Exception {
        return json2MapRecursion(json, INSTANCE);
    }

    /**
     * 把json解析成list，如果list内部的元素存在jsonString，继续解析
     *
     * @param json
     * @param mapper 解析工具
     * @return
     * @throws Exception
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }

        List<Object> list = mapper.readValue(json, List.class);

        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = json2ListRecursion(str, mapper);
                } else if (obj.toString().startsWith("{")) {
                    obj = json2MapRecursion(str, mapper);
                }
            }
        }

        return list;
    }

    /**
     * 把json解析成map，如果map内部的value存在jsonString，继续解析
     *
     * @param json
     * @param mapper
     * @return
     * @throws Exception
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }

        Map<String, Object> map = mapper.readValue(json, Map.class);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);

                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str, mapper);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }

        return map;
    }

    /**
     * @param json 准备转换json
     * @param clazz 集合元素类型
     * @return
     * @description json字符串转换成对象集合
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseJsonList(String json, Class<T> clazz) {
        try {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            return (List<T>) INSTANCE.readValue(json, javaType);
        } catch (IOException e) {
            logger.error("parseJsonList error:",e);
        }
        return null;
    }

    /**
     * map  转JavaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return INSTANCE.convertValue(map, clazz);
    }

    /**
     * map 转json
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        try {
            return INSTANCE.writeValueAsString(map);
        } catch (Exception e) {
            logger.error("mapToJson error:",e);
        }
        return "";
    }

    /**
     * map  转JavaBean
     */
    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return INSTANCE.convertValue(obj, clazz);
    }

    /**
     * @param collectionClass 集合类
     * @param elementClasses 集合元素类
     * @return
     * @description 获取泛型的ColloectionType
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return INSTANCE.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}

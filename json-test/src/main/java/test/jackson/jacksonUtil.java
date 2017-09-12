package test.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import test.entity.User;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by shen.yang on 2017/9/12.
 */
public class jacksonUtil {
    static User user1 =new User(1,"张三","89489",new Date());
    static String  json="{\"ids\":\"2\",\"name\": \"lisi\",\"mobile\": \"137857122\",\"birthday\":\"1980/01/01\"}";
    /**
     *对象转json
     * @param o
     * @return
     */
    public  static  String  ObjToJson(Object o){
        ObjectMapper mapper = new ObjectMapper();
        String json ="";
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            json =  mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  json;
    }

    /**
     * json json转对象
     * @param str
     * @param c
     * @param <T>
     * @return
     */
    public  static  <T> T strToObj(String str ,Class<T> c){
        ObjectMapper mapper = new ObjectMapper();
        T t=null;
        try {
          mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
          t=  mapper.readValue(str, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  t;
    }

    /**
     * str to  Map
     * @param str
     * @return
     */
    public  static Map<String,Object> strToMap(String str){
        ObjectMapper mapper = new ObjectMapper();
        Map t=null;
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            t=  mapper.readValue(str, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  t;
    }
    /**
     @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。

     @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")。

     @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")。
     */

    public static void main(String[] args) {
//        String s = ObjToJson(user1);
//        System.out.println(s);
        Map map = strToMap(json);
        System.out.println(map);

    }


}

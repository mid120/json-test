package test.gson;

import com.google.gson.*;
import test.entity.User;

import java.util.*;

/**
 * Created by shen.yang on 2017/9/12.
 */
public class GsonUtil {

    /**
     * 获取JsonObject
     * @param json
     * @return
     */
    public static JsonObject parseJson(String json){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }

    /**
     * 根据json字符串返回Map对象
     * @param json
     * @return
     */
    public static Map<String,Object> toMap(String json){
        return toMap(parseJson(json));
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json){
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof JsonArray)
                map.put((String) key, toList((JsonArray) value));
            else if(value instanceof JsonObject)
                map.put((String) key, toMap((JsonObject) value));
            else
                map.put((String) key, value);
        }
        return map;
    }

    /**
     * json 转 bean
     * @param str
     * @param c
     * @param <T>
     * @return
     */
    public static   <T> T jsonToBean(String str,Class<T> c){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy/MM/dd")
                .create();
        T t = gson.fromJson(str, c);
        return  t;
    }



    /**
     * 将JSONArray对象转换成List集合
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json){
        List<Object> list = new ArrayList<Object>();
        for (int i=0; i<json.size(); i++){
            Object value = json.get(i);
            if(value instanceof JsonArray){
                list.add(toList((JsonArray) value));
            }
            else if(value instanceof JsonObject){
                list.add(toMap((JsonObject) value));
            }
            else{
                list.add(value);
            }
        }
        return list;
    }

    public static void main(String[] args) {
         User user1 =new User(1,"张三","89489",new Date());
         String  json="{\"ids\":\"2\",\"name\": \"lisi\",\"mobile\": \"137857122\",\"birthday\":\"1980/01/01\"}";
      //  Map<String, Object> stringObjectMap = toMap(json);
        User user = jsonToBean(json, User.class);
        System.out.println(user);
    }
}

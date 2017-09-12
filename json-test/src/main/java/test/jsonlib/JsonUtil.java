package test.jsonlib;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import test.annotation.Uncheck;
import test.entity.User;

import java.util.*;

/**
 * Created by shen.yang on 2017/9/11.
 * 使用json lib操作json转换
 * 使用这个 addIgnoreFieldAnnotation、在对象转json时候会生效 json转对象不生效，原因很简单 json转成jsonObject这一步，是没有和实体交互的。无法识别注解
 * 使用自定义FILTER的时候、 互转都会生效
 *
 * 也可以使用这种方式进行排除
 * JsonConfig cfg = new JsonConfig();
  cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
 */
public class JsonUtil {
    static User  user1 =new User(1,"张三","89489",new Date());
    static User user2 =new User(2,"李四","55555",new Date());

    static String  json="{\"ids\":\"2\",\"name\": \"lisi\",\"mobile\": \"137857122\",\"birthday\":\"1980/01/01\"}";

    static String  jsonlist ="{\"abc\":[{\"id\":\"1\",\"name\":\"lisi\",\"mobile\":\"137857122\"},{\"id\":\"2\",\"name\":\"zhangsan\",\"mobile\":\"4589635\"}]}";

    /**
     * 对象转json
     * @return
     */
    public static String objToJson(Object u){
     if (u==null){
         return null;
     }
        JsonConfig config = new JsonConfig();
//        config.setJsonPropertyFilter(new Myfilter());
        config.addIgnoreFieldAnnotation(Uncheck.class);
     JSONObject result =   JSONObject.fromObject(u,config);
     return  result.toString();
    }

    /**
     * list转json
     * @param list
     * @return
     */
    public static  String  listToJson(List<Object> list){
        if (list==null||list.isEmpty()){
            return  null;
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return  jsonArray.toString();
    }

    /**
     * json转对象
     */
    public static <T> T strToJson(String  str,Class<T> c){
        if ("".equals(str)||str==null){
            return null;
        }
        //添加过滤功能
        JsonConfig config = new JsonConfig();
//        config.setJsonPropertyFilter(new Myfilter());
        config.addIgnoreFieldAnnotation(Uncheck.class);

        JSONObject jsonObject = JSONObject.fromObject(str,config);
        //转换日期格式
        String[] dateFormats = new String[] {"yyyy/MM/dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
        T o =(T)JSONObject.toBean(jsonObject, c);
        return o;
    }
    /**
     * json转list
     */
    public static <T> List<T>   strToList(String str ,Class<T> c){
        if ("".equals(str)||str==null){
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(str);
        //转换日期格式
        String[] dateFormats = new String[] {"yyyy/MM/dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
        List<T> list = (List<T>)JSONArray.toCollection(jsonArray, c);
        return  list;
    }

    /**
     * json 与map 的转换
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr){

        Map<String, Object> map = new HashMap<String, Object>();

        //最外层解析

        JSONObject json = JSONObject.fromObject(jsonStr);

        for(Object k : json.keySet()){

            Object v = json.get(k);

            //如果内层还是数组的话，继续解析

            if(v instanceof JSONArray){

                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

                Iterator<JSONObject> it = ((JSONArray)v).iterator();

                while(it.hasNext()){

                    JSONObject json2 = it.next();

                    list.add(parseJSON2Map(json2.toString()));

                }

                map.put(k.toString(), list);

            } else {

                map.put(k.toString(), v);

            }

        }

        return map;

    }



    /**

     * 函数注释：parseJSON2MapString()<br>

     * 用途：该方法用于json数据转换为<Map<String, String><br>

     * 备注：***<br>

     */

    public static Map<String, String> parseJSON2MapString(String jsonStr){

        Map<String, String> map = new HashMap<String, String>();

        //最外层解析

        JSONObject json = JSONObject.fromObject(jsonStr);

        for(Object k : json.keySet()){

            Object v = json.get(k);

            if(null!=v){


                map.put(k.toString(), v.toString());

            }

        }

        return map;

    }




    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String json = objToJson(user1);
        System.out.println(json);
//        ArrayList<Object> list = new ArrayList<Object>();
//        list.add(user1);
//        list.add(user2);
//        String listJson = listToJson(list);
//        System.out.println(listJson);


//        User user = strToJson(json, User.class);
//
//        System.out.println(user.getBirthday());
//        List<User> users = strToList(jsonlist, User.class);
//        System.out.println(users.size());
//          Map<String,String> map =  parseJSON2MapString(json);
//
//         for(Map.Entry<String,String> entry :map.entrySet()){
//             String key = entry.getKey();
//             Object value = entry.getValue();
//             entry.getValue();
//             System.out.println(key+"----"+value);
//        }

        }






}

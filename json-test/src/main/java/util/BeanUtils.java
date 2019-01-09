package util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 采用Spring的bean拷贝
 * 支持跨java类型
 */
public class BeanUtils {


    /**
     *
     */
    private static Map<String, BeanCopier> copierMap = new HashMap<String, BeanCopier>();

    /**
     * 属性转换器
     */
    private static PropertyConverter propertyConverter = new PropertyConverter();

    /**
     * 对象之间属性复制
     *
     * @param src  源对象
     * @param dest 目标对象
     */
    public static void copyProperties(Object src, Object dest){
        try {
            String key = src.getClass().getName() + dest.getClass().getName();
            BeanCopier copier;
            if(copierMap.containsKey(key)){
                copier = copierMap.get(key);
            }else{
                copier = BeanCopier.create(src.getClass(), dest.getClass(), true);
                copierMap.put(key, copier);
            }
            copier.copy(src, dest, propertyConverter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 属性转换器
     */
    private static class PropertyConverter implements Converter{
        @Override
        public Object convert(Object value, Class target, Object context) {
            if(value == null){
                return null;
            }
            try {
                //类型不一致的时候
                if (!value.getClass().equals(target)) {
                    if(target.equals(BigDecimal.class)){
                        return new BigDecimal(value.toString());
                    }else if(target.equals(Long.class)){
                        return Long.valueOf(value.toString());
                    }else if(target.equals(Integer.class)){
                        return Integer.valueOf(value.toString());
                    }else if(target.equals(int.class)){
                        return Integer.valueOf(value.toString()).intValue();
                    }else if (target.equals(String.class)){
                        return value.toString();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return value;
        }
    }
}

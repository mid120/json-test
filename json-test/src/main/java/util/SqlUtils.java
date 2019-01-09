package util;

import org.apache.commons.lang.StringUtils;

/**
 * 属性驼峰转换数据库下划线
 */
public class SqlUtils {

    public static final char UNDERLINE_CHAR = '_';

    /**
     * 驼峰转下划线
     *
     * @param camelStr
     * @return
     */
    public static String camel2Underline(String camelStr) {

        if (StringUtils.isEmpty(camelStr)) {

            return StringUtils.EMPTY;
        }

        int len = camelStr.length();
        StringBuilder strb = new StringBuilder(len + len >> 1);
        for (int i = 0; i < len; i++) {

            char c = camelStr.charAt(i);
            if (Character.isUpperCase(c)) {

                strb.append(UNDERLINE_CHAR);
                strb.append(Character.toLowerCase(c));
            } else {

                strb.append(c);
            }
        }
        return strb.toString();
    }

    /**
     * 转换为驼峰
     *
     * @param underscoreName
     * @return
     */
    public static String camelCaseName(String underscoreName) {
        if (StringUtils.isEmpty(underscoreName)) {
            return StringUtils.EMPTY;
        }
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0, length = underscoreName.length(); i < length; i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

}



package util;

import util.constant.RegexType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    /**
     * 维护枚举和常量 即可
      * @param str 待验证的字符串
     * @param type 验证的类型
     * @return
     *
     * Pattern.compile()方法尽量抽取为变量。匹配速度更快
     * 这里为了共性，牺牲了速度
     */
    public static boolean validate(String str , RegexType type){
        Matcher matcher = Pattern.compile(type.getRegexValue()).matcher(str);
        return matcher.find();
    }







}

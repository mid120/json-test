package util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字类工具类
 */
public class MathUtil {
    private static String regex = "^[1-9]\\d*$";
    private  static final Pattern positiveInteger = Pattern.compile(regex);

    private  static  Pattern chinesePatarn = Pattern.compile("^[\u4e00-\u9fa5]+$");

    private static Pattern specialPatern = Pattern.compile("\\s*|\t|\r|\n");

    /**
     * 判断是否是正整数
     * @param param
     * @return
     */
    public static boolean isPositiveInteger(String param){
        if(param == null) {
            return false;
        }
        Matcher matcher = positiveInteger.matcher(param);
        return matcher.find();
    }

    /***
     * 截取字符串中的全部汉字
     * @param str
     * @return
     */
    public static String getCharater(String str) {
        StringBuilder sb = new StringBuilder();
        Matcher m = chinesePatarn.matcher(str);
        while (m.find()) {
            sb.append(m.group());
        }
        return sb.toString();
    }

    /**
     * 检验是否是中文
     * @param value
     * @return
     */
    public static boolean checkChineseValue(String value) {
        Matcher m = chinesePatarn.matcher(value);
        boolean b = m.matches();
        return b;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = specialPatern.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断小数的位数是否给定范围之内
     * @param num   数字
     * @param count 最大小数位数
     * @return
     *  <p>非数字返回fasle</p>
     *  <p>空字符串返回true</p>
     *  <p>小数的位数 &lt; count返回true</p>
     *  <p>其他情况返回false</p>
     */
    public static Boolean isNumStandard(String num,int count) {
        if(StringUtils.isEmpty(num)){
            return true;
        }
        try {
            BigDecimal bd = new BigDecimal(num);
            return bd.scale() <= count;
        }catch (Exception e){

        }
        //非数字返回false
        return false;
    }

    /**
     * 判断是否为数字
     * @param num
     * @return
     */
    public static Boolean isNum(String num) {
        if (!StringUtils.isEmpty(num)) {
            return num.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        }
        return false;
    }

    /**
     * 去掉小数点后面无用的零，如小数点后面全是零则去掉小数点
     *
     * @param obj
     * @return
     */
    public static String subZeroAndDot(Object obj) {
        String str1 = obj + "";
        if (str1.indexOf(".") > 0) {
            //去掉后面无用的零
            str1 = str1.replaceAll("0+?$", "");
            //如小数点后面全是零则去掉小数点
            str1 = str1.replaceAll("[.]$", "");
        }
        return str1;
    }


    /**
     * 摸尾零,默认长度
     * @return
     */
    public static String removeLastZero(String str){
        if (isNum(str)){
            BigDecimal decimal = new BigDecimal(str);
            //单价
            DecimalFormat decimalFormat = new DecimalFormat("###,###.000000000000000");
            String   result = decimalFormat.format(decimal);
            //字符串去尾零
            String temp ="";
            do {
                temp=result.substring(result.length()-2,result.length()-1);
                result=result.substring(0,result.length()-1);
                int length = result.lastIndexOf(".")+3;
                if (result.length()==length){
                    break;
                }
            }while ("0".equals(temp));
            return str;
        }else {
            return "0.00";
        }

    }
}

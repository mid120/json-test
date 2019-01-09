package util;

/**
 * 项目名称:
 * 模块名称:
 * 说明:
 * JDK 版本: 1.8
 */
public class MoneyUtil {

    //人民币单位
    private static final String CN_UNIT = "万仟佰拾亿仟佰拾万仟佰拾圆角分";

    //人民币数字
    private static final String CN_DIGIT = "零壹贰叁肆伍陆柒捌玖";

    //最大值
    private static final double MAX_VALUE = 9999999999999.99D;

    /**
     * 小写数字转换为大写  （例如 输入111   返回值为壹佰壹拾壹圆整）
     *
     * @param v 数字金额
     * @return 人民币大写金额字符串
     */
    public static String toChineseUpperCase(double v) {
        v = Math.abs(v);
        if (v > MAX_VALUE) {
            return "参数非法!";
        }
        long l = Math.round(v * 100);
        if (l == 0) {
            return "零圆整";
        }
        String strValue = l + "";
        // i用来控制数
        int i = 0;
        // j用来控制单位
        int j = CN_UNIT.length() - strValue.length();
        StringBuilder rs = new StringBuilder();
        boolean isZero = false;
        for (; i < strValue.length(); i++, j++) {
            char ch = strValue.charAt(i);
            if (ch == '0') {
                isZero = true;
                if (CN_UNIT.charAt(j) == '亿' || CN_UNIT.charAt(j) == '万' || CN_UNIT.charAt(j) == '圆') {
                    rs.append(CN_UNIT.charAt(j));
                    isZero = false;
                }
            } else {
                if (isZero) {
                    rs = rs.append("零");
                    isZero = false;
                }
                rs.append(CN_DIGIT.charAt(ch - '0')).append(CN_UNIT.charAt(j));
            }
        }

        String result = rs.toString();
        if (!result.endsWith("分") && !result.endsWith("角")) {
            rs.append("整");
            result = rs.toString();
        }
        if (result.indexOf("整") < 0 && result.indexOf("零") < 0) {
            result = result.replaceAll("拾圆", "拾圆零");
            result = result.replaceAll("仟圆", "仟圆零");
            result = result.replaceAll("万圆", "万圆零");
        }
        result = result.replaceAll("亿万", "亿");
        return result;
    }

}

package util.constant;

public interface RegexConstant {
    /**
     *   邮箱正则
     */
      String EMAIL_REGEX = "^([a-z0-9A-Z]+[_\\-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 手机号正则
     */
    String MOBILE_REGEX ="^[1][3,4,5,7,8][0-9]{9}$";

    /**
     * 车牌号正则
     */
    String CAR_NO="^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
}

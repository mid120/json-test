package util;

import util.constant.RegexType;

public class mainTest {
    public static void main(String[] args) {
        boolean s = RegexUtils.validate("鄂JNQ25挂", RegexType.carNo);
        System.out.println(s);
    }
}

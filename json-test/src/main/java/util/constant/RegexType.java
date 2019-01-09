package util.constant;

public enum RegexType {
    mobile("mobile",RegexConstant.MOBILE_REGEX),
    email("email",RegexConstant.EMAIL_REGEX),
    carNo("carNo",RegexConstant.CAR_NO),
    ;

    String regexName;
    String regexValue;

    RegexType(String regexName, String regexValue) {
        this.regexName = regexName;
        this.regexValue = regexValue;
    }

    public String getRegexName() {
        return regexName;
    }

    public void setRegexName(String regexName) {
        this.regexName = regexName;
    }

    public String getRegexValue() {
        return regexValue;
    }

    public void setRegexValue(String regexValue) {
        this.regexValue = regexValue;
    }
}

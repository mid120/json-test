package util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 项目名称:  phoenix-bill
 * 模块名称:
 * 说明:
 * JDK 版本: JDK1.8
 */
@Component
public class ExceptionUtils {
    /**
     * 获取异常的栈信息字符串
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

    /**
     * 获取异常栈的最后一个causeBy，
     * 若是没有causeBy 就直接截取相应长度
     * @param stackTrace
     * @param length
     * @return
     */
    public static String getExceptionCauseBy(String stackTrace,int length){
        int lastIndexOf = stackTrace.lastIndexOf("Caused by");
        StringBuilder lastCausedBy = new StringBuilder();
        if (lastIndexOf > 0){
            lastCausedBy.append(splitStringWithLength(stackTrace.substring(lastIndexOf, stackTrace.length()),length));
        }else {
            lastCausedBy.append(splitStringWithLength(stackTrace,length));
        }
        return lastCausedBy.toString();
    }

    /**
     * 将字符串截成相应长度
     *
     * @param temp
     * @param length
     * @return
     */
    public static String splitStringWithLength(String temp, int length) {
        if (StringUtils.isEmpty(temp)) {
            return "";
        }
        if (length < 0) {
            return "";
        }
        temp = temp.length() > length ? temp.substring(0, length) : temp;
        return temp;
    }
}

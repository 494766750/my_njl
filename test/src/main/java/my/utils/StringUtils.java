package my.utils;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by andy on 2018/11/28.
 */
public class StringUtils {


    /**
     * 判断字符串为空对象（null）或者为空字符串（""）
     */
    public static Boolean isEmpty(String arg) {

        if (arg == null || "".equals(arg) || arg.length() == 0) {
            return true;
        }

        return false;
    }

    public static Boolean isEmpty(Object arg) {
        if (arg == null || "".equals(arg)) {
            return true;
        }
        return false;
    }

    /**
     * 已YYYYMMDD的格式返回当前的的日期
     */
    public static String getDateString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);


        return String.format("%04d", year) + String.format("%02d", month) + String.format("%02d", day);
    }

    /**
     * 判断字符串为空对象（null）或者为空字符串（""）
     */
    public static String toString(Double arg) {

        if (arg == null) {
            return "0";
        } else {
            return arg.toString();
        }

    }

    public static String formart(String arg) {
        if (arg == null) {
            return " ";
        } else {
            return arg;
        }
    }

    /**
     * Object 转换 String		by	wuling
     *
     * @param obj      需要转化的对象
     * @param defaults 对象为null默认值
     * @param pattern  日期类型转化的格式
     * @return
     * @Time 2017-12-15 15:03:40
     */
    public static String toString(Object obj, String defaults, String pattern) {
        if (obj == null || "".equals(obj)) {
            return defaults;
        }
        Class<? extends Object> keyClass = obj.getClass();
        //将 obj 做类型转换
        if (keyClass.equals(Integer.class)) { //Integer类型
            defaults = ((Integer) obj).toString();
        } else if (keyClass.equals(Double.class)) { //Double类型
            defaults = ((Double) obj).toString();
        } else if (keyClass.equals(BigDecimal.class)) { //BigDecimal类型
            defaults = ((BigDecimal) obj).toString();
        } else if (keyClass.equals(Date.class)) { //Date类型
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            defaults = (sdf.format((Date) obj)).toString();
        } else { //String类型
            defaults = ((String) obj).toString();
        }
        return defaults;
    }

    /**
     * 参数为null转化
     *
     * @param arg
     * @return
     */
    public static String nullFormart(Object arg) {
        return arg == null ? "" : arg.toString();
    }

    public static Object ifNull(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj;
        }
    }


    /**
     * 匹配字符
     *
     * @param string
     * @param regEx1
     * @return
     */
    public static boolean isPureDigital(String string, String regEx1) {
        if (isBlank(string)) {
            return false;
        }
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        }
        return false;
    }


    /**
     * 检查该错误是否已经存在,不存在即追加
     *
     * @param errMsg
     * @param str
     * @return
     */
    public static void checkIsContainErrMsg(StringBuilder errMsg, String str, String regex) {
        String[] arr = errMsg.toString().split(regex);
        for (String s : arr) {
            if (!s.contains(str)) {
                errMsg.append(str);
            }
        }
    }

    /**
     * 将字符串首字母小写
     *
     * @param str
     * @return
     */
    public static String toUpperCaseFirst(String str) {
        if (isEmpty(str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0));
        }
        matcher.appendTail(sb);
        return sb.toString().substring(1, sb.length()).toUpperCase();
    }

    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0));
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }

    /**
     * 从字节码文件中获取文件的名字
     *
     * @param str
     * @return
     */
    public static String getTableName(String str) {
        int i = str.lastIndexOf(".") + 1;
        return str.substring(i, str.length());
    }

    /**
     * 解析过滤条件表达式
     *
     * @param
     * @return
     */
//    public static Map<String, Object> parseFilterCriteria(String filterCriteria) {
//        // 将表达是  isDeleted  == 1 && isActive == 2  拼装成map  k isDeleted  v 1
//        Map<String, Object> criteria = Maps.newHashMap();
//        if (StringUtils.isEmpty(filterCriteria)) {
//            return criteria;
//        }
//        String[] split = filterCriteria.split("&&");
//        for (String criteria_ : split) {
//            String[] field = null;
//            if (criteria_.split("==") != null && criteria_.split("==").length == 2) {
//                field = criteria_.split("==");
//            } else {
//                throw new RuntimeException("表达式不正确");
//            }
//            criteria.put(field[0].trim(), field[1].trim());
//        }
//        return criteria;
//    }


    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }


}

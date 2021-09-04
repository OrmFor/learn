package com.design.learn.sxyh;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.*;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


/**
 * @version 1.0
 * @Describe 常用工具大全
 */
public class Tools {

    /**
     * @param value 被转换的int值
     * @return
     * @Description: int转boolean------1为true;否则为false
     * @author lcg
     * @date 2017年12月7日 下午3:27:09
     */
    public static boolean parseBoolean(int value) {
        if (value == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param value 被转换的String值
     * @return
     * @Description: String转boolean------"true"为true;否则为false
     * @author lcg
     * @date 2017年12月7日 下午3:27:09
     */
    public static boolean parseBoolean(String value) {
        if (Tools.processNull(value).equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param value 被转换的boolean值
     * @return
     * @Description: boolean转int------true为1;false为0
     * @author lcg
     * @date 2017年12月7日 下午3:27:09
     */
    public static int parseInt(boolean value) {
        if (value == true) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @param str    被替换的String值
     * @param oldStr str中替换前的旧字符串
     * @param newStr str中替换后的新字符串
     * @return
     * @Description: 将字符串中的某字符串替换为新的字符串
     * @author lcg
     * @date 2017年12月7日 下午3:29:07
     */
    public static String replace(String str, String oldStr, String newStr) {
        if (str != null) {
            int index = 0;
            int oldLen = oldStr.length(); // oldStr字符串长度
            if (oldLen <= 0) {
                return str;
            }
            int newLen = newStr.length(); // newStr字符串长度
            while (true) {
                index = str.indexOf(oldStr, index);
                if (index == -1) {
                    return str;
                } else {
                    str = str.substring(0, index) + newStr + str.substring(index + oldLen);
                    index += newLen;
                }
            }
        } else {
            return "";
        }
    }

    /**
     * @param str         被转换的String值
     * @param supportHtml 是否转换&和<为&amp;和&lt;------false为转换
     * @return
     * @Description: 将字符串中""和\n转换为&nbsp;和<br>
     * 如果supportHtml为false,转换&和<为&amp;和&lt;
     * @author lcg
     * @date 2017年12月7日 下午3:31:57
     */
    public static String strToHtml(String str, boolean supportHtml) {
        if (str == null) {
            return "";
        }
        str = replace(str, " ", "&nbsp;");
        str = replace(str, "\n", "<br>");
        if (supportHtml == false) {
            str = replace(str, "&", "&amp;");
            str = replace(str, "<", "&lt;");
        }
        return str;
    }

    /**
     * @param str     被转换的String值
     * @param showstr
     * @return
     * @Description: 将字符串中""和\n转换为&nbsp;和<br>
     * 如果showstr不为空,将转换后的字符串中包含的showstr替换为放入<font>标签内,字体为绿色加粗
     * @author lcg
     * @date 2017年12月7日 下午3:38:09
     */
    public static String strToShow(String str, String showstr) {
        if (str == null) {
            return "";
        }
        str = replace(str, " ", "&nbsp;");
        str = replace(str, "\n", "<br>");
        if (showstr.equals("")) {
        } else {
            String repstr = "<font color=green><b>" + showstr + "</b></font>";
            str = replace(str, showstr, repstr);
        }
        return str;
    }

    /**
     * @param str 被转换的String值
     * @return
     * @Description: 将字符串中""和\n转换为&nbsp;和<br>
     * 不转换&和<为&amp;和&lt;
     * @author lcg
     * @date 2017年12月7日 下午3:43:17
     */
    public static String strToHtml(String str) {
        return strToHtml(str, true);
    }

    /**
     * @return
     * @Description: 获取当前系统文件路径分隔符/或\
     * @author lcg
     * @date 2017年12月7日 下午3:54:49
     */
    public static String getSeparator() {
        Properties prop = new Properties(System.getProperties());
        return prop.getProperty("file.separator");
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: 处理null空指针------null返回"",否则返回原值
     * @author lcg
     * @date 2017年12月7日 下午3:56:01
     */
    public static String processNull(String str) {
        return (str == null) ? "" : str;
    }

    /**
     * @param date 被处理的Date值
     * @return
     * @Description: 处理null空指针------null返回"",否则返回原值.toString()
     * @author lcg
     * @date 2017年12月7日 下午3:57:30
     */
    public static String processNull(Date date) {
        return (date == null) ? "" : date.toString();
    }

    /**
     * @param value 被处理的Long值
     * @return
     * @Description: 处理null空指针------null返回"",否则返回原值.toString()
     * @author lcg
     * @date 2017年12月7日 下午3:57:30
     */
    public static String processNull(Long value) {
        return (value == null) ? "" : value.toString();
    }

    /**
     * @param value 被处理的Object
     * @return
     * @Description: 处理null空指针------null返回"",否则返回原值.toString()
     * @author lcg
     * @date 2017年12月7日 下午3:57:30
     */
    public static String processNull(Object value) {
        return (value == null) ? "" : value.toString();
    }

    /**
     * @param value 被处理的float值
     * @return
     * @Description: 处理null空指针------0.0返回"",否则返回原值+""
     * @author lcg
     * @date 2017年12月7日 下午3:57:30
     */
    public static String processNull(float value) {
        return (value == 0.0) ? "" : value + "";
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: null返回&nbsp,否则返回原值
     * @author lcg
     * @date 2017年12月7日 下午3:59:06
     */
    public static String processSpace(String str) {
        return (str == null) ? "&nbsp;" : str;
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: String转int------如果异常,返回-1
     * @author lcg
     * @date 2017年12月7日 下午4:00:01
     */
    public static int processInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param date 被处理的Long值
     * @return
     * @Description: Long转String------null返回"",否则返回原值+"",如果异常,返回原值.toString()
     * @author lcg
     * @date 2017年12月7日 下午4:02:44
     */
    public static String processLong(Long date) {
        if (date == null) {
            return "";
        }
        try {
            return date + "";
        } catch (Exception e) {
            return date.toString();
        }
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: String转Long------如果异常,返回-1
     * @author lcg
     * @date 2017年12月7日 下午4:02:44
     */
    public static Long processLong(String str) {
        try {
            return new Long(str);
        } catch (Exception e) {
            return new Long(-1);
        }
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: String转Long------如果异常,返回0
     * @author lcg
     * @date 2017年12月7日 下午4:02:44
     */
    public static float processFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: 左右空格都去掉
     * @author lcg
     * @date 2017年12月7日 下午4:21:35
     */
    public static String trim(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            // return leftTrim(rightTrim(str));
            return str.replaceAll("^[　 ]+|[　 ]+$", "");
        }
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: 去左空格
     * @author lcg
     * @date 2017年12月7日 下午4:21:35
     */
    public static String leftTrim(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            return str.replaceAll("^[　 ]+", "");
        }
    }

    /**
     * @param str 被处理的String值
     * @return
     * @Description: 去右空格
     * @author lcg
     * @date 2017年12月7日 下午4:21:35
     */
    public static String rightTrim(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            return str.replaceAll("[　 ]+$", "");
        }
    }

    /**
     * @param money 被处理的double值
     * @return
     * @Description: double转String(钱币)，不知道啥转啥，不建议用
     * @author lcg
     * @date 2017年12月7日 下午4:22:31
     */
    public static String getMoney(double money) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("###,##0.00");
        return df.format(money);
    }


    /**
     * 元转分
     *
     * @param money
     * @return
     */
    public static Long getAmt(String money) {
        Double d = Double.valueOf(money) * 100;
        return d.longValue();
    }

    /**
     * @param from 被处理的double值
     * @param num  保留的小数位数
     * @return
     * @Description: 四舍五入
     * @author lcg
     * @date 2017年12月7日 下午4:23:41
     */
    public static double round(double from, int num) {
        if (num < 1) {
            return from;
        }
        BigDecimal b = new BigDecimal(from);
        double to = b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
        return to;
    }

    /**
     * @param instr 被获取的String值
     * @return
     * @Description: 获取字符串长度
     * @author lcg
     * @date 2017年12月7日 下午4:25:49
     */
    public static int getStrLen(String instr) {
        if (instr == null) {
            return 0;
        }
        int Num = 0, i = 0;
        char chr = ' ';
        for (i = 0; i < instr.length(); i++) {
            chr = instr.charAt(i);
            if ((int) chr <= (int) '~') {
                Num += 1;
            } else {
                Num += 2;
            }
        }
        return Num;
    }

    /**
     * @param str    被扩展的String值
     * @param len    扩展后长度
     * @param pre    是否前扩展------true为前,false为后
     * @param addStr 扩展时所用字符串
     * @return
     * @Description: 扩展字符串
     * @author lcg
     * @date 2017年12月7日 下午4:26:47
     */
    public static String tensileString(String str, int len, boolean pre, String addStr) {
        if (str == null) {
            return null;
        }
        if (str.length() >= len)
            return str.substring(0, len);
        else {
            while (str.length() < len) {
                if (pre) {
                    str = addStr + str;
                } else {
                    str = str + addStr;
                }
            }
            if (pre) {
                str = str.substring(str.length() - len, str.length());
            } else {
                str = str.substring(0, len);
            }
            return str;// 如果addStr的长度本身就大于len，那么事后需要处理成len长度返回
        }
    }

    /**
     * @param src 被转的String值
     * @return
     * @Description: ISO-8859-1转GBK
     * @author lcg
     * @date 2017年12月7日 下午4:28:51
     */
    public static String isoToGBK(String src) {
        String sDst = "";
        try {
            sDst = new String(src.getBytes("ISO8859_1"), "GBK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sDst;
    }

    /**
     * @param src 被转的String值
     * @return
     * @Description: GBK转ISO-8859-1
     * @author lcg
     * @date 2017年12月7日 下午4:28:51
     */
    public static String gbkToISO(String src) {
        String sDst = "";
        try {
            sDst = new String(src.getBytes("GBK"), "ISO8859_1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sDst;
    }

    /**
     * @param src 被编码的String值
     * @return
     * @Description: 字符串编码为UTF-8格式
     * @author lcg
     * @date 2017年12月7日 下午4:30:09
     */
    public static String strtoUTF8(String src) {
        try {
            return URLEncoder.encode(src, "UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return src;
    }

    /**
     * @param src 被解码的String值
     * @return
     * @Description: 字符串解码为UTF-8格式
     * @author lcg
     * @date 2017年12月7日 下午4:30:09
     */
    public static String UTF8tostr(String src) {
        try {
            return URLDecoder.decode(src, "UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return src;
    }

    /**
     * @param s 被编码的String值
     * @return
     * @Description: 将URL中=号后面的内容编码为UTF-8格式
     * @author lcg
     * @date 2017年12月7日 下午4:31:12
     */
    public static String encode4URL(String s) {
        if (s == null || s.trim().length() == 0) {
            return "";
        }
        if (s.indexOf("=") < 0) {
            return s;
        }
        StringBuffer url = new StringBuffer();
        try {
            String[] temS = s.split("&");
            for (int i = 0; i < temS.length; i++) {
                String tem = temS[i].toString();
                if (tem == null || tem.trim().length() == 0) {
                    continue;
                }
                String[] ss = tem.split("=");
                url.append(ss[0].toString()).append("=").append(ss.length == 1 ? "" : URLEncoder.encode(ss[1], "UTF-8"))
                        .append("&");
                // append(URLEncoder.encode(ss[1]));append(URLEncoder.encode(ss[1],"GB2312"));
            }
        } catch (Exception e) {
            return s;
        }
        return url.toString();
    }

    /**
     * @param list  被转换的List
     * @param qhzf  自定义字符
     * @param split 分隔符
     * @return
     * @Description: List转换为字符串，字符前后可相加自定义字符，分隔符也可定义
     * @author lcg
     * @date 2017年12月7日 下午4:32:33
     */
    public static String getConcatStrFromList(List list, String qhzf, String split) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < list.size(); i++) {
                if (!processNull(list.get(i)).equals("")) {
                    sb.append(qhzf + processNull(list.get(i)).trim() + qhzf + split);
                }
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 1);
            }
        } catch (Exception e) {
            return "";
        }
        return sb.toString();
    }

    /**
     * @param list  被转换的List
     * @param split 分隔符
     * @return
     * @Description: List转换为字符串，分隔符也可定义
     * @author lcg
     * @date 2017年12月7日 下午4:32:33
     */
    public static String getConcatStrFromList(List list, String split) {
        return getConcatStrFromList(list, "'", split);
    }

    /**
     * @param list 被转换的List
     * @return
     * @Description: List转换为字符串，字符前后相加单引号，中间通过逗号分割
     * @author lcg
     * @date 2017年12月7日 下午4:32:33
     */
    public static String getConcatStrFromList(List list) {
        return getConcatStrFromList(list, ",");
    }

    /**
     * @param arr   被转换的String[]
     * @param qhzf  自定义字符
     * @param split 分隔符
     * @return
     * @Description: 数组转换为字符串，字符前后可相加自定义字符，分隔符也可定义
     * @author lcg
     * @date 2017年12月7日 下午4:35:32
     */
    public static String getConcatStrFromArray(String[] arr, String qhzf, String split) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < arr.length; i++) {
                if (!processNull(arr[i]).equals("")) {
                    sb.append(qhzf + processNull(arr[i]).trim() + qhzf + split);
                }
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 1);
            }
        } catch (Exception e) {
            return "";
        }
        return sb.toString();
    }

    /**
     * @param arr   被转换的String[]
     * @param split 分隔符
     * @return
     * @Description: 数组转换为字符串，字符前后相加单引号，分隔符可定义
     * @author lcg
     * @date 2017年12月7日 下午4:35:32
     */
    public static String getConcatStrFromArray(String[] arr, String split) {
        return getConcatStrFromArray(arr, "'", split);
    }

    /**
     * @param arr 被转换的String[]
     * @return
     * @Description: 数组转换为字符串，字符前后相加单引号，中间通过逗号分割
     * @author lcg
     * @date 2017年12月7日 下午4:35:32
     */
    public static String getConcatStrFromArray(String[] arr) {
        return getConcatStrFromArray(arr, ",");
    }

    /**
     * @param object 被获取的Object
     * @return
     * @Description: 获取对象的属性------返回类似{[a:1],[b:2],[c:测试]}的字符串
     * @author lcg
     * @date 2017年12月7日 下午4:36:57
     */
    @SuppressWarnings("unchecked")
    public static String getFieldFromObjcet(Object object) {
        StringBuffer str = new StringBuffer("{");
        try {
            Class classType = object.getClass();
            Field fields[] = classType.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getMethod = classType.getMethod(getMethodName, new Class[]{});
                Object value = getMethod.invoke(object, new Object[]{});
                str.append("[" + fieldName + ":" + value + "],");
            }
            if (str.toString().length() > 1) {
                str = new StringBuffer(str.toString().substring(0, str.toString().length() - 1));
            }
            str.append("}");
        } catch (Exception e) {
        }
        return str.toString();
    }

    /**
     * @param o 被获取的Object
     * @param str    被获取的属性名称
     * @return
     * @Description: 获取对象的特定属性值
     * @author lcg
     * @date 2017年12月7日 下午4:36:57
     */
    public static Object getvalueByObject(Object o, String str) throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method getMethod = o.getClass().getMethod("get" + str, new Class[]{});
        return getMethod.invoke(o, new Object[]{});
    }

    /**
     * @param inMap 被转换的Map
     * @return
     * @Description: Map中key转换为search.xxx
     * @author lcg
     * @date 2017年12月7日 下午5:02:33
     */
    public static Map convertToSearch(Map<String, Object> inMap) {
        Map map = new HashMap<>();
        for (Entry entry : inMap.entrySet()) {
            String key = (String) entry.getKey();
            String newKey = "";
            if (key.lastIndexOf(".") > 0) {
                newKey += "search" + key.substring(key.lastIndexOf("."));
            } else {
                newKey += "search." + key;
            }
            map.put(newKey, inMap.get(key));
        }
        return map;
    }

    /**
     * @param
     * @return
     * @Description: Object所有属性放入Map中key转换为search.xxx
     * @author lcg
     * @date 2017年12月7日 下午5:02:33
     */
    public static Map convertToSearch(Object obj) {
        return getObjectFields(obj, new HashMap());
    }

    /**
     * @param
     * @return
     * @Description: Object所有属性放入Map中key转换为search.xxx
     * @author lcg
     * @date 2017年12月7日 下午5:02:33
     */
    public static Map getObjectFields(Object obj, Map map) {
        try {
            Class classType = obj.getClass();
            List<Field[]> fieldList = getClassFields(classType, new ArrayList<Field[]>());
            for (int i = 0; i < fieldList.size(); i++) {
                Field[] fields = fieldList.get(i);
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    String fieldName = field.getName();
                    if (!"serialVersionUID".equals(fieldName)) {
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method getMethod = null;
                        try {
                            getMethod = classType.getMethod(getMethodName, new Class[]{});
                        } catch (NoSuchMethodException e) {
                            getMethod = classType.getMethod("get" + fieldName, new Class[]{});
                        }
                        if (null != getMethod) {
                            Object value = getMethod.invoke(obj, new Object[]{});
                            if (null != value) {
                                if (null != value.getClass().getClassLoader()) {
                                    getObjectFields(value, map);
                                } else {
                                    map.put("search." + fieldName, value);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @param classType 类
     * @param fieldList 存放属性的List
     * @return
     * @Description: 获取类的所有属性
     * @author lcg
     * @date 2017年12月8日 上午9:46:28
     */
    public static List<Field[]> getClassFields(Class classType, List<Field[]> fieldList) {
        Class parentClassType = classType.getSuperclass();
        if (null != classType) {
            Field[] fields = classType.getDeclaredFields();
            fieldList.add(fields);
        }
        if (null != parentClassType) {
            getClassFields(parentClassType, fieldList);
        }
        return fieldList;
    }

    public static final char UNDERLINE = '_'; // 定义下划线

    // 驼峰转下划线
    public static String camelToUnderline(String param) {
        if (param == null) {
            return "";
        }
        if (param.lastIndexOf("Tag") > -1) {
            // tag中拓展
            param = param.substring(0, param.lastIndexOf("Tag"));
        }
        // =================================================//
        if (!checkNull(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 下划线转驼峰
    public static String underlineToCamel(String param) {
        if (!checkNull(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 下划线转类名
    public static String underlineToClass(String param) {
        if (!checkNull(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1);
    }

    /**
     * 检查string是不是null
     *
     * @param str
     * @return
     */
    public static Boolean checkNull(String str) {

        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为纯数字构成
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (null != str && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else if (null == str) {
            return false;
        }
        return true;
    }

    /**
     * 将字符串首字母大写
     *
     * @param str
     * @return
     */
    public static String captureStr(String str) {
        if (null != str && !"".equals(str)) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
            return str;
        } else {
            return "";
        }
    }



    /**
     * 拼接option
     *
     * @param optionStr       被拼接的初始option
     * @param defValStr       设置该项是否被选中
     * @param opvaltypeValue  option的value值
     * @param opshowtypeValue option的显示值
     * @return optionStr 拼接完的option
     */
    public static String getOptionStr(String optionStr, String defValStr, String opvaltypeValue,
                                      String opshowtypeValue) {
        optionStr += "" + "<option value='" + opvaltypeValue + "' " + defValStr + "  >" + opshowtypeValue
                + "</option> ";
        return optionStr;
    }

    /**
     * map转化为json
     *
     * @param map
     * @return
     */
    public static JSONObject mapToJson(Map map) {
        JSONObject json = new JSONObject();
        Iterator mapIt = map.entrySet().iterator();
        while (mapIt.hasNext()) {
            Entry entry = (Entry) mapIt.next();
            json.put(processNull(entry.getKey()), entry.getValue());
        }
        return json;
    }


    /**
     * List&lt;map&gt;转化为List&lt;json&gt;
     *
     * @param listMap
     * @return
     */
    public static List<JSONObject> listMapToListJson(List<Map> listMap) {
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < listMap.size(); i++) {
            list.add(mapToJson(listMap.get(i)));
        }
        return list;
    }

    /**
     * 判断json中元素是否为空
     *
     * @param json
     * @param str  需要判空的元素数组
     * @return 判空元素均不为空返回true，否则返回false
     */
    public static boolean checkJsonItemIsNull(JSONObject json, String[] str) {
        for (int i = 0; i < str.length; i++) {
            String key = str[i];
            String value = processNull(json.get(key));
            if ("".equals(value)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 根据身份证返回性别 1：男； 2：女 ;  0-不确定
     */
    public static String getSexById(String idno) throws Exception {
        try {
            if (idno.length() == 18) {
                if (Integer.parseInt(idno.substring(16, 17)) % 2 == 0) {
                    return "2";
                } else {
                    return "1";
                }
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据身份证返回生日
     */
    public static String getBirthdayById(String idno) throws Exception {
        try {
            if (idno.length() == 18) {
                return idno.substring(6, 10) + "-" + idno.substring(10, 12) + "-" + idno.substring(12, 14);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 将字符串根据长度截取成list
     *
     * @param inString 入参字符串
     * @param len      list每个元素的字符串长度
     * @return
     */
    public static List<String> strSplitByLen(String inString, int len) {
        int size = inString.length() / len;
        if (inString.length() % len != 0) {
            size++;
        }
        return strSplit(inString, len, size);
    }

    /**
     * 字符串截取成list
     *
     * @param inString 入参字符串
     * @param len      list每段元素的字符串长度
     * @param size     list的size
     * @return
     */
    public static List<String> strSplit(String inString, int len, int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String str = strSubString(inString, len * i, len * (i + 1));
            list.add(str);
        }
        return list;
    }

    /**
     * 字符串截取
     *
     * @param inString 入参字符串
     * @param start    截取起始位置
     * @param end      截取终止位置
     * @return
     */
    public static String strSubString(String inString, int start, int end) {
        if (start > inString.length()) {
            return "";
        } else if (end > inString.length()) {
            return inString.substring(start, inString.length());
        } else {
            return inString.substring(start, end);
        }
    }

    /**
     * @param checkMap
     * @param clazz
     * @return
     * @throws
     * @Description: 根据map中的数据封装相似属性到指定类型中，并返回
     * @author shizi
     * @date 2018年7月16日 下午12:46:05
     */
    public static <T> T getInsByMap(Map checkMap, Class<T> clazz) throws Exception {
        //创建实例
        T tIns = clazz.newInstance();
        if (clazz != null && checkMap != null) {
            //获取属性数组
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            for (Object key : checkMap.keySet()) {
                String checkKey = underlineToCamel(key.toString());
                //如果存在该属性
                for (Field f : fieldList) {
                    if (f.getName().equals(checkKey)) {
                        f.setAccessible(true);
                        Object value = processNull(checkMap.get(key));
                        //获取数据类型
                        Class<?> typeClazz = f.getType();
                        try {
                            //判断所传数据是否和数据类型匹配
                            if (Date.class.getName().equals(typeClazz.getName())) {//日期类型
                                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
                                Date date = sdf.parse(value.toString());
                                f.set(tIns, date);
                            } else if (Long.class.getName().equals(typeClazz.getName())) {//long类型
                                Long longVal = Long.parseLong(value.toString());
                                f.set(tIns, longVal);
                            } else if (Integer.class.getName().equals(typeClazz.getName())) {
                                Integer intVal = Integer.parseInt(value.toString());
                                f.set(tIns, intVal);
                            } else if (Double.class.getName().equals(typeClazz.getName())) {
                                Double dVal = Double.parseDouble(value.toString());
                                f.set(tIns, dVal);
                            } else {
                                f.set(tIns, value);
                            }

                        } catch (Exception e) {
//    						e.printStackTrace();
                        }
                        f.setAccessible(false);
                    }
                }
            }
        }
        return tIns;
    }


    /**
     * blob转byte数组
     *
     * @param blob
     * @return
     * @throws IOException
     */
    public static byte[] blobToBytes(Blob blob) throws IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len && (read = in.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    //计算年龄
    public static Integer getPersonAgeFromIdCard(String pensonnelIdCard) {

        //截取身份证中出行人出生日期中的年、月、日
        Integer personYear = Integer.parseInt(pensonnelIdCard.substring(6, 10));
        Integer personMonth = Integer.parseInt(pensonnelIdCard.substring(10, 12));
        Integer personDay = Integer.parseInt(pensonnelIdCard.substring(12, 14));

        Calendar cal = Calendar.getInstance();
        // 得到当前时间的年、月、日
        Integer yearNow = cal.get(Calendar.YEAR);
        Integer monthNow = cal.get(Calendar.MONTH) + 1;
        Integer dayNow = cal.get(Calendar.DATE);

        // 用当前年月日减去生日年月日
        Integer yearMinus = yearNow - personYear;
        Integer monthMinus = monthNow - personMonth;
        Integer dayMinus = dayNow - personDay;

        Integer age = yearMinus; //先大致赋值

        if (yearMinus == 0) { //出生年份为当前年份
            age = 0;
        } else { //出生年份大于当前年份
            if (monthMinus < 0) {//出生月份小于当前月份时，还没满周岁
                age = age - 1;
            }
            if (monthMinus == 0) {//当前月份为出生月份时，判断日期
                if (dayMinus < 0) {//出生日期小于当前月份时，没满周岁
                    age = age - 1;
                }
            }
        }
        return age;
    }

    private static String proxyHost = "";
    private static Integer proxyPort = null;
    private static String charset = "utf-8";

    /**
     * 发送get请求方法
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String openIdDoGet(String url) throws Exception {

        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }
        return resultBuffer.toString();
    }

    private static URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;
        if (proxyHost != null && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            connection = localURL.openConnection(proxy);
        } else {
            connection = localURL.openConnection();
        }
        return connection;
    }

    /**
     * 返回两个日期之间的月份集合
     *
     * @param minDate
     * @param maxDate
     * @return
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 校验时间是否是规定的格式
     * @param dateStr 时间
     * @param pattern 格式
     * @return
     */
    public static boolean isValidDate(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

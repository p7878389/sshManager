package com.manage.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/26.
 */
public class StringUtil {
    
    public static boolean isNull(String str) {
        return (null == str || str.trim().length() <= 0);
    }

    public static List<String> string2List(String str, String sep) {
        List<String> strList = new ArrayList<String>();
        if (isNull(str) || isNull(sep) || str.equals("null")) {
            return strList;
        }
        String[] strToken = str.split(sep);
        for (String ss : strToken) {
            strList.add(ss);
        }
        return strList;
    }

    public static boolean isEqualIgnoreCase(String str1, String str2) {
        if (null == str1 || null == str2)
            return false;
        return str1.toLowerCase().equals(str2.toLowerCase()) ? true : false;
    }

    public static boolean isEqual(String str1, String str2) {
        if (null == str1 || null == str2)
            return false;
        return str1.equals(str2) ? true : false;
    }

    /**
     * 判断字符串是否为空或者NULL
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        if ("".equals(str)) {
            return true;
        }
        if ("null".equals(str)) {
            return true;
        }
        return false;
    }

    public static String join(ArrayList<String> strAry, String join) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strAry.size(); i++) {
            if (i == (strAry.size() - 1)) {
                sb.append(strAry.get(i));
            } else {
                sb.append(strAry.get(i)).append(join);
            }
        }
        return new String(sb);

    }

    public static String clearBlank(String str) {
        if (isNull(str))
            return null;
        Pattern pattern = Pattern.compile("\\s*|\r|\n");
        Matcher matcger = pattern.matcher(str);
        str = matcger.replaceAll("");
        return str;
    }
}

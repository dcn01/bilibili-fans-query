package com.plain.bilibilitools.utils;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;

import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author : Plain
 * ClassName : com.plain.bilibilitools.utils
 * Description : 文本处理工具类
 * CreateDate : 2019-08-02 16:54
 * Version : V1.0
 */
public class TextUtils {

    /**
     * 解析分享得到的文本，得到真实的用户ID
     *
     * @param str str
     */
    public static String parsingShareUrlGetUserId(@NonNull String str) {
        //PlainDev的空间 https://space.bilibili.com/29491175?share_medium=android&share_source=more&bbid=dUQnQntJekN7TSwZKxx-SgpHDjYinfoc&ts=1564736018735
        Matcher matcher = Patterns.WEB_URL.matcher(str);
        if (matcher.find()) {
            String url = matcher.group();
            url = url.split("[?]")[0];
            url = url.replace("https://space.bilibili.com/", "");
            return url;
        }
        return "";
    }

}

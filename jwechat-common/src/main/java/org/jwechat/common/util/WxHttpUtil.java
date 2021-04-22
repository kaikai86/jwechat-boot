package org.jwechat.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxHttpUtil
 * @Description 微信公众号http工具类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Slf4j
public class WxHttpUtil {

    /**
     * 格式化url
     * @param uri
     * @param params
     * @return
     */
    public static String formatUrl(String uri, Map<String,Object> params) {
        if (params == null || params.isEmpty()) {
            return uri;
        }
        String url = uri.contains("?")?uri:uri.concat("?");
        int index = 0;
        int size = params.size();
        if (url.contains("=")) {
            url = url.concat("&");
        }
        for (String key : params.keySet()) {
            Object value = params.get(key);
            url  = url.concat(key).concat("=").concat(String.valueOf(value));
            if (index != size-1) {
                url = url.concat("&");
            }
            index ++;
        }
        log.info("url->{}",url);
        return url;
    }

    /**
     *json请求
     * @param uri   要请求的url
     * @param jsonBody  请求体
     * @param params    请求路径参数
     * @return
     */
    public static String httpPostJson(String uri,String jsonBody, Map<String,Object> params) {
        String url = formatUrl(uri, params);
        return HttpUtil.createPost(url)
                .header(Header.ACCEPT, "application/json")
                .body(jsonBody)
                .timeout(10000)//超时，毫秒
                .execute().body();
    }

    /**
     *json请求
     * @param uri   要请求的url
     * @param filePath  请求体
     * @param params    请求路径参数
     * @return
     */
    public static String httpPostFile(String uri,String filePath, Map<String,Object> params) {
        String url = formatUrl(uri, params);
        return  HttpUtil.createPost(url).form("media", FileUtil.file(filePath))
                .timeout(10000)//超时，毫秒
                .execute().body();
    }

    /**
     * json请求
     * @param uri   要请求的url
     * @param params    请求路径参数
     * @return
     */
    public static String httpGetJson(String uri, Map<String,Object> params) {
        String url = formatUrl(uri, params);
        return HttpUtil.createGet(url)
                .header(Header.ACCEPT, "application/json")
                .timeout(10000)//超时，毫秒
                .execute().body();
    }

    /**
     * 普通http请求
     * @param uri   要请求的url
     * @param params 请求路径参数
     * @return
     */
    public static String httpGet(String uri, Map<String,Object> params) {
        return HttpUtil.get(uri, params, 10000);
    }
}

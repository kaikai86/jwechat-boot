package org.jwechat.common.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Title WxDateUtil
 * @Description 微信时间工具类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Slf4j
public class WxDateUtil {



    /**
     * 格式化Unix时间戳(默认乘以1000)
     * @param unixTime unix时间戳
     * @return
     */
    public static String formatUnixTime(Long unixTime) {
        return formatUnixTime(unixTime,true);
    }

    /**
     * 格式化Unix时间戳
     * @param unixTime unix时间戳
     * @param isNeed1000 是否需要扩大1000倍
     * @return
     */
    public static String formatUnixTime(Long unixTime,boolean isNeed1000) {
        if (isNeed1000) {
            unixTime = unixTime * 1000;
        }
        return DateUtil.format(new Date(unixTime),"yyyyMMdd HH:mm");
    }
}

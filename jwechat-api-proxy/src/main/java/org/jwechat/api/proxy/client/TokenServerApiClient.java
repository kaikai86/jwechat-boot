package org.jwechat.api.proxy.client;

import org.jwechat.common.bean.WxMpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Title TokenServerApiClient
 * @Description 获取access_token
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@FeignClient(name = "jwechat-token-server", url = "${token.url}")
public interface TokenServerApiClient {

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    WxMpResult refresh();
}

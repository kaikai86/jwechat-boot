package org.jwechat.api.proxy.client;

import org.jwechat.common.bean.common.WxMpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/mp/refresh", method = RequestMethod.GET)
    WxMpResult refreshMP();

    @RequestMapping(value = "/corp/refresh", method = RequestMethod.GET)
    WxMpResult refreshCORP(@RequestParam(required = true,value="agentId") String agentId);

    @RequestMapping(value = "/tenant_corp/refresh", method = RequestMethod.GET)
    WxMpResult refreshCORP(@RequestParam(value="corpId") String corpId,@RequestParam(value="agentId") String agentId,@RequestParam(value="secret") String secret);

}

package com.red.feign;

import com.red.hystrix.HiServiceHystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author RedStar
 */
@FeignClient(value = "service-hi", fallback = HiServiceHystrixImpl.class)
public interface HiService {

    /**
     * 测试方法
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    /**
     * zipkin 测试方法
     *
     * @return
     */
    @RequestMapping(value = "/zipkinInfo", method = RequestMethod.GET)
    String getZipkinInfo();
}

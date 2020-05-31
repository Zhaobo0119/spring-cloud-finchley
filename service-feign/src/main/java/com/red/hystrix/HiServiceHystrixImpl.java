package com.red.hystrix;

import com.red.feign.HiService;
import org.springframework.stereotype.Component;

/**
 * @author RedStar
 */
@Component
public class HiServiceHystrixImpl implements HiService {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }

    @Override
    public String getZipkinInfo() {
        return "sorry zipkin info";
    }
}

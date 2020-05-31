package com.red.controller;

import brave.sampler.Sampler;
import com.red.feign.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author RedStar
 */
@RestController
public class HiController {

    private static final Logger LOG = Logger.getLogger(HiController.class.getName());

    private final HiService hiService;

    public HiController(HiService hiService) {
        this.hiService = hiService;
    }

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam(required = false, defaultValue = "zhao") String name) {
        return hiService.sayHiFromClientOne(name);
    }

    /**
     * zipkin 服务测试接口
     *
     * @return
     */
    @RequestMapping("/miya")
    public String info() {
        LOG.log(Level.INFO, "info is being called");
        return hiService.getZipkinInfo();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}

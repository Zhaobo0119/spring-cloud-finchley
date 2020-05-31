package com.red.controller;

import brave.sampler.Sampler;
import com.red.service.HelloService;
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
public class HelloController {

    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());

    final HelloService helloService;
    final RestTemplate restTemplate;

    public HelloController(HelloService helloService, RestTemplate restTemplate) {
        this.helloService = helloService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }

    /**
     * zipkin 测试接口
     *
     * @return
     */
    @RequestMapping("/zipkinHi")
    public String callHome() {
        LOG.log(Level.INFO, "calling trace service-hi  ");
        return helloService.zipkinHiService();
    }

    @RequestMapping("/zipkinInfo")
    public String info() {
        LOG.log(Level.INFO, "calling trace service-hi ");
        return "i'm service-hi";

    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}

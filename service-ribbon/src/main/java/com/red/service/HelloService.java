package com.red.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author RedStar
 */
@Service
public class HelloService {

    final RestTemplate restTemplate;

    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }


    @HystrixCommand(fallbackMethod = "hiError")
    public String zipkinHiService() {
        return restTemplate.getForObject("http://SERVICE-HI/miya", String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

    public String hiError() {
        return "hi miya,sorry,error!";
    }

}

package com.red.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author RedStar
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EurekaClientApplication {

    private static final Logger LOG = Logger.getLogger(EurekaClientApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "zhao") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @RequestMapping("/miya")
    public String miya() {
        return "hi ,i am miya";
    }

    /**
     * @return
     */
    @RequestMapping("/zipkinInfo")
    public String info() {
        LOG.log(Level.INFO, "calling trace service-hi ");
        return "i'm service-hi =》 info";

    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}

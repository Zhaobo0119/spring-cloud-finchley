package com.red;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author RedStar
 * 依次运行这五个工程;打开浏览器访问：http://localhost:8769/api-a/hi?name=zhao ;浏览器显示：
 * hi zhao,i am from port:8762
 * 打开浏览器访问：http://localhost:8769/api-b/hi?name=zhao ;浏览器显示：
 * hi zhao,i am from port:8762
 * 这说明zuul起到了路由的作用
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ServiceZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
    }
}

在上面的配置中，配置了一个Path 的predict,将以/demo/**开头的请求都会转发到uri为lb://SERVICE-HI的地址上，

lb://SERVICE-HI即service-hi服务的负载均衡地址，并用StripPrefix的filter 

在转发之前将/demo去掉。同时将spring.cloud.gateway.discovery.locator.enabled改为false，

如果不改的话，之前的localhost:8081/service-hi/hi?name=1323这样的请求地址也能正常访问，

因为这时为每个服务创建了2个router。

在浏览器上请求localhost:8081/demo/hi?name=1323，浏览器返回以下的响应：
##Spring Cloud Netflix / Nodejs
尝试使用Spring Cloud Netflix 加 Nodejs 技术栈混合搭建微服务。 （示例并无任何业务意义，只为做演示）
> 此库不在有更新，其演进版参考https://github.com/choelea/spring-cloud-nodejs, 在此基础上增加了全链路跟踪，服务监控及跨服务日志跟踪

**代码：** https://github.com/choelea/spring-cloud-netflix/  **ref：** tags/micros-service-hybrid-demo
###  相关版本依赖
**Spring Boot：** 1.5.4.RELEASE
**Spring Cloud: ** Dalston.SR1
**Nodejs：**  7.2.0  (本机是7.2.0的，没有在其他版本上做测试)
## 架构图 (Architecture for microservice)
![架构图](http://img.blog.csdn.net/20170701223331565?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2hvZWxlYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

 - **eureka-server：** Spring Boot + Cloud 技术栈搭建eureka 服务。（服务注册中心）
 - **bookmark-service：** Spring Boot 的微服务程序
 - **nodejs-bookservice** nodejs开发的微服务
 - **spring-apigateway** Spring Boot + Cloud Netflix技术栈搭建的网关
 - **nodejs-web** nodejs开发的网关兼web应用

## 程序运行
按照上面的顺序依次运行。  Spring Boot的程序运行：`mvn spring-boot:run` ; nodejs 程序运行：`npm start` 
###查看服务注册情况： 
打开http://localhost:8761/
![eureka 服务](http://img.blog.csdn.net/20170701224809235?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2hvZWxlYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
instance信息的获取主要通过下面的链接：
 - http://localhost:8761/eureka/apps  获取整个注册进来的服务的信息
 - http://localhost:8761/eureka/apps/{app} 获取某个服务的所有的实例信息 例如：http://localhost:8761/eureka/apps/BOOK-SERVICE  
 - http://localhost:8761/eureka/apps/{app}/{instanceId}  获取具体的instance的信息
> nodejs 使用eureka-js-client 来配置服务的instance信息，需要配置的信息更多，也更直观的反应了instance的信息；和通过连接（http://localhost:8761/eureka/apps）查到的基本一致。

nodejs的微服务app，在eureka的client的配置中最好保持app，vipAddress（secureVipAddress 一般不会用上）一致。
> 经测试发现，Spring Boot / Cloud Netflix 技术栈开发的apigateway，采用Zuul Reverse Proxy 反向代理的时候，必须app 和 vipAddress设置一致。 多个instance通过instanceId来区分。

### 测试spring-apigateway
spring-apigateway 作为eureka的客户端结合zuul proxy的反向代理，为多个微服务提供一个单一的访问节点。
访问http://localhost:8080/bookmarks/jlong/bookmarks （结果同http://localhost:9098/jlong/bookmarks 完全一致）

```
[
	{
		"userId": "jlong",
		"id": 2,
		"href": "http://some-other-hostjlong.com/",
		"description": "A description for jlong's link",
		"label": "jlong"
	}
]
```
访问http://localhost:8080/books  反向代理至nodejs-bookservice的服务：http://localhost:3001/books

```
[
	{
		"bookname": "Nodejs Web Development",
		"author": "David Herron"
	},
	{
		"bookname": "Mastering Web Application Development with Express ",
		"author": "Alexandru Vlăduțu"
	}
]
```

### 测试nodejs
nodejs 采用eureka-js-client 组件获取/注册微服务。这里nodejs-web只作为服务的消费方，接入eureka server，消费上游的服务并展示给客户端（浏览器）。 
查看nodejs-web作为eureka client 获取到注册的服务信息，通过向服务直接发起request来获取数据并展示。访问：http://localhost:3000 即可看如下显示：
![这里写图片描述](http://img.blog.csdn.net/20170701225905199?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2hvZWxlYQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

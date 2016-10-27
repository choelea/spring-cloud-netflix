
server:
  port: ${vcap.application.port:8050}


eureka:
  client:
    serviceUrl:
      defaultZone: ${vcap.services.eureka-service.credentials.uri:http://127.0.0.1:8761}/eureka/

---
spring:
  profiles: cloud
eureka:
  instance:
    hostname: ${APPLICATION_DOMAIN}
#    nonSecurePort: 80

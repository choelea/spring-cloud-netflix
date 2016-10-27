package com.hanover.composite;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CompositeServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CompositeServiceApplication.class)
                .web(false)
                .run(args);
    }
}

/*@Component
class DiscoveryClientExample implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... strings) throws Exception {
//        discoveryClient.getInstances("photo-service").forEach((ServiceInstance s) -> {
//            System.out.println(ToStringBuilder.reflectionToString(s));
//        });
        discoveryClient.getInstances("bookmark-service").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
        });
    }
}*/

//@Component
//class RestTemplateExample implements CommandLineRunner {
//
//    
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Override
//    public void run(String... strings) throws Exception {
//        // use the "smart" Eureka-aware RestTemplate
//        ResponseEntity<List<Bookmark>> exchange =
//                this.restTemplate.exchange(
//                        "http://bookmark-service/{userId}/bookmarks",
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<List<Bookmark>>() {
//                        },
//                        (Object) "mstine");
//System.out.println("-------------------------------");
//        exchange.getBody().forEach(System.out::println);
//    }
//
//}

@Component
class FeignExample implements CommandLineRunner {

    @Autowired
    private BookmarkClient bookmarkClient;

    @Autowired
    private HrClient hrClient;
    @Override
    public void run(String... strings) throws Exception {
        this.bookmarkClient.getBookmarks("jlong").forEach((Bookmark mark) -> {
            System.out.println("----------------------"+mark);
        });
        System.out.println("=============="+hrClient.syncEmployeeToAuth(999999l));
    }
}
@FeignClient("hr")
interface HrClient {
	@RequestMapping(value="/datasync/hr/auth/employees",method = RequestMethod.GET)
	public CommonData<List<SyncEmployeeToAuth>> syncEmployeeToAuth(@RequestParam(value = "startTime", required = true) Long startTime) ;
	
	@RequestMapping(value="/hr/departments",method = RequestMethod.GET)
	public CommonData<List<OutDepartment>> getDepartmentTree(@RequestHeader("X-Auth-Token") String token) ;
}
@FeignClient("bookmark-service")
interface BookmarkClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/bookmarks")
    List<Bookmark> getBookmarks(@PathVariable("userId") String userId);
}

class Bookmark {
    private Long id;
    private String href, label, description, userId;

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", href='" + href + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Bookmark() {
    }

    public Long getId() {
        return id;
    }

    public String getHref() {
        return href;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }
}









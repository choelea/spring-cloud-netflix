package com.joe.bookmark;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.bookmark.entity.Bookmark;
import com.joe.bookmark.repository.BookmarkRepository;

@SpringBootApplication
@EnableEurekaClient
public class BookmarkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmarkServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BookmarkRepository bookmarkRepository) {
        return args -> {
            bookmarkRepository.deleteAll();

            Arrays.asList("mstine", "jlong").forEach(n ->
                    bookmarkRepository.save(new Bookmark(n,
                            "http://some-other-host" + n + ".com/",
                            "A description for " + n + "'s link",
                            n)));
        };
    }
    
    @RefreshScope
    @RestController
    class MessageRestController {

        @Value("${message:Hello default}")
        private String message;

        @RequestMapping("/message")
        String getMessage() {
            return this.message;
        }
    }
}






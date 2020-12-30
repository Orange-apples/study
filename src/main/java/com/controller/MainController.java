package com.controller;

import com.webcsoket.MyWebSocket;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@RestController
public class MainController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    private static MyWebSocket myWebSocket = new MyWebSocket();

    @RequestMapping(value = "/user", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> user() {
        return Flux.interval(Duration.ofSeconds(2)).map(s -> {
            List<String> range = stringRedisTemplate.opsForList().range("newOrder:shop:1", 0, -1);
            if (range != null) {
                return range.toString();
            } else {
                return null;
            }
        });
    }

    @GetMapping("/send")
    public void send(String name, String msg) throws IOException {
        msg = "【" + name + "】:" + msg;
        myWebSocket.onMessage(msg);
    }
}

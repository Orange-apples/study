package com.controller;

import com.webcsoket.MyWebSocket;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Controller
public class MainController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    private static MyWebSocket myWebSocket = new MyWebSocket();

    @ResponseBody
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

    @GetMapping("/index")
    public String getURL(@PathParam("path") String path) {
        return "/index";
    }
}

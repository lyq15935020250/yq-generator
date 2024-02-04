package com.yq.web.vertx;

import com.yq.web.manager.CacheManager;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author lyq
 * @description:
 * @date 2024/2/4 18:13
 */
@Component
public class VertxManager {
    @Resource
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        Verticle myVerticle = new MainVerticle(cacheManager);
        vertx.deployVerticle(myVerticle);
    }
}

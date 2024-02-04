package com.yq.web.manager;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.json.JSONUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yq.web.model.dto.generator.GeneratorQueryRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 多级缓存
 *
 * @author lyq15935020250
 */
@Component
public class CacheManager {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 本地缓存
     */
    Cache<String, Object> localCache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    /**
     * 写缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        localCache.put(key, value);
        redisTemplate.opsForValue().set(key, value, 100, TimeUnit.MINUTES);
    }

    /**
     * 读缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        // 先从本地缓存中尝试读取
        Object value = localCache.getIfPresent(key);
        if (value != null){
            return value;
        }
        // 本地缓存未命中，尝试从 Redis 中获取
        value = redisTemplate.opsForValue().get(key);
        if (value != null){
            // 将 Redis 中的值放入本地缓存
            localCache.put(key,value);
        }
        return value;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void delete(String key) {
        localCache.invalidate(key);
        redisTemplate.delete(key);
    }

    /**
     * 获取缓存文件的路径
     *
     * @param id
     * @param distPath
     * @return
     */
    public static String getCacheFilePath(long id, String distPath) {
        String projectPath = System.getProperty("user.dir");
        String tempDirPath = String.format("%s/.temp/cache/%s", projectPath, id);
        return String.format("%s/%s", tempDirPath, distPath);
    }

    /**
     * 获取分页缓存 key
     *
     * @param generatorQueryRequest
     * @return
     */
    public static String getPageCacheKey(GeneratorQueryRequest generatorQueryRequest) {
        String jsonStr = JSONUtil.toJsonStr(generatorQueryRequest);
        String base64 = Base64Encoder.encode(jsonStr);
        String key = "generator:page" + base64;
        return key;
    }


}

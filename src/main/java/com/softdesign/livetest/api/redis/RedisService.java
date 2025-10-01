package com.softdesign.livetest.api.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void salvar(String chave, String valor) {
        redisTemplate.opsForValue().set(chave, valor);
    }

    public String buscar(String chave) {
        return redisTemplate.opsForValue().get(chave);
    }
}

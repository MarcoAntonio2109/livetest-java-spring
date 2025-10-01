package com.softdesign.livetest.api.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/set")
    public String set(@RequestParam String chave, @RequestParam String valor) {
        redisService.salvar(chave, valor);
        return "Salvo!";
    }

    @GetMapping("/get")
    public String get(@RequestParam String chave) {
        return redisService.buscar(chave);
    }
}

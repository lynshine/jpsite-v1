package com.mty.jpsite.controller.elastic;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mty.jpsite.domain.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestClientController {
    @RequestMapping("/restclient/book/{id}")
    public String getLogByid(@PathVariable String id) throws Exception {
        Book book = null;
        RestTemplate template = new RestTemplate();
        Map<String, Object> paras = new HashMap<>();
        paras.put("id", id);
//得到响应的 JSON
        String str = template.getForObject("http://172.0.0.1:9200/product/book/{id}", String.class, paras);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(str); //只对返回 的 JSON 的 source 字段感兴趣
        JsonNode root = mapper.readTree(parser);
        JsonNode sourceNode = root.get("_source");
//将 source 字段的文档部分映射到 Book 对象
        book = mapper.convertValue(sourceNode, Book.class);
        return book.getMessage();
    }
}

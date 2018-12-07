//package com.mty.jpsite.controller;
//
//import com.fasterxml.jackson.core.*;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RestController
//public class JsonController {
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @GetMapping("/now.json")
//    @ResponseBody
//    public Map datetime() {
//        Map map = new HashMap();
//        map.put("time", new Date());
//        return map;
//    }
//
//    /**
//     * Jackson树遍历
//     *
//     * @return
//     * @throws IOException
//     **/
//    @GetMapping("/readtree.json")
//    @ResponseBody
//    public Map readtree() throws IOException {
//        String json = "{\"name\":\"lijz\",\"id\":10}";
//        JsonNode node = objectMapper.readTree(json);
//        String name = node.get("name").asText();
//        int id = node.get("id").asInt();
////        return "name: " + name + " ,id: " + id + " ,data: " + new Date();
//        Map map = new HashMap();
//        map.put("time", new Date());
//        map.put("name", name);
//        map.put("id", id);
//        return map;
//    }
//
//    /**
//     * 对象绑定
//     *
//     * @GetMapping("/databind.json")
//     * @ResponseBody
//     **/
//
//    public String databind() throws IOException {
//        String json = "{\"name\":\"lijz\",\"password\":\"123456\"}";
//        User user = objectMapper.readValue(json, User.class);
//        return "name: " + user.getName() + " ,id: ";
//    }
//
//    @GetMapping("/serialization.json")
//    @ResponseBody
//    public String serialization() throws JsonProcessingException {
//        User user = new User();
//        user.setName("jls");
//        String str = objectMapper.writeValueAsString(user);
//        return str;
//    }
//
//    /**
//     * 流式操作
//     *
//     * @RequestMapping("/parser.html")
//     * @ResponseBody
//     **/
//    public Map parser() throws IOException {
//        Map map = new HashMap();
//        String json = "{\"name\":\"lijz\",\"password\":\"123456\"}";
//        JsonFactory jsonFactory = objectMapper.getFactory();
//        String key = null, value = null;
//        //JsonParser的解析结果包含了一系列JsonToken
//        JsonParser parser = jsonFactory.createParser(json);
//        //{,  START_OBJECT，忽略第一個Token
//        JsonToken token = parser.nextToken();
//        while (parser.hasCurrentToken()) {
//            token = parser.nextToken();
//            if (token == JsonToken.FIELD_NAME) {
//                key = parser.getCurrentName();
//            }
//            if (token == JsonToken.END_OBJECT) {
//                break;
//            }
//            parser.nextToken();
//            value = parser.getValueAsString();
//            map.put(key, value);
//        }
//        parser.close();
//        return map;
//    }
//
//    @RequestMapping("/generator.html")
//    public @ResponseBody
//    String generator() throws IOException {
//        JsonFactory jsonFactory = objectMapper.getFactory();
//        StringWriter sw = new StringWriter();
//        JsonGenerator generator = jsonFactory.createGenerator(sw);
//        generator.writeStartObject();
//        generator.writeStringField("name", "jls");
//        generator.writeEndObject();
//        generator.close();
//        return sw.toString();
//    }
//}

package com.mty.jpsite.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangpeng on 2018/10/24.
 */
@RestController
public class UserReditRestController {
    @RequestMapping(value = "/usercredit/{id}")
    public String getCreditLevel(@PathVariable String id) {
        return id;
    }

    @PostMapping("/form")
    @ResponseBody
    public String handleFormUpload(String name, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            return "succuess";
        }
        return "false";
    }
}

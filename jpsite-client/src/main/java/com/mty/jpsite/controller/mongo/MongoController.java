/*
package com.mty.jpsite.controller;

import com.mty.jpsite.domain.Baike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

*/
/**
 * Created by jiangpeng on 2018/11/1.
 *//*

@RestController
public class MongoController {
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/addbaike")
    public Baike addDict(Baike baike) {
        baike.setCreateDate(new Date());
        mongoTemplate.insert(baike);
        return baike;
    }

    @GetMapping("/baike/{name}")
    public Baike findUser(@PathVariable String name) {
        Baike diet = mongoTemplate.findById(name, Baike.class);
        return diet;
    }

    @GetMapping("/querybad/{bad}")
    public List<Baike> queryBad(@PathVariable int bad) {
        Criteria criteria = where("comment.bad").gt(bad);
        Criteria criteria2 = where("comment.good").lt(6);
        List<Baike> list = mongoTemplate.find(query(criteria.andOperator(criteria2)), Baike.class);
        return list;
    }
}
*/

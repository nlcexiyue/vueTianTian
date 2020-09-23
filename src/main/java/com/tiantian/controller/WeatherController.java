package com.tiantian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.tiantian.entity.Province;
import com.tiantian.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/12
 * \* Time: 15:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class WeatherController {

    @Autowired
    private ProvinceService provinceService;



    @GetMapping(value = "/addProvince")
    public void addProvince(){
        String url = "http://www.nmc.cn/rest/province/all?";
        Date date = new Date();
        long time = date.getTime();
        url = url  + "_="+time;

        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
        if (forEntity.getStatusCode() == HttpStatus.OK) {
            String body = forEntity.getBody();
            JSONArray jsonArray = JSON.parseArray(body);
            for (Object o : jsonArray) {
                JSONObject jsonObject = JSON.parseObject(o.toString());
                String code = jsonObject.getString("code");
                String name = jsonObject.getString("name");
                String url1 = jsonObject.getString("url");
                Province province = new Province();
                province.setName(name);
                province.setAddreviation(code);
                province.setUrl(url1);
                provinceService.insert(province);
            }


            System.out.println(url + "访问成功" );
        } else {
            System.out.println(url + "访问失败");
        }
    }



    public static void main(String[] args) {

        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // mysql驱动
            con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.2.177:3306/novel?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8",
                    "root", "root");

            Statement ps =  (Statement) con.createStatement();
            String sql = "select * from province";
            ResultSet rs = ps.executeQuery(sql);
//            List<String> list = Lists.newArrayList();
            while (rs.next()) {
                // 循环输出结果集
                String addreviation = rs.getString("addreviation");
                String id = rs.getString("id");

                String url = "http://www.nmc.cn/rest/province/";
                url = url + addreviation + "?";
                Date date = new Date();
                long time = date.getTime();
                url = url  + "_="+time;
                ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
                if (forEntity.getStatusCode() == HttpStatus.OK){
                    String body = forEntity.getBody();

                    JSONArray jsonArray = JSON.parseArray(body);
                    for (Object o : jsonArray) {
                        JSONObject jsonObject = JSON.parseObject(o.toString());
                        String code = jsonObject.getString("code");
                        String city1 = jsonObject.getString("city");
                        String url1 = jsonObject.getString("url");


                        Statement ps1 =  (Statement) con.createStatement();
                        String sql1 = "insert into city values (null ,\'"+code+"\',\'"+city1+"\',\'"+id +"\',\'"+url1+"\' )";
                        ps1.execute(sql1);
                    }
                }





//                list.add(addreviation);
            }
//            System.out.println("加载到" + list.size() + "个省份");


            // 完成后关闭
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("MYSQL error" + e.getMessage());
        }


    }

}
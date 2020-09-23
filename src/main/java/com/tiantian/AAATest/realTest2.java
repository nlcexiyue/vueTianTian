package com.tiantian.AAATest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/12
 * \* Time: 17:45
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class realTest2 {
    public static void main(String[] args) {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // mysql驱动
            con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.2.177:3306/novel?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8",
                    "root", "root");
            while (true){
                Statement ps =  (Statement) con.createStatement();
                String sql = "select * from city";
                ResultSet rs = ps.executeQuery(sql);
                int a= 1;
                while (rs.next()) {
                    a++;
                    if (a == 100){
                        Thread.sleep(10000);
                        a =1;
                    }
                    // 循环输出结果集
                    String cityCode = rs.getString("code");
                    String cityId = rs.getString("id");

                    Connection finalCon = con;
                    Thread thread = new Thread(() -> {
                        String url = "http://www.nmc.cn/rest/weather?stationid=";
                        url = url + cityCode;
                        Date date1 = new Date();
                        long time = date1.getTime();
                        url = url  + "&_="+time;
                        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
                        String body = forEntity.getBody();
                        JSONObject jsonObject = JSON.parseObject(body);
                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                        JSONObject jsonObjectReal = jsonObjectData.getJSONObject("real");
                        if (jsonObjectReal != null){
                            String publishTime = jsonObjectReal.getString("publish_time");

                            Statement ps3 = null;
                            try {
                                ps3 = (Statement) finalCon.createStatement();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            String sql3 = "select id from `real` where publish_time = \'"+publishTime+"\' and city_id = \'"+cityId+"\'";
                            ResultSet rs3 = null;
                            try {
                                rs3 = ps3.executeQuery(sql3);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (!rs3.next()){
                                    JSONObject jsonObjectWeather = jsonObjectReal.getJSONObject("weather");
                                    String airpressure = jsonObjectWeather.getString("airpressure");
                                    String feelst = jsonObjectWeather.getString("feelst");
                                    String humidity = jsonObjectWeather.getString("humidity");
                                    String info = jsonObjectWeather.getString("info");
                                    String rain = jsonObjectWeather.getString("rain");
                                    String temperature = jsonObjectWeather.getString("temperature");
                                    String temperatureDiff = jsonObjectWeather.getString("temperatureDiff");
                                    JSONObject jsonObjectWind = jsonObjectReal.getJSONObject("wind");
                                    String direct = jsonObjectWind.getString("direct");
                                    String power = jsonObjectWind.getString("power");
                                    String speed = jsonObjectWind.getString("speed");

                                    JSONObject jsonObjectWarn = jsonObjectReal.getJSONObject("warn");
                                    String alert = jsonObjectWarn.getString("alert");
                                    String fmeans = jsonObjectWarn.getString("fmeans");
                                    String issuecontent = jsonObjectWarn.getString("issuecontent");
                                    String pic = jsonObjectWarn.getString("pic");
                                    String pic2 = jsonObjectWarn.getString("pic2");
                                    String signallevel = jsonObjectWarn.getString("signallevel");
                                    String signaltype = jsonObjectWarn.getString("signaltype");
                                    String url1 = jsonObjectWarn.getString("url");

                                    Statement ps1 = null;
                                    try {
                                        ps1 = (Statement) finalCon.createStatement();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    String sql1 = "insert into `real` values (null ,\'"+cityId+"\',\'"+airpressure+"\',\'"+feelst+"\',\'"+humidity+"\',\'"+info+"\',\'"+rain+"\',\'"
                                            +temperature+"\',\'"+temperatureDiff +"\',\'"+direct+"\',\'"+power+"\',\'"+speed+"\',\'"+publishTime+"\' )";
                                    try {
                                        ps1.execute(sql1);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    Statement ps2 = null;
                                    try {
                                        ps2 = (Statement) finalCon.createStatement();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    String sql2 = "insert into warn values (null ,\'"+cityId+"\',\'"+alert+"\',\'"+fmeans+"\',\'"+issuecontent+"\',\'"+pic+"\',\'"+pic2+"\',\'"
                                            +signallevel+"\',\'"+signaltype +"\',\'"+url1+"\' )";
                                    try {
                                        ps2.execute(sql2);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                    });
                    thread.start();

                }
//                // 完成后关闭
//                rs.close();
//                con.close();
            }
        } catch (Exception e) {
            System.out.println("MYSQL error" + e.getMessage());
        }







    }


}
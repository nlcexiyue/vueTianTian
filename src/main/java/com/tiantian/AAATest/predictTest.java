package com.tiantian.AAATest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/12
 * \* Time: 17:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class predictTest {
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
                while (rs.next()) {
                    // 循环输出结果集
                    String cityCode = rs.getString("code");
                    String cityId = rs.getString("id");

                    String url = "http://www.nmc.cn/rest/weather?stationid=";
                    url = url + cityCode;
                    Date date1 = new Date();
                    long time = date1.getTime();
                    url = url  + "&_="+time;
                    ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
                    String body = forEntity.getBody();
                    JSONObject jsonObject = JSON.parseObject(body);
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    JSONObject jsonObjectPredict = jsonObjectData.getJSONObject("predict");
                    if (jsonObjectPredict != null){
                        JSONArray detail = jsonObjectPredict.getJSONArray("detail");
                        for (Object o : detail) {
                            JSONObject jsonObject1 = JSON.parseObject(o.toString());
                            String date = jsonObject1.getString("date");

                            Statement ps2 =  (Statement) con.createStatement();
                            String sql2 = "select * from predict where date = \'"+date+"\' and city_id = \'"+cityId+"\'";
                            ResultSet rs2 = ps2.executeQuery(sql2);
                            if(!rs2.next()){
                                String pt = jsonObject1.getString("pt");
                                JSONObject jsonObjectDay = jsonObject1.getJSONObject("day");
                                JSONObject jsonObjectDayWeather = jsonObjectDay.getJSONObject("weather");
                                String infoDay = jsonObjectDayWeather.getString("info");
                                String imgDay = jsonObjectDayWeather.getString("img");
                                String temperatureDay = jsonObjectDayWeather.getString("temperature");
                                JSONObject jsonObjectDayWind = jsonObjectDay.getJSONObject("wind");
                                String directDay = jsonObjectDayWind.getString("direct");
                                String powerDay = jsonObjectDayWind.getString("power");

                                JSONObject jsonObjectNight = jsonObject1.getJSONObject("night");
                                JSONObject jsonObjectNightWeather = jsonObjectNight.getJSONObject("weather");
                                String infoNight = jsonObjectNightWeather.getString("info");
                                String imgNight = jsonObjectNightWeather.getString("img");
                                String temperatureNight = jsonObjectNightWeather.getString("temperature");
                                JSONObject jsonObjectNightWind = jsonObjectNight.getJSONObject("wind");
                                String directNight = jsonObjectNightWind.getString("direct");
                                String powerNight = jsonObjectNightWind.getString("power");

                                Statement ps1 =  (Statement) con.createStatement();
                                String sql1 = "insert into predict values (null ,\'"+cityId+"\',\'"+imgDay+"\',\'"+infoDay+"\',\'"+temperatureDay+"\',\'"+directDay+"\',\'"+powerDay+"\',\'"
                                        +imgNight+"\',\'"+infoNight +"\',\'"+temperatureNight+"\',\'"+directNight+"\',\'"+powerNight+"\',\'"+date+"\',\'"+pt+"\' )";
                                ps1.execute(sql1);
                            }
                        }

                    }
                    Thread.sleep(2000);
                }
                // 完成后关闭
                rs.close();
                con.close();
            }

        } catch (Exception e) {
            System.out.println("MYSQL error" + e.getMessage());
        }






    }


}
package com.tiantian.pachong;



import com.tiantian.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/7/21
 * \* Time: 9:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PaChong {

    final String url = "jdbc:mysql://192.168.2.177:3306/novel?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    final String driverClassName = "com.mysql.jdbc.Driver";
    final String username = "root";
    final String password = "root";
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public void csdn() throws ClassNotFoundException, SQLException {


        try {
            String Url = "https://www.csdn.net/";
            Document document = Jsoup.connect(Url)
                    .timeout(10000)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0")
                    .get();
            Elements element = document.select(".carousel-caption");
            for (Element element1 : element) {
                String text = element1.text();
//                System.out.println(text);

            }


            Elements element1 = document.select(".summary");

            for (Element element2 : element1) {
                String text1 = element2.text();
                System.out.println(text1);
                String title = "";
                if (text1.length() > 20) {
                    title = text1.substring(0, 20);
                } else {
                    title = text1;
                }
                News news = new News();
                news.setNewsName(title);
                news.setNewsAuthor("1");
                news.setNewsAbstract(text1);
                news.setNewsStatus(0);
                news.setNewsLook("开放预览");
                news.setNewsTop("checked");
                news.setNewsTime(new Date());
                news.setContent("<p>" + text1 + "</p>");

//                ResultData insert = newsService.insert(news);
//                System.out.println(insert);

                System.out.println(text1);

                Connection conn = null;
                Class.forName(driverClassName);//指定连接类型
                conn = DriverManager.getConnection(url, username, password);//获取连接
                //设置事务非自动提交
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                String format = simpleDateFormat.format(new Date());
                String sql = "insert into news (id, news_name, news_author, news_abstract, news_status, news_img, news_look, news_top, news_time, content) " +
                        "values ("+null+", '"+title+"','1', '"+text1+"', 0, "+null+", '开放预览', 'checked', '"+format+"', '"+ text1 +"')";
                st.executeUpdate(sql);
                conn.commit();
                st.close();
                conn.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
package Movie.JSON.Crawling;

import com.fasterxml.jackson.core.JsonEncoding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.net.URLEncoder;

@SpringBootApplication
public class Melon {
        public static void main(String[] args) {
                SpringApplication.run(Melon.class, args);

                try {
                        JsonObject melonDataOBJ = new JsonObject();
                        JsonArray songInfo = new JsonArray();

                        Document document1 = Jsoup.connect("https://www.melon.com/new/index.htm").get();
                        Elements melon_song = document1.getElementsByTag("tbody");
                        Elements file = melon_song.select("a");

                        String url="https://www.melon.com/chart/index.htm";

                        //크롤링하고싶은 대상 url 을 적는다

                        Document doc=Jsoup.connect(url).get();

                        Elements titles=doc.select("div.ellipsis>span>a");
                        //하단의 그림참조하여 적는다
                        for(Element e : titles) {
                                String song=e.text();
                                System.out.println("제목:"+song);
                        }

                        System.out.println("============");

                        // System.out.println(file);
                } catch(Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}

package Melon.JSON.Crawling;

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
import java.util.ArrayList;

@SpringBootApplication
public class Melon {
        public static void main(String[] args) {
                SpringApplication.run(Melon.class, args);

                try {
                        JsonObject melonDataOBJ = new JsonObject();
                        JsonArray songInfo = new JsonArray();

                        // 인터넷 코드와 연결
                        Document document1 = Jsoup.connect("https://www.melon.com/new/index.htm#params%5BareaFlg%5D=I&po=pageObj&startIndex=201").get();

                        // 노래 제목 뽑기
                        Elements melon_song = document1.getElementsByClass("ellipsis rank01"); // 클래스 명으로 큰 틀 지정
                        Elements songTitle = melon_song.select("span>a"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감
                        Elements melon_singer = document1.getElementsByClass("ellipsis rank02"); // 클래스 명으로 큰 틀 지정
                        Elements singerName = melon_singer.select("span"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감

                        ArrayList<String> songAndSinger = new ArrayList<>();
                        String[] songArray = new String[melon_song.size()];
                        String[] singerArray = new String[melon_song.size()];

                        for(int i = 0; i < melon_song.size(); i++) {
                                songArray[i] = songTitle.get(i).text().split("\n")[0];
                                singerArray[i] = singerName.get(i).text().split("\n")[0];
//				System.out.println("songArray[ " + i + "] : " + songArray[i]);
//				System.out.println("singerArray[ " + i + "] : " + singerArray[i]);

                                songAndSinger.add(songArray[i] + "_" + singerArray[i]);
				System.out.println("songAndSinger[ " + i + " ] : " + songAndSinger.get(i));
                        }


                } catch(Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}

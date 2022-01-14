package Movie.JSON.Crawling;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Melon {
        public static void main(String[] args) {
                SpringApplication.run(Melon.class, args);

                try {
                        JsonObject melonDataOBJ = new JsonObject();
                        JsonArray songInfo = new JsonArray();

                        // 인터넷 코드와 연결
                        Document document1 = Jsoup.connect("https://www.melon.com/new/index.htm").get();
                        // 클래스 명으로 큰 틀 지정
//                        Elements melon_song = document1.getElementsByClass("wrap_song_info");
//			// 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감
//                        Elements file = melon_song.select("div>div>span>a");

                        // 노래 제목 뽑기
                        Elements melon_song = document1.getElementsByClass("ellipsis rank01"); // 클래스 명으로 큰 틀 지정
                        Elements songTitle = melon_song.select("span>a"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감
                        // 가수 뽑기
                        Elements melon_singer = document1.getElementsByClass("ellipsis rank02"); // 클래스 명으로 큰 틀 지정
                        Elements singerName = melon_singer.select("span"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감

                        ArrayList<String> songAndSinger = new ArrayList<>();
                        String[] songArray = new String[melon_song.size()];
                        String[] singerArray = new String[melon_song.size()];
                        for(int i = 0; i < melon_song.size(); i++) {
                                songArray[i] = songTitle.get(i).text().split("\n")[0];
                                singerArray[i] = singerName.get(i).text().split("\n")[0];
//				System.out.println(songArray[i]);
//				System.out.println(songArray[i]);
                        }

                        System.out.println(singerName.text().split(",")[0]);
//			System.out.println(singerName);
                } catch(Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}

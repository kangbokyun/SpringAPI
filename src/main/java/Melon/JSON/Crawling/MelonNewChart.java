package Melon.JSON.Crawling;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

public class MelonNewChart {
        public static void main(String[] args) {
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
			// 가수명 뽑기
			Elements melon_singer = document1.getElementsByClass("ellipsis rank02"); // 클래스 명으로 큰 틀 지정
                        Elements singerName = melon_singer.select("span"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감
			// 앨범컷 뽑기
			Elements song_img = document1.getElementsByClass("image_typeAll"); // 클래스 명으로 큰 틀 지정
                        Elements imgName = song_img.select("img"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감
			// 좋아요 뽑기
			Elements songLike = document1.getElementsByClass("cnt");

//			System.out.println(songLike.text());

			ArrayList<String> songAndSinger = new ArrayList<>(); // 곡명과 가수명을 합칠 리스트
			String[] songArray = new String[melon_song.size()]; // 곡명
			String[] singerArray = new String[melon_song.size()]; // 가수명
			String[] songIMGArray = new String[melon_song.size()];
			for(int i = 0; i < melon_song.size(); i++) {
				songArray[i] = songTitle.get(i).text().split("\n")[0];
				singerArray[i] = singerName.get(i).text().split("\n")[0];
				// attr("abs:src")는 src라는 속성 값의 절대 경로를 달라는 뜻이므로 URL 뿐 아니라 도메인도 붙어서 오게 된다.
				songIMGArray[i] = imgName.get(i).attr("abs:src");
				System.out.println(songArray[i] + " : " + singerArray[i] + " / " + songIMGArray[i]);
			}

//			System.out.println(singerName);
                } catch(Exception e) {
                        System.out.println(e.getMessage());
                }
        }
}

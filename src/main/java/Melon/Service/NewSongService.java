package Melon.Service;

import Melon.Domain.DTO.NewSongDTO;
import Melon.Domain.Entity.NewSongEntity;
import Melon.Domain.Entity.NewSongRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewSongService {
	@Autowired
	NewSongRepository newSongRepository;

	@Autowired
	HttpSession session;

	// 최신곡 가져오기
	public ArrayList<NewSongDTO> getNewSong() {
		List<NewSongEntity> newSongEntityList = newSongRepository.findAll();
		ArrayList<NewSongDTO> addArray = new ArrayList<>();
		NewSongDTO newSongDTO = null;
		for (NewSongEntity newSongEntity : newSongEntityList) {
			String date = newSongEntity.getCreateTime().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
			newSongDTO = new NewSongDTO(newSongEntity.getNs_no(), newSongEntity.getS_no(), newSongEntity.getStitle(), newSongEntity.getS_singer(), newSongEntity.getS_img(), newSongEntity.getS_album(), date);
			addArray.add(newSongDTO);
		}
		return addArray;
	}

	// 최신곡 업데이트
	public boolean NewSongSave() {
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
			// 앨범명 뽑기
			Elements album = document1.getElementsByClass("ellipsis rank03"); // 클래스 명으로 큰 틀 지정
			Elements albumName = album.select("a"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감

//			System.out.println(songLike.text());

			ArrayList<String> songAndSinger = new ArrayList<>(); // 곡명과 가수명을 합칠 리스트
			String[] songArray = new String[melon_song.size()]; // 곡명
			String[] singerArray = new String[melon_song.size()]; // 가수명
			String[] songIMGArray = new String[melon_song.size()];
			String[] albumArray = new String[melon_song.size()];

			NewSongDTO newSongDTO = new NewSongDTO();
			List<NewSongEntity> result = newSongRepository.findAll();

			for (int i = 0; i < melon_song.size(); i++) {
				System.out.println("size - 1 : " + (result.size() - 1));
				songArray[i] = songTitle.get(i).text().split("\n")[0];
				singerArray[i] = singerName.get(i).text().split("\n")[0];
				// attr("abs:src")는 src라는 속성 값의 절대 경로를 달라는 뜻이므로 URL 뿐 아니라 도메인도 붙어서 오게 된다.
				songIMGArray[i] = imgName.get(i).attr("abs:src");
				albumArray[i] = albumName.get(i).text().split("\n")[0];

				if (result.size() == 0) {
					newSongDTO.setS_title(songArray[i]);
					newSongDTO.setS_singer(singerArray[i]);
					newSongDTO.setS_img(songIMGArray[i]);
					newSongDTO.setS_album(albumArray[i]);
					newSongDTO.setS_no(melon_song.size() - i);
					newSongRepository.save(newSongDTO.newSongEntity());
				} else if (result.size() != 0) {
					for (int j = 0; j <= result.size(); j++) {
						if (!songArray[i].equals(result.get(j).getStitle())) {
							System.out.println("안중복 : " + songArray[i]);
							System.out.println("i.size : " + i);
							System.out.println("j.size : " + j);
							if (j + 1 == result.size()) {
								newSongDTO.setS_title(songArray[i]);
								newSongDTO.setS_singer(singerArray[i]);
								newSongDTO.setS_img(songIMGArray[i]);
								newSongDTO.setS_album(albumArray[i]);
								newSongDTO.setS_no(result.get(0).getS_no() + i);
								newSongRepository.save(newSongDTO.newSongEntity());
								break;
							}
						} else {
							System.out.println("중복 컷 : " + songArray[i]);
							break;
						}
					}
				}
			}
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}

	// 페이징

package Melon.Service;

import Melon.Domain.DTO.NewSongDTO;
import Melon.Domain.DTO.TJNewSongDTO;
import Melon.Domain.Entity.NewSongEntity;
import Melon.Domain.Entity.NewSongRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service; 

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
			newSongDTO = new NewSongDTO(newSongEntity.getNs_no(), newSongEntity.getSno(), newSongEntity.getStitle(),
					newSongEntity.getS_singer(), newSongEntity.getS_img(), newSongEntity.getS_album(), date);
			addArray.add(newSongDTO);
		}
		return addArray;
	}

	// 멜론 최신곡 업데이트
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


			ArrayList<String> songAndSinger = new ArrayList<>(); // 곡명과 가수명을 합칠 리스트
			String[] songArray = new String[melon_song.size()]; // 곡명
			String[] singerArray = new String[melon_song.size()]; // 가수명
			String[] songIMGArray = new String[melon_song.size()];
			String[] albumArray = new String[melon_song.size()];

			NewSongDTO newSongDTO = new NewSongDTO();
			List<NewSongEntity> result = newSongRepository.findAll();

			for (int i = 0; i < melon_song.size(); i++) {
				songArray[i] = songTitle.get(i).text().split("\n")[0];
				singerArray[i] = singerName.get(i).text().split("\n")[0];
				// attr("abs:src")는 src라는 속성 값의 절대 경로를 달라는 뜻이므로 URL 뿐 아니라 도메인도 붙어서 오게 된다.
				songIMGArray[i] = imgName.get(i).attr("abs:src");
				albumArray[i] = albumName.get(i).text().split("\n")[0];

//				System.out.println(songArray[i]);

				if (result.size() == 0) {
					newSongDTO.setS_title(songArray[i]);
					newSongDTO.setS_singer(singerArray[i]);
					newSongDTO.setS_img(songIMGArray[i]);
					newSongDTO.setS_album(albumArray[i]);
					newSongDTO.setSno((melon_song.size() - i) - 1);
					newSongRepository.save(newSongDTO.newSongEntity());
				} else if (result.size() != 0) {
					for (int j = 0; j <= result.size(); j++) {
						if (!songArray[i].equals(result.get(j).getStitle())) {
							if (j + 1 == result.size()) {
								newSongDTO.setS_title(songArray[i]);
								newSongDTO.setS_singer(singerArray[i]);
								newSongDTO.setS_img(songIMGArray[i]);
								newSongDTO.setS_album(albumArray[i]);
								newSongDTO.setSno(result.size() + i);
								newSongRepository.save(newSongDTO.newSongEntity());
								break;
							}
						} else {
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

	// 신곡이 노래방에 나왔는지 검사
	public ArrayList<NewSongDTO> CheckTJSong(ArrayList<NewSongDTO> newChart) {
		try {
			JsonObject melonDataOBJ = new JsonObject();
			JsonArray songInfo = new JsonArray();

			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			calendar.add(Calendar.MONTH, 0);
			String year1 = sdf.format(calendar.getTime()).split("-")[0];
			String mon1 = sdf.format(calendar.getTime()).split("-")[1];
			calendar.add(Calendar.MONTH, -1);
			String year2 = sdf.format(calendar.getTime()).split("-")[0];
			String mon2 = sdf.format(calendar.getTime()).split("-")[1];

			ArrayList<TJNewSongDTO> tjNewSongDTOS = new ArrayList<>();

			for(int a = 0; a < 2; a++) {
				// URL 연결
				Document document1;
				if(a == 0) {
					document1 = Jsoup.connect("http://m.tjmedia.com/tjsong/song_monthNew.asp?YY=" + year1 + "&MM=" + mon1).get();
				} else {
					document1 = Jsoup.connect("http://m.tjmedia.com/tjsong/song_monthNew.asp?YY=" + year2 + "&MM=02").get();
				}
				JsonObject checktj = new JsonObject();
				JsonArray searchtj = new JsonArray();

				// 큰 틀(테이블) 타기
				Elements TJ_song = document1.getElementsByClass("board_type1"); // 클래스 명으로 큰 틀 지정
				Elements TJtag = TJ_song.select("tbody>tr>td"); // 큰 틀에서 태그를 통해 타고 원하는 정보까지 들어감

				String[] TJSong = new String[TJtag.size()];
				String tjNo = null; String tjName = null; String tjSinger = null;
				boolean sw = true;
				// 큰 틀(테이블) 타기

				for(int i = 0; i < TJSong.length; i++) {
					if (i % 3 == 0) {
						// 노래방 번호
						tjNo = TJtag.get(i).text();
					}
					if (i % 3 == 1) {
						// 제목
						tjName = TJtag.get(i).text();
					}
					if (i % 3 == 2) {
						// 가수
						tjSinger = TJtag.get(i).text();
						sw = false;
					}
					if(!sw) {
						TJNewSongDTO tjNewSongDTO = new TJNewSongDTO();
						tjNewSongDTO.setTj_no(tjNo);
						tjNewSongDTO.setTj_name(tjName);
						tjNewSongDTO.setTj_singer(tjSinger);
						tjNewSongDTOS.add(tjNewSongDTO);
						sw = true;
					}
				}
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd");
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			ArrayList<NewSongDTO> newSongDTOS = new ArrayList<>();
			for(int i = 0 ; i < newChart.size(); i++) {
				Date dd = sdf1.parse(newChart.get(i).getS_createDate());
//				if(dd.compareTo(calendar.getTime()) >= 0) {
					for(int j = 0; j < tjNewSongDTOS.size(); j++) {
						if (newChart.get(i).getS_title().replace(" ", "").equals(tjNewSongDTOS.get(j).getTj_name())) {
							NewSongDTO newSongDTO = new NewSongDTO();
							newSongDTO.setS_title(newChart.get(i).getS_title());
							newSongDTO.setS_singer(newChart.get(i).getS_singer());
							newSongDTO.setS_album(newChart.get(i).getS_album());
							newSongDTO.setS_img(newChart.get(i).getS_img());
							newSongDTOS.add(newSongDTO);
						}
					}
//				}
			}
			return newSongDTOS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	// 페이징
	public Page<NewSongEntity> NewSongPaging(Pageable pageable) {
		int page = 0;
		if(pageable.getPageNumber() == 0) {
			page = 0;
		} else {
			page = pageable.getPageNumber() - 1;
		}

		pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "sno"));
		return newSongRepository.findAll(pageable);
	}
}

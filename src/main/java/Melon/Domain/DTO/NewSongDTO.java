package Melon.Domain.DTO;

import Melon.Domain.Entity.NewSongEntity;
import lombok.*;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString @Builder
public class NewSongDTO {
	private int ns_no;
	private int sno;
	private String s_title;
	private String s_singer;
	private String s_img;
        private String s_album;
        private String s_createDate;

	public NewSongEntity newSongEntity() {
		return NewSongEntity.builder()
			.ns_no(this.ns_no)
			.sno(this.sno)
			.stitle(this.s_title)
			.s_singer(this.s_singer)
			.s_img(this.s_img)
			.s_album(this.s_album)
			.build();
	}
}

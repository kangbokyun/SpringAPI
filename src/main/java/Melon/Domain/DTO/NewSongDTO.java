package Melon.Domain.DTO;

import Melon.Domain.Entity.NewSongEntity;
import lombok.*;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString @Builder
public class NewSongDTO {
	private int ns_no;
	private int s_no;
	private String s_title;
	private String s_singer;
	private String s_img;

	public NewSongEntity newSongEntity() {
		return NewSongEntity.builder()
			.ns_no(this.ns_no)
			.s_no(this.s_no)
			.stitle(this.s_title)
			.s_singer(this.s_singer)
			.s_img(this.s_img)
			.build();
	}
}

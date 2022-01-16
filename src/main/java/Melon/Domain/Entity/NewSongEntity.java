package Melon.Domain.Entity;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "newsong")
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class NewSongEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ns_no;
	@Column
	private int s_no;
	@Column
	private String s_title;
	@Column
	private String s_singer;
	@Column
	private String s_img;
}

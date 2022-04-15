package Melon.Domain.DTO;

import lombok.*;

import javax.persistence.ManyToOne;


@Setter @Getter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class BoardDTO {
    private int bno;
    private String btitle;
    private String bcontents;
    private String bwriter;
    private String bview;
}

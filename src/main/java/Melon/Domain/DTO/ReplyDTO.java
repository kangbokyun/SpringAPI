package Melon.Domain.DTO;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class ReplyDTO {
    private int rno;
    private String rcontents;
    private int rmno;
    private int rbno;
    private int rdistinctno;
    private String rwriter;
    private String createdDate;
}

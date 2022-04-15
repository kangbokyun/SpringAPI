package Melon.Domain.DTO;

import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {
    private int cateno;
    private String catename;
    private String catecode;
}

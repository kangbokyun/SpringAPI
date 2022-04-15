package Melon.Domain.DTO;

import lombok.*;

@Setter @Getter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class MiddleCategoryDTO {
    private int mcno;
    private String mcname;
    private String mccode;
}

package Melon.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TJNewSongDTO {
    private String tj_no;
    private String tj_name;
    private String tj_singer;
}

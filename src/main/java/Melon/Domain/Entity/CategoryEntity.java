package Melon.Domain.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "category")
@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class CategoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cateno;
    @Column
    private String catename;
    @Column
    private String catecode;
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<BoardEntity> boardEntityList = new ArrayList<>();
}

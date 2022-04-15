package Melon.Domain.Entity;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "board")
@Setter @Getter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column
    private String btitle;
    @Column
    private String bcontents;
    @Column
    private String bwriter;
    @Column
    private String bview;

    @ManyToOne @JoinColumn(name = "cateno")
    private CategoryEntity categoryEntity;
    @ManyToOne @JoinColumn(name = "mcno")
    private MiddleCategoryEntity middleCategory;
}

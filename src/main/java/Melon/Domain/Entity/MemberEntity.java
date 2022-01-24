package Melon.Domain.Entity;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "member")
@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int mno;
        @Column
        private String mid;
        @Column
        private String mpw;
        @Column
        private String mname;
        @Column
        private String mphone;
        @Column
        private String maddress;
        @Column
        private String mbirth;
}

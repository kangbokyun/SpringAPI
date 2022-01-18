package Melon.Domain.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewSongRepository extends JpaRepository<NewSongEntity, Integer> {

        // findBy필드명 : 필드명을 찾는 메소드
        NewSongEntity findBys_title( String song );

}

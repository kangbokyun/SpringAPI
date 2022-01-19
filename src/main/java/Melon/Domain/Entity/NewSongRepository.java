package Melon.Domain.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewSongRepository extends JpaRepository<NewSongEntity, Integer> {
}

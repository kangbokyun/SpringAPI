package Melon.Domain.Entity;

import Melon.Domain.DTO.NewSongDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface NewSongRepository extends JpaRepository<NewSongEntity, Integer> {
}

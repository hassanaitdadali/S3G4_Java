package ma.emsi.s3g1.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.s3g1.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional public interface PlayerRepository extends JpaRepository<Player,Integer> {

List<Player> findByFullName(String name);
List<Player> findByFullNameContains(String name);
Page<Player> findByFullNameContains(String name, PageRequest pageable);



}

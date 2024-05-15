package ma.emsi.s3g1;

import ma.emsi.s3g1.entities.Player;
import ma.emsi.s3g1.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class S3G1Application implements CommandLineRunner {

    @Autowired
    PlayerRepository playerRepository;
    public static void main(String[] args) {
        SpringApplication.run(S3G1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//
//        List<Player> pagePlayers = playerRepository.findByFullNameContains("ah");
//
//        pagePlayers.forEach(System.out::println);
    }
}

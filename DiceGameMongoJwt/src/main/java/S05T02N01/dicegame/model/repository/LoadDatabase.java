package S05T02N01.dicegame.model.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import S05T02N01.dicegame.model.domain.RegisteredUser;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new RegisteredUser("Júlia", "julia@gmail.com", "julia1234")));
      log.info("Preloading " + repository.save(new RegisteredUser("Carla", "carla@gmail.com", "carla1234")));
      log.info("Preloading " + repository.save(new RegisteredUser("David", "david@gmail.com", "david1234")));
    };
  }
}

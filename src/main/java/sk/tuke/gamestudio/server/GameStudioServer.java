package sk.tuke.gamestudio.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import sk.tuke.gamestudio.minesweeper.PlaygroundJPA;
import sk.tuke.gamestudio.minesweeper.consoleui.ConsoleUI;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@EntityScan(basePackages = "sk.tuke.gamestudio.entity")
public class GameStudioServer {

    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class);

    }
@Bean
    public ScoreService ScoreService() {
        return new ScoreServiceJPA();

}

}

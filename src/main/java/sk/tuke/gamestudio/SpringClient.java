package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.minesweeper.PlaygroundJPA;
import sk.tuke.gamestudio.minesweeper.consoleui.ConsoleUI;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "sk.tuke.gamestudio.server.*" ))
public class SpringClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class);
       // new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }
    @Bean
    public CommandLineRunner runner(ConsoleUI console){
        return s->console.play();
    }
  /* @Bean
    public CommandLineRunner runnerJPA(PlaygroundJPA console){
        return s->console.play();
    }*/

    @Bean
    public PlaygroundJPA consoleJPA(){
        return new PlaygroundJPA();
    }

    @Bean
    public ConsoleUI console(){
        return new ConsoleUI();
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }

    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}

    /*@Bean
    public ScoreService scoreService(){
        return new ScoreServiceJDBC();
    }
    @Bean
    public CommentService commentService(){
        return new CommentServiceJDBC();
    }
    @Bean
    public RatingService ratingService(){
        return new RatingServiceJDBC();
    }*/
    @Bean
    public StudentServiceJPA studentServiceJPA(){
        return new StudentServiceJPA();
    }

    @Bean
    public StudentGroupServiceJPA studentGroupServiceJPA(){
        return new StudentGroupServiceJPA();
    }

    @Bean
    public CountryServiceJPA countryServiceJPA() { return new CountryServiceJPA();}

    @Bean
    public OccupationServiceJPA occupationServiceJPA() { return new OccupationServiceJPA();}

    @Bean
    public PlayerServiceJPA playerServiceJPA() {return new PlayerServiceJPA();}

}

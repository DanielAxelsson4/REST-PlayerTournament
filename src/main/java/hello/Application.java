package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Daniel Axelsson
 *
 */

@SpringBootApplication
@ComponentScan("hello.DAO")
@ComponentScan("hello.Controllers")
@ComponentScan("hello.Domain")
@ComponentScan("hello.Repositories")
@ComponentScan("hello.Services")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package co.com.ias.springboot.reactor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringbootReactorApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringbootReactorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<String> names = Flux.just("Sam", "Rick", "Andrew", "", "Tom", "Steve", "Bob")
                .doOnNext(name -> {
                    if (name.isEmpty()) throw new RuntimeException("Name is empty");
                    System.out.println(name);
                });

        names
                .sort()
                .subscribe(log::info,
                        error -> log.error(error.getMessage())
                );
    }
}

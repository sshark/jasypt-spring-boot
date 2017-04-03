package org.teckhooi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Created by sshark on 3/4/17.
 */

@SpringBootApplication
public class MyApp implements ApplicationRunner {
    @Value("${secret}")
    private String secretMessage;

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyApp.class)
            .bannerMode(Banner.Mode.OFF)
            .application()
            .run(args);

//        new SpringApplication(MyApp.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if (applicationArguments.containsOption("monthly")) {
            System.out.println("Monthly is selected.");
        }

        Optional.ofNullable(applicationArguments.getOptionValues("markers")).ifPresent(args -> args.stream().forEach(System.out::println));

        System.out.println("The secret message is " + secretMessage);
    }
}

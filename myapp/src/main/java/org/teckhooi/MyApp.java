package org.teckhooi;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by sshark on 3/4/17.
 */

@SpringBootApplication
public class MyApp implements ApplicationRunner {
    private final static Logger LOG = LoggerFactory.getLogger(MyApp.class);

    @Value("${secret}")
    private String secretMessage;

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyApp.class)
            .bannerMode(Banner.Mode.OFF)
            .application()
            .run(args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if (applicationArguments.containsOption("monthly")) {
            LOG.info("Monthly is selected.");
        }

        Optional.ofNullable(applicationArguments.getOptionValues("markers")).ifPresent(args -> args.stream().forEach(LOG::debug));

        LOG.info("The secret message is " + secretMessage);
    }
}

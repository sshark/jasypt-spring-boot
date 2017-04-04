package org.teckhooi;

import com.ulisesbocchio.jasyptspringboot.InterceptionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by Lim, Teck Hooi on 4/4/17.
 */

@Configuration
public class MyAppConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(MyAppConfiguration.class);

  @Bean
  public static ViewAllPostProcessor foo(ConfigurableEnvironment environment) {
    LOGGER.info("Return post processor");

    boolean proxyPropertySources = environment.getProperty("jasypt.encryptor.proxyPropertySources", Boolean.TYPE, false);
    InterceptionMode interceptionMode = proxyPropertySources ? InterceptionMode.PROXY : InterceptionMode.WRAPPER;
    return new ViewAllPostProcessor(environment, interceptionMode);
  }
}

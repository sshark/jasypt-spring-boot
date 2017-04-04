package org.teckhooi;

import com.ulisesbocchio.jasyptspringboot.InterceptionMode;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by Lim, Teck Hooi on 4/4/17.
 */

public class ViewAllPostProcessor implements BeanFactoryPostProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(ViewAllPostProcessor.class);

  private ConfigurableEnvironment environment;
  private InterceptionMode interceptionMode;

  public ViewAllPostProcessor() {
    LOG.debug("Constructing {}", getClass());
  }

  public ViewAllPostProcessor(ConfigurableEnvironment environment, InterceptionMode interceptionMode) {
    LOG.debug("Constructing {} with {} and {}", getClass(), environment, interceptionMode);

    this.environment = environment;
    this.interceptionMode = interceptionMode;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    LOG.info("Post processing view bean");

    if (!StreamSupport
        .stream(environment.getPropertySources().spliterator(), false)
        .anyMatch(propertySource -> {
          boolean found = propertySource.containsProperty("secret");
          if (found) {
            LOG.debug("Found property 'secret': {}", propertySource.getProperty("secret"));
          }
          return found;
        })) {
      throw new RuntimeException("secret not found");
    }
  }
}

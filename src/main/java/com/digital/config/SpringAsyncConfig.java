package com.digital.config;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Default Configuration for Async Support
 *
 * @author babacoul
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setCorePoolSize(2);
    threadPoolTaskExecutor.setMaxPoolSize(2);
    threadPoolTaskExecutor.setQueueCapacity(500);
    threadPoolTaskExecutor.afterPropertiesSet();

    return threadPoolTaskExecutor;
  }

  @Override public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return null;
  }
}

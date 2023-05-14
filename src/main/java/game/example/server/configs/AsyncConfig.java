package game.example.server.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "serviceThreadPool")
    public ThreadPoolTaskExecutor serviceThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("service-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "controllerThreadPool")
    public ThreadPoolTaskExecutor controllerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("controller-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "loadingThreadPool")
    public ThreadPoolTaskExecutor loadingThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(12);
        executor.setMaxPoolSize(40);
        executor.setQueueCapacity(120);
        executor.setThreadNamePrefix("loading-");
        executor.initialize();
        return executor;
    }

}
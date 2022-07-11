package com.study.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    //TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 떄, 수정하는 방향으로 한다.
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("uno");
    }


}

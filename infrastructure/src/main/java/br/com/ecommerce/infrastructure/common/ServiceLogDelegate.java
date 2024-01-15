package br.com.ecommerce.infrastructure.common;

import br.com.ecommerce.domain.ServiceLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ServiceLogDelegate implements ServiceLogger {
    @Override
    public void debug(String msg, Object... args) {
        log.debug(msg, args);
    }
}

package br.ufscar.pooa.cinema_api.infrastructure;

import br.ufscar.pooa.cinema_api.adapters.out.notification.SessionNotificationManager;
import br.ufscar.pooa.cinema_api.adapters.out.notification.observers.EmailNotificationService;
import br.ufscar.pooa.cinema_api.adapters.out.notification.observers.SMSNotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class NotificationConfig {

    @Bean
    public SessionNotificationManager sessionNotificationManager(
            EmailNotificationService emailObserver,
            SMSNotificationService smsObserver) {

        SessionNotificationManager manager = new SessionNotificationManager();
        manager.subscribe(emailObserver);
        manager.subscribe(smsObserver);

        return manager;
    }
}
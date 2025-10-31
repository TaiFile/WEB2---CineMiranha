package br.ufscar.pooa.cinema_api.adapters.out.notification.observers;

import br.ufscar.pooa.cinema_api.application.ports.out.email.IEmailService;
import br.ufscar.pooa.cinema_api.adapters.out.notification.entities.Observer;
import br.ufscar.pooa.cinema_api.adapters.out.notification.entities.NotificationEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.HashMap;

@Component
public class EmailNotificationService implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);
    private final IEmailService emailService;

    public EmailNotificationService(IEmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void update(NotificationEvent event) {
        sendEmailNotification(event);
    }

    private void sendEmailNotification(NotificationEvent event) {
        String clientEmail = event.getClient().getEmail();
        String clientName = event.getClient().getName();
        String message = event.getMessage();

        logger.info("Enviando email para {}: {}", clientEmail, message);
        System.out.println("Enviando email para " + clientEmail + ": " + message);

        Map<String, Object> templateVariables = new HashMap<>();
        templateVariables.put("clientName", clientName);
        templateVariables.put("message", message);

        emailService.sendEmail(
            clientEmail,
            "Cinema Notification",
            "notification-email",
            templateVariables
        );
    }
}

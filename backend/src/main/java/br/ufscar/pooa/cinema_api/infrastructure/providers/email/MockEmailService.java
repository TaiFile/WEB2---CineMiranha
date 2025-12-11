package br.ufscar.pooa.cinema_api.infrastructure.providers.email;

import br.ufscar.pooa.cinema_api.features._shared.gateways.email.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MockEmailService implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);

    @Async
    @Override
    public void sendEmail(String to, String subject, String templateName, Map<String, Object> variables) {
        logger.info("========== MOCK EMAIL ==========");
        logger.info("To: {}", to);
        logger.info("Subject: {}", subject);
        logger.info("Template: {}", templateName);
        logger.info("Variables: {}", variables);
        logger.info("================================");
        logger.info("Email would have been sent successfully (mocked)");
    }
}

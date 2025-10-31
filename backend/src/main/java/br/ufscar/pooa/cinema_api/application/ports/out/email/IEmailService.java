package br.ufscar.pooa.cinema_api.application.ports.out.email;

import java.util.Map;

public interface IEmailService {
    void sendEmail(String to, String subject, String templateName, Map<String, Object> variables);
}

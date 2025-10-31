package br.ufscar.pooa.cinema_api.adapters.out.sms;

import br.ufscar.pooa.cinema_api.application.ports.out.sms.ISMSService;
import org.springframework.stereotype.Service;

@Service
public class MockSmsService implements ISMSService {
    @Override
    public void sendSMS(String to, String message) {
        System.out.println("SMS enviado para " + message);
    }
}

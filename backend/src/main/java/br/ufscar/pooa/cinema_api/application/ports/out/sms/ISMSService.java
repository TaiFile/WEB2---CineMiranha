package br.ufscar.pooa.cinema_api.application.ports.out.sms;

public interface ISMSService {
    void sendSMS(String to, String message);
}

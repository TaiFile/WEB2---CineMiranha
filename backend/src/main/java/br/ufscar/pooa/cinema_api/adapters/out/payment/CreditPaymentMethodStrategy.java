package br.ufscar.pooa.cinema_api.adapters.out.payment;


import br.ufscar.pooa.cinema_api.application.ports.out.payment.IPaymentStrategy;
import br.ufscar.pooa.cinema_api.domain.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditPaymentMethodStrategy implements IPaymentStrategy {

    @Override
    public boolean pay(Integer amount) {
        System.out.println("Pagamento de R$ " + amount + " realizado com Crédito.");
        return true;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT;
    }
}
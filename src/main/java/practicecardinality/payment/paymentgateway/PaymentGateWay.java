package practicecardinality.payment.paymentgateway;

import com.stripe.exception.StripeException;

public interface PaymentGateWay {
    String GeneratePaymentLink(long amount, String currency, String phoneNumber) throws StripeException;
}

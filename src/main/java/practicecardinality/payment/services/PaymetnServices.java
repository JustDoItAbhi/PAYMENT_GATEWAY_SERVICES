package practicecardinality.payment.services;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practicecardinality.payment.paymentgateway.stripePaymentGate.StripePaymentGateway;

@Service
public class PaymetnServices {
    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public String  createPaymentLink(long orderID) throws StripeException {
    return stripePaymentGateway.GeneratePaymentLink(10000L,"usd","251351351");
    }
}

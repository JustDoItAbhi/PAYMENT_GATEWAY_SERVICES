package practicecardinality.payment.paymentgateway.stripePaymentGate;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import practicecardinality.payment.paymentgateway.PaymentGateWay;
@Service
public class StripePaymentGateway implements PaymentGateWay {

    @Value("${stripe.secret.key}")
    private String strikebreaker;


    @Override
    public String GeneratePaymentLink(long amount, String currency, String phoneNumber) throws StripeException {
    Stripe.apiKey= strikebreaker;
    ProductCreateParams paramss = ProductCreateParams.builder()
            .setName("Generic")
            .build();
    Product product=Product.create(paramss);


    PriceCreateParams params = PriceCreateParams.builder()
            .setCurrency(currency)
            .setUnitAmount(amount)
            .setProduct(product.getId())
            .build();
        Price price=Price.create(params);

        PaymentLinkCreateParams params1 =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://www.scaler.com").build())
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(params1);

        return paymentLink.getUrl();
    }
}

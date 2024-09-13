package vn.hust.hedspi.ezsport.services.payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@RequiredArgsConstructor
@Slf4j
public class PaypalPayment implements IPayment{
    @Value("${paypal.client-id}")
    private String CLIENT_ID;

    @Value("${paypal.client-secret}")
    private String CLIENT_SECRET;

    public Invoice createInvoice(String payerEmail, String currency, double amount) throws PayPalRESTException {
        log.info(CLIENT_ID);
        log.info(CLIENT_SECRET);
        APIContext apiContext = new APIContext(CLIENT_ID,CLIENT_SECRET,"sandbox");

        Invoice invoice = new Invoice();

        // Thiết lập thông tin người trả tiền
        BillingInfo billing = new BillingInfo();
        billing.setEmail(payerEmail);

        // Thêm vào hóa đơn
        List<BillingInfo> billingInfoList = new ArrayList<>();
        billingInfoList.add(billing);
        invoice.setBillingInfo(billingInfoList);

        // Thiết lập chi tiết hàng hóa hoặc dịch vụ
        InvoiceItem item = new InvoiceItem();
        item.setName("Payment Request");
        item.setQuantity((float)1.0);
        item.setUnitPrice(new Currency().setCurrency(currency).setValue(String.valueOf(amount)));

        // Thêm vào hóa đơn
        List<InvoiceItem> items = new ArrayList<>();
        items.add(item);
        invoice.setItems(items);

        log.info("CREATE MERCHANT");
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setEmail("dongt2ls1720@gmail.com"); // Đặt email của người bán của bạn ở đây
        invoice.setMerchantInfo(merchantInfo);

        log.info("CREATED SUCCESSFUL");
        // Tạo hóa đơn
        return invoice.create(apiContext);
    }

    @Override
    public boolean pay(int amount) {
        return false;
    }
}

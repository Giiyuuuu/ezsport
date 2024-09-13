package vn.hust.hedspi.ezsport.application.controllers;

import com.paypal.api.payments.Invoice;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.hedspi.ezsport.services.payment.PaypalPayment;

@RestController
@RequestMapping("/api/invoices")
@Slf4j
public class InvoiceController {

    @Autowired
    private PaypalPayment paypalPayment;

    @PostMapping("/create")
    public Invoice createInvoice(@RequestParam String email,
                                 @RequestParam String currency,
                                 @RequestParam double amount) {
        try {
            return paypalPayment.createInvoice(email, currency, amount);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating invoice: " + e.getMessage());
        }
    }
}

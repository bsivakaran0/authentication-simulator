package com.paymoni.simulator.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymoni.simulator.controller.pojo.AuthNResponse;
import com.paymoni.simulator.controller.pojo.AuthNStatusResponse;
import com.paymoni.simulator.controller.pojo.CheckBinRequest;
import com.paymoni.simulator.model.Cart;
import com.paymoni.simulator.model.CartItem;
import com.paymoni.simulator.model.Product;
import com.paymoni.simulator.service.CardCaptureService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CardCaptureController {
    
	private final CardCaptureService cardCaptureService;

	@GetMapping("/shoppingcart")
    public String showShoppingCartPage(Model model) {
		
		// Create sample products
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Wireless Headphones");
        product1.setSku("WH-1000XM4");
        product1.setPrice(new BigDecimal("5.00"));
        product1.setImageUrl("headphone.jpg");
        product1.setDescription("Industry-leading noise cancellation");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Smartphone");
        product2.setSku("SP-12PRO");
        product2.setPrice(new BigDecimal("5.00"));
        product2.setImageUrl("smartphone.jpg");
        product2.setDescription("Latest model with advanced camera");

        // Create cart items
        CartItem item1 = new CartItem();
        item1.setProduct(product1);
        item1.setQuantity(1);

        CartItem item2 = new CartItem();
        item2.setProduct(product2);
        item2.setQuantity(2);

        // Create and populate cart
        Cart cart = new Cart();
        cart.setItems(Arrays.asList(item1, item2));
        
        // Calculate totals
        cart.calculateTotals();

        // Add cart to model
        model.addAttribute("cart", cart);
        
        return "shoppingcart";
    }
	
	@GetMapping("/cardcapture")
    public String showCardCapturePage() {
        return "cardcapturepage";
    }
    
    
    @PostMapping("/submit")
    public String handleFormSubmission(@RequestParam Map<String, String> formData, Model model) {
        CheckBinRequest request = new CheckBinRequest();
        request.setAcctNumber(formData.get("cardNumber").replaceAll("\\s+", ""));
        
        try {
            AuthNResponse authNResponse = cardCaptureService.checkBin(request);
            
            if(authNResponse.getTransStatus().equalsIgnoreCase("C"))
            {
            	model.addAttribute("creq", authNResponse.getCreq());
            	model.addAttribute("acsurl", authNResponse.getAcsURL());
            	return "challengerequest";
            }
            else if(authNResponse.getTransStatus().equalsIgnoreCase("Y") || authNResponse.getTransStatus().equalsIgnoreCase("A"))
            {
            	model.addAttribute("result", authNResponse.getEci() + " :: "+ authNResponse.getAuthenticationValue());
            	model.addAttribute("amount", "16.20");
                model.addAttribute("transactionId", "TXN123456789");
                model.addAttribute( "transactionDate",  LocalDateTime.now().format( DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)));
                //model.addAttribute("transactionDate", "Jan 18, 2026 22:30:45");
                model.addAttribute("paymentMethod", "VISA •••• 4242");
                model.addAttribute("email", "user@example.com");
                return "transaction-success";
            }
            else
            {
                model.addAttribute("result", "Transaction failed");
            	model.addAttribute("errorMessage", "Insufficient funds");
                model.addAttribute("transactionId", "TXN123456789");
                return "transaction-failure";
            }
        } catch (Exception e) {
            model.addAttribute("result", "Error validating card details: " + e.getMessage());
            return "resultpage";
        }
    }
    
    @PostMapping("/cres")
    public String handleCres(@RequestParam String cres, Model model) {
        try {
            AuthNStatusResponse authNStatusResponse = cardCaptureService.processCres(cres);
            
            if(authNStatusResponse.getTransStatus().equalsIgnoreCase("Y"))
            {
            	model.addAttribute("result", authNStatusResponse.getEci() + " :: "+ authNStatusResponse.getAuthenticationValue());
             	model.addAttribute("amount", "16.20");
                model.addAttribute("transactionId", "TXN123456789");
                model.addAttribute( "transactionDate",  LocalDateTime.now().format( DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)));
                //model.addAttribute("transactionDate", "Jan 18, 2026 22:30:45");
                model.addAttribute("paymentMethod", "VISA •••• 4242");
                model.addAttribute("email", "user@example.com");
                return "transaction-success";
            }
            else
            {
            	model.addAttribute("result", authNStatusResponse.getErrorCode() + " :: "+ authNStatusResponse.getErrorDescription());
            	model.addAttribute("errorMessage", "Insufficient funds");
                model.addAttribute("transactionId", "TXN123456789");
                return "transaction-failure";
            }
        } catch (Exception e) {
            model.addAttribute("result", "Error validating card details: " + e.getMessage());
            return "resultpage";
        }
    }
}
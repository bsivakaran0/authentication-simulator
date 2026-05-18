package com.paymoni.simulator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.util.Base64URL;
import com.paymoni.simulator.service.CardCaptureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ACSController {
    
	@Value("${tds.merchant-url}")
	private String merchantUrl;
	
	private final CardCaptureService cardCaptureService;
	
	@PostMapping("/creq")
    public String handleCreqSubmission(@RequestParam Map<String, String> formData, Model model) {
		model.addAttribute("creq", formData.get("creq"));
        return "otppage";
    }
	
	@PostMapping("/verifyotp")
    public String verifyotp(@RequestParam Map<String, String> formData, Model model) {
		
		StringBuilder otp = new StringBuilder();
		
		for(String key : formData.keySet())
		{
			if(key.equalsIgnoreCase("creq")) continue;
			otp.append(formData.get(key));
		}
		log.info("Entered OTP {}", otp.toString());
		
		cardCaptureService.sendRReq(formData.get("creq"));
		
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("transStatus", "Y");
		
		model.addAttribute("merchantUrl", merchantUrl);
		model.addAttribute("cres", Base64URL.encode(jsonObject.toString()));
		
		return "redirectToMerchant";
    }
}
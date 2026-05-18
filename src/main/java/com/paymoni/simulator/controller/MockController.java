package com.paymoni.simulator.controller;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymoni.simulator.controller.pojo.AReq;
import com.paymoni.simulator.controller.pojo.ARes;
import com.paymoni.simulator.service.CommonJsonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MockController {

	@Value("${mock.file-location}")
	private String mockFileLocation;
		  
	private final CommonJsonService commonJsonService;
	
	private static Map<String, AReq> areqMap = new HashMap<>();
	
	public static void main(String[] args) {
	
		MockController mockController = new MockController(new CommonJsonService(new ObjectMapper()));
		System.out.println(mockController.simulate("{\"acctNumber\":\"4567890000000001\",\"threeDSCompInd\":\"Y\",\"threeDSRequestorAuthenticationInd\":\"01\",\"threeDSRequestorAuthenticationInfo\":{},\"threeDSRequestorChallengeInd\":\"01\",\"threeDSRequestorID\":\"REQ123\",\"threeDSRequestorName\":\"MerchantApp\",\"threeDSRequestorURL\":\"https://merchant.example.com\",\"threeDSServerRefNumber\":\"REF001\",\"threeDSServerOperatorID\":\"OP001\",\"threeDSServerTransID\":\"370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"threeDSServerURL\":\"http://localhost:8080/authNservice/v1/resultRequest/100000/1234567/370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"acctType\":\"01\",\"acquirerBIN\":\"FILL\",\"acquirerMerchantID\":\"MERCHANT001\",\"addrMatch\":\"Y\",\"broadInfo\":\"Additional info\",\"browserAcceptHeader\":\"text/html,application/xhtml+xml\",\"browserIP\":\"192.168.1.10\",\"browserJavaEnabled\":\"true\",\"browserJavascriptEnabled\":\"true\",\"browserLanguage\":\"en-US\",\"browserColorDepth\":\"24\",\"browserScreenHeight\":\"1080\",\"browserScreenWidth\":\"1920\",\"browserTZ\":\"-330\",\"browserUserAgent\":\"Mozilla/5.0\",\"cardExpiryDate\":\"2512\",\"acctID\":\"ACCT001\",\"billAddrCity\":\"Chennai\",\"billAddrCountry\":\"IN\",\"billAddrLine1\":\"123 Street\",\"billAddrLine2\":\"Area\",\"billAddrLine3\":\"Landmark\",\"billAddrPostCode\":\"600001\",\"billAddrState\":\"TN\",\"email\":\"user@example.com\",\"homePhone\":{\"cc\":\"91\",\"subscriber\":\"4444444444\"},\"mobilePhone\":{\"cc\":\"91\",\"subscriber\":\"9999999999\"},\"cardholderName\":\"Sivakaran\",\"shipAddrCity\":\"Chennai\",\"shipAddrCountry\":\"IN\",\"shipAddrLine1\":\"456 Street\",\"shipAddrLine2\":\"Area\",\"shipAddrLine3\":\"Landmark\",\"shipAddrPostCode\":\"600002\",\"shipAddrState\":\"TN\",\"workPhone\":{\"cc\":\"91\",\"subscriber\":\"8888888888\"},\"deviceChannel\":\"02\",\"mcc\":\"6011\",\"merchantCountryCode\":\"356\",\"merchantName\":\"Test Merchant\",\"messageCategory\":\"01\",\"messageType\":\"AReq\",\"messageVersion\":\"2.2.0\",\"notificationURL\":\"https://merchant.example.com/notify\",\"purchaseAmount\":\"1000\",\"purchaseCurrency\":\"356\",\"purchaseExponent\":\"2\",\"purchaseDate\":\"2025-12-01T00:00:00Z\",\"transType\":\"01\"}")); 
		System.out.println(mockController.simulate("{\"acctNumber\":\"4567890000000002\",\"threeDSCompInd\":\"Y\",\"threeDSRequestorAuthenticationInd\":\"01\",\"threeDSRequestorAuthenticationInfo\":{},\"threeDSRequestorChallengeInd\":\"01\",\"threeDSRequestorID\":\"REQ123\",\"threeDSRequestorName\":\"MerchantApp\",\"threeDSRequestorURL\":\"https://merchant.example.com\",\"threeDSServerRefNumber\":\"REF001\",\"threeDSServerOperatorID\":\"OP001\",\"threeDSServerTransID\":\"370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"threeDSServerURL\":\"http://localhost:8080/authNservice/v1/resultRequest/100000/1234567/370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"acctType\":\"01\",\"acquirerBIN\":\"FILL\",\"acquirerMerchantID\":\"MERCHANT001\",\"addrMatch\":\"Y\",\"broadInfo\":\"Additional info\",\"browserAcceptHeader\":\"text/html,application/xhtml+xml\",\"browserIP\":\"192.168.1.10\",\"browserJavaEnabled\":\"true\",\"browserJavascriptEnabled\":\"true\",\"browserLanguage\":\"en-US\",\"browserColorDepth\":\"24\",\"browserScreenHeight\":\"1080\",\"browserScreenWidth\":\"1920\",\"browserTZ\":\"-330\",\"browserUserAgent\":\"Mozilla/5.0\",\"cardExpiryDate\":\"2512\",\"acctID\":\"ACCT001\",\"billAddrCity\":\"Chennai\",\"billAddrCountry\":\"IN\",\"billAddrLine1\":\"123 Street\",\"billAddrLine2\":\"Area\",\"billAddrLine3\":\"Landmark\",\"billAddrPostCode\":\"600001\",\"billAddrState\":\"TN\",\"email\":\"user@example.com\",\"homePhone\":{\"cc\":\"91\",\"subscriber\":\"4444444444\"},\"mobilePhone\":{\"cc\":\"91\",\"subscriber\":\"9999999999\"},\"cardholderName\":\"Sivakaran\",\"shipAddrCity\":\"Chennai\",\"shipAddrCountry\":\"IN\",\"shipAddrLine1\":\"456 Street\",\"shipAddrLine2\":\"Area\",\"shipAddrLine3\":\"Landmark\",\"shipAddrPostCode\":\"600002\",\"shipAddrState\":\"TN\",\"workPhone\":{\"cc\":\"91\",\"subscriber\":\"8888888888\"},\"deviceChannel\":\"02\",\"mcc\":\"6011\",\"merchantCountryCode\":\"356\",\"merchantName\":\"Test Merchant\",\"messageCategory\":\"01\",\"messageType\":\"AReq\",\"messageVersion\":\"2.2.0\",\"notificationURL\":\"https://merchant.example.com/notify\",\"purchaseAmount\":\"1000\",\"purchaseCurrency\":\"356\",\"purchaseExponent\":\"2\",\"purchaseDate\":\"2025-12-01T00:00:00Z\",\"transType\":\"01\"}"));
		System.out.println(mockController.simulate("{\"acctNumber\":\"4567890000000003\",\"threeDSCompInd\":\"Y\",\"threeDSRequestorAuthenticationInd\":\"01\",\"threeDSRequestorAuthenticationInfo\":{},\"threeDSRequestorChallengeInd\":\"01\",\"threeDSRequestorID\":\"REQ123\",\"threeDSRequestorName\":\"MerchantApp\",\"threeDSRequestorURL\":\"https://merchant.example.com\",\"threeDSServerRefNumber\":\"REF001\",\"threeDSServerOperatorID\":\"OP001\",\"threeDSServerTransID\":\"370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"threeDSServerURL\":\"http://localhost:8080/authNservice/v1/resultRequest/100000/1234567/370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"acctType\":\"01\",\"acquirerBIN\":\"FILL\",\"acquirerMerchantID\":\"MERCHANT001\",\"addrMatch\":\"Y\",\"broadInfo\":\"Additional info\",\"browserAcceptHeader\":\"text/html,application/xhtml+xml\",\"browserIP\":\"192.168.1.10\",\"browserJavaEnabled\":\"true\",\"browserJavascriptEnabled\":\"true\",\"browserLanguage\":\"en-US\",\"browserColorDepth\":\"24\",\"browserScreenHeight\":\"1080\",\"browserScreenWidth\":\"1920\",\"browserTZ\":\"-330\",\"browserUserAgent\":\"Mozilla/5.0\",\"cardExpiryDate\":\"2512\",\"acctID\":\"ACCT001\",\"billAddrCity\":\"Chennai\",\"billAddrCountry\":\"IN\",\"billAddrLine1\":\"123 Street\",\"billAddrLine2\":\"Area\",\"billAddrLine3\":\"Landmark\",\"billAddrPostCode\":\"600001\",\"billAddrState\":\"TN\",\"email\":\"user@example.com\",\"homePhone\":{\"cc\":\"91\",\"subscriber\":\"4444444444\"},\"mobilePhone\":{\"cc\":\"91\",\"subscriber\":\"9999999999\"},\"cardholderName\":\"Sivakaran\",\"shipAddrCity\":\"Chennai\",\"shipAddrCountry\":\"IN\",\"shipAddrLine1\":\"456 Street\",\"shipAddrLine2\":\"Area\",\"shipAddrLine3\":\"Landmark\",\"shipAddrPostCode\":\"600002\",\"shipAddrState\":\"TN\",\"workPhone\":{\"cc\":\"91\",\"subscriber\":\"8888888888\"},\"deviceChannel\":\"02\",\"mcc\":\"6011\",\"merchantCountryCode\":\"356\",\"merchantName\":\"Test Merchant\",\"messageCategory\":\"01\",\"messageType\":\"AReq\",\"messageVersion\":\"2.2.0\",\"notificationURL\":\"https://merchant.example.com/notify\",\"purchaseAmount\":\"1000\",\"purchaseCurrency\":\"356\",\"purchaseExponent\":\"2\",\"purchaseDate\":\"2025-12-01T00:00:00Z\",\"transType\":\"01\"}"));
		
	}
	
    @PostMapping("/ds/areq")
    public String simulate(@RequestBody String request) {
    	// Load JSON file from resources
        //ClassPathResource resource = new ClassPathResource("mock-response.json");
        // Example external path
        FileSystemResource resource = new FileSystemResource(mockFileLocation);

        try {
        	String jsonResponse = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        	JsonNode jsonNode = commonJsonService.fromJson(jsonResponse, JsonNode.class);

        	AReq areq = commonJsonService.fromJson(request, AReq.class);
        	areqMap.put(areq.getThreeDSServerTransID(), areq);
        	
			ARes ares = commonJsonService.fromJson(jsonNode.get(areq.getAcctNumber()).toString(), ARes.class);
	        
			ares.setThreeDSServerTransID(areq.getThreeDSServerTransID());
			ares.setAcsTransID(UUID.randomUUID().toString());
			ares.setDsTransID(UUID.randomUUID().toString());
			
			return commonJsonService.toJson(ares);
        } catch (Exception e) {
			log.error("Error in Mock server", e);
		}
		return null;
    }

	public static Map<String, AReq> getAreqMap() {
		return areqMap;
	}
}
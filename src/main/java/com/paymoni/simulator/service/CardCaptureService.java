package com.paymoni.simulator.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.paymoni.simulator.config.CommonUtil;
import com.paymoni.simulator.controller.MockController;
import com.paymoni.simulator.controller.pojo.AReq;
import com.paymoni.simulator.controller.pojo.AuthNRequest;
import com.paymoni.simulator.controller.pojo.AuthNResponse;
import com.paymoni.simulator.controller.pojo.AuthNStatusRequest;
import com.paymoni.simulator.controller.pojo.AuthNStatusResponse;
import com.paymoni.simulator.controller.pojo.CReq;
import com.paymoni.simulator.controller.pojo.CheckBinRequest;
import com.paymoni.simulator.controller.pojo.CheckBinResponse;
import com.paymoni.simulator.controller.pojo.RReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardCaptureService {
 
	 @Value("${tds.server-url}")
	 private String tdsServerUrl;
	 
	 private final RestTemplate restTemplate;
	 private final JweJwsService jweJwsService;
	 private final CommonJsonService commonJsonService;
	 
	 private final String BANK_ID = "bank1";

	 private final String SERVER_ID = "100000";
	 private final String SERVER_ALIAS = "100000-pub";
	 private final String REQUESTER_KEY = "8f3d2c9e-7a41-4b6a-9f2a-3c1b8d7e4a65";
	 
	 private String authNStatusUrl;
	 
	 public AuthNResponse checkBin(CheckBinRequest request) {
		 
		 	request.setThreeDSMethodNotificationURL("https://callbacks.test/3ds");
		 	String jweReq = jweJwsService.toJwe(request, BANK_ID, SERVER_ALIAS);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("requesterKey", REQUESTER_KEY);
	        HttpEntity<String> binCheckEntity = new HttpEntity<>(jweReq, headers);
	        
	        try {
	            ResponseEntity<String> binCheckResponse = restTemplate.exchange(
	            	tdsServerUrl.replace(":bankId", SERVER_ID).replace(":merchantTxnId", UUID.randomUUID().toString()),
	                HttpMethod.POST,
	                binCheckEntity,
	                String.class
	            );

	            CheckBinResponse checkBinResponse = jweJwsService.fromJwe(binCheckResponse.getBody(), CheckBinResponse.class, BANK_ID, SERVER_ALIAS);
	            if (checkBinResponse == null || checkBinResponse.getAuthNRequestUrl() == null) {
	                throw new RuntimeException("Invalid BIN check response");
	            }
	            
	            AuthNRequest authNRequest = commonJsonService.fromJson("{\"threeDSCompInd\":\"Y\",\"threeDSRequestorAuthenticationInd\":\"01\",\"threeDSRequestorAuthenticationInfo\":{\"method\":\"password\",\"timestamp\":\"2025-12-01T00:00:00Z\"},\"threeDSRequestorChallengeInd\":\"01\",\"threeDSRequestorID\":\"REQ123\",\"threeDSRequestorName\":\"MerchantApp\",\"threeDSRequestorURL\":\"https://merchant.example.com\",\"threeDSServerTransID\":\"370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"acctType\":\"01\",\"acquirerMerchantID\":\"MERCHANT001\",\"addrMatch\":\"Y\",\"broadInfo\":\"Additional info\",\"browserAcceptHeader\":\"text/html,application/xhtml+xml\",\"browserIP\":\"192.168.1.10\",\"browserJavaEnabled\":\"true\",\"browserJavascriptEnabled\":\"true\",\"browserLanguage\":\"en-US\",\"browserColorDepth\":\"24\",\"browserScreenHeight\":\"1080\",\"browserScreenWidth\":\"1920\",\"browserTZ\":\"-330\",\"browserUserAgent\":\"Mozilla/5.0\",\"cardExpiryDate\":\"2512\",\"acctNumber\":\"4567890000000001\",\"acctID\":\"ACCT001\",\"billAddrCity\":\"Chennai\",\"billAddrCountry\":\"IN\",\"billAddrLine1\":\"123 Street\",\"billAddrLine2\":\"Area\",\"billAddrLine3\":\"Landmark\",\"billAddrPostCode\":\"600001\",\"billAddrState\":\"TN\",\"email\":\"user@example.com\",\"homePhone\":{\"cc\":\"91\",\"subscriber\":\"4444444444\"},\"mobilePhone\":{\"cc\":\"91\",\"subscriber\":\"9999999999\"},\"cardholderName\":\"Sivakaran\",\"shipAddrCity\":\"Chennai\",\"shipAddrCountry\":\"IN\",\"shipAddrLine1\":\"456 Street\",\"shipAddrLine2\":\"Area\",\"shipAddrLine3\":\"Landmark\",\"shipAddrPostCode\":\"600002\",\"shipAddrState\":\"TN\",\"workPhone\":{\"cc\":\"91\",\"subscriber\":\"8888888888\"},\"deviceChannel\":\"02\",\"mcc\":\"6011\",\"merchantCountryCode\":\"356\",\"merchantName\":\"Test Merchant\",\"messageCategory\":\"01\",\"messageVersion\":\"2.2.0\",\"notificationURL\":\"https://merchant.example.com/notify\",\"purchaseAmount\":\"1620\",\"purchaseCurrency\":\"840\",\"purchaseExponent\":\"2\",\"purchaseDate\":\"2025-12-01T00:00:00Z\",\"transType\":\"01\"}", AuthNRequest.class);
	            authNRequest.setThreeDSServerTransID(checkBinResponse.getThreeDSServerTransID());
	            authNRequest.setAcctNumber(request.getAcctNumber());
	            
	            jweReq = jweJwsService.toJwe(authNRequest, BANK_ID, SERVER_ALIAS);
	            HttpEntity<String> authNEntity = new HttpEntity<>(jweReq, headers);
	            ResponseEntity<String> authNResponseEntity = restTemplate.exchange(
	            	checkBinResponse.getAuthNRequestUrl(),
	                HttpMethod.POST,
	                authNEntity,
	                String.class
	            );

	            AuthNResponse authNResponse = jweJwsService.fromJwe(authNResponseEntity.getBody(), AuthNResponse.class, BANK_ID, SERVER_ALIAS);
	            authNStatusUrl = authNResponse.getAuthNStatusURL();
	            		
	            return authNResponse;
	        } catch (Exception e) {
	            throw new RuntimeException("Error during BIN check or AuthN request: " + e.getMessage(), e);
	        }
	    }
	 
		public AuthNStatusResponse processCres(String cres) {

			AuthNStatusRequest authNStatusRequest = new AuthNStatusRequest();
			authNStatusRequest.setCres(cres);

			String jweReq = jweJwsService.toJwe(authNStatusRequest, BANK_ID, SERVER_ALIAS);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("requesterKey", REQUESTER_KEY);
			HttpEntity<String> binCheckEntity = new HttpEntity<>(jweReq, headers);

			try {
				ResponseEntity<String> response = restTemplate.exchange(authNStatusUrl, HttpMethod.POST, binCheckEntity,
						String.class);

				AuthNStatusResponse authNStatusResponse = jweJwsService.fromJwe(response.getBody(),
						AuthNStatusResponse.class, BANK_ID, SERVER_ALIAS);
				return authNStatusResponse;
			} catch (Exception e) {
				throw new RuntimeException("Error during BIN check or AuthN request: " + e.getMessage(), e);
			}
		}
		
		public void sendRReq(String creq) {
			
			try
			{
			String rreqStr = "{\"threeDSServerTransID\":\"370ecc47-3d86-4208-a20a-a0df73a1c7a9\",\"acsTransID\":\"ACSTRANS001\",\"acsRenderingType\":{\"acsInterface\":\"02\",\"acsUiTemplate\":\"03\"},\"authenticationMethod\":\"02\",\"authenticationType\":\"01\",\"authenticationValue\":\"authenticationvalue000000000\",\"challengeCancel\":\"N\",\"dsTransID\":\"DSTRANS001\",\"eci\":\"05\",\"interactionCounter\":\"01\",\"messageCategory\":\"01\",\"messageType\":\"RReq\",\"messageVersion\":\"2.2.0\",\"transStatus\":\"Y\"}";
			RReq rReq = commonJsonService.fromJson(rreqStr, RReq.class);
			CReq cReq = commonJsonService.fromJson(CommonUtil.base64UrlDecode(creq), CReq.class);
			AReq aReq = MockController.getAreqMap().get(cReq.getThreeDSServerTransID());
			
			rReq.setThreeDSServerTransID(cReq.getThreeDSServerTransID());
			rReq.setAcsTransID(cReq.getAcsTransID());
			rReq.setDsTransID(cReq.getAcsTransID());
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<RReq> rReqEntity = new HttpEntity<>(rReq, headers);
	        
            ResponseEntity<String> rResResponse = restTemplate.exchange(
            	aReq.getThreeDSServerURL(),
                HttpMethod.POST,
                rReqEntity,
                String.class
            );
	        } catch (Exception e) {
	            throw new RuntimeException("Error during BIN check or AuthN request: " + e.getMessage(), e);
	        }
	    }

}
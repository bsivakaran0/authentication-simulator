package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CReq {
    private String threeDSRequestorAppURL;
    private String threeDSServerTransID;
    private String acsTransID;
    private String challengeCancel;
    private String challengeDataEntry;
    private String challengeHTMLDataEntry;
    private String challengeNoEntry;
    private String challengeWindowSize;
    private MessageExtension messageExtension;
    private String messageType;
    private String messageVersion;
    private String oobContinue;
    private String resendChallenge;
    private String sdkTransID;
    private String sdkCounterStoA;
    private String whitelistingDataEntry;
}
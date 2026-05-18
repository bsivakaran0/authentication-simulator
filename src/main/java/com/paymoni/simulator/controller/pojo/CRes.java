package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CRes {
    private String threeDSServerTransID;
    private String acsCounterAtoS;
    private String acsTransID;
    private String acsHTML;
    private String acsUiType;
    private String challengeCompletionInd;
    private String challengeInfoHeader;
    private String challengeInfoLabel;
    private String challengeInfoText;
    private String challengeInfoTextIndicator;
    private String challengeSelectInfo;
    private String expandInfoLabel;
    private String expandInfoText;
    private String issuerImage;
    private MessageExtension messageExtension;
    private String messageType;
    private String messageVersion;
    private String oobAppURL;
    private String oobAppLabel;
    private String oobContinueLabel;
    private String psImage;
    private String resendInformationLabel;
    private String transStatus;
    private String sdkTransID;
    private String submitAuthenticationLabel;
    private String whitelistingInfoText;
    private String whyInfoLabel;
    private String whyInfoText;
}
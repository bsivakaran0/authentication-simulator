package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ARes {
    private String threeDSServerTransID;
    private String acsChallengeMandated;
    private String acsDecConInd;
    private String acsOperatorID;
    private String acsReferenceNumber;
    private AcsRenderingType acsRenderingType;
    private String acsSignedContent;
    private String acsTransID;
    private String acsURL;
    private String authenticationType;
    private String authenticationValue;
    private String broadInfo;
    private String cardholderInfo;
    private String dsReferenceNumber;
    private String dsTransID;
    private String eci;
    private MessageExtension messageExtension;
    private String messageType;
    private String messageVersion;
    private String sdkTransID;
    private String transStatus;
    private String transStatusReason;
    private String whiteListStatus;
    private String whiteListStatusSource;
}
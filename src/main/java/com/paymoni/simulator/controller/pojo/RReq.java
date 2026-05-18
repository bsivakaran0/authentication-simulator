package com.paymoni.simulator.controller.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RReq {
    private String threeDSServerTransID;
    private String acsTransID;
    private AcsRenderingType acsRenderingType;
    private String authenticationMethod;
    private String authenticationType;
    private String authenticationValue;
    private String challengeCancel;
    private String dsTransID;
    private String eci;
    private String interactionCounter;
    private String messageCategory;
    private List<MessageExtension> messageExtension;
    private String messageType;
    private String messageVersion;
    private String sdkTransID;
    private String transStatus;
    private String transStatusReason;
    private String whiteListStatus;
    private String whiteListStatusSource;
}
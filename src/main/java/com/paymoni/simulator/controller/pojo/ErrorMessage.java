package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String threeDSServerTransID;
    private String acsTransID;
    private String dsTransID;
    private String errorCode;
    private String errorComponent;
    private String errorDescription;
    private String errorDetail;
    private String errorMessageType;
    private String messageType;
    private String messageVersion;
    private String sdkTransID;
}
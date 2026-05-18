package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthNStatusResponse {
    private String threeDSServerTransID;
    private String acsTransID;
    private String dsTransID;
    private String eci;
    private String authenticationValue;
    private String transStatus;
    private String errorCode;
    private String errorDescription;
}
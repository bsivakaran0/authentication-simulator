package com.paymoni.simulator.controller.pojo;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RRes {
    private String threeDSServerTransID;
    private String acsTransID;
    private String dsTransID;
    private List<MessageExtension> messageExtension;
    private String messageType;
    private String messageVersion;
    private String resultsStatus;
    private String sdkTransID;
}
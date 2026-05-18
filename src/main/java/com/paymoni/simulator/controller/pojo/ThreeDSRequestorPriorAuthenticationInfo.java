package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreeDSRequestorPriorAuthenticationInfo {
    private String threeDSReqPriorAuthData;
    private String threeDSReqPriorAuthMethod;
    private String threeDSReqPriorAuthTimestamp;
    private String threeDSReqPriorRef;
}
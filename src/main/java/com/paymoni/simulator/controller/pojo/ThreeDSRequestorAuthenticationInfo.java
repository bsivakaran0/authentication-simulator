package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreeDSRequestorAuthenticationInfo {
    private String threeDSReqAuthData;
    private String threeDSReqAuthMethod;
    private String threeDSReqAuthTimestamp;
}
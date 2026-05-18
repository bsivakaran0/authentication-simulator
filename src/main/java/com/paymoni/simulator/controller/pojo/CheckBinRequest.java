package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckBinRequest {
    private String messageType;
    private String deviceChannel;
    private String messageCategory;
    private String acctNumber;
    private String threeDSMethodNotificationURL;
}

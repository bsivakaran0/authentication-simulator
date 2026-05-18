package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreeDSMethodData {
    private String threeDSServerTransID;
    private String threeDSMethodNotificationURL;
}

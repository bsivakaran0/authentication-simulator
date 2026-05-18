package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRenderOptions {
    private String sdkInterface;
    private String[] sdkUiType;
}
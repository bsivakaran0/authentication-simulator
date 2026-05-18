package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcsRenderingType {
    private String acsInterface;
    private String acsUiTemplate;
}
package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthNStatusRequest {
    private String threeDSServerTransID;
    private String cres;
}
package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckBinResponse {
	private String messageType;
	private String messageVersion;
	private String threedsMethodUrl;
    private String threeDSMethodData;
    private String threeDSServerTransID;
	private String authNRequestUrl;
    private String errorCode;
    private String errorDescription;
    private String errorDetail;
}

package com.paymoni.simulator.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageExtension {
	private boolean criticalityIndicator;
	private String data;
	private String id;
	private String name;
}

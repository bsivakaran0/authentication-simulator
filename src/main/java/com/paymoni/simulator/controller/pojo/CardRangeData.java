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
public class CardRangeData {

    private String threeDSMethodURL;
    private String acsEndProtocolVersion;
    private List<String> acsInfoInd;
    private String acsStartProtocolVersion;
    private String actionInd;
    private String dsEndProtocolVersion;
    private String dsStartProtocolVersion;
    private String endRange;
    private String startRange;
}
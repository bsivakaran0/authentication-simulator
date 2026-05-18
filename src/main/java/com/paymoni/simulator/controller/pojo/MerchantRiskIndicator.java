package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRiskIndicator {
    private String deliveryEmailAddress;
    private String deliveryTimeframe;
    private String giftCardAmount;
    private String giftCardCount;
    private String giftCardCurr;
    private String preOrderDate;
    private String preOrderPurchaseInd;
    private String reorderItemsInd;
    private String shipIndicator;
}
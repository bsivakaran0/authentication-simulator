package com.paymoni.simulator.controller.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcctInfo {
    private String chAccAgeInd;
    private String chAccChange;
    private String chAccChangeInd;
    private String chAccDate;
    private String chAccPwChange;
    private String chAccPwChangeInd;
    private String nbPurchaseAccount;
    private String provisionAttemptsDay;
    private String txnActivityDay;
    private String txnActivityYear;
    private String paymentAccAge;
    private String paymentAccInd;
    private String shipAddressUsage;
    private String shipAddressUsageInd;
    private String shipNameIndicator;
    private String suspiciousAccActivity;
}
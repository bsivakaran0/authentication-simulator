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
public class AReq {
    private String threeDSCompInd;
    private String threeDSRequestorAuthenticationInd;
    private ThreeDSRequestorAuthenticationInfo threeDSRequestorAuthenticationInfo;
    private String threeDSRequestorChallengeInd;
    private String threeDSRequestorDecMaxTime;
    private String threeDSRequestorDecReqInd;
    private String threeDSRequestorID;
    private String threeDSRequestorName;
    private ThreeDSRequestorPriorAuthenticationInfo threeDSRequestorPriorAuthenticationInfo;
    private String threeDSRequestorURL;
    private String threeDSServerRefNumber;
    private String threeDSServerOperatorID;
    private String threeDSServerTransID;
    private String threeDSServerURL;
    private String threeRIInd;
    private String acctType;
    private String acquirerBIN;
    private String acquirerMerchantID;
    private String addrMatch;
    private String broadInfo;
    private String browserAcceptHeader;
    private String browserIP;
    private String browserJavaEnabled;
    private String browserJavascriptEnabled;
    private String browserLanguage;
    private String browserColorDepth;
    private String browserScreenHeight;
    private String browserScreenWidth;
    private String browserTZ;
    private String browserUserAgent;
    private String cardExpiryDate;
    private AcctInfo acctInfo;
    private String acctNumber;
    private String acctID;
    private String billAddrCity;
    private String billAddrCountry;
    private String billAddrLine1;
    private String billAddrLine2;
    private String billAddrLine3;
    private String billAddrPostCode;
    private String billAddrState;
    private String email;
    private Phone homePhone;
    private Phone mobilePhone;
    private String cardholderName;
    private String shipAddrCity;
    private String shipAddrCountry;
    private String shipAddrLine1;
    private String shipAddrLine2;
    private String shipAddrLine3;
    private String shipAddrPostCode;
    private String shipAddrState;
    private Phone workPhone;
    private String deviceChannel;
    private String deviceInfo;
    private DeviceRenderOptions deviceRenderOptions;
    private String payTokenInd;
    private String payTokenSource;
    private String purchaseInstalData;
    private String mcc;
    private String merchantCountryCode;
    private String merchantName;
    private MerchantRiskIndicator merchantRiskIndicator;
    private String messageCategory;
    private List<MessageExtension> messageExtension;
    private String messageType;
    private String messageVersion;
    private String notificationURL;
    private String purchaseAmount;
    private String purchaseCurrency;
    private String purchaseExponent;
    private String purchaseDate;
    private String recurringExpiry;
    private String recurringFrequency;
    private String sdkAppID;
    private String sdkEncData;
    private String sdkEphemPubKey;
    private String sdkMaxTimeout;
    private String sdkReferenceNumber;
    private String sdkTransID;
    private String transType;
    private String whiteListStatus;
    private String whiteListStatusSource;
}

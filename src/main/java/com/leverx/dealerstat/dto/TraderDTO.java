package com.leverx.dealerstat.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TraderDTO {

    private BigInteger traderId;
    private String traderName;
    private BigDecimal traderRating;

    public TraderDTO(Object[] rawInputData) {
        this.traderId = (BigInteger) rawInputData[0];
        this.traderName = (String) rawInputData[1];
        this.traderRating = (BigDecimal) rawInputData[2];
    }

    public TraderDTO(BigInteger traderId, String traderName, BigDecimal traderRating) {
        this.traderId = traderId;
        this.traderName = traderName;
        this.traderRating = traderRating;
    }

    public BigInteger getTraderId() {
        return traderId;
    }

    public void setTraderId(BigInteger traderId) {
        this.traderId = traderId;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public BigDecimal getTraderRating() {
        return traderRating;
    }

    public void setTraderRating(BigDecimal traderRating) {
        this.traderRating = traderRating;
    }

}

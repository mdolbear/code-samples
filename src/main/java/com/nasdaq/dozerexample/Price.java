package com.nasdaq.dozerexample;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {

    private BigDecimal value;
    private String currency;
    private SourceType type;

}

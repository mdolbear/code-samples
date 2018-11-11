package com.nasdaq.dozerexample;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "id", callSuper = true)
@Builder
public class Option extends Derivative {

    @NonNull private OptionType optionType;
    @NonNull private OptionStyle optionStyle;

    private Price strikePrice;
    private BigDecimal timeErosion;


    /**
     * Override no argument constructor to initialize instrument type
     */
    public Option() {

        super();
        this.setInstrumentType(InstrumentType.OPTION);
    }

}

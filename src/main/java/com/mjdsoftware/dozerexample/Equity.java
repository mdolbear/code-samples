package com.mjdsoftware.dozerexample;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "id", callSuper = true)
@Builder
public class Equity extends Instrument {


    /**
     * Override no argument constructor to initialize instrument type
     */
    public Equity() {

        super();
        this.setInstrumentType(InstrumentType.EQUITY);

    }

}

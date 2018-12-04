package com.mjdsoftware.dozerexample;

import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = "id")
@Builder
@AllArgsConstructor
public class Future extends Derivative {

    @NonNull FutureType futureType;
    LocalDate firstNoticeDate;
    LocalDate lastTradingDate;


    /**
     * Override no argument constructor to initialize instrument type
     */
    public Future() {

        super();
        this.setInstrumentType(InstrumentType.FUTURE);
    }

}
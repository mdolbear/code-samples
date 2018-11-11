package com.nasdaq.dozerexample;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Value
@Builder
public class Dividend {

    @NonNull
    BigDecimal amount;
    @NonNull LocalDate exDividendDate;
    LocalDate recordDate;
    LocalDate paymentDate;
}

package com.mjdsoftware.haventsince;

import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
public class LoginCount implements HaventSinceElement {

    private String           countParameterValue;
    private Long             count;
    private String           intervalDateTime;

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private ZonedDateTime    myDate;

    /**
     * Answer an instance of me for the args below
     * @param aCountParameterValue String
     * @param aCount Long
     * @param anIntervalDateTime String
     */
    public LoginCount(String aCountParameterValue,
                      Long aCount,
                      String anIntervalDateTime) {

        this();
        this.setCountParameterValue(aCountParameterValue);
        this.setCount(aCount);
        this.setIntervalDateTime(anIntervalDateTime);

    }


    /**
     * Answer my date
     * @param aDateFormat String
     * @param aTimezone
     * @return ZonedDateTime
     */
    public ZonedDateTime getDate(String aDateFormat,
                                 String aTimezone) {

        ZonedDateTime                tempResult;

        tempResult = this.getMyDate();
        if (tempResult == null) {

            tempResult = this.convertDateForFormat(aDateFormat, aTimezone);
            this.setMyDate(tempResult);
        }

        return tempResult;

    }

    /**
     * Answer my date for aDateFormat
     * @param aDateFormat  String
     * @return ZonedDateTime
     */
    private ZonedDateTime convertDateForFormat(String aDateFormat,
                                               String aTimezone) {

        DateTimeFormatter       tempFormat;
        LocalDate               tempLocalTime;

        tempFormat = DateTimeFormatter.ofPattern(aDateFormat);

        try {

            tempLocalTime = LocalDate.parse(this.getIntervalDateTime(),
                                            tempFormat);
            return tempLocalTime.atStartOfDay(ZoneId.of(aTimezone));

        }
        catch (Exception e) {

            throw new RuntimeException("Date parse failure");
        }

    }

    /**
     * Answer my characteristics
     * @return List
     */
    public List<String> getCharacteristics() {

        List<String>        tempResults = new ArrayList<String>();

        tempResults.add("login: " + this.getCountParameterValue());
        tempResults.add("count: " + this.getCount());

        return tempResults;
    }

    /**
     * Answer my identifier
     * @return String
     */
    public String getIdentifier() {

        return this.getCountParameterValue();

    }

}

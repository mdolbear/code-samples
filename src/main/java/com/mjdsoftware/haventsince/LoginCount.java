package com.mjdsoftware.haventsince;

import lombok.*;

import java.text.SimpleDateFormat;
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
    private Date myDate;

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
     * @return Date
     */
    public Date getDate(String aDateFormat) {

        Date                tempResult;

        tempResult = this.getMyDate();
        if (tempResult == null) {

            tempResult = this.convertDateForFormat(aDateFormat);
            this.setMyDate(tempResult);
        }

        return tempResult;

    }

    /**
     * Answer my date for aDateFormat
     * @param aDateFormat  String
     * @return Date
     */
    private Date convertDateForFormat(String aDateFormat) {

        SimpleDateFormat tempFormat;
        Date tempResult;

        tempFormat = new SimpleDateFormat(aDateFormat);

        try {

            tempResult = tempFormat.parse(this.getIntervalDateTime());
            return tempResult;

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

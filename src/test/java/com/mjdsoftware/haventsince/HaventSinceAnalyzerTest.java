package com.mjdsoftware.haventsince;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class HaventSinceAnalyzerTest {

    private static final String TIMEZONE = "America/Denver";

    /**
     * Test havent since analyzer -- determine if something hasn't occurred since a given
     * data
     */
    @Test
    public void haventSinceBasicTest() {

        HaventSinceAnalyzer         tempAnalyzer;
        List<HaventSinceElement>    tempResults;

        tempAnalyzer = new HaventSinceAnalyzer(this.getCutoffTestDate(TIMEZONE),
                                               HaventSinceAnalyzer.YEAR_MN_DY_FORMAT,
                                               this.createSampleLoginCounts());
        tempResults = tempAnalyzer.produceResult(TIMEZONE);

        assertTrue("No result returned, or incorrect",
                    !tempResults.isEmpty() &&
                                    tempResults.size() == 3 //Only Joe and Sue should be found
                  );

        System.out.println("Results from havent since: " + tempResults.toString());

    }

    /**
     * Answer my cutoff test date
     * @return Date
     */
    private ZonedDateTime getCutoffTestDate(String aTimezone) {

        return this.basicGetCutoffTestDate("2019-03-01", aTimezone);

    }


    /**
     * Test havent since analyzer -- determine if something hasn't occurred since a given
     * data
     */
    @Test
    public void haventSinceTestWithCutoffDateNotInData() {

        HaventSinceAnalyzer         tempAnalyzer;
        List<HaventSinceElement>    tempResults;
        CutoffDateException         tempException = null;

        try {
            tempAnalyzer = new HaventSinceAnalyzer(this.getCutoffTestDateNotInData(TIMEZONE),
                                                   HaventSinceAnalyzer.YEAR_MN_DY_FORMAT,
                                                   this.createSampleLoginCounts());

            tempResults = tempAnalyzer.produceResult(TIMEZONE);
            fail("Should have thrown an exception");

        }
        catch (CutoffDateException e) {

            tempException = e;
        }

        tempAnalyzer = new HaventSinceAnalyzer(tempException.getSearchResult().getMidpointDate(),
                                               HaventSinceAnalyzer.YEAR_MN_DY_FORMAT,
                                               this.createSampleLoginCounts());

        tempResults = tempAnalyzer.produceResult(TIMEZONE);

        assertTrue("No result returned, or incorrect",
                !tempResults.isEmpty() &&
                                tempResults.size() == 3 //Only Joe and Sue should be found
        );

        System.out.println("Results from havent since: " + tempResults.toString());

    }


    /**
     * Answer my cutoff test date
     * @return Date
     */
    private ZonedDateTime getCutoffTestDateNotInData(String aTimezone) {

       return this.basicGetCutoffTestDate("2019-03-15", aTimezone);

    }

    /**
     * Answer my cutoff test date
     * @param aDateString String
     * @return ZonedDateTime
     */
    private ZonedDateTime basicGetCutoffTestDate(String aDateString,
                                                 String aTimezone) {

        DateTimeFormatter       tempFormat;
        LocalDate               tempLocalTime;

        tempFormat = DateTimeFormatter.ofPattern(HaventSinceAnalyzer.YEAR_MN_DY_FORMAT);

        try {

            tempLocalTime = LocalDate.parse(aDateString,
                                             tempFormat);
            return tempLocalTime.atStartOfDay(ZoneId.of(aTimezone));

        }
        catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Date parse failure");
        }


    }



    /**
     * Create login counts
     * @return List
     */
    private List<HaventSinceElement> createSampleLoginCounts() {

        List<HaventSinceElement> tempCounts = new ArrayList<>();
        LoginCount               tempCount;



        tempCount = new LoginCount("joe@gmail.com",
                                    new Long(1l),
                  "2019-01-01" );
        tempCounts.add(tempCount);


        tempCount = new LoginCount("bob@gmail.com",
                                    new Long(3l),
                                    "2019-02-01" );
        tempCounts.add(tempCount);

        tempCount = new LoginCount("joe@gmail.com",
                                    new Long(10l),
                                    "2019-02-01" );
        tempCounts.add(tempCount);

        tempCount = new LoginCount("sue@gmail.com",
                                    new Long(1l),
                                    "2019-01-01" );
        tempCounts.add(tempCount);


        tempCount = new LoginCount("bill@gmail.com",
                                    new Long(10l),
                                    "2019-02-01" );
        tempCounts.add(tempCount);

        tempCount = new LoginCount("bob@gmail.com",
                                    new Long(10l),
                                    "2019-02-01" );
        tempCounts.add(tempCount);

        //Cutoff at 3/01
        tempCount = new LoginCount("bill@gmail.com",
                                    new Long(2l),
                                    "2019-03-01" );
        tempCounts.add(tempCount);

        tempCount = new LoginCount("bob@gmail.com",
                                    new Long(1l),
                                    "2019-03-01" );
        tempCounts.add(tempCount);

        tempCount = new LoginCount("bill@gmail.com",
                                    new Long(6l),
                                    "2019-04-01" );
        tempCounts.add(tempCount);

        tempCount = new LoginCount("bob@gmail.com",
                                    new Long(1l),
                                    "2019-04-01" );
        tempCounts.add(tempCount);

        return tempCounts;

    }

    /**
     * Test that a date is truncated to a 24 hour boundary
     *
     */
    @Test
    public void truncateDateTo24HourBoundaryTest() {

        Date        tempDate = new Date();
        Date        tempNewDate;


        System.out.println("Starting Date: " + tempDate.toString());

        tempNewDate = this.removeTime(tempDate);

        System.out.println("Resulting Date: " + tempNewDate.toString());

    }

    private Date removeTime(Date date) {

        Calendar tempCalendar = Calendar.getInstance();

        tempCalendar.setTime(date);
        tempCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tempCalendar.set(Calendar.MINUTE, 0);
        tempCalendar.set(Calendar.SECOND, 0);
        tempCalendar.set(Calendar.MILLISECOND, 0);

        return tempCalendar.getTime();

    }


}

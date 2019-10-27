package com.mjdsoftware.haventsince;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class HaventSinceAnalyzerTest {

    /**
     * Test havent since analyzer -- determine if something hasn't occurred since a given
     * data
     */
    @Test
    public void haventSinceBasicTest() {

        HaventSinceAnalyzer         tempAnalyzer;
        List<HaventSinceElement>    tempResults;

        tempAnalyzer = new HaventSinceAnalyzer(this.getCutoffTestDate(),
                                               HaventSinceAnalyzer.YEAR_MN_DY_FORMAT,
                                               this.createSampleLoginCounts());
        tempResults = tempAnalyzer.produceResult();

        assertTrue("No result returned", !tempResults.isEmpty() &&
                                                                tempResults.size() == 3 //Only Joe and Sue should be found
                  );

        System.out.println("Results from havent since: " + tempResults.toString());

    }

    /**
     * Answer my cutoff test date
     * @return Date
     */
    private Date getCutoffTestDate() {

        SimpleDateFormat tempFormat;
        Date tempResult;

        tempFormat = new SimpleDateFormat(HaventSinceAnalyzer.YEAR_MN_DY_FORMAT);

        try {

            tempResult = tempFormat.parse("2019-03-01");
            return tempResult;

        }
        catch (Exception e) {

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


}

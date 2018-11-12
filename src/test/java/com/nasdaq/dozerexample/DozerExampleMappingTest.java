package com.nasdaq.dozerexample;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class DozerExampleMappingTest {


    /**
     * Data mapping test
     */
    @Test
    public void dataMappingTest() {

        Mapper                  tempMapper;
        Option                  tempOption;
        Equity                  tempEquity;
        Future                  tempFuture;
        Instrument              tempInstrument;
        Map<String, String>     tempMap;

        //Create the dozer mapper from the builder -- load the xml mapping files
        tempMapper = DozerBeanMapperBuilder
                        .create()
                        .withMappingFiles(this.getMappingFiles())
                        .build();

        //Create an option from a hashmap
        tempMap = this.createOptionMap();
        tempOption = tempMapper.map(tempMap, Option.class);
        assertTrue("Option is null", (tempOption != null)
                                                && this.equals(tempMap,tempOption));
        //Create an equity from a hashmap
        tempMap = this.createEquityMap();
        tempEquity = tempMapper.map(tempMap, Equity.class);
        assertTrue("Equity is null", tempEquity != null
                                                            && this.equals(tempMap,tempEquity));

        //Create a future from a hashmap
        tempMap = this.createFutureMap();
        tempFuture = tempMapper.map(tempMap, Future.class);
        assertTrue("Future is null", tempFuture != null
                                                            && this.equals(tempMap,tempFuture));

        //Create an Instrument from a from a hashmap, but see if it creates the right subclass
        tempMap = this.createFutureMap();
        tempInstrument = tempMapper.map(tempMap, Instrument.class);
        assertTrue("Instrument is null", tempInstrument != null
                                                            && tempInstrument.getClass().equals(Future.class)
                                                                && this.equals(tempMap,(Future)tempInstrument));

        //Create an Instrument from a hashnmap, but see if it creates the right subclass
        tempMap = this.createOptionMap();
        tempInstrument = tempMapper.map(tempMap, Instrument.class);
        assertTrue("Option is null", tempInstrument != null
                                                        && tempInstrument.getClass().equals(Option.class)
                                                            && this.equals(tempMap,(Option)tempInstrument));


    }

    /**
     * Answer my list of mapping files
     * @List
     */
    private List<String> getMappingFiles() {

        List<String> tempMappingFiles = new ArrayList<String>();

        tempMappingFiles.add("file:/Users/micdol/code/code-samples/src/main/resources/dozer/equityMapping.xml");
        tempMappingFiles.add("file:/Users/micdol/code/code-samples/src/main/resources/dozer/futureMapping.xml");
        tempMappingFiles.add("file:/Users/micdol/code/code-samples/src/main/resources/dozer/instrumentMapping.xml");
        tempMappingFiles.add("file:/Users/micdol/code/code-samples/src/main/resources/dozer/optionMapping.xml");

        return tempMappingFiles;

    }


    /**
     * Create option map values
     */
    private Map<String, String> createOptionMap() {

        Map<String, String> tempOptionMap = new HashMap<>();

         tempOptionMap.put(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY, "OPTION");
         tempOptionMap.put(FinancialObjectBuilder.EXTERNAL_ID_KEY, "1");
         tempOptionMap.put(FinancialObjectBuilder.ID_KEY, UUID.randomUUID().toString());
         tempOptionMap.put(FinancialObjectBuilder.SYMBOL_KEY, "AAPL");

         tempOptionMap.put(FinancialObjectBuilder.UNDERLYING_ID_KEY, UUID.randomUUID().toString());
         tempOptionMap.put(FinancialObjectBuilder.EXPIRATION_DATE_KEY, LocalDate.now().toString());
         tempOptionMap.put(FinancialObjectBuilder.EFFECTIVE_DATE_KEY, LocalDate.now().toString());
         tempOptionMap.put(FinancialObjectBuilder.CONTRACT_SIZE_KEY, "100000");

         tempOptionMap.put(FinancialObjectBuilder.OPTION_TYPE_KEY, "CALL");
         tempOptionMap.put(FinancialObjectBuilder.OPTION_STYLE_KEY, "AMERICAN");
         tempOptionMap.put(FinancialObjectBuilder.STRIKE_PRICE_VALUE_KEY, "21.00");
         tempOptionMap.put(FinancialObjectBuilder.STRIKE_PRICE_CURRENCY_KEY, "dollars");
         tempOptionMap.put(FinancialObjectBuilder.STRIKE_PRICE_TYPE_KEY, "MARKET");
         tempOptionMap.put(FinancialObjectBuilder.TIME_EROSION_KEY, "50.0");

        return tempOptionMap;
    }

    /**
     * Answer whether or not anOption equals the values in aMap
     * @param aMap Map
     * @param anOption Option
     */
    private boolean equals(Map<String, String> aMap, Option anOption) {

        boolean tempValue;

        tempValue =
            aMap.get(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY).equals(anOption.getInstrumentType().name())
                &&
            aMap.get(FinancialObjectBuilder.EXTERNAL_ID_KEY).equals(anOption.getExternalId())
                &&
            aMap.get(FinancialObjectBuilder.ID_KEY).equals(anOption.getId().toString())
                &&
            aMap.get(FinancialObjectBuilder.SYMBOL_KEY).equals(anOption.getSymbol())
                &&
            aMap.get(FinancialObjectBuilder.UNDERLYING_ID_KEY).equals(anOption.getUnderlyingId().toString())
                &&
            LocalDate.parse(aMap.get(FinancialObjectBuilder.EXPIRATION_DATE_KEY)).equals(anOption.getExpirationDate())
                &&
            LocalDate.parse(aMap.get(FinancialObjectBuilder.EFFECTIVE_DATE_KEY)).equals(anOption.getEffectiveDate())
                &&
            aMap.get(FinancialObjectBuilder.CONTRACT_SIZE_KEY).equals(anOption.getContractSize().toString())
                &&
            aMap.get(FinancialObjectBuilder.OPTION_TYPE_KEY).equals(anOption.getOptionType().name())
                &&
            aMap.get(FinancialObjectBuilder.OPTION_STYLE_KEY).equals(anOption.getOptionStyle().name())
                &&
            aMap.get(FinancialObjectBuilder.STRIKE_PRICE_VALUE_KEY).equals(anOption.getStrikePrice().getValue().toString())
                &&
            aMap.get(FinancialObjectBuilder.STRIKE_PRICE_CURRENCY_KEY).equals(anOption.getStrikePrice().getCurrency())
                &&
            aMap.get(FinancialObjectBuilder.STRIKE_PRICE_TYPE_KEY).equals(anOption.getStrikePrice().getType().name())
                &&
            aMap.get(FinancialObjectBuilder.TIME_EROSION_KEY).equals(anOption.getTimeErosion().toString());

        return tempValue;

    }

    /**
     * Answer whether or not anEquity equals the values in aMap
     * @param aMap Map
     * @param anEquity Equity
     */
    private boolean equals(Map<String, String> aMap, Equity anEquity) {

        boolean tempValue;

        tempValue =
                aMap.get(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY).equals(anEquity.getInstrumentType().name())
                        &&
                        aMap.get(FinancialObjectBuilder.EXTERNAL_ID_KEY).equals(anEquity.getExternalId())
                        &&
                        aMap.get(FinancialObjectBuilder.ID_KEY).equals(anEquity.getId().toString())
                        &&
                        aMap.get(FinancialObjectBuilder.SYMBOL_KEY).equals(anEquity.getSymbol());




        return tempValue;

    }

    /**
     * Answer whether or not aFuture equals the values in aMap
     * @param aMap Map
     * @param aFuture Future
     */
    private boolean equals(Map<String, String> aMap, Future aFuture) {

        boolean tempValue;

        tempValue =
                aMap.get(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY).equals(aFuture.getInstrumentType().name())
                        &&
                        aMap.get(FinancialObjectBuilder.EXTERNAL_ID_KEY).equals(aFuture.getExternalId())
                        &&
                        aMap.get(FinancialObjectBuilder.ID_KEY).equals(aFuture.getId().toString())
                        &&
                        aMap.get(FinancialObjectBuilder.SYMBOL_KEY).equals(aFuture.getSymbol())
                        &&
                        aMap.get(FinancialObjectBuilder.UNDERLYING_ID_KEY).equals(aFuture.getUnderlyingId().toString())
                        &&
                        LocalDate.parse(aMap.get(FinancialObjectBuilder.EXPIRATION_DATE_KEY)).equals(aFuture.getExpirationDate())
                        &&
                        LocalDate.parse(aMap.get(FinancialObjectBuilder.EFFECTIVE_DATE_KEY)).equals(aFuture.getEffectiveDate())
                        &&
                        aMap.get(FinancialObjectBuilder.CONTRACT_SIZE_KEY).equals(aFuture.getContractSize().toString())
                        &&
                        aMap.get(FinancialObjectBuilder.FUTURE_TYPE_KEY).equals(aFuture.getFutureType().name())
                        &&
                        LocalDate.parse(aMap.get(FinancialObjectBuilder.FIRST_NOTICE_DATE_KEY)).equals(aFuture.getFirstNoticeDate())
                        &&
                        LocalDate.parse(aMap.get(FinancialObjectBuilder.LAST_TRADING_DATE_KEY)).equals(aFuture.getLastTradingDate());


        return tempValue;

    }




    /**
     * Create equity map values
     */
    private Map<String, String> createEquityMap() {

        Map<String, String> tempEquityMap = new HashMap<>();

        tempEquityMap.put(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY, "EQUITY");
        tempEquityMap.put(FinancialObjectBuilder.EXTERNAL_ID_KEY, "2");
        tempEquityMap.put(FinancialObjectBuilder.ID_KEY, UUID.randomUUID().toString());
        tempEquityMap.put(FinancialObjectBuilder.SYMBOL_KEY, "IBM");

        tempEquityMap.put(FinancialObjectBuilder.UNDERLYING_ID_KEY, UUID.randomUUID().toString());
        tempEquityMap.put(FinancialObjectBuilder.EXPIRATION_DATE_KEY, LocalDate.now().toString());
        tempEquityMap.put(FinancialObjectBuilder.EFFECTIVE_DATE_KEY, LocalDate.now().toString());
        tempEquityMap.put(FinancialObjectBuilder.CONTRACT_SIZE_KEY, "100000");


        return tempEquityMap;
    }

    /**
     * Create option map values
     */
    private Map<String, String> createFutureMap() {

        Map<String, String> tempFutureMap = new HashMap<>();

        tempFutureMap.put(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY, "FUTURE");
        tempFutureMap.put(FinancialObjectBuilder.EXTERNAL_ID_KEY, "3");
        tempFutureMap.put(FinancialObjectBuilder.ID_KEY, UUID.randomUUID().toString());
        tempFutureMap.put(FinancialObjectBuilder.SYMBOL_KEY, "TXN");

        tempFutureMap.put(FinancialObjectBuilder.UNDERLYING_ID_KEY, UUID.randomUUID().toString());
        tempFutureMap.put(FinancialObjectBuilder.EXPIRATION_DATE_KEY, LocalDate.now().toString());
        tempFutureMap.put(FinancialObjectBuilder.EFFECTIVE_DATE_KEY, LocalDate.now().toString());
        tempFutureMap.put(FinancialObjectBuilder.CONTRACT_SIZE_KEY, "100000");

        tempFutureMap.put(FinancialObjectBuilder.FUTURE_TYPE_KEY, "CURRENCY");
        tempFutureMap.put(FinancialObjectBuilder.FIRST_NOTICE_DATE_KEY, LocalDate.now().toString());
        tempFutureMap.put(FinancialObjectBuilder.LAST_TRADING_DATE_KEY, LocalDate.now().toString());


        return tempFutureMap;
    }

}

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
     * Option mapping test
     */
    @Test
    public void optionMappingTest() {

        Mapper tempMapper;
        Option tempOption;
        Equity tempEquity;
        Future tempFuture;
        Instrument tempInstrument;

        //Create the dozer mapper from the builder -- load the xml mapping files
        tempMapper = DozerBeanMapperBuilder
                        .create()
                        .withMappingFiles(this.getMappingFiles())
                        .build();

        //Create an option from a hashmap
        tempOption = tempMapper.map(this.createOptionMap(), Option.class);
        assertTrue("Option is null", tempOption != null);

        //Create an equity from a hashmap
        tempEquity = tempMapper.map(this.createEquityMap(), Equity.class);
        assertTrue("Equity is null", tempEquity != null);

        //Create a future from a hashmap
        tempFuture = tempMapper.map(this.createFutureMap(), Future.class);
        assertTrue("Future is null", tempFuture != null);

        //Create an Instrument from a from a hashmap, but see if it creates the right subclass
        tempInstrument = tempMapper.map(this.createFutureMap(), Instrument.class);
        assertTrue("Instrument is null", tempInstrument != null);


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
     * Create equity map values
     */
    private Map<String, String> createEquityMap() {

        Map<String, String> tempOptionMap = new HashMap<>();

        tempOptionMap.put(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY, "EQUITY");
        tempOptionMap.put(FinancialObjectBuilder.EXTERNAL_ID_KEY, "2");
        tempOptionMap.put(FinancialObjectBuilder.ID_KEY, UUID.randomUUID().toString());
        tempOptionMap.put(FinancialObjectBuilder.SYMBOL_KEY, "IBM");

        tempOptionMap.put(FinancialObjectBuilder.UNDERLYING_ID_KEY, UUID.randomUUID().toString());
        tempOptionMap.put(FinancialObjectBuilder.EXPIRATION_DATE_KEY, LocalDate.now().toString());
        tempOptionMap.put(FinancialObjectBuilder.EFFECTIVE_DATE_KEY, LocalDate.now().toString());
        tempOptionMap.put(FinancialObjectBuilder.CONTRACT_SIZE_KEY, "100000");


        return tempOptionMap;
    }

    /**
     * Create option map values
     */
    private Map<String, String> createFutureMap() {

        Map<String, String> tempOptionMap = new HashMap<>();

        tempOptionMap.put(FinancialObjectBuilder.INSTRUMENT_TYPE_KEY, "FUTURE");
        tempOptionMap.put(FinancialObjectBuilder.EXTERNAL_ID_KEY, "3");
        tempOptionMap.put(FinancialObjectBuilder.ID_KEY, UUID.randomUUID().toString());
        tempOptionMap.put(FinancialObjectBuilder.SYMBOL_KEY, "TXN");

        tempOptionMap.put(FinancialObjectBuilder.UNDERLYING_ID_KEY, UUID.randomUUID().toString());
        tempOptionMap.put(FinancialObjectBuilder.EXPIRATION_DATE_KEY, LocalDate.now().toString());
        tempOptionMap.put(FinancialObjectBuilder.EFFECTIVE_DATE_KEY, LocalDate.now().toString());
        tempOptionMap.put(FinancialObjectBuilder.CONTRACT_SIZE_KEY, "100000");

        tempOptionMap.put(FinancialObjectBuilder.FUTURE_TYPE_KEY, "CURRENCY");
        tempOptionMap.put(FinancialObjectBuilder.FIRST_NOTICE_DATE_KEY, LocalDate.now().toString());
        tempOptionMap.put(FinancialObjectBuilder. LAST_TRADING_DATE_KEY, LocalDate.now().toString());


        return tempOptionMap;
    }

}

package com.nasdaq.dozerexample;

import com.github.dozermapper.core.BeanFactory;
import com.github.dozermapper.core.config.BeanContainer;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;


public class FinancialObjectBuilder implements BeanFactory {

    //Constants -- most are used for the tests
    public static final String INSTRUMENT_TYPE_KEY = "instrumentType";
    public static final String EXTERNAL_ID_KEY = "externalId";
    public static final String ID_KEY = "id";
    public static final String SYMBOL_KEY = "symbol";

    public static final String UNDERLYING_ID_KEY = "underlyingId";
    public static final String EXPIRATION_DATE_KEY = "expirationDate";
    public static final String EFFECTIVE_DATE_KEY = "effectiveDate";
    public static final String CONTRACT_SIZE_KEY = "contractSize";

    public static final String FUTURE_TYPE_KEY = "futureType";
    public static final String FIRST_NOTICE_DATE_KEY = "firstNoticeDate";
    public static final String LAST_TRADING_DATE_KEY = "lastTradingDate";

    public static final String OPTION_TYPE_KEY = "optionType";
    public static final String OPTION_STYLE_KEY = "optionStyle";
    public static final String STRIKE_PRICE_VALUE_KEY = "strikePriceValue";
    public static final String STRIKE_PRICE_CURRENCY_KEY = "strikePriceCurrency";
    public static final String STRIKE_PRICE_TYPE_KEY = "strikePriceType";
    public static final String TIME_EROSION_KEY = "timeErosion";

    /**
     * Create bean
     * @param source
     * @param sourceClass
     * @param targetBeanId
     * @param beanContainer
     * @return
     */
    @Override
    public Object createBean(Object source,
                             Class<?> sourceClass,
                             String targetBeanId,
                             BeanContainer beanContainer) {

        return this.createFinancialObject((Map<String, String>)source);

    }

    /**
     * Create a instrument subclass from aMap
     * @param aMap Map<String, String>
     * @return Instrument
     */
    public Instrument createFinancialObject(Map<String, String> aMap) {


        String          tempInstrumentTypeKey;
        Instrument      tempResult = null;

        tempInstrumentTypeKey = aMap.get(INSTRUMENT_TYPE_KEY);

        if (tempInstrumentTypeKey.equals(InstrumentType.EQUITY.name())) {

            tempResult = new Equity();
        }
        else if (tempInstrumentTypeKey.equals(InstrumentType.OPTION.name())) {

            tempResult = new Option();
        }
        else if (tempInstrumentTypeKey.equals(InstrumentType.FUTURE.name())) {

            tempResult = new Future();
        }

        return tempResult;
    }

}

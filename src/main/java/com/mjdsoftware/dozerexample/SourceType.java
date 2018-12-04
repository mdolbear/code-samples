package com.mjdsoftware.dozerexample;

public enum SourceType {
    NONE,
    MARKET,
    MANUAL,
    THEORETICAL,
    HISTORICAL,
    SCENARIO,
    MISSING,
    UNKNOWN, // when we cannot parse the side from a msg
}

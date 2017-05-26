package com.epam.cache;

import java.time.LocalDate;

public interface CacheUtils {

    LocalDate getLastAccess(String key);

    long getNumHits(String key);

    // It would be great to implement this
    void callThisMethodToWinTheLottery();

}

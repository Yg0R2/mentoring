package com.epam.cache;

import java.time.LocalDate;

public class CacheUtilsImpl implements CacheUtils{

    private Cache<?> cache;

    public CacheUtilsImpl(Cache<?> cache) {
        this.cache = cache;
    }

    @Override
    public LocalDate getLastAccess(String key) {
        if (cache.get(key) != null) {
            return LocalDate.now();
        }

        return null;
    }

    @Override
    public long getNumHits(String key) {
        int count = 0;

        for (String cachedKey : cache.getKeys()) {
            if (cachedKey.equals(key)) {
                count++;
            }
        }

        return count;
    }

    @Override
    public void callThisMethodToWinTheLottery() {
        System.out.println("You have to be lucky.");
    }

}

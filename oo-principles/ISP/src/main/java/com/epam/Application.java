package com.epam;

import com.epam.cache.BestCache;
import com.epam.cache.Cache;
import com.epam.cache.CacheUtils;
import com.epam.cache.CacheUtilsImpl;

public class Application {

	public static void main(String[] args) {
		Cache<String> stringCache = new BestCache<>();
		use(stringCache);
		monitor(stringCache);
		winLottery(stringCache);
	}

	private static void monitor(Cache<String> stringCache) {
		Monitor<String> stringCacheMonitor = new Monitor<>(stringCache);
		stringCacheMonitor.printInfo();
	}

	private static void use(Cache<String> stringCache) {
		CacheUser cacheUser = new CacheUser(stringCache);
		cacheUser.use();
	}

	private static void winLottery(Cache<String> stringCache) {
		CacheUtils cacheUtils = new CacheUtilsImpl(stringCache);

		cacheUtils.callThisMethodToWinTheLottery();
	}

}

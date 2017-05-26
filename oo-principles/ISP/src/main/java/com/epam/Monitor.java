package com.epam;

import com.epam.cache.Cache;
import com.epam.cache.CacheUtils;
import com.epam.cache.CacheUtilsImpl;

public class Monitor<T> {

	private Cache<T> cache;
	private CacheUtils cacheUtils;

	public Monitor(Cache<T> cache) {
		this.cache = cache;
		cacheUtils = new CacheUtilsImpl(cache);
	}
	
	public void printInfo() {
		System.out.println("Cache info:");
		for (String key : cache.getKeys()) {
			System.out.println("Element: " + key);
			System.out.println("Last access: " + cacheUtils.getLastAccess(key));
			System.out.println("Number of hits: " + cacheUtils.getNumHits(key));
		}
	}
	
}

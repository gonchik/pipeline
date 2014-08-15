package com.cobalt.bamboo.plugin.pipeline.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WallBoardCache {
	private ConcurrentMap<String, WallBoardData> cache;
	
	public WallBoardCache() {
		cache = new ConcurrentHashMap<String, WallBoardData>();
	}
	
	public void put(String planKey, WallBoardData wallBoardData) {
		cache.put(planKey, wallBoardData);
	}
	
	public boolean isEmpty() {
		return cache.isEmpty();
	}
	
	public List<WallBoardData> getAllWallBoardData() {
		List<WallBoardData> resultList = new ArrayList<WallBoardData>();
		for (String planKey : cache.keySet()) {
			resultList.add(cache.get(planKey));
		}
		return resultList;
	}
	
	public WallBoardData get(String planKey) {
		return cache.get(planKey);
	}
	
	public void clear() {
		cache.clear();
	}
}
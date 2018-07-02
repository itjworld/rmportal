package com.rmportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Cache;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.rmportal.service.CacheDataService;
import com.rmportal.vo.CacheDetails;

@Service
public class CacheDataServiceImpl implements CacheDataService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManagerFactory entityManagerFactory;

//	@CacheEvict(allEntries = true)
	public void clearstatics() {
		LOGGER.debug("clearing the cache.....");
		Cache cache = entityManagerFactory.unwrap(SessionFactory.class).getCache();
        if (null != cache) {
            System.out.println("Clearing cache...");
            cache.evictAll();
            cache.evictAllRegions();
            System.out.println("Clearing cache...Done!");
        } else {
        	System.out.println("No second level cache available for session-factory");
        }
	}

	public List<CacheDetails> getEhCacheDetail() {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		Statistics stats = sessionFactory.getStatistics();
		Long fetchCount = stats.getEntityFetchCount();
		Long hitCount = stats.getSecondLevelCacheHitCount();
		Long putCount = stats.getSecondLevelCachePutCount();
		Long missCount = stats.getSecondLevelCacheMissCount();
		String region = "default";
		LOGGER.debug("sessionFactory : {}" , stats);
		LOGGER.debug("Entity fetch count : {}" , fetchCount);
		LOGGER.debug("Second level cache hit count : {}" , hitCount);
		LOGGER.debug("Second level cache put count : {}" , putCount);
		LOGGER.debug("Second level cache miss count : {}" , missCount);
		List<CacheDetails> cacheDataList = new ArrayList<CacheDetails>();
		cacheDataList.add(new CacheDetails(region, hitCount, missCount, fetchCount, putCount));
		 for (String reg : stats.getSecondLevelCacheRegionNames()) {
			 LOGGER.debug("region : {} : {}" , reg, stats.getSecondLevelCacheStatistics(region));
		 }
		return cacheDataList;
	}

}

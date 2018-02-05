package com.rmportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmportal.service.CacheDataService;
import com.rmportal.vo.CacheDetails;

@Service
public class CacheDataServiceImpl implements CacheDataService {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public void clearstatics() {
		System.out.println("clearing the cache.....");
		entityManagerFactory.unwrap(SessionFactory.class).getStatistics().clear();
	}

	public List<CacheDetails> getEhCacheDetail() {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		Statistics stats = sessionFactory.getStatistics();
		Long fetchCount = stats.getEntityFetchCount();
		Long hitCount = stats.getSecondLevelCacheHitCount();
		Long putCount = stats.getSecondLevelCachePutCount();
		Long missCount = stats.getSecondLevelCacheMissCount();
		String region = "default";
		System.out.println("sessionFactory : " + stats);
		System.out.println("Entity fetch count :" + fetchCount);
		System.out.println("Second level cache hit count : " + hitCount);
		System.out.println("Second level cache put count : " + putCount);
		System.out.println("Second level cache miss count : " + missCount);

		List<CacheDetails> cacheDataList = new ArrayList<CacheDetails>();
		// for (String region : stats.getSecondLevelCacheRegionNames()) {
		cacheDataList.add(new CacheDetails(region, hitCount, missCount, fetchCount, putCount));
		// }
		return cacheDataList;
	}

}

package com.rmportal.service;

import java.util.List;

import com.rmportal.vo.CacheDetails;

public interface CacheDataService {

	void clearstatics();

	List<CacheDetails> getEhCacheDetail();

}

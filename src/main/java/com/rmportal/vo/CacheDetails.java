package com.rmportal.vo;

import java.io.Serializable;

public class CacheDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long hitCount;
	private Long missCount;
	private Long fetchCount;
	private Long putCount;
	private String region;

	public CacheDetails(String region, Long hitCount, Long missCount, Long fetchCount, Long putCount) {
		this.fetchCount = fetchCount;
		this.hitCount = hitCount;
		this.missCount = missCount;
		this.putCount = putCount;
		this.region = region;
	}

	public Long getHitCount() {
		return hitCount;
	}

	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}

	public Long getMissCount() {
		return missCount;
	}

	public void setMissCount(Long missCount) {
		this.missCount = missCount;
	}

	public Long getFetchCount() {
		return fetchCount;
	}

	public void setFetchCount(Long fetchCount) {
		this.fetchCount = fetchCount;
	}

	public Long getPutCount() {
		return putCount;
	}

	public void setPutCount(Long putCount) {
		this.putCount = putCount;
	}

	public String toString() {
		return "region : " + region + " :: fetchCount : " + fetchCount + " :: missCount : " + missCount
				+ " :: putCount : " + putCount + " :: hitCount : " + hitCount;
	}
}

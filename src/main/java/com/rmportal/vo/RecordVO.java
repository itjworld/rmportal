package com.rmportal.vo;

import java.util.List;

import com.rmportal.model.GuestDetail;

public class RecordVO {
	private List<GuestDetail> data;
	private long total;
	
	public List<GuestDetail> getData() {
		return data;
	}
	public void setData(List<GuestDetail> data) {
		this.data = data;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}

package com.rmportal.vo;

import java.util.List;

import com.rmportal.model.RoomBookDetails;

public class RecordVO {
	private List<RoomBookDetails> data;
	private long total;
	
	public List<RoomBookDetails> getData() {
		return data;
	}
	public void setData(List<RoomBookDetails> data) {
		this.data = data;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}

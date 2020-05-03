package com.rmportal.vo;

import java.util.List;

public class RecordVO<T> {
	private List<T> data;
	private long total;

	public RecordVO() {

	}

	public RecordVO(List<T> data, long total) {
		this.data = data;
		this.total = total;
	}

	public static <T> RecordVO<T> of(List<T> data, long total) {
		return new RecordVO<T>(data, total);
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}

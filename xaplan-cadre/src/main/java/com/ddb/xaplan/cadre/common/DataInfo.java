package com.ddb.xaplan.cadre.common;

public class DataInfo<T> extends BaseInfo {

	public DataInfo() {
	}

	public DataInfo(T data) {
		this.data = data;
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

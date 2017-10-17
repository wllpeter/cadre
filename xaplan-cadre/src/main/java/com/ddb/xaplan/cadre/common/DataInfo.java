package com.ddb.xaplan.cadre.common;

public class DataInfo<T> extends BaseInfo {

	public DataInfo() {
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> DataInfo<T> success(T t){
		DataInfo<T> result = new DataInfo<>();
		result.setStatus(SUCCESS_CODE);
		result.setMessage(SUCCESS_MESSAGE);
		result.setData(t);
		return result;
	}

	public static <T> DataInfo<T> error(String message){
		DataInfo<T> result = new DataInfo<>();
		result.setStatus(ERROR_CODE);
		result.setMessage(message);
		return result;
	}
}

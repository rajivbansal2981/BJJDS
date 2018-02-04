package com.jmk.bjjd.service;

import java.util.List;


public interface BaseMgmtService<T> {
	public T saveRecord(T model);
	
	public T updateRecord(T model);
	
	public List<T> fetchAllRecordsByTenantId(Long tenantId);
	
	public void deleteRecordById(String id);
	
	public T findRecordByKey(String id);
	
	public List<T> saveRecords(List<T> records);
	
	public void deleteRecords(List<T> records);
}

package com.pls.service;

import java.util.List;

import com.pls.domain.Load;

public interface LoadService {

	public Load getById(Long id);
	
	public List<Load> getAllLoads();
	
	public void addLoad(Load load);
}

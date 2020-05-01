package com.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.model.StockItem;
import com.stock.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;

	public List<StockItem> getItem(){
		return stockRepository.getItem();
	}
}

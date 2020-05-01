package com.stock.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.model.StockItem;
import com.stock.service.StockService;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
	@Autowired
	private StockService stockService;

	@GetMapping
	public ResponseEntity<List<StockItem>> getItem(){
		List<StockItem> result=stockService.getItem();
		result = result.stream().map(StockItem::of).collect(Collectors.toList());
		return ResponseEntity.ok().body(result);
	}
}

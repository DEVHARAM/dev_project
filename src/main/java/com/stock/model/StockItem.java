package com.stock.model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockItem {
	private int idstock;
	private String item_name;
	private int current_price;
	private int full_day_rate;
	private int change_rate;
	private int market_cap;
	private int listed_stock;
	private int ratio_of_foreigner;
	private int volume;
	private int PER;
	private int ROE;

	public StockItem(){}

	public static StockItem of(StockItem item) {
		return new StockItem(item.getIdstock(),item.getItem_name(),item.getCurrent_price(),item.getFull_day_rate(),item.getChange_rate(),
			item.getMarket_cap(),item.getListed_stock(),item.getRatio_of_foreigner(),item.getVolume(),item.getPER(),item.getROE());
	}

	public static final BeanPropertyRowMapper<StockItem> MAPPER = new BeanPropertyRowMapper<StockItem>(StockItem.class);
}

package com.stock.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stock.model.StockItem;

@Repository
public class StockRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger LOG = LoggerFactory.getLogger(StockRepository.class);

	public StockRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<StockItem> getItem(){
		String sql = "SELECT * FROM dev.stock";
		try {
			LOG.info(String.valueOf(namedParameterJdbcTemplate.query(sql, StockItem.MAPPER)));
			return namedParameterJdbcTemplate.query(sql, StockItem.MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

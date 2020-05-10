package com.stock.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.stock.model.StockItem;
import com.stock.repository.StockRepository;

@Service
public class StockService {
	private static final Logger LOG = LoggerFactory.getLogger(StockService.class);

	@Autowired
	private StockRepository stockRepository;

	public List<StockItem> getItem(){
		return stockRepository.getItem();
	}
	public void extractExcel(HttpServletResponse response) {
		List<StockItem> stockItems=getItem();
		response.setHeader("Set-Cookie", "fileDownload=true");
		response.setHeader("Content-Disposition", "attachment; filename=\"extractionListExcel.xlsx\"");

		SXSSFWorkbook wb = new SXSSFWorkbook(-1);

		final Sheet sheet = wb.createSheet("주식 데이터");
		List<String> cells = new ArrayList();
		cells.add("아이디");
		cells.add("종목명");
		cells.add("현재가");
		cells.add("전일비");
		cells.add("등락률");
		cells.add("시가총액");
		cells.add("상장주식수");
		cells.add("외국인비율");
		cells.add("거래량");
		cells.add("PER");
		cells.add("ROE");
		CellStyle headStyle = wb.createCellStyle();
		Cell headCell;
		Row headerRow = sheet.createRow(0);
		for (String cell : cells) {
			sheet.setColumnWidth(cells.indexOf(cell), 8000);
			headCell = headerRow.createCell(cells.indexOf(cell));
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(cell);
		}

		int rowNum = 1;
		for (StockItem st : stockItems) {
			Row row = sheet.createRow(rowNum++);
			Cell cell = row.createCell(0);
			cell.setCellValue(st.getIdstock());
			cell = row.createCell(1);
			cell.setCellValue(st.getItem_name());
			cell = row.createCell(2);
			cell.setCellValue(st.getCurrent_price());
			cell = row.createCell(3);
			cell.setCellValue(st.getFull_day_rate());
			cell = row.createCell(4);
			cell.setCellValue(st.getChange_rate());
			cell = row.createCell(5);
			cell.setCellValue(st.getMarket_cap());
			cell = row.createCell(6);
			cell.setCellValue(st.getListed_stock());
			cell = row.createCell(7);
			cell.setCellValue(st.getRatio_of_foreigner());
			cell = row.createCell(8);
			cell.setCellValue(st.getVolume());
			cell = row.createCell(9);
			cell.setCellValue(st.getPER());
			cell = row.createCell(10);
			cell.setCellValue(st.getROE());
		}
		try {
			wb.write(response.getOutputStream());
		} catch (IOException e) {
			LOG.error("[ExtractionViewController.downloadMbrExcel] excel download exception");
		}

	}
}

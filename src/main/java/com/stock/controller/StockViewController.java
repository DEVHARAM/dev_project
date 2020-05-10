package com.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class StockViewController {

	@RequestMapping(value="/")
	public String viewMain(){
		return "main";
	}
}

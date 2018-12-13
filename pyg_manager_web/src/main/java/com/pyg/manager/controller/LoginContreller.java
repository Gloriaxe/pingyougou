package com.pyg.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginContreller {

	@RequestMapping("/name")
	@ResponseBody
	public Map name() {
		Map map = new HashMap();
		String name=SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("loginName", name);
		return map;
	}
}

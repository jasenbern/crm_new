package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ReportService;
import com.atguigu.crm.utils.CRMUtils;

@RequestMapping("/report")
@Controller
public class ReportHandler {

	@Autowired
	private ReportService reportService;

	@RequestMapping("/drain")
	public String getCustomerDrain(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<Map<String, Object>> page = reportService.getDrainPage(pageNo,
				params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "report/drain";
	}

	@RequestMapping("/service/excel")
	public String serviceExcel(HttpServletRequest request) throws ParseException{
		
		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		List<Map<String, Object>> excelList = reportService.getServiceExcelList(params);
		
		request.setAttribute("excelList", excelList);
		
		return "myExcelDownloadView";
	}
	
	@RequestMapping("/service")
	public String getCustomerService(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<Map<String, Object>> page = reportService.getServicePage(pageNo,
				params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "report/service";
	}

	@RequestMapping("/consist/picture")
	public String consistPicture(Map<String, Object> map,
			@RequestParam("type") String type) {

		Map<String, Integer> consistNumMap = reportService
				.getConsistNumMap(type);

		for (Entry<String, Integer> entry : consistNumMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		return "myJFreeChartView";
	}

	@RequestMapping("/consist")
	public String getCustomerConsist(Map<String, Object> map,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			@RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		

		if (type == null) {
			type = "customer_level";
		}

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Map<String, Object>> page = reportService.getConsistPage(pageNo,
				type);
		map.put("page", page);
		map.put("queryString", "&type=" + type);
		
		request.setAttribute("type", type);

		return "report/consist";
	}

	@RequestMapping("/contribute")
	public String getCustomerContributeReport(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map, HttpServletRequest request)
			throws ParseException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		Page<Map<String, Object>> page = reportService.getContributePage(
				pageNo, params);
		map.put("page", page);

		String queryString = CRMUtils.parseParams2QueryString(params);
		map.put("queryString", queryString);

		return "report/contribute";
	}
}

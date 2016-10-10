package com.atguigu.crm.orm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.utils.CRMUtils;

@Component
public class MyExcelDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Map<String, Object>> excelList = (List<Map<String, Object>>) request
				.getAttribute("excelList");

		String prefix = "search_D_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, prefix);

		HSSFWorkbook workbook = getWorkBook(excelList);

		// 为了能够正常以文件下载的形式到处Excel文件，设置必要的响应消息头
		response.setContentType("application/vnd.ms-excel");

		// String fileName = "";
		String fileName = "details";
		boolean left = false;
		boolean right = false;
		if (params.get("minCreateDate") != null) {
			fileName = fileName + "(" + params.get("minCreateDate") + "-";
			left = true;
		}
		if (params.get("maxCreateDate") != null) {
			if (!left) {
				fileName = fileName + "(-";
			}
			fileName = fileName + params.get("maxCreateDate") + ")";
			right = true;
		}
		if (left && !right) {
			fileName = fileName + ")";
		}
		fileName = fileName + ".xls";

		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);

		ServletOutputStream out = response.getOutputStream();

		workbook.write(out);
	}

	private HSSFWorkbook getWorkBook(List<Map<String, Object>> excelList) {

		HSSFWorkbook workbook = new HSSFWorkbook();

		String title = "客户信息详细情况";

		HSSFSheet sheet = workbook.createSheet(title);

		HSSFRow row0 = sheet.createRow(0);

		List<String> row0List = new ArrayList<>();
		row0List.add("客户等级");
		row0List.add("客户名称");
		row0List.add("客户经理");
		row0List.add("联系电话");
		row0List.add("官方网站");
		row0List.add("客户地区");
		row0List.add("客户信用度");
		row0List.add("客户满意度");
		row0List.add("已付款总金额");
		row0List.add("未付款总金额");
		for (int i = 0; i < row0List.size(); i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(row0List.get(i));
		}

		List<String> rowNextList = new ArrayList<>();
		rowNextList.add("LEVEL");
		rowNextList.add("NAME");
		rowNextList.add("MNAME");
		rowNextList.add("TEL");
		rowNextList.add("WEBSIT");
		rowNextList.add("REGION");
		rowNextList.add("CREDIT");
		rowNextList.add("SATIFY");

		for (int i = 0; i < excelList.size(); i++) {

			Map<String, Object> cellMap = excelList.get(i);

			HSSFRow row = sheet.createRow(i + 1);

			for (int j = 0; j < rowNextList.size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue((String) cellMap.get(rowNextList.get(j)));
			}
			if (cellMap.get("payed") != null) {
				HSSFCell cellPay = row.createCell(rowNextList.size());
				cellPay.setCellValue(((BigDecimal) cellMap.get("payed"))
						.toString());
			}
			if (cellMap.get("unpayed") != null) {
				HSSFCell cellUnPay = row.createCell(rowNextList.size() + 1);
				cellUnPay.setCellValue(((BigDecimal) cellMap.get("unpayed"))
						.toString());
			}

		}
		for (int i = 0; i < row0List.size(); i++) {
			sheet.setColumnWidth(i, 200 * 20);
		}

		return workbook;
	}

}

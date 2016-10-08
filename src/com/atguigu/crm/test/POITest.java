package com.atguigu.crm.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POITest {
	public static void main(String[] args) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("工作表名称");

		// 5.创建代表行的HSSFRow对象
		HSSFRow row = sheet.createRow(0);
		// index表示行的索引，从0开始

		// 6.创建代表单元格的HSSFCell对象
		HSSFCell cell = row.createCell(0);
		// index表示单元格的索引，从0开始

		cell.setCellValue("csaljndsan");
		// 7.将Excel文件写入到文件系统中
		// ①创建一个文件输出流对象
		FileOutputStream outputStream = new FileOutputStream("文件路径.xls");
		// ②将文件内容写入这个输出流
		workbook.write(outputStream);

		// 8.调整工作表格式
		// 为了避免处理工作表数据时内存溢出，相关对象要尽可能重用，而不是每次都创建新的。
		HSSFDataFormat format = workbook.createDataFormat();
		// ①日期格式
		HSSFCellStyle styleDate = workbook.createCellStyle();
		styleDate.setDataFormat(format.getFormat("yyyy/MM/dd HH:mm:ss"));

		// ②小数格式
		HSSFCellStyle styleDouble = workbook.createCellStyle();
		styleDouble.setDataFormat(format.getFormat("#,#.00000"));
		// "#,#.00000"表示：节与节之间使用逗号分隔，保留5位小数

		// ③回绕文本
		HSSFCellStyle styleWrapText = workbook.createCellStyle();
		styleWrapText.setWrapText(true);

		// ④指定列宽：单位1/20像素
		// sheet.setColumnWidth(columnIndex, width);

		// ⑤自动列宽：根据内容自动调整列宽，但并不是绝对的
		// sheet.autoSizeColumn(columnIndex);

		// ⑥应用已有的样式
		cell.setCellStyle(styleWrapText);
	}
}

package com.atguigu.crm.test;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChartTest2 {
	public static void main(String[] args) throws IOException {
		// 1.使用JFreeChart技术生成图片形式的图表
		// 2.JFreeChart技术使用指南
		// ①jar包依赖
		// <dependency>
		// <groupId>org.jfree</groupId>
		// <artifactId>jfreechart</artifactId>
		// <version>1.0.19</version>
		// </dependency>
		// ②创建DefaultPieDataset对象，用来保存图表中要显示的数据
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("男生", 20);
		dataset.setValue("女生", 50);
		// ③创建图表数据对应的JFreeChart对象
		JFreeChart chart = ChartFactory.createPieChart("男女比例图", dataset, false,
				false, false);
		// ④将图表数据写入文件
		File file = new File("pie.jpg");
		ChartUtilities.saveChartAsJPEG(file, chart, 500, 300);
		// ⑤修饰
		// [1]设置“标题”部分字体、风格、字号
		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));
		// [2]设置“图例”部分信息字体、风格、字号
		// chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));
		// [3]通过PiePlot对象设置绘图区信息
		// 获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		// 设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));

		// 设置前景色半透明
		plot.setForegroundAlpha(0.6f);

		// 设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0},{1}/{3},{2}"));

		// 0：标签本身
		// 1：当前标签对应的数量
		// 3：总数量
		// 2：当前标签所占百分比
		// ⑥将图表图片作为响应数据返回
		// JFreeChart chart = statisticsService.getChart(questionId);
//		ServletOutputStream out = response.getOutputStream();
//		ChartUtilities.writeChartAsJPEG(out, chart, 400, 300);
		// 3.简答题数据直接显示答案列表，不做其他处理

	}
}

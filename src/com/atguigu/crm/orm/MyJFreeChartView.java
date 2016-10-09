package com.atguigu.crm.orm;

import java.awt.Font;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class MyJFreeChartView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JFreeChart chart = createChart(createDataset(map));
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 500,
				300);
	}

	private static PieDataset createDataset(Map<String, Object> map) {

		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();

			dataset.setValue(key, (Number) val);
		}

		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {

		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));
		ChartFactory.setChartTheme(standardChartTheme);

		JFreeChart chart = ChartFactory.createPieChart("统计结果", dataset, true,
				true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setCircular(true);
		plot.setStartAngle(290.0D);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setNoDataMessage("No data to display");
		plot.setLabelGap(0.02D);

		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}\n{2}"));
		// "{0}\n{1}/{3}\n{2}"));
		return chart;
	}
}

/*
 * Copyright (C) 2007-2009 Solertium Corporation
 *
 * This file is part of the open source GoGoEgo project.
 *
 * GoGoEgo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GoGoEgo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GoGoEgo.  If not, see http://www.gnu.org/licenses/.
 *
 * Unless you have been granted a different license in writing by the
 * copyright holders for GoGoEgo, only the GNU General Public License
 * grants you rights to modify or redistribute this code.
 */
package com.solertium.util.gwt.charts.example;

import java.util.Collection;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Image;
import com.solertium.util.gwt.charts.client.BarChart;
import com.solertium.util.gwt.charts.client.BaseChart;
import com.solertium.util.gwt.charts.client.ChartData;
import com.solertium.util.gwt.charts.client.ShapeMarker;

/**
 * BarChartExample.java
 * 
 * Example of a bar chart
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public class BarChartExample extends BaseExample {

	public Image getChart() {
		final Collection<Double> dataSet = getSampleDataSet();
		
		final ChartData chartData = getSampleChartData();
		chartData.setDefaultScaling("0,1");
				
		final int max = chartData.getMaxValue(dataSet).intValue();
		
		final BarChart chart = new BarChart(BarChart.ORIENTATION_VERTICAL);
		chart.setChartData(chartData);
		chart.setAvailableAxes(BarChart.AXIS_Y);
		chart.setAxisRange(BarChart.AXIS_Y, 0, max, (max / 5));
		chart.setChartSize(500, 500);
		chart.setTitle("Weekly Sales");
		chart.setAutoFitBarWidth(true);
		chart.setLegendPosition(BaseChart.LEGEND_POSITION_TOP, BaseChart.LEGEND_ORIENTATION_HORIZONTAL);
				
		int count = 0;
		for (Double value : dataSet) {
			final ShapeMarker marker = new ShapeMarker(
				NumberFormat.getCurrencyFormat().format(value.doubleValue()), "0", true	
			);
			marker.setDataPoint(count++);
			chart.addShapeMarker(marker);
		}
		
		return chart.generateWidget();
	}

	public String getDescription() {
		return "A bar chart with the legend moved to the bottom, the " +
		"modified axis range, and custom shape markers containing " +
		"the values formatted as currency.";
	}

	public String getSource() {
		return "final Collection<Double> dataSet = getSampleDataSet();\n\n"+
		
		"final ChartData chartData = getSampleChartData();\n"+
		"chartData.setDefaultScaling(\"0,1\");\n\n"+
				
		"final int max = chartData.getMaxValue(dataSet).intValue();\n\n"+
		
		"final BarChart chart = new BarChart(BarChart.ORIENTATION_VERTICAL);\n"+
		"chart.setChartData(chartData);\n"+
		"chart.setAvailableAxes(BarChart.AXIS_Y);\n"+
		"chart.setAxisRange(BarChart.AXIS_Y, 0, max, (max / 5));\n"+
		"chart.setChartSize(500, 500);\n"+
		"chart.setTitle(\"Weekly Sales\");\n"+
		"chart.setAutoFitBarWidth(true);\n"+
		"chart.setLegendPosition(BaseChart.LEGEND_POSITION_TOP, BaseChart.LEGEND_ORIENTATION_HORIZONTAL);\n\n"+
				
		"int count = 0;\n"+
		"for (Double value : dataSet) {\n"+
		"	final ShapeMarker marker = new ShapeMarker(\n"+
		"      NumberFormat.getCurrencyFormat().format(value.doubleValue()), \"0\", true\n"+	
		"	);\n"+
		"	marker.setDataPoint(count++);\n"+
		"	chart.addShapeMarker(marker);\n"+
		"}\n\n"+
		
		"return chart.generateWidget();";
	}

}

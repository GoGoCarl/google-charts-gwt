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
import com.solertium.util.gwt.charts.client.BaseChart;
import com.solertium.util.gwt.charts.client.ChartData;
import com.solertium.util.gwt.charts.client.LineChart;
import com.solertium.util.gwt.charts.client.ShapeMarker;

/**
 * LineChartExample.java
 * 
 * Example of a line chart. 
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public class LineChartExample extends BaseExample {

	public Image getChart() {
		final Collection<Double> dataSet = getSampleDataSet();
		
		final ChartData chartData = getSampleChartData(false);
		chartData.setDefaultScaling("0,1");
		
		final ShapeMarker marker = new ShapeMarker(ShapeMarker.MARKER_TYPE_DIAMOND, "0");
		marker.setDataPointOnEveryPoint();
						
		final LineChart chart = new LineChart();
		chart.setChartData(getSampleChartData(false));
		chart.setChartSize(500, 500);
		chart.setAvailableAxes(LineChart.AXIS_Y);
		chart.addShapeMarker(marker);
		chart.setTitle("Weekly Sales", "(in USD)");
		chart.setTitleFontColorAndSize("FF0000", "12");
		chart.setLegendPosition(BaseChart.LEGEND_POSITION_BOTTOM, BaseChart.LEGEND_ORIENTATION_HORIZONTAL);
				
		int count = 0;
		for (Double value : dataSet) {
			final ShapeMarker marker2 = new ShapeMarker(NumberFormat.getCurrencyFormat().format(value.doubleValue()), "0", true);
			marker2.setDataPoint(count++);
			chart.addShapeMarker(marker2);
		}
		
		return chart.generateWidget();
	}

	public String getDescription() {
		return "A simple line chart with the " +
		"title color changed and given a second line, " +
		"and dual shape markers on the data points.";
	}

	public String getSource() {
		return "final Collection<Double> dataSet = getSampleDataSet();\n\n"+
		
		"final ChartData chartData = getSampleChartData(false);\n"+
		"chartData.setDefaultScaling(\"0,1\");\n\n"+
		
		"final ShapeMarker marker = new ShapeMarker(ShapeMarker.MARKER_TYPE_DIAMOND, \"0\");\n"+
		"marker.setDataPointOnEveryPoint();\n\n"+
						
		"final LineChart chart = new LineChart();\n"+
		"chart.setChartData(getSampleChartData(false));\n"+
		"chart.setChartSize(500, 500);\n"+
		"chart.setAvailableAxes(LineChart.AXIS_Y);\n"+
		"chart.addShapeMarker(marker);\n"+
		"chart.setTitle(\"Weekly Sales\", \"(in USD)\");\n"+
		"chart.setTitleFontColorAndSize(\"FF0000\", \"12\");\n"+
		"chart.setLegendPosition(BaseChart.LEGEND_POSITION_BOTTOM, BaseChart.LEGEND_ORIENTATION_HORIZONTAL);\n\n"+
				
		"int count = 0;\n"+
		"for (Double value : dataSet) {\n"+
		"	final ShapeMarker marker2 = new ShapeMarker(NumberFormat.getCurrencyFormat().format(value.doubleValue()), \"0\", true);\n"+
		"	marker2.setDataPoint(count++);\n"+
		"	chart.addShapeMarker(marker2);\n"+
		"}\n\n"+
		
		"return chart.generateWidget();";
	}

}

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

import java.util.List;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LegendPosition;
import com.googlecode.charts4j.Plots;

/**
 * Charts4JBarChartExample.java
 * 
 * Attempt at an implementation based on the Charts4j GWT 
 * project.  This does not depend on any of the code in 
 * Google Charts for GWT.
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public class Charts4JBarChartExample extends BarChartExample {
	
	public Image getChart() {
		final List<Double> dataSet = getSampleDataSet();
		final BarChartPlot plot = Plots.newBarChartPlot(Data.newData(dataSet));
		
		int i = 0;
		for (Double value : dataSet) {
			plot.addTextMarker(
				NumberFormat.getCurrencyFormat().format(
					value.doubleValue()), Color.BLACK, 12, i++
			);
		}
		
		final BarChart chart = GCharts.newBarChart(plot);
		chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(dataSet));
		chart.setSize(500, 500);
		chart.setTitle("Weekly Sales");
		chart.setLegendPosition(LegendPosition.TOP);
		
		return new Image(chart.toURLString());
	}
	
	@Override
	public String getSource() {
		return "final List<Double> dataSet = getSampleDataSet();\n" +
		"final BarChartPlot plot = Plots.newBarChartPlot(Data.newData(dataSet));\n\n" +
		
		"int i = 0;\n"+
		"for (Double value : dataSet) {\n"+
		"	plot.addTextMarker(\n"+
		"		NumberFormat.getCurrencyFormat().format(\n"+
		"			value.doubleValue()), Color.BLACK, 12, i++\n"+
		"	);\n"+
		"}\n\n"+
		
		"final BarChart chart = GCharts.newBarChart(plot);\n"+
		"chart.addYAxisLabels(AxisLabelsFactory.newNumericAxisLabels(dataSet));\n"+
		"chart.setSize(500, 500);\n"+
		"chart.setTitle(\"Weekly Sales\");\n"+
		"chart.setLegendPosition(LegendPosition.TOP);\n\n"+
		
		"return new Image(chart.toURLString());";
	}

}

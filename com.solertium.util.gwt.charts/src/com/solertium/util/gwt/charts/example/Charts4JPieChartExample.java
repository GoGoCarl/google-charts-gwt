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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Image;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LegendPosition;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

/**
 * Charts4JPieChartExample.java
 * 
 * Attempt at an implementation based on the Charts4j GWT 
 * project.  This does not depend on any of the code in 
 * Google Charts for GWT.
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public class Charts4JPieChartExample extends SimplePieChartExample {
	
	@Override
	public Image getChart() {
		/*
		final String[] sampleNames = new String[] {
			"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
		};
		*/
		final Color[] sampleColors = new Color[] {
			Color.RED, Color.BLUE,  Color.GREEN, Color.YELLOW, Color.BLACK
		};
		final List<Double> dataSet = getSampleDataSet();
		final List<Slice> slices = new ArrayList<Slice>();
		
		double total = 0;
		for (Double value : dataSet)
			total += value;
		
		for (int i = 0; i < dataSet.size(); i++) {
			int normalizedPercentage = (int)((dataSet.get(i) / total) * 100);
			slices.add(Slice.newSlice(normalizedPercentage, sampleColors[i],"",""));
		}
		
		final PieChart chart = GCharts.newPieChart(slices);
		chart.setTitle("Weekly Sales");
		chart.setSize(500, 500);
		chart.setLegendPosition(LegendPosition.RIGHT);
		
		
		
		return new Image(chart.toURLString());
	}
	
	public String getSource() {
		return "final String[] sampleNames = new String[] {\n"+
		"	\"Monday\", \"Tuesday\", \"Wednesday\", \"Thursday\", \"Friday\"\n"+
		"};\n"+
		"final Color[] sampleColors = new Color[] {\n"+
		"	Color.RED, Color.BLUE,  Color.GREEN, Color.YELLOW, Color.BLACK\n"+
		"};\n"+
		"final List<Double> dataSet = getSampleDataSet();\n"+
		"final List<Slice> slices = new ArrayList<Slice>();\n\n"+
		
		"double total = 0;\n"+
		"for (Double value : dataSet)\n"+
		"	total += value;\n\n"+
		
		"for (int i = 0; i < dataSet.size(); i++) {\n"+
		"	int normalizedPercentage = (int)((dataSet.get(i) / total) * 100);\n"+
		"	slices.add(Slice.newSlice(normalizedPercentage, sampleColors[i]));\n"+
		"}\n\n"+
		
		"final PieChart chart = GCharts.newPieChart(slices);\n"+
		"chart.setTitle(\"Weekly Sales\");\n"+
		"chart.setSize(500, 500);\n\n"+
		
		"return new Image(chart.toURLString());";
	}

}

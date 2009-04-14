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

import com.google.gwt.user.client.ui.Image;
import com.solertium.util.gwt.charts.client.PieChart;
import com.solertium.util.gwt.charts.client.fill.ChartFill;

/**
 * AdvancedPieChartExample.java
 * 
 * Example of an advanced pie chart.
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public class AdvancedPieChartExample extends BaseExample {

	public Image getChart() {
		final ChartFill fill = new ChartFill();
		fill.setBackgroundColor("0000FF", "21");
		fill.setChartTransparent(false);
		
		final PieChart chart = new PieChart(true);
		chart.setTitle("Weekly Sales");
		chart.setOrientation(34);
		chart.setChartData(getSampleChartData());
		chart.setChartSize(500, 500);
		chart.setChartBackground(fill);
		
		return chart.generateWidget();
	}

	public String getDescription() {
		return "A pie chart with a semi-transparent background, " +
		"legend, in 3D, and angled at 34 radians.";
	}

	public String getSource() {
		return "final ChartFill fill = new ChartFill();\n"+
		"fill.setBackgroundColor(\"0000FF\", \"21\");\n"+
		"fill.setChartTransparent(false);\n\n"+		
		"final PieChart chart = new PieChart(true);\n"+
		"chart.setTitle(\"Weekly Sales\");\n"+
		"chart.setOrientation(34);\n"+
		"chart.setChartData(getSampleChartData());\n"+
		"chart.setChartSize(500, 500);\n"+
		"chart.setChartBackground(fill);\n\n"+
		
		"return chart.generateWidget();";
	}

}

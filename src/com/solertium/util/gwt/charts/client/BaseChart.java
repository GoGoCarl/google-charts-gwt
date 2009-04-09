/*
 * Copyright (C) 2007-2008 Solertium Corporation
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
package com.solertium.util.gwt.charts.client;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.user.client.ui.Image;

/**
 * BaseChart.java
 *
 * Your basic chart implementation,  It uses the standard
 * Google Chart API to generate a URL and display the
 * image of the chart based on settings provided via the
 * implementing chart classes.
 *
 * This class also provides a means of setting global
 * variables.
 *
 * @author carl.scott
 *
 */
public abstract class BaseChart extends HashMap<String, String> {
	
	private static final long serialVersionUID = 1L;

	public static final String LEGEND_POSITION_BOTTOM = "b";
	public static final String LEGEND_POSITION_TOP = "t";
	public static final String LEGEND_POSITION_RIGHT = "r";
	public static final String LEGEND_POSITION_LEFT = "l";

	public static final String LEGEND_ORIENTATION_HORIZONTAL = "h";
	public static final String LEGEND_ORIENTATION_VERTICAL = "v";

	private ChartData chartData;

	public BaseChart(String chartType) {
		super();
		put("cht", chartType);
	}

	/**
	 * The maximum size is 300,000 px, the max of either
	 * width or height is 1000.
	 * @param width
	 * @param height
	 */
	public void setChartSize(int width, int height) {
		if (width * height > 300000) {
			width = 1000;
			height = 300;
		}
		put("chs", width + "x" + height);
	}

	public void setTitle(String... titleLines) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < titleLines.length; i++) {
			builder.append(titleLines[i].replace(' ', '+'));
			if ((i+1) < titleLines.length)
				builder.append('|');
		}
		put("chtt", builder.toString());
	}

	public void setTitleFontColorAndSize(String fontColorHex, String size) {
		if (fontColorHex == null)
			fontColorHex = "000000";
		if (size == null)
			size = "14";
		put("chts", fontColorHex + "," + size);
	}

	public void setLegendPosition(String position, String orientation) {
		if (LEGEND_ORIENTATION_VERTICAL.equals(orientation)) {
			if (LEGEND_POSITION_BOTTOM.equals(position) ||
				LEGEND_POSITION_TOP.equals(position)) {
				put("chdlp", position + orientation);
			}
			else
				put("chdlp", position);
		}
		else if (LEGEND_ORIENTATION_HORIZONTAL.equals(orientation)) {
			if (LEGEND_POSITION_BOTTOM.equals(position) ||
					LEGEND_POSITION_TOP.equals(position)) {
				put("chdlp", position);
			}
		}
		else
			remove("chdlp");
	}

	public void setChartMargins(ChartMargins chartMargins) {
		put("chma", chartMargins.toString());
	}

	public String generate() {
		if (chartData == null) {
			System.out.println("Please specify chart data.");
			return null;
		}

		put("chd", chartData.getEncodedData());

		final StringBuilder builder = new StringBuilder();
		for (Iterator<String> iter = keySet().iterator(); iter.hasNext(); ) {
			String key = iter.next();
			String value = get(key);
			builder.append(key);
			builder.append('=');
			builder.append(value);
			if (iter.hasNext())
				builder.append("&");
		}

		return "http://chart.apis.google.com/chart?" + builder.toString();
	}

	public Image generateWidget() {
		return new Image(generate());
	}

	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
		put("chds", chartData.getScaling());
		if (chartData.getLegendNames() != null)
			put("chdl", chartData.getLegendNames());
		if (chartData.getLegendColors() != null)
			put("chco", chartData.getLegendColors());
	}
}

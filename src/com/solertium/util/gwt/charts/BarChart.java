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
package com.solertium.util.gwt.charts;

import com.solertium.util.gwt.charts.fill.BackgroundFill;

/**
 * BarChart.java
 *
 * @author carl.scott
 *
 */
public class BarChart extends AxisChart {

	private static final long serialVersionUID = 1L;

	public static final String ORIENTATION_HORIZONTAL = "h";
	public static final String ORIENTATION_VERTICAL = "v";

	public static final String DISPLAY_STACKED = "s";
	public static final String DISPLAY_GROUPED = "g";

	/**
	 * Creates a bar chart with a vertical orientation
	 * and grouped display.
	 * @param chartType
	 */
	public BarChart() {
		this(ORIENTATION_VERTICAL);
	}

	public BarChart(String orientation) {
		this(orientation, DISPLAY_GROUPED);
	}

	public BarChart(String orientation, String display) {
		super("b" + orientation + display);
	}

	public void setAutoFitBarWidth(boolean enable) {
		if (enable)
			put("chbh", "a");
		else
			remove("chbh");
	}

	public void setChartBackground(BackgroundFill fill) {
		put("chf", fill.toString());
	}

	public void setZeroLine(double lineAsPercentage) {
		put("chp", Double.toString(lineAsPercentage));
	}

}

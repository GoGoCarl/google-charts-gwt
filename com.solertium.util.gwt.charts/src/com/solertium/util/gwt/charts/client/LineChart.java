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
package com.solertium.util.gwt.charts.client;

import com.solertium.util.gwt.charts.client.fill.ChartFill;


/**
 * LineChart.java
 * 
 * Implementation of a line chart.
 *
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 *
 */
public class LineChart extends AxisChart {

	public static final String PLOT_X_AXIS_WITH_LINES = "c";
	public static final String PLOT_X_AXIS_WITHOUT_LINES = "s";
	public static final String PLOT_XY = "xy";

	private static final long serialVersionUID = 1L;

	public LineChart() {
		this(PLOT_X_AXIS_WITH_LINES);
	}

	public LineChart(String plotType) {
		super("l" + plotType);
	}

	public void setChartBackground(ChartFill fill) {
		put("chf", fill.toString());
	}

}

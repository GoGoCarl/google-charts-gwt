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

import com.solertium.util.gwt.charts.client.fill.BackgroundFill;

/**
 * PieChart.java
 * 
 * Implementation of a pie chart.
 *
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 *
 */
public class PieChart extends BaseChart {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public PieChart() {
		this(false);
	}

	/**
	 * Constructor with option to make this chart 3D
	 * @param is3d true for 3d, false otherwise
	 */
	public PieChart(boolean is3d) {
		super(is3d ? "p3" : "p");
	}

	/**
	 * Use this to set this chart at an angle.
	 * @param angleInRadians
	 */
	public void setOrientation(int angleInRadians) {
		put("chp", Integer.toString(angleInRadians));
	}

	public void setChartLabels(String... labels) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < labels.length; i++) {
			builder.append(labels[i]);
			if ((i+1) < labels.length)
				builder.append('|');
		}
		put("chl", builder.toString());
	}

	public void setChartBackground(BackgroundFill fill) {
		put("chf", fill.toString());
	}

}

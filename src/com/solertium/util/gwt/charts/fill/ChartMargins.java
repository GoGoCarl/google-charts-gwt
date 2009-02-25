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
package com.solertium.util.gwt.charts.fill;

/**
 * ChartMargins.java
 *
 * @author carl.scott
 *
 */
public class ChartMargins {

	public final int top, left, bottom, right;
	public int legendWidth, legendHeight;

	public ChartMargins(int margins) {
		this(margins, margins, margins, margins);
	}

	public ChartMargins(int top, int left, int bottom, int right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.legendHeight = this.legendWidth = -1;
	}

	public void setLegendSize(int legendWidth, int legendHeight) {
		this.legendWidth = legendWidth;
		this.legendHeight = legendHeight;
	}

	public String toString() {
		String out = top + "," + left + "," + bottom + "," + right;
		if (legendHeight >= 0 && legendWidth >= 0)
			out += "|" + legendWidth + "," + legendHeight;
		return out;
	}

}

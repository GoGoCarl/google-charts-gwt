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
 * BackgroundFill.java
 *
 * @author carl.scott
 *
 */
public class BackgroundFill {

	private String bgColor;
	private boolean isChartTransparent;

	public BackgroundFill() {
		bgColor = "FFFFFF";
		isChartTransparent = true;
	}

	public void setBackgroundColor(String bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * Transparency is an option two letter hex from
	 * 00 to FF that will cause the specified bgColor
	 * to be transparent by that amount
	 * @param bgColor
	 * @param transparency
	 */
	public void setBackgroundColor(String bgColor, String transparency) {
		if (transparency != null && transparency.length() == 2)
			this.bgColor = bgColor + transparency;
		else
			this.bgColor = bgColor;
	}

	public void setChartTransparent(boolean isChartTransparent) {
		this.isChartTransparent = isChartTransparent;
	}

	public String toString() {
		return (isChartTransparent) ? "a,s,FFFFFF" : "bg,s," + bgColor;
	}

}

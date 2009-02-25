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

/**
 * ShapeMarker.java
 *
 * @author carl.scott
 *
 */
public class ShapeMarker {

	public static final String MARKER_TYPE_ARROW = "a";
	public static final String MARKER_TYPE_CROSS = "c";
	public static final String MARKER_TYPE_DIAMOND = "d";
	public static final String MARKER_TYPE_CIRCLE = "o";
	public static final String MARKER_TYPE_SQUARE = "s";
	public static final String MARKER_TYPE_VERTICAL_LINE_TO_POINT = "v";
	public static final String MARKER_TYPE_VERTICAL_LINE_ACROSS_CHART = "V";
	public static final String MARKER_TYPE_HORIZONAL_LINE_ACROSS_CHART = "h";
	public static final String MARKER_TYPE_X_SHAPE = "x";

	public static final String PRIORITY_DRAW_FIRST = "-1";
	public static final String PRIORITY_DEFAULT = "0";
	public static final String PRIORITY_DRAW_LAST = "1";

	private final String markerType, dataSetIndex;
	private String dataPoint, color, size, priority;

	private String prefix;

	public ShapeMarker(String markerType, String dataSetIndex) {
		this(markerType, dataSetIndex, false);
	}

	public ShapeMarker(String markerType, String dataSetIndex, boolean typeAsText) {
		this.markerType = typeAsText ? "t" + markerType.replace(' ', '+') : markerType;
		this.dataSetIndex = dataSetIndex;
		dataPoint = "-1";
		size = "10";
		priority = "0";
		prefix = "";
	}

	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 0 to draw a marker on the first data point, 1 to draw a marker
	 * on the second data point, and so on. Use a decimal number to
	 * interpolate a marker between two data points.
	 * @param point
	 */
	public void setDataPoint(double point) {
		this.dataPoint = Double.toString(point);
		prefix = "";
	}

	public void setDataPointOnEveryXPoint(int x) {
		this.dataPoint = "-" + Integer.toString(x);
		prefix = "";
	}

	public void setDataPointOnEveryXPoint(int x, int startRange, int endRange) {
		this.dataPoint = startRange + ":" + endRange + ":" + x;
		prefix = "";
	}

	public void setDataPointAsPlottedPoint(double xCoord, double yCoord) {
		this.dataPoint = xCoord + ":" + yCoord;
		prefix = "@";
	}

	public void setDataPointAsHorizontalLine(double lineLocationAsPercentage) {
		this.dataPoint = Double.toString(lineLocationAsPercentage);
	}

	public void setDataPointOnEveryPoint() {
		this.dataPoint = "-1";
		prefix = "";
	}

	public void setMarkerSize(int pixelSize) {
		this.size = Integer.toString(pixelSize);
	}

	/**
	 * Use the static variables.
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String toString() {
		return prefix + markerType + "," + (color == null ? "," : color) +
			dataSetIndex + "," + dataPoint + "," + size + "," +
			priority;
	}
}

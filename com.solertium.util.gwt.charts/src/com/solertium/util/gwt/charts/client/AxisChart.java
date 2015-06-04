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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * AxisChart.java
 * 
 * Abstract class for all charts that have axes, and the
 * functionality that comes with them.
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public abstract class AxisChart extends BaseChart {

	private static final long serialVersionUID = 1L;
	private static final String prefixPossibilities = "xyrt";

	public static final String AXIS_X = "x";
	public static final String AXIS_Y = "y";
	public static final String AXIS_RIGHT = "r";
	public static final String AXIS_TOP = "t";

	public final HashMap<String, String> axisLabels, axisRange;
	public final HashMap<String, AxisStyle> axisStyles;

	public final ArrayList<ShapeMarker> shapeMarkers;

	public AxisChart(String chartType) {
		super(chartType);
		axisLabels = new HashMap<String, String>();
		axisRange = new HashMap<String, String>();
		axisStyles = new HashMap<String, AxisStyle>();
		shapeMarkers = new ArrayList<ShapeMarker>();
	}

	public void setAvailableAxes(String... axes) {
		put("chxt", getSeparatedList(axes, ','));
	}

	public void setAxisLabels(String axis, String... labels) {
		axisLabels.put(""+prefixPossibilities.indexOf(axis.charAt(0)), getSeparatedList(labels, '|'));

		final StringBuilder builder = new StringBuilder();

		final ArrayList<String> keys = new ArrayList<String>(axisLabels.keySet());
		Collections.sort(keys);

		final Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			builder.append(key);
			builder.append(":|");
			builder.append(axisLabels.get(key));
			if (iterator.hasNext())
				builder.append('|');
		}

		put("chxl", builder.toString());
	}

	public void setAxisRange(String axis, int min, int max) {
		setAxisRange(axis, min, max, -1);
	}

	public void setAxisRange(String axis, int min, int max, int interval) {
		axisRange.put(""+prefixPossibilities.indexOf(axis.charAt(0)),
			min + "," + max + (interval > 0 ? ("," + interval) : "")
		);

		final StringBuilder builder = new StringBuilder();

		final ArrayList<String> keys = new ArrayList<String>(axisRange.keySet());
		Collections.sort(keys);

		final Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			builder.append(key);
			builder.append(",");
			builder.append(axisRange.get(key));
			if (iterator.hasNext())
				builder.append('|');
		}

		put("chxr", builder.toString());
	}

	public void setAxisStyle(String axis, AxisStyle style) {
		axisStyles.put(""+prefixPossibilities.indexOf(axis.charAt(0)),
			style
		);

		final StringBuilder builder = new StringBuilder();

		final ArrayList<String> keys = new ArrayList<String>(axisStyles.keySet());
		Collections.sort(keys);

		final Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			builder.append(key);
			builder.append(",");
			builder.append(axisStyles.get(key));
			if (iterator.hasNext())
				builder.append('|');
		}

		put("chxs", builder.toString());
	}

	public void addShapeMarker(ShapeMarker shapeMarker) {
		shapeMarkers.add(shapeMarker);

		final StringBuilder builder = new StringBuilder();
		for (Iterator<ShapeMarker> iter = shapeMarkers.listIterator(); iter.hasNext(); ) {
			builder.append(iter.next().toString());
			if (iter.hasNext())
				builder.append('|');
		}

		put("chm", builder.toString());
	}

	private String getSeparatedList(String[] list, char separator) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.length; i++) {
			builder.append(list[i]);
			if ((i+1) < list.length)
				builder.append(separator);
		}
		return builder.toString();
	}

	public static class AxisStyle {

		public static final String ALIGN_LEFT = "-1";
		public static final String ALIGN_CENTER = "0";
		public static final String ALIGN_RIGHT = "1";

		public static final String DRAWING_CONTROL_AXIS_ONLY = "l";
		public static final String DRAWING_CONTROL_TICKS_ONLY = "t";
		public static final String DRAWING_CONTROL_AXIS_TICKS = "lt";


		private String axisColor, fontSize, alignment, drawingControl, tickMarkColor;

		public AxisStyle(String axisColor) {
			this.axisColor = axisColor;
		}

		public void setFontSize(String fontSize) {
			this.fontSize = fontSize;
		}

		public void setAlignLabels(String align) {
			this.alignment = align;
		}

		public void setDrawingControl(String drawingControl) {
			this.drawingControl = drawingControl;
		}

		public void setTickMarkColor(String tickMarkColor) {
			this.tickMarkColor = tickMarkColor;
		}

		public String toString() {
			StringBuilder out = new StringBuilder();
			out.append(axisColor + ",");
			out.append(fontSize == null ? "," : fontSize);
			out.append(alignment == null ? "," : alignment);
			out.append(drawingControl == null ? "," : drawingControl);
			out.append(tickMarkColor == null ? "," : tickMarkColor);
			return out.toString();
		}
	}

}

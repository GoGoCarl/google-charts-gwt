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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * ChartData.java
 *
 * Specify data that is to be charted here.  Uses the encoding
 * method.
 *
 * @author carl.scott
 *
 */
public class ChartData {

	private ArrayList<ChartDataStruct> data;
	private String defaultScaling;

	public ChartData() {
		data = new ArrayList<ChartDataStruct>();
	}

	public void setDefaultScaling(String defaultScaling) {
		this.defaultScaling = defaultScaling;
	}

	public void addData(Collection<Double> data) {
		ChartDataExtras extras = new ChartDataExtras();
		extras.legendColors = new String[] {"000000"};
		extras.legendNames = new String[] {"Column " + (this.data.size()+1)};
		addData(data, extras);
	}

	public void addData(Collection<Double> data, ChartDataExtras chartDataExtras) {
		this.data.add(new ChartDataStruct(data, chartDataExtras));
	}

	public String getData() {
		final StringBuilder builder = new StringBuilder();
		builder.append("t:");
		for (Iterator<ChartDataStruct> keyIter = data.listIterator(); keyIter.hasNext(); ) {
			Collection<Double> current = keyIter.next().data;
			for (Iterator<Double> iter = current.iterator(); iter.hasNext(); )
				builder.append(iter.next() + (iter.hasNext() ? "," : ""));
			if (keyIter.hasNext())
				builder.append('|');
		}
		return builder.toString();
	}

	public String getEncodedData() {
		final StringBuilder builder = new StringBuilder();
		builder.append("s:");
		for (Iterator<ChartDataStruct> keyIter = data.listIterator(); keyIter.hasNext(); ) {
			Collection<Double> current = keyIter.next().data;
			final StringBuilder csv = new StringBuilder();
			for (Iterator<Double> iter = current.iterator(); iter.hasNext(); )
				csv.append(iter.next() + (iter.hasNext() ? "," : ""));
			builder.append(_encode(csv.toString(), getMaxValue(current).toString()));
			if (keyIter.hasNext())
				builder.append(',');
		}
		return builder.toString();
	}

	public String getScaling() {
		if (defaultScaling != null)
			return defaultScaling;

		final StringBuilder csv = new StringBuilder();
		for (Iterator<ChartDataStruct> iter = data.listIterator(); iter.hasNext(); ) {
			String out = iter.next().extras.scaling;
			if (out == null)
				out = "-1000,1000";

			csv.append(out + (iter.hasNext() ? "," : ""));
		}
		return csv.toString();
	}

	public String getLegendNames() {
		final StringBuilder builder = new StringBuilder();
		for (Iterator<ChartDataStruct> iter = data.listIterator(); iter.hasNext(); ) {
			String name = iter.next().extras.getLegendNames();
			if (name != null) {
				builder.append(name);
				if (iter.hasNext())
					builder.append('|');
			}
		}
		final String out = builder.toString();
		return "".equals(out) ? null : out;
	}

	public String getLegendColors() {
		final StringBuilder builder = new StringBuilder();
		for (Iterator<ChartDataStruct> iter = data.listIterator(); iter.hasNext(); ) {
			String color = iter.next().extras.getLegendColors();
			if (color != null) {
				builder.append(color);
				if (iter.hasNext())
					builder.append(',');
			}
		}
		final String out = builder.toString();
		return "".equals(out) ? null : out;
	}

	public Double getMaxValue(Collection<Double> dataSet) {
		Double maxValue = new Double(0);
		for (Iterator<Double> iter = dataSet.iterator(); iter.hasNext(); ) {
			Double current = iter.next();
			if (current.doubleValue() > maxValue.doubleValue())
				maxValue = current;
		}
		return maxValue.doubleValue() > 0 ? maxValue : new Double(100);
	}

	public static native String _encode(String csv, String maxValueStr) /*-{
		var simpleEncoding = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

		var maxValue = maxValueStr * 1;

		var valueArray = csv.split(",");
		var chartData = [''];

  		for (var i = 0; i < valueArray.length; i++) {
    		var currentValue = valueArray[i];
    		if (!isNaN(currentValue) && currentValue >= 0) {
    			chartData.push(simpleEncoding.charAt(Math.round((simpleEncoding.length-1) * currentValue / maxValue)));
    		}
      		else {
      			chartData.push('_');
      		}
  		}
		return chartData.join('');
	}-*/;

	public static class ChartDataExtras {

		private String scaling;
		private String[] legendNames;
		private String[] legendColors;

		public void setLegendColors(String... legendColors) {
			this.legendColors = legendColors;
		}

		public void setLegendNames(String... legendNames) {
			this.legendNames = legendNames;
		}

		public void setScaling(String scaling) {
			this.scaling = scaling;
		}

		public String getLegendColors() {
			return legendColors == null ? null : toCSV(legendColors, '|');
		}

		public String getLegendNames() {
			return legendNames == null ? null : toCSV(legendNames, '|');
		}

		private String toCSV(String[] array, char separator) {
			StringBuilder out = new StringBuilder();
			int size = array.length;
			for (int i = 0; i < size; i++) {
				out.append(array[i]);
				if ((i+1) < size)
					out.append(separator);
			}
			return out.toString();
		}

	}

	private static class ChartDataStruct {
		private final Collection<Double> data;
		private final ChartDataExtras extras;
		public ChartDataStruct(Collection<Double> data, ChartDataExtras extras) {
			this.data = data;
			this.extras = extras;
		}
	}

}

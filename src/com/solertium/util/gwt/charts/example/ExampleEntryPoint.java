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

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.solertium.util.gwt.charts.client.BarChart;
import com.solertium.util.gwt.charts.client.BaseChart;
import com.solertium.util.gwt.charts.client.ChartData;
import com.solertium.util.gwt.charts.client.LineChart;
import com.solertium.util.gwt.charts.client.PieChart;
import com.solertium.util.gwt.charts.client.ShapeMarker;
import com.solertium.util.gwt.charts.client.ChartData.ChartDataExtras;
import com.solertium.util.gwt.charts.client.fill.ChartFill;

/**
 * ExampleEntryPoint.java
 * 
 * A small example of functionality implemented.  This is not a full 
 * kitchen sink as there's too many possible combinations of features, 
 * but this should give an idea of what's available.
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public class ExampleEntryPoint implements EntryPoint {
	
	private final Image chartImage;
	private final HTML description;
	
	public ExampleEntryPoint() {
		chartImage = new Image();
		chartImage.setVisible(false);
		
		description = new HTML("Click a button above to render an example chart.");
	}

	public void onModuleLoad() {
		final VerticalPanel container = new VerticalPanel();
		container.add(createButtonPanel());
		container.add(chartImage);
		container.add(description);
	
		RootPanel.get("loading").setVisible(false);
		RootPanel.get("loading").removeFromParent();
		
		RootPanel.get().add(container);
	}
	
	private HorizontalPanel createButtonPanel() {
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(4);
		buttonPanel.add(new Button("Simple Pie Chart", new ClickListener() {
			public void onClick(Widget sender) {
				chartImage.setVisible(true);
				showSimplePieChart();
			}
		}));
		buttonPanel.add(new Button("3D Pie Chart", new ClickListener() {
			public void onClick(Widget sender) {
				chartImage.setVisible(true);
				show3DPieChart();
			}
		}));
		buttonPanel.add(new Button("Bar Chart", new ClickListener() {
			public void onClick(Widget sender) {
				chartImage.setVisible(true);
				showBarChart();
			}
		}));
		buttonPanel.add(new Button("Line Chart", new ClickListener() {
			public void onClick(Widget sender) {
				chartImage.setVisible(true);
				showLineChart();
			}
		}));
		
		return buttonPanel;
	}
	
	private void showLineChart() {
		final Collection<Double> dataSet = getSampleDataSet();
		
		final ChartData chartData = getSampleChartData(false);
		chartData.setDefaultScaling("0,1");
		
		final ShapeMarker marker = new ShapeMarker(ShapeMarker.MARKER_TYPE_DIAMOND, "0");
		marker.setDataPointOnEveryPoint();
						
		final LineChart chart = new LineChart();
		chart.setChartData(getSampleChartData(false));
		chart.setChartSize(500, 500);
		chart.setAvailableAxes(LineChart.AXIS_Y);
		chart.addShapeMarker(marker);
		chart.setTitle("Weekly Sales", "(in USD)");
		chart.setTitleFontColorAndSize("FF0000", "12");
		chart.setLegendPosition(BaseChart.LEGEND_POSITION_BOTTOM, BaseChart.LEGEND_ORIENTATION_HORIZONTAL);
				
		int count = 0;
		for (Double value : dataSet) {
			final ShapeMarker marker2 = new ShapeMarker(NumberFormat.getCurrencyFormat().format(value.doubleValue()), "0", true);
			marker2.setDataPoint(count++);
			chart.addShapeMarker(marker2);
		}
		
		chartImage.setUrl(chart.generate());
		
		description.setHTML(
			"A simple line chart with the " +
			"title color changed and given a second line, " +
			"and dual shape markers on the data points."
		);
	}
	
	private void showBarChart() {
		final Collection<Double> dataSet = getSampleDataSet();
		
		final ChartData chartData = getSampleChartData();
		chartData.setDefaultScaling("0,1");
				
		final int max = chartData.getMaxValue(dataSet).intValue();
		
		final BarChart chart = new BarChart(BarChart.ORIENTATION_VERTICAL);
		chart.setChartData(chartData);
		chart.setAvailableAxes(BarChart.AXIS_Y);
		chart.setAxisRange(BarChart.AXIS_Y, 0, max, (max / 5));
		chart.setChartSize(500, 500);
		chart.setTitle("Weekly Sales");
		chart.setAutoFitBarWidth(true);
		chart.setLegendPosition(BaseChart.LEGEND_POSITION_TOP, BaseChart.LEGEND_ORIENTATION_HORIZONTAL);
				
		int count = 0;
		for (Double value : dataSet) {
			final ShapeMarker marker = new ShapeMarker(
				NumberFormat.getCurrencyFormat().format(value.doubleValue()), "0", true	
			);
			marker.setDataPoint(count++);
			chart.addShapeMarker(marker);
		}
		
		chartImage.setUrl(chart.generate());
		
		description.setHTML(
			"A bar chart with the legend moved to the bottom, the " +
			"modified axis range, and custom shape markers containing " +
			"the values formatted as currency."
		);
	}
	
	private void show3DPieChart() {
		final ChartFill fill = new ChartFill();
		fill.setBackgroundColor("0000FF", "21");
		fill.setChartTransparent(false);
		
		final PieChart chart = new PieChart(true);
		chart.setTitle("Weekly Sales");
		chart.setOrientation(34);
		chart.setChartData(getSampleChartData());
		chart.setChartSize(500, 500);
		chart.setChartBackground(fill);
		
		chartImage.setUrl(chart.generate());
		
		description.setHTML(
			"A pie chart with a semi-transparent background, " +
			"legend, in 3D, and angled at 34 radians."
		);
	}
	
	private void showSimplePieChart() {		
		final PieChart chart = new PieChart();
		chart.setTitle("Weekly Sales");
		chart.setChartData(getSampleChartData());
		chart.setChartSize(500, 500);
		
		chartImage.setUrl(chart.generate());
		
		description.setHTML(
			"A simple, no-frills implementation of a pie chart." +
			"Just contains a title and legend."
		);
	}
	
	private Collection<Double> getSampleDataSet() {
		final Collection<Double> sampleData = new ArrayList<Double>();
		sampleData.add(Double.valueOf(10));
		sampleData.add(Double.valueOf(20));
		sampleData.add(Double.valueOf(50));
		sampleData.add(Double.valueOf(65));
		sampleData.add(Double.valueOf(100));
		
		return sampleData;
	}
	
	private ChartData getSampleChartData() {
		return getSampleChartData(true);
	}
	
	private ChartData getSampleChartData(boolean addFullLegend) {
		final Collection<Double> sampleData = getSampleDataSet();
		
		final ChartDataExtras extras = new ChartDataExtras();
		if (addFullLegend) {
			extras.setLegendColors("FFFF00", "00FF00", "FF0000", "0000FF", "000000");
			extras.setLegendNames("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
		}
		else {
			extras.setLegendNames("Weekly Sales");
			extras.setLegendColors("000000");
		}
		
		final ChartData chartData = new ChartData();
		chartData.addData(sampleData, extras);
				
		return chartData;
	}

}
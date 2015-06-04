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
import java.util.List;

import com.google.gwt.user.client.ui.Image;
import com.solertium.util.gwt.charts.client.ChartData;
import com.solertium.util.gwt.charts.client.ChartData.ChartDataExtras;

/**
 * BaseExample.java
 * 
 * The baseline for an example chart
 * 
 * @author <a href="mailto:carl.scott@solertium.com">Carl Scott</a>, <a
 *  href="http://www.solertium.com">Solertium Corporation</a>
 */
public abstract class BaseExample {
	
	public abstract Image getChart();
	
	public abstract String getDescription();
	
	public abstract String getSource();
	
	protected List<Double> getSampleDataSet() {
		final List<Double> sampleData = new ArrayList<Double>();
		sampleData.add(Double.valueOf(10));
		sampleData.add(Double.valueOf(20));
		sampleData.add(Double.valueOf(50));
		sampleData.add(Double.valueOf(65));
		sampleData.add(Double.valueOf(100));
		
		return sampleData;
	}
	
	protected ChartData getSampleChartData() {
		return getSampleChartData(true);
	}
	
	protected ChartData getSampleChartData(boolean addFullLegend) {
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

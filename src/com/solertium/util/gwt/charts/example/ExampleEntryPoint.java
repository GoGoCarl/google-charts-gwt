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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

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
	
	private final BaseExample simplePie; 
	private final BaseExample advancedPie;
	private final BaseExample bar;
	private final BaseExample line;
	
	private final SimplePanel content;
	private final HTML description;
	
	private BaseExample current;
	
	public ExampleEntryPoint() {
		content = new SimplePanel();
		
		simplePie = new SimplePieChartExample(); 
		advancedPie = new AdvancedPieChartExample(); 
		bar = new BarChartExample();
		line = new LineChartExample();
		
		current = null;
		
		description = new HTML("Click a button above to render an example chart.");
	}

	public void onModuleLoad() {
		final VerticalPanel container = new VerticalPanel();
		container.add(createChartButtonPanel());
		container.add(createSourceButtonPanel());
		container.add(content);
		container.add(description);
	
		RootPanel.get("loading").setVisible(false);
		RootPanel.get("loading").removeFromParent();
		
		RootPanel.get().add(container);
	}
	
	private HorizontalPanel createChartButtonPanel() {
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(4);
		buttonPanel.add(new Button("Simple Pie Chart", new ClickListener() {
			public void onClick(Widget sender) {
				showChart(simplePie);
			}
		}));
		buttonPanel.add(new Button("3D Pie Chart", new ClickListener() {
			public void onClick(Widget sender) {
				showChart(advancedPie);
			}
		}));
		buttonPanel.add(new Button("Bar Chart", new ClickListener() {
			public void onClick(Widget sender) {
				showChart(bar);
			}
		}));
		buttonPanel.add(new Button("Line Chart", new ClickListener() {
			public void onClick(Widget sender) {
				showChart(line);
			}
		}));
		
		return buttonPanel;
	}
	
	private HorizontalPanel createSourceButtonPanel() {
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(4);
		buttonPanel.add(new Button("Source", new ClickListener() {
			public void onClick(Widget sender) {
				if (current != null)
					showSource(current);
				else
					Window.alert("Please select a chart first.");
			}
		}));		
		
		return buttonPanel;
	}	
	
	private void showChart(BaseExample chart) {
		current = chart;
		content.clear();
		content.setWidget(chart.getChart());
		description.setHTML(chart.getDescription());
	}
	
	private void showSource(BaseExample chart) {
		final TextArea area = new TextArea();
		area.setSize("500px", "500px");
		area.setText(chart.getSource());
		
		content.clear();
		content.setWidget(area);
	}
}
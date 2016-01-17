package com.txsec.task.impl;

import java.util.Calendar;


import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import com.txsec.task.Task;


public class ClockTask extends Task {
	
	 private Calendar calendar;
	private JLabel jlabel;

	public ClockTask(JLabel jlabel) {
		super(0, 1, TimeUnit.SECONDS);
		this.jlabel = jlabel;
		this.calendar = GregorianCalendar.getInstance();
	}



	@Override
	public void execute() {
		calendar.add(Calendar.SECOND, 1);
		jlabel.setText("Current Time: "+calendar.getTime().toLocaleString());
	}

}

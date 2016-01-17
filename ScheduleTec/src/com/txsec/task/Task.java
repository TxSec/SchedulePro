package com.txsec.task;

import java.util.concurrent.TimeUnit;


public abstract class Task {
	
	private int initDelay;
	private int delay;
	private TimeUnit unit;
	
	public Task(int initDelay,int delay,TimeUnit unit){
		this.initDelay = initDelay;
		this.delay = delay;
		this.unit = unit;
		
	}

	public int getInitDelay() {
		return initDelay;
	}

	public void setInitDelay(int initDelay) {
		this.initDelay = initDelay;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	public abstract void execute();
}

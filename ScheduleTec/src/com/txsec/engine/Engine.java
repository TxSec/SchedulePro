package com.txsec.engine;



import java.util.concurrent.BlockingQueue;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.txsec.gui.GraphicUI;
import com.txsec.main.Settings;
import com.txsec.net.Updater;
import com.txsec.task.Task;
import com.txsec.util.FileUtil;


public class Engine implements Runnable{

	private GraphicUI graphics;
	
	/**
	 * An Executor for the logic service.
	 * 
	 * 
	 */
	private final ScheduledExecutorService timeTask = Executors
			.newScheduledThreadPool(1);
	
	private ExecutorService service = Executors.newCachedThreadPool();
	
	
	private final BlockingQueue<Task> tasks = new LinkedBlockingQueue<Task>();
	
	
	private Thread thread;
	private boolean running;
	 
	public void start(){
		new Updater(Settings.WEBSITE);
		FileUtil.createDirectory();
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * Starts the Graphic user interface.
	 */
	public void startGraphics() {
		graphics = new GraphicUI(this);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	 graphics.pack();
                graphics.setVisible(true);
            }
        });

	}

	/**
	 * 
	 * @param runnable
	 * @param startTime The delay at the start time.
	 * @param delayTime The delay of the schedule process.
	 * @param unit The Time unit.
	 * @return
	 */
	public ScheduledFuture<?> scheduleLogic(final Runnable runnable,
			long startTime,long delayTime, TimeUnit unit) {
		
		return timeTask.scheduleAtFixedRate(new Runnable() {
			public void run() {
					runnable.run();
			}
		}, startTime,delayTime, unit);
	}

	@Override
	public void run() {
		while(running){
			try {
			final Task task = tasks.take();
			scheduleLogic(new Runnable(){

				@Override
				public void run() {
					task.execute();
				}
			},task.getInitDelay(),task.getDelay(),task.getUnit());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void pushTask(Task task){
		tasks.offer(task);
	}


}

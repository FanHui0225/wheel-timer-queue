package com.lhj.queue.bootstrap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhj.queue.WheelQueue;

/**
 * @ClassName QueueBootstrap
 * @Description 启动队列（后期将模仿netty或springboot启动结构）。
 * @date 2017-03-21
 * @author hongjian.liu
 * @version 1.0.0
 *
 */
public class QueueBootstrap {
	private static final Logger LOG = LoggerFactory.getLogger(QueueBootstrap.class);
	@SuppressWarnings("all")
	private ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
	
	/** 设置一些配置。暂时没用，保留扩展用 */
	private volatile Map<String, Object> options = new HashMap<String, Object>();

	/**
	 * Returns the options
	 */
	public Map<String, Object> getOptions() {
		return new TreeMap<String, Object>(options);
	}

	/**
	 * 创建一个环形队列；并开启定时扫描队列
	 * 
	 * @return
	 */
	public WheelQueue start() {
		LOG.info("scaner starting...");
		WheelQueue wheelQueue = new WheelQueue();
		// 定义任务
		QueueScanTimer timerTask = new QueueScanTimer(wheelQueue);
		// 设置任务的执行，1秒后开始，每1秒执行一次
		newScheduledThreadPool.scheduleWithFixedDelay(timerTask, 1, 1, TimeUnit.SECONDS);
		LOG.info("scaner start up.");
		
		return wheelQueue;
	}

	/**
	 * 停止此队列运行。
	 */
	public void shutdown() {
		// 只停止扫描队列。已运行的任务暂不停止。
		if (newScheduledThreadPool != null) {
			newScheduledThreadPool.shutdown();
		}
	}

	/**
	 * Sets the options
	 */
	public void setOptions(Map<String, Object> options) {
		if (options == null) {
			throw new NullPointerException("options");
		}
		this.options = new HashMap<String, Object>(options);
	}

	/**
	 * Returns the value of the option with the specified key.
	 *
	 * @param key
	 *            the option name
	 *
	 * @return the option value if the option is found. {@code null} otherwise.
	 */
	public Object getOption(String key) {
		if (key == null) {
			throw new NullPointerException("key");
		}
		return options.get(key);
	}

	/**
	 * Sets an option with the specified key and value. If there's already an
	 * option with the same key, it is replaced with the new value. If the
	 * specified value is {@code null}, an existing option with the specified
	 * key is removed.
	 *
	 * @param key
	 *            the option name
	 * @param value
	 *            the option value
	 */
	public void setOption(String key, Object value) {
		if (key == null) {
			throw new NullPointerException("key");
		}
		if (value == null) {
			options.remove(key);
		} else {
			options.put(key, value);
		}
	}

	/**
	 * 设置一个值
	 * 
	 * @param option
	 * @param value
	 * @return
	 */
	public QueueBootstrap option(String option, String value) {
		if (option == null) {
			throw new NullPointerException("option");
		}
		if (value == null) {
			synchronized (options) {
				options.remove(option);
			}
		} else {
			synchronized (options) {
				options.put(option, value);
			}
		}
		return this;
	}

}

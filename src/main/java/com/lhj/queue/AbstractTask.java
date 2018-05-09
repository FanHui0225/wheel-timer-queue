package com.lhj.queue;

/**
 * @ClassName Task
 * @Description 要运行的任务
 * @date 2017-03-20
 * @author hongjian.liu
 * @version 1.0.0
 *
 */
public abstract class AbstractTask implements Runnable {
	/**任务id. 如果是分布式部署多台应用，那次id要是全局唯一的*/
	private String id;
	/**第几圈*/
	private Integer cycleNum;
	
	/**
	 * 
	 * @param id 任务id
	 */
	public AbstractTask(String id) {
		super();
		this.id = id;
	}

	/**
	 * 	倒计数，为0时即可执行此任务
	 */
    public void countDown() {
        this.cycleNum--;
    }

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getCycleNum() {
		return cycleNum;
	}


	public void setCycleNum(Integer cycleNum) {
		this.cycleNum = cycleNum;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", cycleNum=" + cycleNum + "]";
	}

}

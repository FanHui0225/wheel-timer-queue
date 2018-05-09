package com.lhj.queue;

import java.util.Date;

/**
 * @ClassName TaskAttribute
 * @Description 任务属性
 * @date 2017-03-20
 * @author hongjian.liu
 * @version 1.0.0
 *
 */
public class TaskAttribute {
	
	/** 第几个槽位 */
	private int soltIndex;
	/** 任务应该什么时候执行 */
	private Date executeTime;
	/** 任务加入槽位的时间 */
	private Date joinTime;

	public int getSoltIndex() {
		return soltIndex;
	}

	public void setSoltIndex(int soltIndex) {
		this.soltIndex = soltIndex;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

}

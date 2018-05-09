# 延时任务队列
高性能延时处理的任务队列。单机支持每秒10万延时消息高效触发（可部署多台提供更高并发）如：下单之后如果三十分钟之内没有付款就自动取消订单， 大量未付款数据如何高效的取消订单。
详细介绍请看wiki:　https://gitee.com/itman666/wheel-timer-queue/wikis/Home
![输入图片说明](https://gitee.com/uploads/images/2017/1030/185111_8fc28d77_120349.png "0.png")

使用：

//启动队列
QueueBootstrap queueBootstrap = new QueueBootstrap();
WheelQueue wheelQueue = queueBootstrap.start();

//把任务加入队列
		wheelQueue.add(new AbstractTask("9527") {

			@Override
			public void run() {
				LOG.info("add task. id=" + this.getId());
			}

		}, 5);


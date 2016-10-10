package com.atguigu.crm.orm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyQuartzJobBean extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		// 后台每10分钟打印一次时间
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}

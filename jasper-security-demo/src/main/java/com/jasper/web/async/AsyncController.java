package com.jasper.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 
 * @author jasper
 *
 */
@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@RequestMapping("/order1")
	public String order() throws InterruptedException {
		logger.info("Main Thread start");
		Thread.sleep(1000);
		logger.info("Main Thread end");
		return "success";
	}

	@RequestMapping("/order2")
	public Callable<String> orderAsync() throws InterruptedException {
		logger.info("Main Thread start");

		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info("Callable thread start...");
				Thread.sleep(1000);
				logger.info("Callable thread end...");
				return "success";
			}

		};

		Thread.sleep(1000);
		logger.info("Main Thread end");
		return result;
	}

	@RequestMapping("/order3")
	public DeferredResult<String> orderAsyncQueue() throws Exception {
		logger.info("Main Thread start");

		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);

		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);

		Thread.sleep(1000);
		logger.info("Main Thread end");
		return result;
	}

}

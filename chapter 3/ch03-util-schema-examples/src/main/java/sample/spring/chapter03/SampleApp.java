package sample.spring.chapter03;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter03.beans.BankDetails;
import sample.spring.chapter03.beans.DataTypesExample;

public class SampleApp {
	private static Logger logger = Logger.getLogger(SampleApp.class);

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		BankDetails bankDetails = context.getBean(BankDetails.class);
		logger.info(bankDetails);
		
		context.getBean(DataTypesExample.class);
		logger.info("Created context successfully");
	}
}

package sample.spring.chapter06.bankapp;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter06.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = Logger.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		logger.info("Validating FixedDepositDetails object using Spring Validation API");

		FixedDepositService fixedDepositService = (FixedDepositService) context
				.getBean("fixedDepositService");
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 0,
				12, "someemail@somedomain.com"));
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000,
				12, "someemail@somedomain.com"));

		logger.info("Validating FixedDepositDetails object using JSR 303's Validator");
		FixedDepositService fixedDepositServiceJsr303 = (FixedDepositService) context
				.getBean("fixedDepositServiceJsr303");
		fixedDepositServiceJsr303.createFixedDeposit(new FixedDepositDetails(1,
				0, 12, "someemail@somedomain.com"));
		fixedDepositServiceJsr303.createFixedDeposit(new FixedDepositDetails(1,
				1000, 12, "someemail@somedomain.com"));
	}
}

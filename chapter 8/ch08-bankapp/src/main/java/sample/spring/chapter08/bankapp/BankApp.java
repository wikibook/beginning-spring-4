package sample.spring.chapter08.bankapp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import sample.spring.chapter08.bankapp.domain.BankAccountDetails;
import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter08.bankapp.service.BankAccountService;
import sample.spring.chapter08.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = Logger.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		BankAccountService bankAccountService = context
				.getBean(BankAccountService.class);
		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalanceAmount(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

		int bankAccountId = bankAccountService
				.createBankAccount(bankAccountDetails);

		logger.info("Created bank account with id - " + bankAccountId);

		FixedDepositService fixedDepositService = context
				.getBean(FixedDepositService.class);
		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setActive("N");
		fdd.setBankAccountId(bankAccountId);
		fdd.setFdCreationDate(new Date());
		fdd.setFdAmount(500);
		fdd.setTenure(12);
		// -- set the email id here
		fdd.setEmail("someemail@somedomain.com");
		fixedDepositService.createFixedDeposit(fdd);

		Thread.sleep(5000);
		// -- get the newly created FixedDepositDetails twice from the database
		fixedDepositService.findFixedDepositsByBankAccount(bankAccountId);
		fixedDepositService.findFixedDepositsByBankAccount(bankAccountId);
		fixedDepositService.createFixedDeposit(fdd);

		Thread.sleep(5000);
		List<FixedDepositDetails> fixedDepositDetailsList = fixedDepositService
				.findFixedDepositsByBankAccount(bankAccountId);

		for (FixedDepositDetails detail : fixedDepositDetailsList) {
			fixedDepositService.getFixedDeposit(detail.getFixedDepositId());
		}

		for (FixedDepositDetails detail : fixedDepositDetailsList) {
			fixedDepositService.getFixedDepositFromCache(detail
					.getFixedDepositId());
		}

		Thread.sleep(5000);

		Map<String, DefaultMessageListenerContainer> containers = context
				.getBeansOfType(DefaultMessageListenerContainer.class);
		Set<String> keySet = containers.keySet();
		Iterator<String> iterator = keySet.iterator();

		while (iterator.hasNext()) {
			DefaultMessageListenerContainer container = containers.get(iterator
					.next());
			System.out.println("Container - " + container);
			container.destroy();
		}

		ThreadPoolTaskScheduler poolTaskScheduler = (ThreadPoolTaskScheduler) context
				.getBean("emailScheduler");
		poolTaskScheduler.destroy();
		BrokerService brokerService = context.getBean(BrokerService.class);
		brokerService.stop();
	}
}
package sample.spring.chapter07.bankapp;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.spring.chapter07.bankapp.domain.BankAccountDetails;
import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter07.bankapp.service.BankAccountService;
import sample.spring.chapter07.bankapp.service.FixedDepositService;

public class BankApp {
	private static Logger logger = Logger.getLogger(BankApp.class);

	public static void main(String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		BankAccountService bankAccountService = context
				.getBean(BankAccountService.class);
		FixedDepositService fixedDepositService = context
				.getBean(FixedDepositService.class);

		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBalanceAmount(1000);
		bankAccountDetails.setLastTransactionTimestamp(new Date());

		int bankAccountId = bankAccountService
				.createBankAccount(bankAccountDetails);

		logger.info("Created bank account (with balance amount 1000) with id - "
				+ bankAccountId);

		FixedDepositDetails fdd = new FixedDepositDetails();

		fdd.setActive("Y");
		fdd.setFdAmount(1500);
		fdd.setBankAccountId(bankAccountId);
		fdd.setFdCreationDate(new Date());
		fdd.setTenure(12);

		int fixedDepositId = fixedDepositService.createFixedDeposit(fdd);
		logger.info("Created fixed deposit (for 1500 amount) with id - "
				+ fixedDepositId
				+ " Check FIXED_DEPOSIT_DETAILS table to verify the transaction was committed or rolledback.");
	}
}

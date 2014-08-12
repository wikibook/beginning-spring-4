package sample.spring.chapter04.bankapp.dao;

import java.util.Date;

import org.apache.log4j.Logger;

import sample.spring.chapter04.bankapp.domain.AccountStatement;

public class AccountStatementDaoImpl implements AccountStatementDao {
	private static Logger logger = Logger.getLogger(AccountStatementDaoImpl.class);
	
	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		logger.info("Getting account statement");
		return new AccountStatement();
	}

}

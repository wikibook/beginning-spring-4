package sample.spring.chapter06.bankapp.dao;

import java.util.Date;

import javax.inject.Named;

import org.apache.log4j.Logger;

import sample.spring.chapter06.bankapp.domain.AccountStatement;

@Named(value="accountStatementDao")
public class AccountStatementDaoImpl implements AccountStatementDao {
	private static Logger logger = Logger.getLogger(AccountStatementDaoImpl.class);
	
	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		logger.info("Getting account statement");
		return new AccountStatement();
	}
}

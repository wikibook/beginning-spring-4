package sample.spring.chapter06.bankapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import sample.spring.chapter06.bankapp.dao.AccountStatementDao;
import sample.spring.chapter06.bankapp.domain.AccountStatement;

public class AccountStatementServiceImpl implements AccountStatementService {
	@Autowired
	private AccountStatementDao accountStatementDao;

	@Override
	public AccountStatement getAccountStatement(Date from, Date to) {
		return accountStatementDao.getAccountStatement(from, to);
	}
}

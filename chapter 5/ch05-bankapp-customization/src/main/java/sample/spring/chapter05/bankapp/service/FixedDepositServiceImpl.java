package sample.spring.chapter05.bankapp.service;

import org.apache.log4j.Logger;

import sample.spring.chapter05.bankapp.dao.FixedDepositDao;
import sample.spring.chapter05.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = Logger.getLogger(FixedDepositServiceImpl.class);
	private FixedDepositDao myFixedDepositDao;

	public void setMyFixedDepositDao(FixedDepositDao myFixedDepositDao) {
		logger.info("FixedDepositServiceImpl's setMyFixedDepositDao method invoked");
		this.myFixedDepositDao = myFixedDepositDao;
	}

	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		myFixedDepositDao.createFixedDeposit(fdd);
	}
}

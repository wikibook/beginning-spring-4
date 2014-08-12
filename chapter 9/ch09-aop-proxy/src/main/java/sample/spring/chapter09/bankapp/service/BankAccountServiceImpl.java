package sample.spring.chapter09.bankapp.service;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.spring.chapter09.bankapp.dao.BankAccountDao;
import sample.spring.chapter09.bankapp.domain.BankAccountDetails;
import sample.spring.chapter09.bankapp.exception.BankAccountAlreadyExistsException;

@Service(value = "bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {
	private static Logger logger = Logger
			.getLogger(BankAccountServiceImpl.class);
	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		logger.info("createBankAccount method invoked");
		//-- obtain the proxy and invoke the isDuplicateAccount method via proxy
		boolean isDuplicateAccount = ((BankAccountService)AopContext.currentProxy()).isDuplicateAccount(bankAccountDetails);
		if(!isDuplicateAccount) {
			return bankAccountDao.createBankAccount(bankAccountDetails);
		} else {
			throw new BankAccountAlreadyExistsException("Bank account already exists"); 
		}
	}
	
	public boolean isDuplicateAccount(BankAccountDetails bankAccountDetails) {
		//--check if the account already exists
		return false;
	}
}

package sample.spring.chapter07.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import sample.spring.chapter07.bankapp.dao.BankAccountDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public int createFixedDeposit(final FixedDepositDetails fdd)
			throws Exception {
		// -- create fixed deposit
		transactionTemplate
				.execute(new TransactionCallback<FixedDepositDetails>() {

					@Override
					public FixedDepositDetails doInTransaction(
							TransactionStatus status) {
						try {
							myFixedDepositDao.createFixedDeposit(fdd);
							bankAccountDao.subtractFromAccount(
									fdd.getBankAccountId(), fdd.getFdAmount());
						} catch (Exception e) {
							status.setRollbackOnly();
						}
						return fdd;
					}
				});
		return fdd.getFixedDepositId();
	}

	@Override
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return myFixedDepositDao.getFixedDeposit(fixedDepositId);
	}
}

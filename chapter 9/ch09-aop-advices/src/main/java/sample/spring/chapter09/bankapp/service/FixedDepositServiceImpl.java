package sample.spring.chapter09.bankapp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.spring.chapter09.bankapp.dao.FixedDepositDao;
import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = Logger
			.getLogger(FixedDepositServiceImpl.class);

	@Autowired
	private FixedDepositDao fixedDepositDao;

	public void createFixedDeposit(final FixedDepositDetails fdd)
			throws Exception {
		logger.info("createFixedDeposit method invoked");
		fixedDepositDao.createFixedDeposit(new FixedDepositDetails(1, 100, 12, "somedomain@someemail.com"));
	}

	@Override
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		logger.info("getFixedDeposit method invoked with fixedDepositId "
				+ fixedDepositId);
		return fixedDepositDao.getFixedDeposit(fixedDepositId);
	}
}

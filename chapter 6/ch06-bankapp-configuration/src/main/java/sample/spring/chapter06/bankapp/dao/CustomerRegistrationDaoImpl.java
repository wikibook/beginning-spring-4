package sample.spring.chapter06.bankapp.dao;

import org.apache.log4j.Logger;

import sample.spring.chapter06.bankapp.domain.CustomerRegistrationDetails;

public class CustomerRegistrationDaoImpl implements CustomerRegistrationDao {
	private static Logger logger = Logger
			.getLogger(CustomerRegistrationDaoImpl.class);

	public CustomerRegistrationDaoImpl() {
		logger.info("Created CustomerRegistrationDaoImpl instance");
	}

	@Override
	public void registerCustomer(
			CustomerRegistrationDetails customerRegistrationDetails) {
	}

}

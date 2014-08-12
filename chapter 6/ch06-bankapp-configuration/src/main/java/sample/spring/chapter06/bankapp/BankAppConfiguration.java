package sample.spring.chapter06.bankapp;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import sample.spring.chapter06.bankapp.beanpostprocessor.BeanNamePrinterBeanFactoryPostProcessor;
import sample.spring.chapter06.bankapp.beanpostprocessor.ExampleBeanPostProcessor;
import sample.spring.chapter06.bankapp.dao.AccountStatementDao;
import sample.spring.chapter06.bankapp.dao.AccountStatementDaoImpl;
import sample.spring.chapter06.bankapp.dao.CustomerRegistrationDao;
import sample.spring.chapter06.bankapp.dao.CustomerRegistrationDaoImpl;
import sample.spring.chapter06.bankapp.dao.FixedDepositDao;
import sample.spring.chapter06.bankapp.dao.FixedDepositDaoImpl;
import sample.spring.chapter06.bankapp.domain.CustomerRegistrationDetails;
import sample.spring.chapter06.bankapp.service.AccountStatementService;
import sample.spring.chapter06.bankapp.service.AccountStatementServiceImpl;
import sample.spring.chapter06.bankapp.service.CustomerRegistrationService;
import sample.spring.chapter06.bankapp.service.CustomerRegistrationServiceImpl;
import sample.spring.chapter06.bankapp.service.FixedDepositService;
import sample.spring.chapter06.bankapp.service.FixedDepositServiceImpl;

@Configuration
public class BankAppConfiguration {

	@Bean(name = "accountStatementService")
	public AccountStatementService accountStatementService() {
		return new AccountStatementServiceImpl();
	}

	@Bean(name = "accountStatementDao")
	public AccountStatementDao accountStatementDao() {
		return new AccountStatementDaoImpl();
	}

	@Bean(name = "customerRegistrationService")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationService customerRegistrationService() {
		return new CustomerRegistrationServiceImpl();
	}

	@Bean(name = "customerRegistrationDao")
	public CustomerRegistrationDao customerRegistrationDao() {
		return new CustomerRegistrationDaoImpl();
	}

	@Bean(name = "customerRegistrationDetails")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CustomerRegistrationDetails customerRegistrationDetails() {
		return new CustomerRegistrationDetails();
	}

	@Bean(name = "fixedDepositService")
	public FixedDepositService fixedDepositService() {
		return new FixedDepositServiceImpl();
	}

	@Bean(name = "myFixedDepositDao")
	public FixedDepositDao fixedDepositDao() {
		return new FixedDepositDaoImpl();
	}
	
	@Bean
	public ExampleBeanPostProcessor exampleBeanPostProcessor() {
		return new ExampleBeanPostProcessor();
	}
	
	@Bean
	public static BeanNamePrinterBeanFactoryPostProcessor applicationConfigurer() {
		return new BeanNamePrinterBeanFactoryPostProcessor();
	}
}
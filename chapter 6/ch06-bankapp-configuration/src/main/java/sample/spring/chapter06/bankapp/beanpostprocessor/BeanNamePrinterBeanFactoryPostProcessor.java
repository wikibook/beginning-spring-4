package sample.spring.chapter06.bankapp.beanpostprocessor;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanNamePrinterBeanFactoryPostProcessor implements
		BeanFactoryPostProcessor {
	private static Logger logger = Logger
			.getLogger(BeanNamePrinterBeanFactoryPostProcessor.class);

	public BeanNamePrinterBeanFactoryPostProcessor() {
		logger.info("Created ApplicationConfigurer instance");
	}

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		// -- get all the bean definitions
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			String beanName = beanDefinitionNames[i];
			logger.info("Found bean named: " + beanName);
		}
	}
}

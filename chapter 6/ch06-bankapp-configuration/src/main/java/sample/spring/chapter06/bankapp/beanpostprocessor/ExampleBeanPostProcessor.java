package sample.spring.chapter06.bankapp.beanpostprocessor;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ExampleBeanPostProcessor implements BeanPostProcessor {
	private static Logger logger = Logger
			.getLogger(ExampleBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		logger.info("ExampleBeanPostProcessor's postProcessBeforeInitialization method invoked for bean "
				+ beanName + " of type " + bean.getClass());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		logger.info("ExampleBeanPostProcessor's postProcessAfterInitialization method invoked for bean "
				+ beanName + " of type " + bean.getClass());
		return bean;
	}
}

package sample.spring.chapter12;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import sample.spring.chapter12.domain.FixedDepositDetails;

public class FixedDepositWSAsyncClient {
	private static Logger logger = Logger
			.getLogger(FixedDepositWSAsyncClient.class);
	private static ApplicationContext context;
 
	public static void main(String args[]) {
		context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");
		try {
			getFixedDepositList(context.getBean(AsyncRestTemplate.class));
			openFixedDeposit(context.getBean(AsyncRestTemplate.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getFixedDepositList(AsyncRestTemplate restTemplate)
			throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

		ParameterizedTypeReference<List<FixedDepositDetails>> typeRef = new ParameterizedTypeReference<List<FixedDepositDetails>>() {
		};

		ListenableFuture<ResponseEntity<List<FixedDepositDetails>>> futureResponseEntity = restTemplate
				.exchange(
						"http://localhost:8080/ch12-webservice-uritemplates/fixedDeposits",
						HttpMethod.GET, requestEntity, typeRef);
		futureResponseEntity
				.addCallback(new ListenableFutureCallback<ResponseEntity<List<FixedDepositDetails>>>() {
					@Override
					public void onSuccess(
							ResponseEntity<List<FixedDepositDetails>> entity) {
						List<FixedDepositDetails> fixedDepositDetails = entity
								.getBody();
						logger.info("List of fixed deposit details: \n"
								+ fixedDepositDetails);
					}

					@Override
					public void onFailure(Throwable t) {
					}
				});
	}

	private static void openFixedDeposit(AsyncRestTemplate restTemplate) {
		FixedDepositDetails fdd = new FixedDepositDetails();
		fdd.setDepositAmount("9999");
		fdd.setEmail("99@somedomain.com");
		fdd.setTenure("12");

		HttpEntity<FixedDepositDetails> requestEntity = new HttpEntity<FixedDepositDetails>(
				fdd);

		ListenableFuture<ResponseEntity<FixedDepositDetails>> futureResponseEntity = restTemplate
				.postForEntity(
						"http://localhost:8080/ch12-webservice-uritemplates/fixedDeposits",
						requestEntity, FixedDepositDetails.class);

		futureResponseEntity
				.addCallback(new ListenableFutureCallback<ResponseEntity<FixedDepositDetails>>() {
					@Override
					public void onSuccess(
							ResponseEntity<FixedDepositDetails> entity) {
						FixedDepositDetails fixedDepositDetails = entity
								.getBody();
						logger.info("Details of the newly created fixed deposit: "
								+ fixedDepositDetails);
					}

					@Override
					public void onFailure(Throwable t) {
					}
				});
	}
}
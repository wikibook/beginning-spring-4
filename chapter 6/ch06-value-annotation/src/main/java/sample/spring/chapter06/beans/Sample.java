package sample.spring.chapter06.beans;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="sample")
public class Sample {
	@Value("#{configuration.environment}")
	private String environment;

	@Value("Some currency")
	private String currency;

	@Value("#{configuration.getCountry()}")
	private String country;

	@Value("#{configuration.state}")
	private String state;

	private String[] splitName;
	private String city;

	@Autowired
	public void splitName(
			@Value("#{configuration.splitName('FirstName LastName')}") String[] splitName) {
		this.splitName = splitName;
	}

	@Autowired
	@Value("#{configuration.getCity()}")
	public void city(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Sample [environment=" + environment + ", currency=" + currency
				+ ", country=" + country + ", state=" + state + ", splitName="
				+ Arrays.toString(splitName) + ", city=" + city + "]";
	}
}
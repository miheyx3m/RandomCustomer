package betcio.com.randomcustomers.utils;

import betcio.com.randomcustomers.dto.CustomerDto;
import betcio.com.randomcustomers.enumerations.CustomerLastName;
import betcio.com.randomcustomers.enumerations.CustomerName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class Randoms {

	public static CustomerName[] CUSTOMER_NAMES = CustomerName.values();
	public static CustomerLastName[] CUSTOMER_LAST_NAMES = CustomerLastName.values();

	private static int MIN_SEC_CREATE_CUSTOMER;
	private static int MAX_SEC_CREATE_CUSTOMER;

	@Value("${minseccreatecustomer}")
	public void setMinSecCreateCustomer(int minseccreatecustomer) {
		Randoms.MIN_SEC_CREATE_CUSTOMER = minseccreatecustomer;
	}
	@Value("${maxseccreatecustomer}")
	public void setMaxSecCreateCustomer(int mxaseccreatecustomer) {
		Randoms.MAX_SEC_CREATE_CUSTOMER = mxaseccreatecustomer;
	}

	public static Random gen = new Random();

	public static <T>T randomOfArray(T[] arr) {
		int arrLength = arr.length;
		return arrLength == 0 ? null : arr[gen.nextInt(arrLength)];
	}

	public static CustomerDto getRandomCustomer() {
		String first_name = randomOfArray(CUSTOMER_NAMES).toString();
		String last_name = randomOfArray(CUSTOMER_LAST_NAMES).toString();
		LocalDateTime created_time = LocalDateTime.now();
		return new CustomerDto(first_name, last_name, created_time);
	}

	public static int getRandomSec(){
		return MIN_SEC_CREATE_CUSTOMER + gen.nextInt(MAX_SEC_CREATE_CUSTOMER + 1 - MIN_SEC_CREATE_CUSTOMER);
	}
}

package betcio.com.randomcustomers.service;

import betcio.com.randomcustomers.dto.CustomerDto;
import betcio.com.randomcustomers.entity.Customer;
import betcio.com.randomcustomers.repository.CustomerMySQLRepository;
import betcio.com.randomcustomers.utils.Randoms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWriteToDB extends Thread {

    int count_customers = 1;
    int sec;
    int milli_sec;

    private final CustomerMySQLRepository customerRepository;

    @Autowired
    public ServiceWriteToDB(CustomerMySQLRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run() {
        while (true) {
            sec = Randoms.getRandomSec();
            milli_sec = sec * 1000;

            try {
                sleep(milli_sec);
                CustomerDto customerDto = Randoms.getRandomCustomer();
                writerToDB(customerDto);
                System.out.println(customerDto);
                System.out.println("time wait - " + sec + "sec ,send number- " + count_customers + " customer");
                count_customers++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                if(Thread.currentThread().isInterrupted()) {
                    Thread.currentThread().interrupt(); // restore interuppted status
                }
            }
        }
    }

    public boolean writerToDB(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto);
        customerRepository.save(customer);
        return true;
    }

}

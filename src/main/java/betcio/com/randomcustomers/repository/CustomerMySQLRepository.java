package betcio.com.randomcustomers.repository;

import betcio.com.randomcustomers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMySQLRepository extends JpaRepository<Customer,Integer> {

}

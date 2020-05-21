package betcio.com.randomcustomers.entity;


import betcio.com.randomcustomers.dto.CustomerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "created")
    private LocalDateTime created;

    public Customer(String first_name, String last_name, LocalDateTime created) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.created = created;
    }

    public Customer(CustomerDto customerDto){
        this.first_name=customerDto.getFirst_name();
        this.last_name=customerDto.getLast_name();
        this.created=customerDto.getCreated();
    }


}

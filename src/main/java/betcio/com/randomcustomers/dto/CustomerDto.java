package betcio.com.randomcustomers.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class CustomerDto {
    private String first_name;
    private String last_name;
    private LocalDateTime created;
}

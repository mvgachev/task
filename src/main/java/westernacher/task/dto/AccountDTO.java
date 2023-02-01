package westernacher.task.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

    @NonNull
    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private Date dateOfBirth;
}

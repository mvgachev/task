package westernacher.task.model;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import westernacher.task.dto.AccountDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sorting {
    @NonNull
    private SortingType sortingType;

    public enum SortingType {
        FIRSTNAME,
        LASTNAME,
        EMAILADDRESS,
        DATEOFBIRTH
    }

    public Comparator<AccountDTO> getSortingComparator() {
        switch (sortingType) {
        case LASTNAME:
            return Comparator.comparing(AccountDTO::getLastName);
        case DATEOFBIRTH:
            return Comparator.comparing(AccountDTO::getDateOfBirth);
        case EMAILADDRESS:
            return Comparator.comparing(AccountDTO::getEmailAddress);
        default:
            return Comparator.comparing(AccountDTO::getFirstName);
        }
    }
}

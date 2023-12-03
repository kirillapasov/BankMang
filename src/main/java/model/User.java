package model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {
    private String firstName;
    private String secondName;
    private long phoneNumber;
    private long passportData;
    @NonNull
    private String email;


}

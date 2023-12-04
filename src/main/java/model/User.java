package model;
//Todo Добавить id в класс user
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
    private long id;
    private String email;



}

package myapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Component
  public class Account implements Validator, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;
    @Min(18)
    private int age;
    private String phoneNumber;
    private String email;
    @Column(unique = true)
    @NotEmpty
    @Size(min = 8,max = 20)
    private String username;
    @NotEmpty
    @Size(min = 8,max = 25)
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account= (Account) target;
        String phoneNumber= account.getPhoneNumber();
        String email= account.getEmail();
        ValidationUtils.rejectIfEmpty(errors,"phoneNumber","phoneNumber.empty");
        ValidationUtils.rejectIfEmpty(errors,"email","email.empty");
        if(phoneNumber.length()>11||phoneNumber.length()<10){
            errors.rejectValue("phoneNumber","phoneNumber.length");
        }
        if(!phoneNumber.startsWith("0")){
            errors.rejectValue("phoneNumber","phoneNumber.startWith");
        }
        if(!phoneNumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber","phoneNumber.matches");
        }
        if (!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")){
            errors.rejectValue("email","email.matches");
        }

    }



    @Override
    public String getAuthority() {
        return this.role;
    }
}
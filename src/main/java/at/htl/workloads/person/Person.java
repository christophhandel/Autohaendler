package at.htl.workloads.person;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="person_type", discriminatorType = DiscriminatorType.STRING)
public class Person {

    //region fields
    @Id
    private String svNr;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String driverLicenceNumber;
    //endregion

    //region Constructor
    public Person(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber) {
        this.svNr = svNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.driverLicenceNumber = driverLicenceNumber;
    }

    public Person() {
    }
    //endregion

    //region Getter and Setter
    public String getSvNr() {
        return svNr;
    }

    public void setSvNr(String svNr) {
        this.svNr = svNr;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverLicenceNumber() {
        return driverLicenceNumber;
    }

    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }
    //endregion

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(svNr, person.svNr) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(driverLicenceNumber, person.driverLicenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
    }
    //endregion
}

package at.htl.models.results;

import at.htl.workloads.person.Person;
import java.math.BigDecimal;

public class IncomePerPerson {

    BigDecimal income;
    Person person;

    //region Constructor
    public IncomePerPerson(BigDecimal income, Person person) {
        this.income = income;
        this.person = person;
    }

    public IncomePerPerson(double income, Person person) {
        this.income = BigDecimal.valueOf(income);
        this.person = person;
    }

    public IncomePerPerson() {
    }
    //endregion

    //region Getter and Setter
    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    //endregion
}
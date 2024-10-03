package me.cj;

import java.time.LocalDate;

public class Person {

    private Name name;
    private LocalDate birthday;
    private long ssn;

    public Person(Name name, LocalDate birthday, long ssn) {
        this.name = name;
        this.birthday = birthday;
        this.ssn = ssn;
    }

    public Person(Name name) {
        this(name, LocalDate.of(2023, 1, 1), 123456789);
    }

    public Name getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    protected long getSsn() {
        return ssn;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nBirthday: " + birthday + "\n";
    }

}

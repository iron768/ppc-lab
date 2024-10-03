package me.cj;

public class Medical extends Person {

    private double salary;

    public Medical(Name name, double salary) {
        super(name);

        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

}

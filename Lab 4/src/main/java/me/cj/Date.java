package me.cj;

public class Date {

    private String month;
    private int day, year;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Date(String month, int day, int year) {
        setMonth(month);
        setDay(day);
        setYear(year);
    }

    public Date(String month, int year) {
        this(month, 0, year);
    }

    public void setMonth(String month) {
        for (String s : months) {
            if (month.equals(s)) {
                this.month = month;
                return;
            }
        }

        System.out.println("Invalid month");
        System.exit(1);
    }

    public void setDay(int day) {
        if (day >= 1 && day <= 31)
            this.day = day;
        else
            this.day = 1;
    }

    public void setYear(int year) {
        if (year >= 1800 && year <= 2030)
            this.year = year;
        else
            this.year = 1800;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return convert() + "/" + day + "/" + year;
    }

    public int convert() {
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i]))
                return i + 1;
        }

        return 0;
    }

    public boolean equals(Date date) {
        return month.equals(date.getMonth()) && day == date.getDay() && year == date.getYear();
    }

}
package Java.classes_and_object;

public class Weekday {
    private Day day;

    public Weekday(Day day) {
        this.day = day;
    }

    public Day getDay() {
        return day;
    }

    public String isBusinessDay() {
        if (day != Day.SATURDAY && day != Day.SUNDAY) {
            return "It is a business day";
        } else {
            return "It is not a business day";
        }
    }
}

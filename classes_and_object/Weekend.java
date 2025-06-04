package Java.classes_and_object;

public class Weekend extends Weekday {
    public Weekend(Day day) {
        super(day);
    }

    @Override
    public String isBusinessDay() {
        return "It is not a business day";
    }

    public String isRelaxDay() {
        return "It is time to relax";
    }
}

package Java.classes_and_object;

public class DisplayDays {
    public static void main(String[] args) {
        for (Day day : Day.values()) {
            Weekday weekday;
            if (day == Day.SATURDAY || day == Day.SUNDAY) {
                weekday = new Weekend(day);
                System.out.println(day + ": " + weekday.isBusinessDay() + ", " + ((Weekend) weekday).isRelaxDay());
            } else {
                weekday = new Weekday(day);
                System.out.println(day + ": " + weekday.isBusinessDay());
            }
        }
    }
}

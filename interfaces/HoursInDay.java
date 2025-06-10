package Java.interfaces;

public class HoursInDay implements TimeCalculator {
    @Override
    public int getHours() {
        return 0;
    }

    @Override
    public int calculateHours() {
        return 24;
    }

    public static void main(String[] args) {
        HoursInDay hoursInDay = new HoursInDay();
        int hours = hoursInDay.calculateHours();
        System.out.println("Hours in a day: " + hours);
    }
}

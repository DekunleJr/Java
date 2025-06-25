public class NumberToString {

    public static String convertToString(int number) {
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        int num = 12345;
        String str = convertToString(num);
        System.out.println("Number: " + num);
        System.out.println("String: " + str);
    }
}

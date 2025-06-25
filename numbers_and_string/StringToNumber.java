public class StringToNumber {

    public static int convertToInt(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        String str = "12345";
        int num = convertToInt(str);
        System.out.println("String: " + str);
        System.out.println("Number: " + num);
    }
}

package Java.statements;

class BreakDemo {
    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 5 };
        for (int number : numbers) {
            if (number == 3) {
                System.out.println("Breaking at number: " + number);
                break; // Exit the loop when number is 3
            }
            System.out.println("Number: " + number);
        }
        System.out.println("Loop exited.");
    }
}

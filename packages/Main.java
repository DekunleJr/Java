package Java.packages;

public class Main {
    public static void main(String[] args) {
        // Using Class1
        Class1 class1 = new Class1("Hello", 10);
        System.out.println("Class1 field1: " + class1.getField1());
        System.out.println("Class1 field2: " + class1.getField2());

        // Using Class2
        Class2 class2 = new Class2(3.14, true);
        System.out.println("Class2 field1: " + class2.getField1());
        System.out.println("Class2 field2: " + class2.isField2());

        // Using Class3
        Class3 class3 = new Class3("World", "Java");
        System.out.println("Class3 field1: " + class3.getField1());
        System.out.println("Class3 field2: " + class3.getField2());

        // Using Class4
        Class4 class4 = new Class4(5, 2.71);
        System.out.println("Class4 field1: " + class4.getField1());
        System.out.println("Class4 field2: " + class4.getField2());

        // Using Class5
        Class5 class5 = new Class5(false, "Coding");
        System.out.println("Class5 field1: " + class5.isField1());
        System.out.println("Class5 field2: " + class5.getField2());

        // Using AbstractClass1
        AbstractClass1 abstractClass1 = new AbstractClass1("Abstract", 20) {
            @Override
            public void abstractMethod() {
                System.out.println("AbstractClass1 abstractMethod called");
            }
        };
        System.out.println("AbstractClass1 field1: " + abstractClass1.getField1());
        System.out.println("AbstractClass1 field2: " + abstractClass1.getField2());
        abstractClass1.abstractMethod();

        // Using AbstractClass2
        AbstractClass2 abstractClass2 = new AbstractClass2(1.618, true) {
            @Override
            public String abstractMethod(int param) {
                return "AbstractClass2 abstractMethod called with param: " + param;
            }
        };
        System.out.println("AbstractClass2 field1: " + abstractClass2.getField1());
        System.out.println("AbstractClass2 field2: " + abstractClass2.isField2());
        System.out.println(abstractClass2.abstractMethod(100));

        // Using AbstractClass3
        AbstractClass3 abstractClass3 = new AbstractClass3("Abstract3", "Base") {
            @Override
            public int abstractMethod(String param1, boolean param2) {
                return param1.length() + (param2 ? 1 : 0);
            }
        };
        System.out.println("AbstractClass3 field1: " + abstractClass3.getField1());
        System.out.println("AbstractClass3 field2: " + abstractClass3.getField2());
        System.out.println("AbstractClass3 abstractMethod: " + abstractClass3.abstractMethod("Test", true));

        // Using Interface1
        Interface1 interface1 = new Interface1() {
            @Override
            public void method1() {
                System.out.println("Interface1 method1 called");
            }

            @Override
            public int method2(String param) {
                System.out.println("Interface1 method2 called with param: " + param);
                return param.length();
            }

            @Override
            public String method3(int param1, boolean param2) {
                System.out.println("Interface1 method3 called with param1: " + param1 + ", param2: " + param2);
                return "Result: " + (param1 * (param2 ? 1 : -1));
            }
        };
        interface1.method1();
        System.out.println("Interface1 method2 result: " + interface1.method2("Example"));
        System.out.println("Interface1 method3 result: " + interface1.method3(5, true));

         // Using Interface2
        Interface2 interface2 = new Interface2() {
            @Override
            public String method1(int param) {
                return "Interface2 method1 called with param: " + param;
            }

            @Override
            public boolean method2(String param1, String param2) {
                return param1.equals(param2);
            }

            @Override
            public void method3(double param) {
                System.out.println("Interface2 method3 called with param: " + param);
            }
        };
        System.out.println(interface2.method1(42));
        System.out.println(interface2.method2("hello", "hello"));
        interface2.method3(2.71);

        // Using Interface3
        Interface3 interface3 = new Interface3() {
            @Override
            public int method1() {
                return 100;
            }

            @Override
            public void method2(int param1, int param2) {
                System.out.println("Interface3 method2 called with param1: " + param1 + ", param2: " + param2);
            }

            @Override
            public double method3() {
                return 3.14;
            }
        };
        System.out.println("Interface3 method1: " + interface3.method1());
        interface3.method2(10, 20);
        System.out.println("Interface3 method3: " + interface3.method3());

        // Using Interface4
        Interface4 interface4 = new Interface4() {
            @Override
            public boolean method1(boolean param) {
                return !param;
            }

            @Override
            public String method2() {
                return "Interface4 method2 called";
            }

            @Override
            public void method3(String param) {
                System.out.println("Interface4 method3 called with param: " + param);
            }
        };
        System.out.println("Interface4 method1: " + interface4.method1(true));
        System.out.println(interface4.method2());
        interface4.method3("Test");

        // Using Interface5
        Interface5 interface5 = new Interface5() {
            @Override
            public double method1(int param1, double param2) {
                return param1 * param2;
            }

            @Override
            public int method2(int param) {
                return param * 2;
            }

            @Override
            public boolean method3(String param1, boolean param2, int param3) {
                return param1.length() > param3 == param2;
            }
        };
        System.out.println("Interface5 method1: " + interface5.method1(5, 2.5));
        System.out.println("Interface5 method2: " + interface5.method2(10));
        System.out.println("Interface5 method3: " + interface5.method3("Hello", true, 3));
    }
}

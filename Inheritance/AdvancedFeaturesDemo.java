package Java.Inheritance;

public class AdvancedFeaturesDemo {

    // Access Modifiers Demo
    public String publicVar = "This is a public variable";
    private String privateVar = "This is a private variable";
    protected String protectedVar = "This is a protected variable";
    String defaultVar = "This is a default variable"; // Package-private

    public String getPrivateVar() {
        return privateVar;
    }

    // Nested Class Demo
    public class NestedClass {
        private String nestedClassVar = "This is a nested class variable";

        public String getNestedClassVar() {
            return nestedClassVar;
        }

        public String accessOuterClassVars() {
            return publicVar + " " + privateVar + " " + protectedVar + " " + defaultVar;
        }
    }

    // Anonymous Class Demo
    public interface Greeting {
        void greet();
    }

    public void anonymousClassDemo() {
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("Hello from anonymous class!");
            }
        };
        greeting.greet();
    }

    public static void main(String[] args) {
        AdvancedFeaturesDemo demo = new AdvancedFeaturesDemo();

        // Accessing variables
        System.out.println("Public variable: " + demo.publicVar);
        System.out.println("Private variable: " + demo.getPrivateVar());
        System.out.println("Protected variable: " + demo.protectedVar);
        System.out.println("Default variable: " + demo.defaultVar);

        // Nested class demo
        NestedClass nestedClass = demo.new NestedClass();
        System.out.println("Nested class variable: " + nestedClass.getNestedClassVar());
        System.out.println("Accessing outer class variables from nested class: " + nestedClass.accessOuterClassVars());

        // Anonymous class demo
        demo.anonymousClassDemo();
    }
}

package Java.packages;

public abstract class AbstractClass2 {
    private double field1;
    private boolean field2;

    public AbstractClass2(double field1, boolean field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public double getField1() {
        return field1;
    }

    public void setField1(double field1) {
        this.field1 = field1;
    }

    public boolean isField2() {
        return field2;
    }

    public void setField2(boolean field2) {
        this.field2 = field2;
    }

    public abstract String abstractMethod(int param);
}

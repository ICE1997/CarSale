package util;

public class Converter {
    private Converter() {}
    public static int BooleanToInt(Boolean b) {
        return b ? 1 : 0;
    }

    public static Boolean IntToBoolean (int b) {
        return b == 1;
    }
}





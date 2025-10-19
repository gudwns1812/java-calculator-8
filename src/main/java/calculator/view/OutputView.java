package calculator.view;

public abstract class OutputView {

    private static final String OUTPUT_MESSAGE = "결과 : ";

    public static void printResult(long result) {
        System.out.println(OUTPUT_MESSAGE + result);
    }
}

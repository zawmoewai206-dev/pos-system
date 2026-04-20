package io.github.zawmoewai206.util;

/**
 * 注文ID生成
 */
public class IdGenerator {

    private static int counter = 1;

    public static String generateOrderId() {
        return "O" + String.format("%03d", counter++);
    }
}

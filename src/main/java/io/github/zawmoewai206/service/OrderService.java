package io.github.zawmoewai206.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 注文サービス
 */
public class OrderService {

    private static final String FILE_PATH = "data/orders.csv";

    /**
     * 注文保存
     */
    public void saveOrder(String orderId, String productName,
                          int qty, double subtotal) {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) {

            String time = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // 注文ID,商品名,数量,小計,日時
            bw.write(orderId + "," + productName + "," + qty + "," + subtotal + "," + time);
            bw.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

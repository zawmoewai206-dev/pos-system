package io.github.zawmoewai206.util;

import io.github.zawmoewai206.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVファイル操作ユーティリティ
 */
public class CsvUtil {

    // 商品データをCSVから読み込む
    public static List<Product> loadProducts(String path) {
        List<Product> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0];
                String name = data[1];
                double price = Double.parseDouble(data[2]);

                list.add(new Product(id, name, price));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

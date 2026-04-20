package io.github.zawmoewai206.service;

import io.github.zawmoewai206.model.Product;
import io.github.zawmoewai206.util.CsvUtil;

import java.util.List;

/**
 * 商品サービス（ビジネスロジック）
 */
public class ProductService {

    private List<Product> products;

    public ProductService() {
        // CSVから読み込み
        products = CsvUtil.loadProducts("data/products.csv");
    }

    /**
     * IDで商品を検索
     */
    public Product getById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     * 全商品取得（将来使う）
     */
    public List<Product> getAll() {
        return products;
    }
}

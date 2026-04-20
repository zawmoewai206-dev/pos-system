package io.github.zawmoewai206.view;

import io.github.zawmoewai206.model.Product;
import io.github.zawmoewai206.util.CsvUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * 商品一覧パネル
 */
public class ProductPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JButton addButton;

    public ProductPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 0));

        // テーブル作成
        String[] columns = {"ID", "商品名", "価格"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // カートに追加ボタン
        addButton = new JButton("カートに追加");
        add(addButton, BorderLayout.SOUTH);

        // CSVからデータ読み込み
        loadProductsFromCSV();
    }

    /**
     * CSVから商品データを読み込んでテーブルに表示
     */
    private void loadProductsFromCSV() {
        List<Product> products = CsvUtil.loadProducts("data/products.csv");

        // テーブル初期化
        model.setRowCount(0);

        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getPrice()
            });
        }
    }


    // 選択された行のデータ取得
    public String getSelectedProductId() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        return table.getValueAt(row, 0).toString();
    }

    public JButton getAddButton() {
        return addButton;
    }
}

package io.github.zawmoewai206.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * カートパネル
 */
public class CartPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    // 追加フィールド
    private JButton removeButton;

    public JTable getTable() {
        return table;
    }

    public void clearCart() {
        model.setRowCount(0);
    }

    public CartPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"商品名", "数量", "小計"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // コンストラクタ内に追加
        removeButton = new JButton("削除");
        add(removeButton, BorderLayout.SOUTH);
    }

    /**
     * 商品追加（同じ商品なら数量を増やす）
     */
    public void addItem(String name, int qty, double price) {

        // 既存の商品を探す
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingName = model.getValueAt(i, 0).toString();

            if (existingName.equals(name)) {
                // 数量更新
                int currentQty = Integer.parseInt(model.getValueAt(i, 1).toString());
                int newQty = currentQty + qty;

                // 小計更新
                double newSubtotal = newQty * price;

                model.setValueAt(newQty, i, 1);
                model.setValueAt(newSubtotal, i, 2);
                return;
            }
        }

        // 新規追加
        model.addRow(new Object[]{name, qty, price});
    }

    /**
     * 選択された行を削除
     */
    public void removeSelectedItem() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "削除する商品を選択してください");
            return;
        }

        model.removeRow(row);
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    /**
     * 合計金額計算
     */
    public double calculateTotal() {
        double total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            total += Double.parseDouble(model.getValueAt(i, 2).toString());
        }

        return total;
    }
}

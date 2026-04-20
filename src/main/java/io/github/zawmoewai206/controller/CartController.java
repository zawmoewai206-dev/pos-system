package io.github.zawmoewai206.controller;

import io.github.zawmoewai206.model.Product;
import io.github.zawmoewai206.service.ProductService;
import io.github.zawmoewai206.service.OrderService;
import io.github.zawmoewai206.util.IdGenerator;
import io.github.zawmoewai206.view.ProductPanel;
import io.github.zawmoewai206.view.CartPanel;
import io.github.zawmoewai206.view.CheckoutPanel;

import javax.swing.*;

/**
 * カート制御クラス
 */
public class CartController {

    private ProductPanel productPanel;
    private CartPanel cartPanel;
    private CheckoutPanel checkoutPanel;

    private ProductService productService;
    private OrderService orderService;

    public CartController(ProductPanel productPanel,
                          CartPanel cartPanel,
                          CheckoutPanel checkoutPanel) {

        this.productPanel = productPanel;
        this.cartPanel = cartPanel;
        this.checkoutPanel = checkoutPanel;

        productService = new ProductService();
        orderService = new OrderService();

        // ボタンイベント登録
        initEvents();
    }

    /**
     * イベント初期化
     */
    private void initEvents() {

        productPanel.getAddButton().addActionListener(e -> {

            // 選択された商品ID取得
            String id = productPanel.getSelectedProductId();

            if (id == null) {
                JOptionPane.showMessageDialog(null, "商品を選択してください");
                return;
            }

            // 🔥 Serviceから商品取得
            Product product = productService.getById(id);

            // カートに追加（merge対応）
            cartPanel.addItem(
                    product.getName(),
                    1,
                    product.getPrice()
            );

            checkoutPanel.updateTotal(cartPanel.calculateTotal());
        });

        // 🗑 Remove item
        cartPanel.getRemoveButton().addActionListener(e -> {
            cartPanel.removeSelectedItem();
            checkoutPanel.updateTotal(cartPanel.calculateTotal());
        });

        // 💾 Checkout
        checkoutPanel.getCheckoutButton().addActionListener(e -> {

            if (cartPanel.calculateTotal() == 0) {
                JOptionPane.showMessageDialog(null, "カートが空です");
                return;
            }

            String orderId = IdGenerator.generateOrderId();

            // 全行ループして保存
            for (int i = 0; i < cartPanel.getTable().getRowCount(); i++) {

                String name = cartPanel.getTable().getValueAt(i, 0).toString();
                int qty = Integer.parseInt(
                        cartPanel.getTable().getValueAt(i, 1).toString()
                );
                double subtotal = Double.parseDouble(
                        cartPanel.getTable().getValueAt(i, 2).toString()
                );

                orderService.saveOrder(orderId, name, qty, subtotal);
            }

            JOptionPane.showMessageDialog(null, "注文完了しました！");

            // カートクリア
            cartPanel.clearCart();
            checkoutPanel.updateTotal(0);
        });

    }
}

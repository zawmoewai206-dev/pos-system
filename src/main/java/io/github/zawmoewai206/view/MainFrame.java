package io.github.zawmoewai206.view;

import io.github.zawmoewai206.controller.CartController;
import javax.swing.*;
import java.awt.*;

/**
 * メイン画面（POSレジシステム）
 */
public class MainFrame extends JFrame {

    private ProductPanel productPanel;
    private CartPanel cartPanel;
    private CheckoutPanel checkoutPanel;

    public MainFrame() {
        // タイトル設定（日本語）
        setTitle("POSレジシステム");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 画面中央に表示

        // レイアウト設定
        setLayout(new BorderLayout());

        // パネル初期化
        productPanel = new ProductPanel();
        cartPanel = new CartPanel();
        checkoutPanel = new CheckoutPanel();

        // 左：商品一覧
        add(productPanel, BorderLayout.WEST);

        // 中央：カート
        add(cartPanel, BorderLayout.CENTER);

        // 下：会計パネル
        add(checkoutPanel, BorderLayout.SOUTH);


        // コントローラー接続
        new CartController(productPanel, cartPanel, checkoutPanel);
    }
}

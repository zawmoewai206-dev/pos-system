package io.github.zawmoewai206.view;

import javax.swing.*;
import java.awt.*;

/**
 * 会計パネル
 */
public class CheckoutPanel extends JPanel {

    private JLabel totalLabel;
    private JButton checkoutButton;

    public CheckoutPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        totalLabel = new JLabel("合計: 0 円");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        checkoutButton = new JButton("会計");

        add(totalLabel);
        add(checkoutButton);
    }

    public void updateTotal(double total) {
        totalLabel.setText("合計: " + total + " 円");
    }

    public JButton getCheckoutButton() {
        return checkoutButton;
    }
}

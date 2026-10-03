
/*package miniproject1;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pureconnect.mycon;

public class payment extends JFrame implements ActionListener {

    JLabel title;

    JLabel trackingLabel;
    JLabel senderLabel;
    JLabel receiverLabel;
    JLabel pickupLabel;
    JLabel dropLabel;
    JLabel packageLabel;
    JLabel weightLabel;
    JLabel deliveryLabel;
    JLabel dateLabel;

    JLabel baseChargeLabel;
    JLabel deliveryChargeLabel;
    JLabel totalLabel;

    JComboBox<String> paymentmethod;

    JTextField verificationCode;

    JButton pay;
    JButton back;

    Connection con;

    String trackingid;

    double amount;
    String phone;
    String generatedCode;

    public payment(
            String trackingId,
            String sender,
            String receiver,
            String pickup,
            String drop,
            String packageType,
            double weight,
            String deliveryType,
            String date,
            double amount,
            String phone) {

    	 this.trackingid = trackingId;
    	 this.amount = amount;
    	 this.phone = phone;
    	 
        setTitle("Courier Payment");
        setSize(700, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // =================================
        // TITLE
        // =================================

        title = new JLabel("COURIER PAYMENT");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(230, 20, 300, 40);
        add(title);

        // =================================
        // BOOKING DETAILS PANEL
        // =================================

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBorder(
                BorderFactory.createTitledBorder("Booking Details"));

        detailsPanel.setBounds(30, 70, 620, 300);

        add(detailsPanel);

        // Tracking ID
        JLabel l1 = new JLabel("Tracking ID:");
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        l1.setBounds(20, 30, 120, 25);
        detailsPanel.add(l1);

        trackingLabel = new JLabel(trackingId);
        trackingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        trackingLabel.setBounds(150, 30, 180, 25);
        detailsPanel.add(trackingLabel);

        // Sender
        JLabel l2 = new JLabel("Sender:");
        l2.setBounds(20, 65, 120, 25);
        detailsPanel.add(l2);

        senderLabel = new JLabel(sender);
        senderLabel.setBounds(150, 65, 180, 25);
        detailsPanel.add(senderLabel);

        // Receiver
        JLabel l3 = new JLabel("Receiver:");
        l3.setBounds(20, 100, 120, 25);
        detailsPanel.add(l3);

        receiverLabel = new JLabel(receiver);
        receiverLabel.setBounds(150, 100, 180, 25);
        detailsPanel.add(receiverLabel);

        // Pickup
        JLabel l4 = new JLabel("Pickup:");
        l4.setBounds(20, 135, 120, 25);
        detailsPanel.add(l4);

        pickupLabel = new JLabel(pickup);
        pickupLabel.setBounds(150, 135, 180, 25);
        detailsPanel.add(pickupLabel);

        // Drop
        JLabel l5 = new JLabel("Drop:");
        l5.setBounds(20, 170, 120, 25);
        detailsPanel.add(l5);

        dropLabel = new JLabel(drop);
        dropLabel.setBounds(150, 170, 180, 25);
        detailsPanel.add(dropLabel);

        // Package
        JLabel l6 = new JLabel("Package:");
        l6.setBounds(330, 65, 100, 25);
        detailsPanel.add(l6);

        packageLabel = new JLabel(packageType);
        packageLabel.setBounds(440, 65, 150, 25);
        detailsPanel.add(packageLabel);

        // Weight
        JLabel l7 = new JLabel("Weight:");
        l7.setBounds(330, 100, 100, 25);
        detailsPanel.add(l7);

        weightLabel = new JLabel(weight + " kg");
        weightLabel.setBounds(440, 100, 150, 25);
        detailsPanel.add(weightLabel);

        // Delivery
        JLabel l8 = new JLabel("Delivery:");
        l8.setBounds(330, 135, 100, 25);
        detailsPanel.add(l8);

        deliveryLabel = new JLabel(deliveryType);
        deliveryLabel.setBounds(440, 135, 150, 25);
        detailsPanel.add(deliveryLabel);

        // Date
        JLabel l9 = new JLabel("Booking Date:");
        l9.setBounds(330, 170, 100, 25);
        detailsPanel.add(l9);

        dateLabel = new JLabel(date);
        dateLabel.setBounds(440, 170, 150, 25);
        detailsPanel.add(dateLabel);

        // =================================
        // CHARGE DETAILS
        // =================================

        double baseCharge;

        if (weight <= 1) {

            baseCharge = 50;

        } else if (weight <= 5) {

            baseCharge = 100;

        } else {

            baseCharge = 150;
        }

        double deliveryCharge = amount - baseCharge;

        JLabel bc = new JLabel("Base Charge:");
        bc.setBounds(330, 205, 100, 25);
        detailsPanel.add(bc);

        baseChargeLabel = new JLabel("₹ " + baseCharge);
        baseChargeLabel.setBounds(440, 205, 150, 25);
        detailsPanel.add(baseChargeLabel);

        JLabel dc = new JLabel("Delivery Charge:");
        dc.setBounds(330, 235, 100, 25);
        detailsPanel.add(dc);

        deliveryChargeLabel = new JLabel("₹ " + deliveryCharge);
        deliveryChargeLabel.setBounds(440, 235, 150, 25);
        detailsPanel.add(deliveryChargeLabel);

        // =================================
        // TOTAL
        // =================================

        totalLabel = new JLabel("TOTAL: ₹ " + amount);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(20, 235, 250, 30);

        detailsPanel.add(totalLabel);

        // =================================
        // PAYMENT SECTION
        // =================================

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(null);
        paymentPanel.setBorder(
                BorderFactory.createTitledBorder("Payment"));

        paymentPanel.setBounds(30, 390, 620, 130);

        add(paymentPanel);

        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Arial", Font.BOLD, 14));
        methodLabel.setBounds(20, 35, 130, 30);
        paymentPanel.add(methodLabel);

        String methods[] = {
                "UPI",
                "Cash on Delivery"
        };

        paymentmethod = new JComboBox<>(methods);

        paymentmethod.setBounds(160, 35, 200, 30);

        paymentPanel.add(paymentmethod);

        // Verification
        JLabel verifyLabel = new JLabel("Verification Code:");

        verifyLabel.setBounds(20, 80, 130, 30);

        paymentPanel.add(verifyLabel);

        verificationCode = new JTextField();

        verificationCode.setBounds(160, 80, 200, 30);

        paymentPanel.add(verificationCode);

        // =================================
        // BUTTONS
        // =================================

        pay = new JButton("Pay Now");

        pay.setBounds(170, 545, 120, 35);

        add(pay);

        back = new JButton("Back");

        back.setBounds(320, 545, 120, 35);

        add(back);

        // Listeners
        pay.addActionListener(this);

        back.addActionListener(this);

        paymentmethod.addActionListener(this);

        // =================================
        // DATABASE CONNECTION
        // =================================

        try {

            con = mycon.myownconnection();

        } catch (Exception e) {

            e.printStackTrace();
        }

        // Generate demo verification code
        generatedCode = generateVerificationCode(phone);

        System.out.println(
                "Demo Verification Code for " + phone + " : " + generatedCode);
      

        setVisible(true);
    }

    // =================================
    // GENERATE VERIFICATION CODE
    // =================================

    public String generateVerificationCode(String phone) {

        if (phone == null || !phone.matches("[0-9]{10}")) {

            return null;
        }

        String code = phone.substring(4, 10);

        return code;
    }
    // =================================
    // BUTTON ACTIONS
    // =================================

    @Override
    public void actionPerformed(ActionEvent e) {

        // ===============================
        // PAYMENT METHOD CHANGE
        // ===============================

        if (e.getSource() == paymentmethod) {

            String method =
                    paymentmethod.getSelectedItem().toString();

            if (method.equals("Cash on Delivery")) {

                verificationCode.setEnabled(false);
                verificationCode.setText("");

            } else {

                verificationCode.setEnabled(true);
            }
        }

        // ===============================
        // PAY NOW
        // ===============================

        if (e.getSource() == pay) {

            String method =
                    paymentmethod.getSelectedItem().toString();

            String paymentStatus;

            // ===========================
            // CASH ON DELIVERY
            // ===========================

            if (method.equals("Cash on Delivery")) {

                paymentStatus = "Pending";
            }

            // ===========================
            // UPI / CARD
            // ===========================

            else {

                String enteredCode =
                        verificationCode.getText().trim();

                if (enteredCode.equals("")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter verification code");

                    return;
                }

                if (!enteredCode.equals(generatedCode)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid verification code");

                    return;
                }

                paymentStatus = "Paid";
            }

            // ===========================
            // INSERT PAYMENT
            // ===========================

            try {

                if (con == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Database connection failed");

                    return;
                }

                String sql =
                        "INSERT INTO payment "
                                + "(trackingid, amount, paymentmethod, paymentstatus) "
                                + "VALUES (?, ?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, trackingid);

                ps.setDouble(2, amount);

                ps.setString(3, method);

                ps.setString(4, paymentStatus);

                ps.executeUpdate();

                // =========================
                // SUCCESS MESSAGE
                // =========================

                if (method.equals("Cash on Delivery")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Order Confirmed!\n\n"
                                    + "Tracking ID: " + trackingid
                                    + "\nAmount: ₹" + amount
                                    + "\nPayment Method: "
                                    + method
                                    + "\nPayment Status: Pending");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Payment Successful!\n\n"
                                    + "Tracking ID: " + trackingid
                                    + "\nAmount: ₹" + amount
                                    + "\nPayment Method: "
                                    + method
                                    + "\nPayment Status: Paid");
                }

                ps.close();
                con.close();

                dispose();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Failed:\n"
                                + ex.getMessage());
            }
        }

        // ===============================
        // BACK
        // ===============================

        if (e.getSource() == back) {

            dispose();
        }
    }
}*/


package miniproject1;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pureconnect.mycon;

public class payment extends JFrame implements ActionListener {

    JLabel title;

    JLabel trackingLabel;
    JLabel senderLabel;
    JLabel receiverLabel;
    JLabel pickupLabel;
    JLabel dropLabel;
    JLabel packageLabel;
    JLabel weightLabel;
    JLabel deliveryLabel;
    JLabel dateLabel;

    JLabel baseChargeLabel;
    JLabel deliveryChargeLabel;
    JLabel totalLabel;

    JComboBox<String> paymentmethod;

    JTextField upiId;

    JButton pay;
    JButton back;

    Connection con;

    String trackingid;
    double amount;
    String phone;

    // ==========================================
    // CHANGE THIS TO YOUR REAL RECEIVER UPI ID
    // ==========================================

    String receiverUPI = "courierdemo@upi";

    public payment(
            String trackingId,
            String sender,
            String receiver,
            String pickup,
            String drop,
            String packageType,
            double weight,
            String deliveryType,
            String date,
            double amount,
            String phone) {

        this.trackingid = trackingId;
        this.amount = amount;
        this.phone = phone;

        setTitle("Courier Payment");
        setSize(700, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // =================================
        // TITLE
        // =================================

        title = new JLabel("COURIER PAYMENT");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(230, 20, 300, 40);
        add(title);

        // =================================
        // BOOKING DETAILS PANEL
        // =================================

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBorder(
                BorderFactory.createTitledBorder("Booking Details"));

        detailsPanel.setBounds(30, 70, 620, 300);
        add(detailsPanel);

        // Tracking ID
        JLabel l1 = new JLabel("Tracking ID:");
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        l1.setBounds(20, 30, 120, 25);
        detailsPanel.add(l1);

        trackingLabel = new JLabel(trackingId);
        trackingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        trackingLabel.setBounds(150, 30, 180, 25);
        detailsPanel.add(trackingLabel);

        // Sender
        JLabel l2 = new JLabel("Sender:");
        l2.setBounds(20, 65, 120, 25);
        detailsPanel.add(l2);

        senderLabel = new JLabel(sender);
        senderLabel.setBounds(150, 65, 180, 25);
        detailsPanel.add(senderLabel);

        // Receiver
        JLabel l3 = new JLabel("Receiver:");
        l3.setBounds(20, 100, 120, 25);
        detailsPanel.add(l3);

        receiverLabel = new JLabel(receiver);
        receiverLabel.setBounds(150, 100, 180, 25);
        detailsPanel.add(receiverLabel);

        // Pickup
        JLabel l4 = new JLabel("Pickup:");
        l4.setBounds(20, 135, 120, 25);
        detailsPanel.add(l4);

        pickupLabel = new JLabel(pickup);
        pickupLabel.setBounds(150, 135, 180, 25);
        detailsPanel.add(pickupLabel);

        // Drop
        JLabel l5 = new JLabel("Drop:");
        l5.setBounds(20, 170, 120, 25);
        detailsPanel.add(l5);

        dropLabel = new JLabel(drop);
        dropLabel.setBounds(150, 170, 180, 25);
        detailsPanel.add(dropLabel);

        // Package
        JLabel l6 = new JLabel("Package:");
        l6.setBounds(330, 65, 100, 25);
        detailsPanel.add(l6);

        packageLabel = new JLabel(packageType);
        packageLabel.setBounds(440, 65, 150, 25);
        detailsPanel.add(packageLabel);

        // Weight
        JLabel l7 = new JLabel("Weight:");
        l7.setBounds(330, 100, 100, 25);
        detailsPanel.add(l7);

        weightLabel = new JLabel(weight + " kg");
        weightLabel.setBounds(440, 100, 150, 25);
        detailsPanel.add(weightLabel);

        // Delivery
        JLabel l8 = new JLabel("Delivery:");
        l8.setBounds(330, 135, 100, 25);
        detailsPanel.add(l8);

        deliveryLabel = new JLabel(deliveryType);
        deliveryLabel.setBounds(440, 135, 150, 25);
        detailsPanel.add(deliveryLabel);

        // Date
        JLabel l9 = new JLabel("Booking Date:");
        l9.setBounds(330, 170, 100, 25);
        detailsPanel.add(l9);

        dateLabel = new JLabel(date);
        dateLabel.setBounds(440, 170, 150, 25);
        detailsPanel.add(dateLabel);

        // =================================
        // CHARGE DETAILS
        // =================================

        double baseCharge;

        if (weight <= 1) {

            baseCharge = 50;

        } else if (weight <= 5) {

            baseCharge = 100;

        } else {

            baseCharge = 150;
        }

        double deliveryCharge = amount - baseCharge;

        JLabel bc = new JLabel("Base Charge:");
        bc.setBounds(330, 205, 100, 25);
        detailsPanel.add(bc);

        baseChargeLabel = new JLabel("₹ " + baseCharge);
        baseChargeLabel.setBounds(440, 205, 150, 25);
        detailsPanel.add(baseChargeLabel);

        JLabel dc = new JLabel("Delivery Charge:");
        dc.setBounds(330, 235, 100, 25);
        detailsPanel.add(dc);

        deliveryChargeLabel = new JLabel("₹ " + deliveryCharge);
        deliveryChargeLabel.setBounds(440, 235, 150, 25);
        detailsPanel.add(deliveryChargeLabel);

        // =================================
        // TOTAL
        // =================================

        totalLabel = new JLabel("TOTAL: ₹ " + amount);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(20, 235, 250, 30);

        detailsPanel.add(totalLabel);

        // =================================
        // PAYMENT SECTION
        // =================================

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(null);
        paymentPanel.setBorder(
                BorderFactory.createTitledBorder("Payment"));

        paymentPanel.setBounds(30, 390, 620, 130);

        add(paymentPanel);

        // Payment Method
        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Arial", Font.BOLD, 14));
        methodLabel.setBounds(20, 25, 130, 30);
        paymentPanel.add(methodLabel);

        String methods[] = {
                "UPI",
                "Cash on Delivery"
        };

        paymentmethod = new JComboBox<>(methods);
        paymentmethod.setBounds(160, 25, 200, 30);
        paymentPanel.add(paymentmethod);

        // UPI ID
        JLabel upiLabel = new JLabel("UPI ID:");
        upiLabel.setBounds(20, 70, 130, 30);
        paymentPanel.add(upiLabel);

        upiId = new JTextField(receiverUPI);
        upiId.setBounds(160, 70, 200, 30);
        upiId.setEditable(false);
        paymentPanel.add(upiId);

        // =================================
        // BUTTONS
        // =================================

        pay = new JButton("Pay Now");
        pay.setBounds(170, 545, 120, 35);
        add(pay);

        back = new JButton("Back");
        back.setBounds(320, 545, 120, 35);
        add(back);

        // Listeners
        pay.addActionListener(this);
        back.addActionListener(this);
        paymentmethod.addActionListener(this);

        // =================================
        // DATABASE CONNECTION
        // =================================

        try {

            con = mycon.myownconnection();

        } catch (Exception e) {

            e.printStackTrace();
        }

        setVisible(true);
    }

    // =================================
    // BUTTON ACTIONS
    // =================================

    @Override
    public void actionPerformed(ActionEvent e) {

        // =================================
        // PAYMENT METHOD CHANGE
        // =================================

        if (e.getSource() == paymentmethod) {

            String method =
                    paymentmethod.getSelectedItem().toString();

            if (method.equals("Cash on Delivery")) {

                upiId.setEnabled(false);

            } else {

                upiId.setEnabled(true);
            }
        }

        // =================================
        // PAY NOW
        // =================================

        if (e.getSource() == pay) {

            String method =
                    paymentmethod.getSelectedItem().toString();

            String paymentStatus;

            // =================================
            // CASH ON DELIVERY
            // =================================

            if (method.equals("Cash on Delivery")) {

                paymentStatus = "Pending";

            }

            // =================================
            // UPI PAYMENT
            // =================================

            else {

                paymentStatus = "Paid";

                try {

                    // Create UPI payment URL
                    String upiUrl =
                            "upi://pay?pa="
                            + receiverUPI
                            + "&pn="
                            + URLEncoder.encode(
                                    "Courier Management System",
                                    StandardCharsets.UTF_8)
                            + "&am="
                            + amount
                            + "&cu=INR";

                    // Open UPI payment link
                    if (Desktop.isDesktopSupported()) {

                        Desktop.getDesktop().browse(
                                new URI(upiUrl));

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Unable to open UPI payment link.");
                        return;
                    }

                    JOptionPane.showMessageDialog(
                            this,
                            "UPI Payment Link Opened.\n\n"
                            + "UPI ID: " + receiverUPI
                            + "\nAmount: ₹" + amount
                            + "\n\nComplete the payment in your UPI application.");

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to open UPI payment:\n"
                            + ex.getMessage());

                    return;
                }
            }

            // =================================
            // INSERT PAYMENT INTO DATABASE
            // =================================

            try {

                if (con == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Database connection failed");

                    return;
                }

                String sql =
                        "INSERT INTO payment "
                        + "(trackingid, amount, paymentmethod, paymentstatus) "
                        + "VALUES (?, ?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, trackingid);
                ps.setDouble(2, amount);
                ps.setString(3, method);
                ps.setString(4, paymentStatus);

                ps.executeUpdate();

                // =================================
                // SUCCESS MESSAGE
                // =================================

                if (method.equals("Cash on Delivery")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Order Confirmed!\n\n"
                            + "Tracking ID: " + trackingid
                            + "\nAmount: ₹" + amount
                            + "\nPayment Method: "
                            + method
                            + "\nPayment Status: Pending");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Payment Successful!\n\n"
                            + "Tracking ID: " + trackingid
                            + "\nAmount: ₹" + amount
                            + "\nPayment Method: "
                            + method
                            + "\nUPI ID: " + receiverUPI
                            + "\nPayment Status: Paid");
                }

                ps.close();
                con.close();

                dispose();

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Failed:\n"
                        + ex.getMessage());
            }
        }

        // =================================
        // BACK
        // =================================

        if (e.getSource() == back) {

            dispose();
        }
    }
}
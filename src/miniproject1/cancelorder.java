package miniproject1;

	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.pureconnect.mycon;

	public class cancelorder extends JFrame implements ActionListener {

	    JLabel l1, l2, l3;
	    JTextField orderId, customerName, status;

	    JButton search, cancel, viewCancelled;

	    Connection con;

	    public cancelorder() {

	        setTitle("Cancel Courier Order");
	        setSize(500, 500);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        // =========================
	        // ORDER ID
	        // =========================

	        l1 = new JLabel("Order ID:");
	        l1.setBounds(50, 50, 120, 30);
	        l1.setFont(new Font("Arial", Font.BOLD, 16));
	        add(l1);

	        orderId = new JTextField();
	        orderId.setBounds(180, 50, 200, 30);
	        add(orderId);

	        // =========================
	        // SEARCH BUTTON
	        // =========================

	        search = new JButton("Search Order");
	        search.setBounds(180, 100, 130, 35);
	        add(search);

	        // =========================
	        // CUSTOMER NAME
	        // =========================

	        l2 = new JLabel("Customer Name:");
	        l2.setBounds(50, 160, 130, 30);
	        l2.setFont(new Font("Arial", Font.BOLD, 14));
	        add(l2);

	        customerName = new JTextField();
	        customerName.setBounds(180, 160, 200, 30);
	        customerName.setEditable(false);
	        add(customerName);

	        // =========================
	        // STATUS
	        // =========================

	        l3 = new JLabel("Status:");
	        l3.setBounds(50, 210, 120, 30);
	        l3.setFont(new Font("Arial", Font.BOLD, 14));
	        add(l3);

	        status = new JTextField();
	        status.setBounds(180, 210, 200, 30);
	        status.setEditable(false);
	        add(status);

	        // =========================
	        // CANCEL BUTTON
	        // =========================

	        cancel = new JButton("Cancel Order");
	        cancel.setBounds(180, 270, 130, 40);
	        add(cancel);

	        // =========================
	        // VIEW CANCELLED ORDERS
	        // =========================

	        viewCancelled = new JButton("View Cancelled Orders");
	        viewCancelled.setBounds(140, 340, 220, 40);
	        add(viewCancelled);

	        // =========================
	        // ACTION LISTENERS
	        // =========================

	        search.addActionListener(this);
	        cancel.addActionListener(this);
	        viewCancelled.addActionListener(this);

	        // =========================
	        // DATABASE CONNECTION
	        // =========================

	        connect();

	        setVisible(true);
	    }

	    // =========================
	    // DATABASE CONNECTION
	    // =========================

	    public void connect() {

	        try {

	            con = mycon.myownconnection();

	            if (con != null) {

	                System.out.println(
	                    "Database connected successfully"
	                );

	            } else {

	                System.out.println(
	                    "Database connection failed"
	                );
	            }

	        } catch (Exception e) {

	            e.printStackTrace();

	            JOptionPane.showMessageDialog(
	                this,
	                "Database connection failed:\n"
	                + e.getMessage()
	            );
	        }
	    }

	    // =========================
	    // ACTION PERFORMED
	    // =========================

	    @Override
	    public void actionPerformed(ActionEvent e) {

	        // =====================================================
	        // SEARCH ORDER
	        // =====================================================

	        if (e.getSource() == search) {

	            try {

	                String id = orderId.getText().trim();

	                if (id.isEmpty()) {

	                    JOptionPane.showMessageDialog(
	                        this,
	                        "Please enter Order ID"
	                    );

	                    return;
	                }

	                String sql =
	                    "SELECT sname, status "
	                    + "FROM Bookeddetails "
	                    + "WHERE trackingid=?";

	                PreparedStatement ps =
	                    con.prepareStatement(sql);

	                ps.setString(1, id);

	                ResultSet rs =
	                    ps.executeQuery();

	                if (rs.next()) {

	                    customerName.setText(
	                        rs.getString("sname")
	                    );

	                    status.setText(
	                        rs.getString("status")
	                    );

	                } else {

	                    JOptionPane.showMessageDialog(
	                        this,
	                        "Order not found!"
	                    );

	                    customerName.setText("");
	                    status.setText("");
	                }

	                rs.close();
	                ps.close();

	            } catch (Exception ex) {

	                ex.printStackTrace();

	                JOptionPane.showMessageDialog(
	                    this,
	                    "Error while searching order:\n"
	                    + ex.getMessage()
	                );
	            }
	        }

	        // =====================================================
	        // CANCEL ORDER
	        // =====================================================

	        if (e.getSource() == cancel) {

	            try {

	                String id =
	                    orderId.getText().trim();

	                if (id.isEmpty()) {

	                    JOptionPane.showMessageDialog(
	                        this,
	                        "Please enter Order ID"
	                    );

	                    return;
	                }

	                String currentStatus =
	                    status.getText().trim();

	                if (currentStatus.isEmpty()) {

	                    JOptionPane.showMessageDialog(
	                        this,
	                        "Please search the order first!"
	                    );

	                    return;
	                }

	                // Already cancelled

	                if (currentStatus.equalsIgnoreCase("Cancelled")) {

	                    JOptionPane.showMessageDialog(
	                        this,
	                        "Order is already cancelled!"
	                    );

	                    return;
	                }

	                // Delivered order

	                if (currentStatus.equalsIgnoreCase("Delivered")) {

	                    JOptionPane.showMessageDialog(
	                        this,
	                        "Delivered order cannot be cancelled!"
	                    );

	                    return;
	                }

	                int choice =
	                    JOptionPane.showConfirmDialog(
	                        this,
	                        "Are you sure you want to cancel this order?",
	                        "Cancel Order",
	                        JOptionPane.YES_NO_OPTION
	                    );

	                if (choice == JOptionPane.YES_OPTION) {

	                    String sql =
	                        "UPDATE Bookeddetails "
	                        + "SET status=? "
	                        + "WHERE trackingid=?";

	                    PreparedStatement ps =
	                        con.prepareStatement(sql);

	                    ps.setString(1, "Cancelled");
	                    ps.setString(2, id);

	                    int result =
	                        ps.executeUpdate();

	                    if (result > 0) {

	                        status.setText("Cancelled");

	                        JOptionPane.showMessageDialog(
	                            this,
	                            "Order cancelled successfully!"
	                        );

	                    } else {

	                        JOptionPane.showMessageDialog(
	                            this,
	                            "Order not found!"
	                        );
	                    }

	                    ps.close();
	                }

	            } catch (Exception ex) {

	                ex.printStackTrace();

	                JOptionPane.showMessageDialog(
	                    this,
	                    "Error while cancelling order:\n"
	                    + ex.getMessage()
	                );
	            }
	        }

	        // =====================================================
	        // VIEW ALL CANCELLED ORDERS
	        // =====================================================

	        if (e.getSource() == viewCancelled) {

	            viewCancelledOrders();
	        }
	    }

	    // =========================================================
	    // VIEW CANCELLED ORDERS METHOD
	    // =========================================================

	    public void viewCancelledOrders() {

	        JFrame frame = new JFrame(
	            "All Cancelled Orders"
	        );

	        frame.setSize(700, 500);
	        frame.setLocationRelativeTo(this);
	        frame.setLayout(null);

	        // Table columns

	        String[] columns = {
	            "Order ID",
	            "Customer Name",
	            "Status"
	        };

	        DefaultTableModel model =
	            new DefaultTableModel(columns, 0);

	        JTable table =
	            new JTable(model);

	        table.setRowHeight(30);

	        // Scroll pane

	        JScrollPane scrollPane =
	            new JScrollPane(table);

	        scrollPane.setBounds(
	            30,
	            30,
	            620,
	            350
	        );

	        frame.add(scrollPane);

	        // Close button

	        JButton close =
	            new JButton("Close");

	        close.setBounds(
	            280,
	            400,
	            120,
	            35
	        );

	        frame.add(close);

	        close.addActionListener(
	            new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent e) {

	                    frame.dispose();
	                }
	            }
	        );

	        // =========================
	        // GET CANCELLED ORDERS
	        // =========================

	        try {

	            String sql =
	                "SELECT trackingid, sname, status "
	                + "FROM Bookeddetails "
	                + "WHERE status=?";

	            PreparedStatement ps =
	                con.prepareStatement(sql);

	            ps.setString(1, "Cancelled");

	            ResultSet rs =
	                ps.executeQuery();

	            boolean found = false;

	            while (rs.next()) {

	                found = true;

	                String trackingId =
	                    rs.getString("trackingid");

	                String customer =
	                    rs.getString("sname");

	                String orderStatus =
	                    rs.getString("status");

	                model.addRow(
	                    new Object[] {
	                        trackingId,
	                        customer,
	                        orderStatus
	                    }
	                );
	            }

	            rs.close();
	            ps.close();

	            if (!found) {

	                JOptionPane.showMessageDialog(
	                    frame,
	                    "No cancelled orders found!"
	                );
	            }

	        } catch (Exception ex) {

	            ex.printStackTrace();

	            JOptionPane.showMessageDialog(
	                frame,
	                "Error while loading cancelled orders:\n"
	                + ex.getMessage()
	            );

	            return;
	        }

	        frame.setVisible(true);
	    }

	}

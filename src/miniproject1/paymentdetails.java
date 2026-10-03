	package miniproject1;

	import java.awt.Font;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JOptionPane;
	import javax.swing.table.DefaultTableModel;

	import com.pureconnect.mycon;

	public class paymentdetails extends JFrame {

	    JLabel title;
	    JTable table;
	    JScrollPane scrollPane;
	    JButton back;

	    Connection con;

	    public paymentdetails() {

	        setTitle("Payment Details");
	        setSize(850, 550);
	        setLayout(null);
	        setLocationRelativeTo(null);
	        setResizable(false);

	        // Title
	        title = new JLabel("ALL PAYMENT DETAILS");
	        title.setFont(new Font("Arial", Font.BOLD, 24));
	        title.setBounds(300, 20, 300, 40);
	        add(title);

	        // Table
	        String[] columns = {
	            "Payment ID",
	            "Tracking ID",
	            "Amount",
	            "Payment Method",
	            "Payment Status",
	            "Payment Date"
	        };

	        DefaultTableModel model = new DefaultTableModel(columns, 0);

	        table = new JTable(model);

	        scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(30, 80, 780, 330);
	        add(scrollPane);

	        // Back button
	        back = new JButton("Back");
	        back.setBounds(350, 440, 120, 35);
	        add(back);

	        back.addActionListener(e -> {
	            dispose();
	        });

	        loadPaymentDetails();

	        setVisible(true);
	    }

	    public void loadPaymentDetails() {

	        try {

	            con = mycon.myownconnection();

	            String sql = "select paymentid, trackingid, amount, "
	                    + "paymentmethod, paymentstatus, paymentdate "
	                    + "from payment order by paymentid desc";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ResultSet rs = ps.executeQuery();

	            DefaultTableModel model =
	                    (DefaultTableModel) table.getModel();

	            while (rs.next()) {

	                Object[] row = {
	                    rs.getInt("paymentid"),
	                    rs.getString("trackingid"),
	                    rs.getDouble("amount"),
	                    rs.getString("paymentmethod"),
	                    rs.getString("paymentstatus"),
	                    rs.getTimestamp("paymentdate")
	                };

	                model.addRow(row);
	            }

	            rs.close();
	            ps.close();
	            con.close();

	        } catch (Exception ex) {

	            ex.printStackTrace();

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Unable to load payment details:\n"
	                    + ex.getMessage());
	        }
	    }
}

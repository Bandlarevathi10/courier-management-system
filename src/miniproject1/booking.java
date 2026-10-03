package miniproject1;

import java.awt.Font;
import com.twilio.Twilio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;



public class booking extends JFrame implements ActionListener,ItemListener{
	//private static final String String = null;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl0;
	JTextField jt1,jt2,jt3,jt4,jt5,jt6,jt7,jt9;
	JDateChooser jt8;
	JComboBox jcb1,jcb2;
	JButton jb1,jb2,jb3,jb4;
	String packagetype,deliverytype;
	
	String currentTrackingId = null;
	public booking()
	{
		ImageIcon ii1=new ImageIcon("C:\\Users\\revat\\OneDrive\\Documents\\Desktop\\inage3.jpg");
		
		        jl0 = new JLabel(ii1);

		        jl1 = new JLabel("Book Courier");
		        jl1.setFont(new Font("Arial", Font.BOLD, 24));

		        jl2 = new JLabel("Sender name");
		        jl3 = new JLabel("Sender phoneno");
		        jl4 = new JLabel("Pickup location");
		        jl5 = new JLabel("Receiver name");
		        jl6 = new JLabel("Receiver phoneno");
		        jl7 = new JLabel("Drop location");
		        jl8 = new JLabel("Package Type");
		        jl9 = new JLabel("Weight (kg)");
		        jl10 = new JLabel("Delivery Type");
		        jl11 = new JLabel("Booking Date");

		        jt1 = new JTextField();
		        jt2 = new JTextField();
		        jt3 = new JTextField();
		        jt4 = new JTextField();
		        jt5 = new JTextField();
		        jt6 = new JTextField();
		        jt7 = new JTextField();
		        jt8 = new JDateChooser();
		        jt8.setDateFormatString("dd-MM-yyyy");
		        jt8.setDate(null);
		        jt9 = new JTextField();

		        // Package types
		        Vector v = new Vector();

		        v.add("Document");
		        v.add("Parcel");
		        v.add("Electronics");
		        v.add("Clothes");
		        v.add("others");

		        jcb1 = new JComboBox(v);

		        // Delivery types
		        Vector vv = new Vector();

		        vv.add("Standard");
		        vv.add("Express");
		        vv.add("Same day");

		        jcb2 = new JComboBox(vv);

		        setLayout(null);
		        setContentPane(jl0);
		        jl0.setLayout(null);

		        jl0.setBounds(0, 0, 800, 600);

		        // Buttons
		        jb1 = new JButton("Back");
		        jb2 = new JButton("Clear");
		        jb3 = new JButton("Submit");
		        jb4 = new JButton("Payment");

		        // Labels
		        jl1.setBounds(170, 20, 250, 40);

		        jl2.setBounds(50, 50, 120, 80);
		        jl3.setBounds(50, 90, 120, 80);
		        jl4.setBounds(50, 130, 120, 80);
		        jl5.setBounds(50, 170, 120, 80);
		        jl6.setBounds(50, 210, 120, 80);
		        jl7.setBounds(50, 250, 120, 80);
		        jl8.setBounds(50, 290, 120, 80);
		        jl9.setBounds(50, 330, 120, 80);
		        jl10.setBounds(50, 370, 120, 80);
		        jl11.setBounds(50, 410, 120, 80);

		        // Text fields
		        jt1.setBounds(200, 80, 120, 20);
		        jt2.setBounds(200, 120, 120, 20);
		        jt3.setBounds(200, 160, 120, 20);
		        jt4.setBounds(200, 200, 120, 20);
		        jt5.setBounds(200, 240, 120, 20);
		        jt6.setBounds(200, 280, 120, 20);
		        jt7.setBounds(200, 360, 120, 20);
		        jt8.setBounds(200, 440, 120, 20);

		        // Combo boxes
		        jcb1.setBounds(200, 320, 120, 20);
		        jcb2.setBounds(200, 400, 120, 20);

		        // Buttons
		        jb1.setBounds(50, 520, 100, 30);
		        jb2.setBounds(180, 520, 100, 30);
		        jb3.setBounds(310, 520, 100, 30);
		        jb4.setBounds(440, 520, 100, 30);

		        // Add labels
		        add(jl1);
		        add(jl2);
		        add(jl3);
		        add(jl4);
		        add(jl5);
		        add(jl6);
		        add(jl7);
		        add(jl8);
		        add(jl9);
		        add(jl10);
		        add(jl11);

		        // Add text fields
		        add(jt1);
		        add(jt2);
		        add(jt3);
		        add(jt4);
		        add(jt5);
		        add(jt6);
		        add(jt7);
		        add(jt8);

		        // Add combo boxes
		        add(jcb1);
		        add(jcb2);

		        // Add buttons
		        add(jb1);
		        add(jb2);
		        add(jb3);
		        add(jb4);

		        // Listeners
		        jb1.addActionListener(this);
		        jb2.addActionListener(this);
		        jb3.addActionListener(this);
		        jb4.addActionListener(this);

		        jcb1.addItemListener(this);
		        jcb2.addItemListener(this);

		        // Default values
		        packagetype = "Document";
		        deliverytype = "Standard";
		    }

		    @Override
		    public void itemStateChanged(ItemEvent ie) {

		        if (ie.getSource().equals(jcb1)) {
		            packagetype = (String) jcb1.getSelectedItem();
		        }

		        if (ie.getSource().equals(jcb2)) {
		            deliverytype = (String) jcb2.getSelectedItem();
		        }
		    }

		    @Override
		    public void actionPerformed(ActionEvent e) {

		        // =========================
		        // BACK BUTTON
		        // =========================

		        if (e.getSource().equals(jb1)) {
		            dispose();
		        }

		        // =========================
		        // CLEAR BUTTON
		        // =========================

		        if (e.getSource().equals(jb2)) {

		            jt1.setText("");
		            jt2.setText("");
		            jt3.setText("");
		            jt4.setText("");
		            jt5.setText("");
		            jt6.setText("");
		            jt7.setText("");
		            //jt8.setText("");

		            jcb1.setSelectedIndex(0);
		            jcb2.setSelectedIndex(0);

		            currentTrackingId = null;
		        }

		        // =========================
		        // SUBMIT / BOOK COURIER
		        // =========================

		        if (e.getSource().equals(jb3)) {

		            String sname = jt1.getText().trim();
		            String sphnno = jt2.getText().trim();
		            String pickup = jt3.getText().trim();
		            String rname = jt4.getText().trim();
		            String rphnno = jt5.getText().trim();
		            String dropaddr = jt6.getText().trim();
		            String weight = jt7.getText().trim();
		            String date = "";

		            if (jt8.getDate() != null) {
		                date = new java.text.SimpleDateFormat("dd-MM-yyyy")
		                        .format(jt8.getDate());
		            }
		            packagetype = (String) jcb1.getSelectedItem();
		            deliverytype = (String) jcb2.getSelectedItem();

		            // Validation
		            if (sname.equals("")
		                    || sphnno.equals("")
		                    || pickup.equals("")
		                    || rname.equals("")
		                    || rphnno.equals("")
		                    || dropaddr.equals("")
		                    || weight.equals("")
		                    || date.equals("")) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Please fill all the fields");

		                return;
		            }
		         // Sender phone validation
		            if (!sphnno.matches("[0-9]{10}")) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Sender phone number must contain exactly 10 digits.");

		                return;
		            }

		            // Receiver phone validation
		            if (!rphnno.matches("[0-9]{10}")) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Receiver phone number must contain exactly 10 digits.");

		                return;
		            }
		            try {

		                double weightvalue = Double.parseDouble(weight);

		                if (weightvalue <= 0) {

		                    JOptionPane.showMessageDialog(
		                            this,
		                            "Weight must be greater than 0");

		                    return;
		                }

		                // Database connection
		                Class.forName("com.mysql.cj.jdbc.Driver");

		                Connection con = DriverManager.getConnection(
		                        "jdbc:mysql://localhost:3306/company",
		                        "root",
		                        "Bandla@123");

		                // Insert booking
		                String sql = "insert into bookeddetails "
		                        + "(sname,sphnno,pickup,rname,rphnno,dropaddr,weight,date,"
		                        + "packagetype,deliverytype,status) "
		                        + "values(?,?,?,?,?,?,?,?,?,?,?)";

		                PreparedStatement pst = con.prepareStatement(
		                        sql,
		                        java.sql.Statement.RETURN_GENERATED_KEYS);

		                pst.setString(1, sname);
		                pst.setString(2, sphnno);
		                pst.setString(3, pickup);
		                pst.setString(4, rname);
		                pst.setString(5, rphnno);
		                pst.setString(6, dropaddr);
		                pst.setString(7, weight);
		                pst.setString(8, date);
		                pst.setString(9, packagetype);
		                pst.setString(10, deliverytype);
		                pst.setString(11, "Booked");

		                pst.executeUpdate();
		                //Get generated ID
		                ResultSet rs = pst.getGeneratedKeys();

		                if (rs.next()) {

		                    int id = rs.getInt(1);

		                    // IMPORTANT:
		                    // Do NOT write "String" here
		                    currentTrackingId = "CR" + (1000 + id);

		                    // Update tracking ID
		                    PreparedStatement pst2 = con.prepareStatement(
		                            "update bookeddetails set trackingid=? where id=?");

		                    pst2.setString(1, currentTrackingId);
		                    pst2.setInt(2, id);

		                    pst2.executeUpdate();

		                    // Calculate amount
		                    double amount = calculateAmount(weightvalue);
		                    
		                    //SMSNotification.sendSMS(sphnno, currentTrackingId);

		                    JOptionPane.showMessageDialog(
		                            this,
		                            "Courier Booked Successfully\n\n"
		                            + "Tracking ID : " + currentTrackingId
		                            + "\nWeight : " + weightvalue + " kg"
		                            + "\nDelivery Type : " + deliverytype
		                            + "\nAmount : ₹" + amount
		                            + "\nStatus : Booked");

		                    pst2.close();
		                    pst.close();
		                    rs.close();
		                    con.close();
		                }

		            } catch (NumberFormatException ex) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Please enter a valid weight");

		            } catch (Exception ex) {

		                ex.printStackTrace();

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Booking failed:\n" + ex.getMessage());
		            }
		        }

		        // =========================
		     // =========================

		        if (e.getSource().equals(jb4)) {

		            String sname = jt1.getText().trim();
		            String sphnno = jt2.getText().trim();
		            String pickup = jt3.getText().trim();
		            String rname = jt4.getText().trim();
		            String dropaddr = jt6.getText().trim();
		            String weight = jt7.getText().trim();

		            String date = "";

		            if (jt8.getDate() != null) {
		                date = new java.text.SimpleDateFormat("dd-MM-yyyy")
		                        .format(jt8.getDate());
		            }

		            packagetype = (String) jcb1.getSelectedItem();
		            deliverytype = (String) jcb2.getSelectedItem();

		            // Check empty fields
		            if (sname.equals("")
		                    || sphnno.equals("")
		                    || pickup.equals("")
		                    || rname.equals("")
		                    || dropaddr.equals("")
		                    || weight.equals("")
		                    || date.equals("")) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Please fill all the fields");

		                return;
		            }

		            // Phone validation
		            if (!sphnno.matches("[0-9]{10}")) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Phone number must contain exactly 10 digits.");

		                return;
		            }

		            // Submit must be clicked first
		            if (currentTrackingId == null) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Please click Submit first to book the courier.");

		                return;
		            }

		            try {

		                double weightvalue = Double.parseDouble(weight);

		                if (weightvalue <= 0) {

		                    JOptionPane.showMessageDialog(
		                            this,
		                            "Weight must be greater than 0");

		                    return;
		                }

		                double amount = calculateAmount(weightvalue);

		                new payment(
		                        currentTrackingId,
		                        sname,
		                        rname,
		                        pickup,
		                        dropaddr,
		                        packagetype,
		                        weightvalue,
		                        deliverytype,
		                        date,
		                        amount,
		                        sphnno
		                );

		            } catch (NumberFormatException ex) {

		                JOptionPane.showMessageDialog(
		                        this,
		                        "Please enter a valid weight");
		            }
		        }
		    }
		    // =========================
		    // CALCULATE COURIER CHARGE
		    // =========================

		    public double calculateAmount(double weightvalue) {

		        double amount;

		        // Weight charge
		        if (weightvalue <= 1) {

		            amount = 50;

		        } else if (weightvalue <= 5) {

		            amount = 100;

		        } else {

		            amount = 150;
		        }

		        // Delivery charge
		        if (deliverytype.equals("Express")) {

		            amount = amount + 50;

		        } else if (deliverytype.equals("Same day")) {

		            amount = amount + 100;
		        }

		        return amount;
		    }

		}

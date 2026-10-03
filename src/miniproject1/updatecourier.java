package miniproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.pureconnect.mycon;

public class updatecourier extends JFrame implements ActionListener, ItemListener{
/*
		JLabel jl1, jl2, jl3;
	    JTextField jt1;
	    JComboBox jcb1;
	    JButton jb1, jb2;

	    String status;

	    public updatecourier()
	    {
	        jl1 = new JLabel("UPDATE COURIER");
	        jl2 = new JLabel("Enter Tracking ID:");
	        jl3 = new JLabel("Select Status:");

	        jt1 = new JTextField();

	        Vector v = new Vector();

	        v.add("Booked");
	        v.add("Picked Up");
	        v.add("In Transit");
	        v.add("Out for Delivery");
	        v.add("Delivered");

	        jcb1 = new JComboBox(v);

	        jb1 = new JButton("Update");
	        jb2 = new JButton("Back");

	        setLayout(null);

	        jl1.setBounds(220, 40, 200, 40);

	        jl2.setBounds(80, 120, 140, 30);
	        jt1.setBounds(230, 120, 200, 30);

	        jl3.setBounds(80, 180, 140, 30);
	        jcb1.setBounds(230, 180, 200, 30);

	        jb1.setBounds(150, 270, 100, 30);
	        jb2.setBounds(280, 270, 100, 30);

	        add(jl1);
	        add(jl2);
	        add(jt1);
	        add(jl3);
	        add(jcb1);
	        add(jb1);
	        add(jb2);

	        jb1.addActionListener(this);
	        jb2.addActionListener(this);

	        jcb1.addItemListener(this);
	    }

	    @Override
	    public void itemStateChanged(ItemEvent ie)
	    {
	        if(ie.getSource().equals(jcb1))
	        {
	            status = (String) jcb1.getSelectedItem();
	        }
	    }

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        // Update button
	        if(e.getSource().equals(jb1))
	        {
	            String trackingid = jt1.getText().trim();

	            if(trackingid.equals(""))
	            {
	                JOptionPane.showMessageDialog(this, "Please enter Tracking ID");

	                return;
	            }

	            try
	            {
	                //Class.forName("com.mysql.cj.jdbc.Driver");

	                //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Bandla@123");
	            	Connection con=mycon.myownconnection();
	                PreparedStatement pst = con.prepareStatement("update bookeddetails "+ "set status=? " + "where trackingid=?");

	                pst.setString(1, status);
	                pst.setString(2, trackingid);

	                int x = pst.executeUpdate();

	                if(x > 0)
	                {
	                    JOptionPane.showMessageDialog(this,"Courier Status Updated Successfully");
	                }
	                else
	                {
	                    JOptionPane.showMessageDialog(this, "Tracking ID Not Found");
	                }

	                pst.close();
	                con.close();
	            }
	            catch(Exception ee)
	            {
	    	                ee.printStackTrace();
	            }
	        }

	        // Back button
	        if(e.getSource().equals(jb2))
	        {
	            dispose();
	        }
	    }
	    */

    JLabel jl1, jl2, jl3;

    JTextField jt1;

    JComboBox jcb1;

    JButton jb1, jb2, jb3;

    String status;

    public updatecourier() {

        // =========================
        // LABELS
        // =========================

        jl1 = new JLabel("UPDATE COURIER");
        jl2 = new JLabel("Enter Tracking ID:");
        jl3 = new JLabel("Select Status:");

        // =========================
        // TEXT FIELD
        // =========================

        jt1 = new JTextField();

        // =========================
        // STATUS COMBOBOX
        // =========================

        Vector v = new Vector();

        v.add("Booked");
        v.add("Picked Up");
        v.add("In Transit");
        v.add("Out for Delivery");
        v.add("Delivered");

        jcb1 = new JComboBox(v);

        status = "Booked";

        // =========================
        // BUTTONS
        // =========================

        jb1 = new JButton("Update");
        jb2 = new JButton("Back");
        jb3 = new JButton("View Updated Details");

        // =========================
        // FRAME
        // =========================

        setTitle("Track Courier");

        setSize(700, 600);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =========================
        // SET BOUNDS
        // =========================

        jl1.setBounds(220, 40, 250, 40);

        jl2.setBounds(80, 120, 140, 30);

        jt1.setBounds(230, 120, 300, 30);

        jl3.setBounds(80, 180, 140, 30);

        jcb1.setBounds(230, 180, 300, 30);

        jb1.setBounds(150, 270, 150, 40);

        jb2.setBounds(330, 270, 150, 40);

        jb3.setBounds(220, 350, 220, 40);

        // =========================
        // ADD COMPONENTS
        // =========================

        add(jl1);
        add(jl2);
        add(jt1);
        add(jl3);
        add(jcb1);
        add(jb1);
        add(jb2);
        add(jb3);

        // =========================
        // ACTION LISTENERS
        // =========================

        jb1.addActionListener(this);

        jb2.addActionListener(this);

        jb3.addActionListener(this);

        jcb1.addItemListener(this);

        // =========================
        // SHOW FRAME
        // =========================

        setVisible(true);
    }

    // =====================================================
    // COMBO BOX
    // =====================================================

    @Override
    public void itemStateChanged(ItemEvent ie) {

        if (ie.getSource().equals(jcb1)) {

            if (ie.getStateChange() == ItemEvent.SELECTED) {

                status = (String) jcb1.getSelectedItem();
            }
        }
    }

    // =====================================================
    // ACTION PERFORMED
    // =====================================================

    @Override
    public void actionPerformed(ActionEvent e) {

        // =================================================
        // UPDATE BUTTON
        // =================================================

        if (e.getSource().equals(jb1)) {

            String trackingid =
                    jt1.getText().trim();

            if (trackingid.equals("")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Tracking ID"
                );

                return;
            }

            try {

                Connection con =
                        mycon.myownconnection();

                String sql =
                        "UPDATE Bookeddetails "
                        + "SET status=? "
                        + "WHERE trackingid=?";

                PreparedStatement pst =
                        con.prepareStatement(sql);

                pst.setString(1, status);

                pst.setString(2, trackingid);

                int x =
                        pst.executeUpdate();

                if (x > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Courier Status Updated Successfully"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Tracking ID Not Found"
                    );
                }

                pst.close();

                con.close();

            } catch (Exception ee) {

                ee.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ee.getMessage()
                );
            }
        }

        // =================================================
        // BACK BUTTON
        // =================================================

        if (e.getSource().equals(jb2)) {

            dispose();

            // If your home page class is homepage,
            // use this:
            //
            // new homepage();
        }

        // =================================================
        // VIEW UPDATED DETAILS
        // =================================================

        if (e.getSource().equals(jb3)) {

            viewUpdatedDetails();
        }
    }

    // =====================================================
    // VIEW UPDATED DETAILS
    // =====================================================
    public void viewUpdatedDetails() {

        String trackingid = jt1.getText().trim();

        if (trackingid.equals("")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Tracking ID first"
            );

            return;
        }

        JFrame frame = new JFrame("Updated Courier Details");

        frame.setSize(700, 400);
        frame.setLocationRelativeTo(this);
        frame.setLayout(null);

        String[] columns = {
                "Tracking ID",
                "Customer Name",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table =
                new JTable(model);

        table.setRowHeight(30);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                30,
                30,
                620,
                250
        );

        frame.add(scrollPane);

        JButton close =
                new JButton("Close");

        close.setBounds(
                280,
                300,
                120,
                35
        );

        frame.add(close);

        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });

        // =========================
        // GET ONLY UPDATED RECORD
        // =========================

        try {

            Connection con =
                    mycon.myownconnection();

            String sql =
                    "SELECT trackingid, sname, status "
                    + "FROM Bookeddetails "
                    + "WHERE trackingid=?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, trackingid);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                model.addRow(
                        new Object[] {

                                rs.getString("trackingid"),

                                rs.getString("sname"),

                                rs.getString("status")
                        }
                );

                frame.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Tracking ID Not Found"
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error while loading details:\n"
                    + ex.getMessage()
            );
        }
    }

}

package miniproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.pureconnect.mycon;

public class trackcourier extends JFrame implements ActionListener{
	
	JLabel jl1, jl2, jl3,jl0; 
	JTextField jt1; 
	JButton jb1, jb2; 
	public trackcourier() 
	{ 

		ImageIcon ii1=new ImageIcon("C:\\Users\\revat\\OneDrive\\Documents\\Desktop\\image.jpg");
		jl0=new JLabel(ii1);
		
		jl1 = new JLabel("TRACK COURIER"); 
		jl2 = new JLabel("Enter Tracking ID:"); 
		jl3 = new JLabel("Status:"); 
		jt1 = new JTextField();
		jb1 = new JButton("Track"); 
		jb2 = new JButton("Back"); 
		setLayout(null); 
		setContentPane(jl0);
		jl1.setBounds(230, 20, 200, 40);
		jl2.setBounds(80, 100, 140, 30);
		jt1.setBounds(230, 100, 200, 30); 
		jl3.setBounds(80, 150, 350, 30); 
		jb1.setBounds(150, 250, 100, 30); 
		jb2.setBounds(280, 250, 100, 30); 
		add(jl1); 
		add(jl2);
		add(jt1);
		add(jl3);
		add(jb1);
		add(jb2); 
		jb1.addActionListener(this); 
		jb2.addActionListener(this); 
		
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(jb1))
		{ 
			String trackingid = jt1.getText().trim();
			System.out.println("Entered ID = " + trackingid);
			if(trackingid.equals("")) 
			{ 
				JOptionPane.showMessageDialog( this, "Please enter Tracking ID"); 
				return; 
			}
			try 
			{
				//Class.forName("com.mysql.cj.jdbc.Driver");
				//Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Bandla@123");
				Connection con=mycon.myownconnection();
				String number = trackingid.substring(2); 
				int id = Integer.parseInt(number) - 1000; 
		        PreparedStatement pst = con.prepareStatement( "select status from bookeddetails where id=?");
		        pst.setInt(1, id);
		        ResultSet rs = pst.executeQuery(); 
		        if (rs.next()) {

		            String status = rs.getString("status");

		            System.out.println("Status = " + status);

		            // Show status on the page
		            jl3.setText("Status : " + status);

		            // Show status in dialog box
		            JOptionPane.showMessageDialog(
		                this,
		                "Tracking ID: " + trackingid
		                + "\nStatus: " + status,
		                "Courier Status",
		                JOptionPane.INFORMATION_MESSAGE
		            );

		        } 
		        else {

		            JOptionPane.showMessageDialog(
		                this,
		                "Tracking ID Not Found",
		                "Courier Status",
		                JOptionPane.WARNING_MESSAGE
		            );

		            jl3.setText("Status:");
		        }
		        if(e.getSource().equals(jb1))
		        {
		            String trackingid1 = jt1.getText().trim();

		            System.out.println("Entered ID = " + trackingid);

		            if(trackingid.equals(""))
		            {
		                JOptionPane.showMessageDialog(
		                    this,
		                    "Please enter Tracking ID"
		                );

		                return;
		            }

		            try
		            {
		                Connection con1 =
		                    mycon.myownconnection();

		                String number1 =
		                    trackingid.substring(2);

		                int id1 =
		                    Integer.parseInt(number1) - 1000;

		                PreparedStatement pst1 =
		                    con1.prepareStatement(
		                        "SELECT status FROM Bookeddetails WHERE id=?"
		                    );

		                pst1.setInt(1, id1);

		                ResultSet rs1 =
		                    pst1.executeQuery();

		                if(rs1.next())
		                {
		                    String status =
		                        rs1.getString("status");

		                    System.out.println(
		                        "Status = " + status
		                    );

		                    // Show on page
		                    jl3.setText(
		                        "Status : " + status
		                    );

		                    // Show dialog box
		                    JOptionPane.showMessageDialog(
		                        this,
		                        "Tracking ID: " + trackingid
		                        + "\nStatus: " + status,
		                        "Courier Status",
		                        JOptionPane.INFORMATION_MESSAGE
		                    );
		                }
		                else
		                {
		                    JOptionPane.showMessageDialog(
		                        this,
		                        "Tracking ID Not Found",
		                        "Courier Status",
		                        JOptionPane.WARNING_MESSAGE
		                    );

		                    jl3.setText("Status:");
		                }

		                rs1.close();
		                pst1.close();
		                con1.close();
		            }
		            catch(Exception ee)
		            {
		                ee.printStackTrace();

		                JOptionPane.showMessageDialog(
		                    this,
		                    "Error: " + ee.getMessage(),
		                    "Database Error",
		                    JOptionPane.ERROR_MESSAGE
		                );
		            }
		        }
		        con.close(); 
			} 
			catch (Exception ee) 
			{ 
				ee.printStackTrace(); 
			}
		}
	
		
		if(e.getSource().equals(jb2)) { 
			dispose(); 
			}
		
	}

}

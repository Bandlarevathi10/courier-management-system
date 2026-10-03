package miniproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pureconnect.mycon;

public class couriersetails extends JFrame implements ActionListener{
	    JTable jt;
	    JScrollPane jsp;
	    JButton jb1;

	    public couriersetails()
	    {
	        setLayout(null);

	        jb1 = new JButton("Back");

	        String data[][] = new String[50][13];

	        String column[] = {
	                "ID",
	                "Sender Name",
	                "Sender Phone",
	                "Pickup",
	                "Receiver Name",
	                "Receiver Phone",
	                "Drop Address",
	                "Weight",
	                "Date",
	                "Package Type",
	                "Delivery Type",
	                "Tracking ID",
	                "Status"
	        };

	        try
	        {
	            //Class.forName("com.mysql.cj.jdbc.Driver");

	            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Bandla@123");
	        	Connection con=mycon.myownconnection();
	            Statement st = con.createStatement();

	            ResultSet rs = st.executeQuery("select * from bookeddetails");

	            int i = 0;

	            while(rs.next())
	            {
	                data[i][0] = rs.getString("id");
	                data[i][1] = rs.getString("sname");
	                data[i][2] = rs.getString("sphnno");
	                data[i][3] = rs.getString("pickup");
	                data[i][4] = rs.getString("rname");
	                data[i][5] = rs.getString("rphnno");
	                data[i][6] = rs.getString("dropaddr");
	                data[i][7] = rs.getString("weight");
	                data[i][8] = rs.getString("date");
	                data[i][9] = rs.getString("packagetype");
	                data[i][10] = rs.getString("deliverytype");
	                data[i][11] = rs.getString("trackingid");
	                data[i][12] = rs.getString("status");

	                i++;
	            }

	            rs.close();
	            st.close();
	            con.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

	        jt = new JTable(data, column);

	        jsp = new JScrollPane(jt);

	        jsp.setBounds(20, 30, 950, 400);

	        jb1.setBounds(430, 460, 100, 30);

	        add(jsp);
	        add(jb1);

	        jb1.addActionListener(this);
	    }

	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		if(ae.getSource().equals(jb1))
        {
            dispose();
        }

		
	}

}

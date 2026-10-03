package miniproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.pureconnect.mycon;

public class loginpage extends JFrame implements ActionListener{
	
	private JLabel jl1,jl2,jl3,jl4,jl5;
	private JTextField jt1;
	private JPasswordField jp1;
	private JButton jb1,jb2,jb3;
	public loginpage()
	{
		ImageIcon ii1=new ImageIcon("C:\\Users\\revat\\OneDrive\\Documents\\Desktop\\image2.jpg");
		jl4=new JLabel(ii1);
		//System.out.println(ii1.getImage());
		jl1=new JLabel("WELCOME TO COURIER MANAGEMENT SYSTEM");
		jl2=new JLabel("username :");
		jl3=new JLabel("Password :");
		jl5=new JLabel("Dont have account ? register");
		jt1=new JTextField();
		jp1=new JPasswordField();
		jb1=new JButton("Login");
		jb2=new JButton("clear");
		jb3=new JButton("Register");
		setLayout(null);
		setContentPane(jl4);
		jl1.setBounds(100,20,350,150);
		jl2.setBounds(150,100,120,80);
		jl3.setBounds(150,150,120,80);
		jl5.setBounds(280,290,200,80);
		
		jt1.setBounds(250,130,80,20);
		jp1.setBounds(250,180,80,20);
		
		jb1.setBounds(300,250,80,20);
		jb2.setBounds(150,250,80,20);
		jb3.setBounds(300,350,120,20);
		
		add(jl1);
		add(jl2);
		add(jl3);
		add(jt1);
		add(jp1);
		add(jb1);
		add(jb2);
		add(jb3);
		add(jl5);
		jb3.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jb1))
		{
			Vector v=new Vector();
			//step-1 taking db data into java location
				try
				{
					//Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Bandla@123");
					
					Connection con=mycon.myownconnection();
					
					
					PreparedStatement pst=con.prepareStatement("select username,pass from details");
					ResultSet rs=pst.executeQuery();
					for(;rs.next();)
					{
						v.add(rs.getString("username"));
						v.add(rs.getString("pass"));
						
					}
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
			//step-2taking ui_data(email,pass)data
				String u_username=jt1.getText();
				String u_pass=jp1.getText();
	
			//step3=comparing 
				
				if(v.contains(u_username)&&v.contains(u_pass))
				{
					homepage hp=new homepage();
					hp.setTitle("sign page");
					hp.setSize(1000,700);
					hp.setVisible(true);
					
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invalid credentials");
				}
				
			
		}
		
		if(e.getSource().equals(jb3))
		{
		registerpage l=new registerpage();
		l.setTitle("sign page");
		l.setSize(800,800);
		l.setVisible(true);
		}
		if(e.getSource().equals(jb2))
		{
			jt1.setText("");
            jp1.setText("");
		}
	}

	

}

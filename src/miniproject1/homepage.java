package miniproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class homepage extends JFrame implements ActionListener {

	
	
	JLabel title,jl2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7; 
	
	public homepage() 
	{ 
		//Title
		ImageIcon ii1=new ImageIcon("C:\\Users\\revat\\OneDrive\\Documents\\Desktop\\home.jpg");
		jl2=new JLabel(ii1);
		
		title = new JLabel("Courier Management System");
		title.setFont(new Font("Arial", Font.BOLD, 24));
		title.setBounds(120, 40, 400, 40); 
		title.setForeground(Color.WHITE);
		// Buttons
		jb1= new JButton("Book Courier"); 
		jb2 = new JButton("Track Courier"); 
		jb3 = new JButton("Courier Details"); 
		jb4 = new JButton("Update Courier"); 
		jb5 = new JButton("Cancel Order");
		jb6=new JButton("Logout");
		jb7=new JButton("Payment details");
		// Button positions 
		jb1.setBounds(250, 120, 200, 40); 
		jb2.setBounds(250, 180, 200, 40); 
		jb3.setBounds(250, 240, 200, 40); 
		jb4.setBounds(250, 300, 200, 40); 
		jb5.setBounds(250, 360, 200, 40); 
		jb6.setBounds(250, 480, 200,40);
		jb7.setBounds(250,420,200,40);
		// Add action listeners
		
		// Layout 
		setLayout(null);
		setContentPane(jl2);
		// Add components 
		add(title); 
		add(jb1); 
		add(jb2); 
		add(jb3); 
		add(jb4); 
		add(jb5); 
		add(jb6);
		add(jb7);

		// Frame settings 
		setTitle("homepage "); 
		setSize(500, 500); 
		setVisible(true); 
		
		jb1.addActionListener(this);
		jb2.addActionListener(this); 
		jb3.addActionListener(this); 
		jb4.addActionListener(this); 
		jb5.addActionListener(this); 
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) 
		{ 
			booking b=new booking();
			b.setTitle("booking page");
			b.setSize(800,800);
			b.setVisible(true);
		} else if (e.getSource() == jb2) { 
			trackcourier t =new trackcourier();
            t.setTitle("Track Courier");
            t.setSize(600, 400);
            t.setVisible(true);
		} else if (e.getSource() == jb3) { 
			couriersetails cd =new couriersetails();
            cd.setTitle("Track Courier");
            cd.setSize(1000,600);
            cd.setVisible(true);
					// Later: // new CourierDetails(); 
		} else if (e.getSource() == jb4) { 
			updatecourier uc =new updatecourier();
            uc.setTitle("Track Courier");
            uc.setSize(1000,600);
            uc.setVisible(true);

		} else if (e.getSource() == jb6) { 
							JOptionPane.showMessageDialog(this, "Logged out successfully"); 
							dispose(); // Later: // new LoginPage(); 
		}else if   (e.getSource()==jb5)
		{
			 cancelorder co = new cancelorder();

	            co.setTitle("Cancel Order");

	            co.setSize(600, 450);

	            co.setVisible(true);
		}
		if (e.getSource().equals(jb7)) {

		    new paymentdetails();

		}
		}
	

}

package miniproject1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.pureconnect.mycon;

public class registerpage extends JFrame implements ActionListener,ItemListener{

    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7,jl8,jl9,jl10;
    private JTextField jt1, jt2, jt3, jt4;
    private JPasswordField jp1, jp2;
    private JComboBox jcb;
    private JButton jb1, jb2;
    private JRadioButton jr1,jr2,jr3;
    String address;
    String gender;
    public registerpage() {
    	ImageIcon ii1=new ImageIcon("C:\\Users\\revat\\OneDrive\\Documents\\Desktop\\image1.jpg");
		jl9=new JLabel(ii1);
        jl1 = new JLabel("CREATE ACCOUNT");
        jl2 = new JLabel("First Name       :");
        jl3 = new JLabel("Last Name        :");
        jl4 = new JLabel("username         :");
        jl5 = new JLabel("Password         :");
        jl6 = new JLabel("Conpass          :");
        jl7 = new JLabel("Phone            :");
        jl10=new JLabel(" gender            :");
        jl8 = new JLabel("Address          :");

        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        jt4 = new JTextField();

        jp1 = new JPasswordField();
        jp2 = new JPasswordField();
        
        jr1=new JRadioButton("Male");
		jr2=new JRadioButton("Female");
		jr3=new JRadioButton("Other");

        jb1 = new JButton("Clear");
        jb2 = new JButton("Submit");
        
        
        
        Vector v=new Vector();
		v.add("Guntur");
		v.add("Vijayawada");
		v.add("Hyderabad");
		v.add("Banglore");
		jcb=new JComboBox(v);
		
        setLayout(null);
    	setContentPane(jl9);

        jl1.setBounds(220, 40, 200, 40);

        jl2.setBounds(80, 100, 120, 30);
        jl3.setBounds(80, 150, 120, 30);
        jl4.setBounds(80, 200, 120, 30);
        jl5.setBounds(80, 250, 120, 30);
        jl6.setBounds(80, 300, 120, 30);
        jl7.setBounds(80, 350, 120, 30);
        jl8.setBounds(80,420,120,80);
        jl10.setBounds(80,400,120,30);

        jt1.setBounds(220, 100, 200, 30);
        jt2.setBounds(220, 150, 200, 30);
        jt3.setBounds(220, 200, 200, 30);

        jp1.setBounds(220, 250, 200, 30);
        jp2.setBounds(220, 300, 200, 30);
        
        jr1.setBounds(220,400,80,30);
		jr2.setBounds(300,400,80,30);
		jr3.setBounds(380,400,80,30);

        jt4.setBounds(220, 350, 200, 30);
        
        jcb.setBounds(220,440,200,30);
        
        

        jb1.setBounds(150, 500, 100, 30);
        jb2.setBounds(280, 500, 100, 30);

        add(jl1);
        add(jl2);
        add(jl3);
        add(jl4);
        add(jl5);
        add(jl6);
        add(jl7);
        add(jl8);
       // add(jl9);
        add(jl10);
        
        add(jt1);
        add(jt2);
        add(jt3);
        add(jt4);

        add(jp1);
        add(jp2);
        
        add(jr1);
		add(jr2);
		add(jr3);

        add(jb1);
        add(jb2);
        add(jcb);
        
        jl1.setForeground(Color.WHITE);
		jl2.setForeground(Color.WHITE);
		jl3.setForeground(Color.WHITE);
		jl4.setForeground(Color.WHITE);
		jl5.setForeground(Color.WHITE);
		jl6.setForeground(Color.WHITE);
		jl7.setForeground(Color.WHITE);
		jl8.setForeground(Color.WHITE);
		jl9.setForeground(Color.WHITE);
		jl10.setForeground(Color.WHITE);
		
        jr1.addActionListener(this);
		jr2.addActionListener(this);
		jr3.addActionListener(this);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jcb.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
	
		if(ie.getSource().equals(jcb))
		{
			address=(String)jcb.getSelectedItem();
		}
	}
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(jb1)) {

            jt1.setText("");
            jt2.setText("");
            jt3.setText("");
            jt4.setText("");
            jp1.setText("");
            jp2.setText("");
        }
        if(e.getSource().equals(jr1))
		{
			//disselectingjr2,jr3
			jr2.setSelected(false);
			jr3.setSelected(false);
			gender=jr1.getText();
		}
		if(e.getSource().equals(jr2))
		{
			jr1.setSelected(false);
			jr3.setSelected(false);
			gender=jr2.getText();
		}
		if(e.getSource().equals(jr3))
		{
			jr2.setSelected(false);
			jr1.setSelected(false);
			gender=jr3.getText();
		}

        if (e.getSource().equals(jb2)) {

            String fname = jt1.getText();
            String lname = jt2.getText();
            String username = jt3.getText();
            String pass = jp1.getText();
            String conpass = jp2.getText();
            String sphnno = jt4.getText().trim();

            if (!sphnno .matches("[0-9]{10}")) {

            	JOptionPane.showMessageDialog(
            			this,
            			"Phone number must contain exactly 10 digits.");

            	return;
            }

            if (!pass.equals(conpass)) {

            	JOptionPane.showMessageDialog(
            			this,
            			"Passwords do not match");

            	return;
            }

            try {

            	//Class.forName("com.mysql.cj.jdbc.Driver");
				//Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Bandla@123");
     
            	Connection con=mycon.myownconnection();
                PreparedStatement pst = con.prepareStatement("insert into details values(?,?,?,?,?,?,?,?)");

                pst.setString(1, fname);
                pst.setString(2, lname);
                pst.setString(3, username);
                pst.setString(4, pass);
                pst.setString(5, conpass);
                pst.setString(6,sphnno );
                pst.setString(7,gender);
                pst. setString(8,address);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,"Successfully Registered");

                dispose();

            } catch (Exception ee) {

                ee.printStackTrace();
            }
        }
    }

}

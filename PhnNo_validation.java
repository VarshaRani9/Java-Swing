import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class PhnNo_validation {

	JFrame f;
	JLabel l;
	JTextField tf;
	JButton  bt1,bt2;
	PhnNo_validation(){
		f = new JFrame();
		l=new JLabel("Phone number");
		tf=new JTextField();
		bt1=new JButton("Submit");
		bt2= new JButton("Clear");
		
		l.setBounds(0, 0, 80, 30);
		tf.setBounds(200, 0, 100, 30);
		bt1.setBounds(20, 100, 100, 30);
		bt2.setBounds(200, 100, 100, 30);
	
		f.getContentPane().add(l);
		f.getContentPane().add(tf);
		f.getContentPane().add(bt1);
		f.getContentPane().add(bt2);
		f.setLayout(null);
		f.setVisible(true);
		f.setSize(500,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count=0;
				String s= tf.getText();
				if(s.length()!=10 || s.charAt(0)==0) {
					count++;
				}
				else
				for(int i=0;i<s.length();i++) {
					if(s.charAt(i)>57 || s.charAt(i)<48  ) {
						count++;
					}
				}
				if(count==0) 
				JOptionPane.showMessageDialog(f, "phone no. is registered successffuly");
				else 
					JOptionPane.showMessageDialog(f, "incorrect phone number");
			}
		});
		bt2.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
			tf.setText("");
			}
		});
		
	}
	public static void main(String[] args) {
		new PhnNo_validation();
	}

}

// importing the required packages
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

//Creating a class which is implementing ActionListener
public class linList implements ActionListener {

	static LinkedList<Integer> list;
	static Stack<Integer> list1;
	static ArrayDeque<Integer> list2;

	JFrame frame;
	JLabel l1,l2;
	JRadioButton stackbutton, queuebutton;
	JButton push, pop, add, del;
	JTextField tff,tfff;

	int max = 50;
	int min = 0;
	int range = 50 - 0 + 1;

	// constructor
	linList() {
		frame = new JFrame();

		l1=new JLabel("original list ->");
		l1.setBounds(25,2,200,20);
		tff = new JTextField();
		tff.setEditable(false);
		tff.setText(list + " ");
		l2=new JLabel("modified list ->");
		l2.setBounds(25,220,200,20);
		tfff = new JTextField();
		tfff.setEditable(false);
		tfff.setBounds(25, 250, 400, 20);
		tfff.setText(list + " ");

		stackbutton = new JRadioButton("Stack");
		stackbutton.setBounds(25, 80, 100, 20);
		queuebutton = new JRadioButton("Queue");
		queuebutton.setBounds(250, 80, 100, 20);
		
		//creating button group for working of radio buttons
		ButtonGroup grp = new ButtonGroup();
		grp.add(stackbutton);
		stackbutton.addActionListener(this);
		grp.add(queuebutton);
		queuebutton.addActionListener(this);

		push = new JButton("Push");
		push.setVisible(false);
		push.setBounds(25, 120, 100, 20);
		pop = new JButton("Pop");
		pop.setVisible(false);
		pop.setBounds(25, 170, 100, 20);
		add = new JButton("Add");
		add.setVisible(false);
		add.setBounds(250, 120, 100, 20);
		del = new JButton("Del");
		del.setVisible(false);
		del.setBounds(250, 170, 100, 20);
		
		//Setting font to buttons
		push.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        pop.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        add.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        del.setFont(new Font("Times_New_Roman",Font.BOLD,20));

        //Setting background colour of buttons
        push.setBackground(Color.magenta);
        pop.setBackground(Color.magenta);
        add.setBackground(Color.green);
        del.setBackground(Color.green);


		// Adding ActionListener to buttons
		push.addActionListener(this);
		pop.addActionListener(this);
		add.addActionListener(this);
		del.addActionListener(this);

		// Adding components to the frame
		frame.add(l1);
		frame.add(l2);
		frame.add(push);
		frame.add(pop);
		frame.add(add);
		frame.add(del);
		frame.add(tff);
		frame.add(tfff);
		frame.add(stackbutton);
		frame.add(queuebutton);
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stackbutton) {
			list1 = new Stack<Integer>();
			list1.addAll(list);
			push.setVisible(true);
			pop.setVisible(true);
			add.setVisible(true);
			del.setVisible(true);


		}
		if (e.getSource() == queuebutton) {
			list2 = new ArrayDeque<Integer>();
			list2.addAll(list);
			add.setVisible(true);
			del.setVisible(true);
			push.setVisible(true);
			pop.setVisible(true);
			
		}

		if (e.getSource() == push) {
			list1.push((int) (Math.random() * range) + min);
			tfff.setText(list1 + " ");
		}

		if (e.getSource() == pop) {

			try {
				list1.pop();

			} catch (EmptyStackException e1) {
				System.out.println(e1);
				System.out.println("first put any element in stack !!");
			}

			tfff.setText(list1 + " ");
		}

		if (e.getSource() == add) {
			list2.addFirst((int) (Math.random() * range) + min);
			tfff.setText(list2 + " ");
		}
		if (e.getSource() == del) {
			try {
				list2.removeFirst
				();
			} catch (NoSuchElementException e1) {
				System.out.println(e1);
				System.out.println("first put any element in queue !!");
			}
			tfff.setText(list2 + " ");
		}
	}

	public static void main(String[] args) {
		linList obj = new linList();
		list = new LinkedList<Integer>();
		
		// generating 10 random elements and adding them to the linked list
		for (int i = 0; i < 10; i++) {
			list.add((int) (Math.random() * obj.range) + obj.min);
		}
		
		// printing the elements of linkedList
		System.out.println(list);
		
        // calling constuctor
		new linList();
	}

}

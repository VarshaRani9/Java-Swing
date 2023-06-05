import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//Creating class
public class SchedulingAlgo {
	JFrame frame1, frame2;
	JLabel label1, label2, label3, label4, label5, label6;
	JLabel l1, l2;
	JComboBox<String> combobox;
	JTextField tf1, tf2, tf3, tf4;
	JButton button;
	JTable jt;
	JLabel lll;
	static int AT[], BT[], job[];
	static int CT[], TAT[], WT[];
	static float avgwt = 0, avgta = 0;
	static int i = 0;

	//Constructor
	SchedulingAlgo() {

		String[] algo = { "FCFS", "SJF", "SRTF" };
		
		//creating instances of each component
		frame1 = new JFrame();
		label1 = new JLabel("Input");
		label1.setBounds(5, 5, 100, 30);
		label2 = new JLabel("Algorithm");
		label2.setBounds(5, 20, 100, 30);
		
		combobox = new JComboBox<String>(algo);
		combobox.setBounds(5, 70, 100, 30);
		
		label3 = new JLabel("Arrival Times of 5 processes :");
		label3.setBounds(5, 110, 100, 60);
		tf1 = new JTextField();
		tf1.setBounds(5, 150, 100, 30);
		label4 = new JLabel("Burst Times of 5 processes ");
		label4.setBounds(5, 200, 100, 60);
		tf2 = new JTextField();
		tf2.setBounds(5, 240, 100, 30);
		
		button = new JButton("Solve");
		button.setBounds(5, 280, 100, 30);

		//Adding Components to frame1
		frame1.add(label1);
		frame1.add(label2);
		frame1.add(combobox);
		frame1.add(label3);
		frame1.add(tf1);
		frame1.add(label4);
		frame1.add(tf2);
		frame1.add(button);
		
		frame1.setSize(500, 500);
		frame1.setLayout(null);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Adding ActionListener to solve button
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame2 = new JFrame();
				label5 = new JLabel();
				Object item = combobox.getSelectedItem();
				label5.setText((String) item);

				String[] t1 = tf1.getText().split(" ");
				String[] t2 = tf2.getText().split(" ");
				int AT[] = new int[10];
				int BT[] = new int[10];
				int CT[] = new int[10];
				int TAT[] = new int[10];
				int WT[] = new int[10];
				int job[] = new int[10];

				for (i = 0; i < t1.length; i++) {
					try {
						AT[i] = Integer.parseInt(t1[i]);
						BT[i] = Integer.parseInt(t2[i]);
						job[i] = i + 1;
					} catch (NumberFormatException e2) {
						System.out.println(e2);
					}
				}
				

				// FCFS Scheduling Algorithm -> 

				if ((String) item == "FCFS") {

					int temp;

					// sorting according to arrival times
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5 - (i + 1); j++) {
							if (AT[j] > AT[j + 1]) {
								temp = AT[j];
								AT[j] = AT[j + 1];
								AT[j + 1] = temp;
								temp = BT[j];
								BT[j] = BT[j + 1];
								BT[j + 1] = temp;
								temp = job[j];
								job[j] = job[j + 1];
								job[j + 1] = temp;
							}
						}
					}
					
					// finding completion times
					for (int i = 0; i < 5; i++) {
						if (i == 0) {
							CT[i] = AT[i] + BT[i];
						} else {
							if (AT[i] > CT[i - 1]) {
								CT[i] = AT[i] + BT[i];
							} else
								CT[i] = CT[i - 1] + BT[i];
						}
						TAT[i] = CT[i] - AT[i]; // turnaround time= completion time- arrival time
						WT[i] = TAT[i] - BT[i]; // waiting time= turnaround time- burst time
						avgwt += WT[i]; // total waiting time
						avgta += TAT[i]; // total turnaround time
					}

				}
				
				
				// SJF Scheduling Algorithm ->

				if ((String) item == "SJF") {
					int st = 0, tot = 0;
					int f[] = new int[10]; // f means it is flag it checks process is completed or not
					for (int i = 0; i < 6; i++) {
						f[i] = 0;
					}
					while (true) {
						int c = 5, min = 999;
						if (tot == 5) // total no of process = completed process loop will be terminated
							break;
						for (int i = 0; i < 5; i++) {

							if ((AT[i] <= st) && (f[i] == 0) && (BT[i] < min)) {
								min = BT[i];
								c = i;
							}
						}
						/*
						 * If c==n means c value can not updated because no process arrival time < system
						 * time so we increase the system time
						 */
						if (c == 5)
							st++;
						else {
							CT[c] = st + BT[c];
							st += BT[c];
							TAT[c] = CT[c] - AT[c];
							WT[c] = TAT[c] - BT[c];
							f[c] = 1;
							tot++;
						}
					}

					for (int i = 0; i < 5; i++) {
						avgwt += WT[i];
						avgta += TAT[i];

					}
				}

				// SRTF Scheduling Algorithm ->

				if ((String) item == "SRTF") {
					int f[] = new int[10]; // f means it is flag it checks process is completed or not
					int k[] = new int[10]; // it is also stores brust time
					int i, st = 0, tot = 0;

					for (i = 0; i < 5; i++) {
						job[i] = i + 1;
						k[i] = BT[i];
						f[i] = 0;
					}

					while (true) {
						int min = 99, c = 5;
						if (tot == 5)
							break;

						for (i = 0; i < 5; i++) {
							if ((AT[i] <= st) && (f[i] == 0) && (BT[i] < min)) {
								min = BT[i];
								c = i;
							}
						}

						if (c == 5)
							st++;
						else {
							BT[c]--;
							st++;
							if (BT[c] == 0) {
								CT[c] = st;
								f[c] = 1;
								tot++;
							}
						}
					}

					for (i = 0; i < 5; i++) {
						TAT[i] = CT[i] - AT[i];
						WT[i] = TAT[i] - k[i];
						avgwt += WT[i];
						avgta += TAT[i];
					}

				}
			
				lll = new JLabel("   Job                    Arrival time   "
						+ "         burst time         completion time      turnaround time         waiting time");

				lll.setBounds(15, 150, 600, 30);
				label5.setBounds(400, 10, 50, 30);
//				label6=new JLabel("Gantt Chart");
//				label6.setBounds(200, 40, 100, 30);
				l1 = new JLabel("Average waiting time");
				l1.setBounds(100, 400, 150, 50);
				tf3 = new JTextField();
				tf3.setBounds(100, 440, 100, 30);
				l2 = new JLabel("Average turnaround time");
				l2.setBounds(300, 400, 150, 50);
				tf4 = new JTextField();
				tf4.setBounds(300, 440, 100, 30);

				DefaultTableModel model = new DefaultTableModel();
				jt = new JTable(model);
				model.addColumn("Job");
				model.addColumn("Arrival Time");
				model.addColumn("Burst Time");
				model.addColumn("Finish Time");
				model.addColumn("Turnaround Time");
				model.addColumn("Waiting Time");

				for (int i = 1; i <= 5; i++) {
					model.addRow((new Object[] { i, AT[i-1], BT[i-1], CT[i-1], TAT[i-1], WT[i-1] }));
				}

				tf3.setText(avgwt / 5 + " ");
				tf4.setText(avgta / 5 + " ");

				jt.setBounds(15, 200, 600, 100);
				
				//Adding Components to frame2
				frame2.add(lll);
				frame2.add(label5);
				// frame2.add(label6);
				frame2.add(l1);
				frame2.add(l2);
				frame2.add(tf3);
				frame2.add(tf4);
				frame2.add(jt);
				
				frame2.setSize(600, 650);
				frame2.setLayout(null);
				frame2.setVisible(true);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

	}
	

	public static void main(String[] args) {
		//calling constructor
		new SchedulingAlgo();
	}

}

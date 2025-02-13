package GUIExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class form1 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	String [] coloumNmaes = {"Name", "Age","Stage"};
	JTextField  text1,text2,text3;
	JLabel label1,label2,label3;
	JButton Submit;
	JTable tabel;
	JScrollPane scroll;
	DefaultTableModel model;
	
	
	form1(){	
		super("Regesting");
		
		label1 = new JLabel("Name:");
		label1.setBounds(10,10,50,20);
		
		text1 = new JTextField();
		text1.setBounds(95,10,70,20);
		
		
		label2 = new JLabel("Age:");
		label2.setBounds(10,35,50,20);
		
		text2 = new JTextField();
		text2.setBounds(95,35,70,20);
		
		label3 = new JLabel("Stage:");
		label3.setBounds(10,60,50,20);
		
		text3 = new JTextField();
		text3.setBounds(95,60,70,20);
		
		
		Submit = new JButton("submit");
		Submit.setBounds(10,95,75,30);
		Submit.addActionListener(this);
		
		
		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
		add(Submit);

		
		model = new DefaultTableModel(coloumNmaes, 0);
		tabel = new JTable(model);
		scroll = new JScrollPane(tabel);
		scroll.setBounds(120,125,250,100);
		add(scroll);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (text1.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"please Enter your name","are you stupid?!",JOptionPane.QUESTION_MESSAGE);
		} else {
			try {
				Integer.parseInt(text2.getText());
				Integer.parseInt(text3.getText());
				String name = text1.getText();
				int age = Integer.parseInt(text2.getText());
				int stage = Integer.parseInt(text3.getText());
				Object [] rowData = {name,age,stage};
				model.addRow(rowData);
				text1.setText("");
				text2.setText("");
				text3.setText("");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null,"please Enter a valid number","are you stupid?!",JOptionPane.QUESTION_MESSAGE);
			}
		}
		
	}

	public static void main(String[] args) {
		new form1();
	}
}

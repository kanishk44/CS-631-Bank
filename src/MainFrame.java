import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
	public MainFrame() {
		setSize(new Dimension(585, 400));
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 250, 205));
		setTitle("CS631 - Bank System");
		
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.setBounds(60, 104, 163, 71);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerAction ca = new CustomerAction();
				ca.setVisible(true);
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Generate Passbook");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchForm sf = new SearchForm(false, true);
				sf.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(60, 187, 163, 71);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Transaction");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionForm tf = new TransactionForm();
				tf.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(60, 269, 163, 71);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Welcome To CS631-Bank System");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(81, 11, 415, 82);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Create, Modify, Delete a customer");
		lblNewLabel_1.setBounds(272, 104, 238, 71);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Generate a passbook for a customer");
		lblNewLabel_2.setBounds(272, 208, 238, 28);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Generate a transaction for a customer");
		lblNewLabel_3.setBounds(272, 290, 238, 28);
		getContentPane().add(lblNewLabel_3);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new MainFrame();
	}
}

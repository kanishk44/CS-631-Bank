import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SearchForm extends JFrame {
	private JTextField firstName;
	private JTextField lastName;
	private JTextField ssn;
	private JTextField accountNo;

	public SearchForm(boolean modification, boolean passbook) {
		getContentPane().setBackground(new Color(255, 250, 205));
		setSize(new Dimension(585, 400));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer First Name");
		lblNewLabel.setBounds(91, 101, 163, 29);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Customer SSN");
		lblNewLabel_1.setBounds(91, 191, 163, 20);
		getContentPane().add(lblNewLabel_1);

		firstName = new JTextField();
		firstName.setBounds(264, 105, 158, 20);
		getContentPane().add(firstName);
		firstName.setColumns(10);

		JLabel lblCustomerLastName = new JLabel("Customer Last Name");
		lblCustomerLastName.setBounds(91, 141, 163, 29);
		getContentPane().add(lblCustomerLastName);

		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(264, 145, 158, 20);
		getContentPane().add(lastName);

		ssn = new JTextField();
		ssn.setColumns(10);
		ssn.setBounds(264, 191, 158, 20);
		getContentPane().add(ssn);

		JLabel lblNewLabel_2 = new JLabel("Search");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(232, 11, 86, 56);
		getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
					Connection con = DriverManager
							.getConnection("jdbc:mysql://localhost/CS631_Project_Fall2021?user=root&password=root");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(
							"select * from customer where ssn = \'" + ssn.getText() + "\' and firstname = \'"
									+ firstName.getText() + "\' and lastname = \'" + lastName.getText() + "\'");
					Statement stmt2 = con.createStatement();
					ResultSet acc = stmt2.executeQuery(
							"select * from myAccount where accountNo = " + Integer.parseInt(accountNo.getText()));
					if (rs.next() && acc.next()) {
						if (passbook) {
							PassbookForm pf = new PassbookForm(Integer.parseInt(accountNo.getText()));
							pf.setVisible(passbook);
						} else {
							CustomerBean result = new CustomerBean(rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
									rs.getString(1));
							String rate = acc.getString(5);
							AccountBean account = new AccountBean(acc.getInt(1), acc.getDouble(2), acc.getDate(3),
									acc.getInt(4), rate.substring(1), acc.getDouble(6), acc.getDouble(7));
							// System.out.println(account.toString());
							CustomerForm cf = new CustomerForm(result, account, rate.substring(0, 1));
							cf.setVisible(modification);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Customer/Account does not exist", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(229, 293, 89, 23);
		getContentPane().add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Account Number");
		lblNewLabel_3.setBounds(91, 243, 118, 14);
		getContentPane().add(lblNewLabel_3);

		accountNo = new JTextField();
		accountNo.setColumns(10);
		accountNo.setBounds(264, 244, 158, 20);
		getContentPane().add(accountNo);
	}
}

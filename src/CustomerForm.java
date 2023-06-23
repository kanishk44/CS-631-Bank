import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Date;

public class CustomerForm extends JFrame {
	private JTextField firstName;
	private JTextField lastName;
	private JTextField ssn;
	private JTextField streetNo;
	private JTextField aptNo;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JLabel lblNewLabel_3_2;
	private JTextField balanceField;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnLoan;
	private JRadioButton rdbtnChecking;
	private JRadioButton rdbtnSaving;
	private JLabel lblNewLabel_3_3;
	private JLabel lblNewLabel_3_4;
	private JTextField rateField;
	private JLabel lblNewLabel_3_5;
	private JLabel lblNewLabel_3_6;
	private JTextField loanAmountField;
	private JTextField loanPaymentField;

	public CustomerForm(CustomerBean searchResult, AccountBean acc, String accountType) {
		setTitle("Customer Form");
		setSize(new Dimension(585, 474));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		if (searchResult == null) {
			firstName = new JTextField();
			firstName.setBounds(107, 27, 148, 20);
			getContentPane().add(firstName);
			firstName.setColumns(10);

			lastName = new JTextField();
			lastName.setColumns(10);
			lastName.setBounds(392, 27, 148, 20);
			getContentPane().add(lastName);

			ssn = new JTextField();
			ssn.setColumns(10);
			ssn.setBounds(107, 65, 148, 20);
			getContentPane().add(ssn);

			streetNo = new JTextField();
			streetNo.setColumns(10);
			streetNo.setBounds(107, 109, 148, 20);
			getContentPane().add(streetNo);

			aptNo = new JTextField();
			aptNo.setColumns(10);
			aptNo.setBounds(107, 153, 148, 20);
			getContentPane().add(aptNo);

			city = new JTextField();
			city.setColumns(10);
			city.setBounds(43, 193, 94, 20);
			getContentPane().add(city);

			state = new JTextField();
			state.setColumns(10);
			state.setBounds(216, 193, 94, 20);
			getContentPane().add(state);

			zip = new JTextField();
			zip.setColumns(10);
			zip.setBounds(388, 193, 94, 20);
			getContentPane().add(zip);

			balanceField = new JTextField();
			balanceField.setColumns(10);
			balanceField.setBounds(107, 241, 148, 20);
			getContentPane().add(balanceField);

			rateField = new JTextField();
			rateField.setColumns(10);
			rateField.setBounds(107, 283, 148, 20);
			getContentPane().add(rateField);

			loanAmountField = new JTextField();
			loanAmountField.setColumns(10);
			loanAmountField.setBounds(107, 327, 148, 20);
			getContentPane().add(loanAmountField);

			loanPaymentField = new JTextField();
			loanPaymentField.setColumns(10);
			loanPaymentField.setBounds(107, 370, 148, 20);
			getContentPane().add(loanPaymentField);

			rdbtnNewRadioButton = new JRadioButton("Money Market");
			rdbtnNewRadioButton.setBounds(425, 266, 109, 23);
			getContentPane().add(rdbtnNewRadioButton);

			rdbtnLoan = new JRadioButton("Loan");
			rdbtnLoan.setBounds(425, 294, 109, 23);
			getContentPane().add(rdbtnLoan);

			rdbtnChecking = new JRadioButton("Checking");
			rdbtnChecking.setBounds(317, 266, 109, 23);
			getContentPane().add(rdbtnChecking);

			rdbtnSaving = new JRadioButton("Saving");
			rdbtnSaving.setBounds(317, 294, 109, 23);
			getContentPane().add(rdbtnSaving);

			ButtonGroup group = new ButtonGroup();
			group.add(rdbtnNewRadioButton);
			group.add(rdbtnLoan);
			group.add(rdbtnChecking);
			group.add(rdbtnSaving);
		} else {
			firstName = new JTextField(searchResult.getFirstName());
			firstName.setBounds(107, 27, 148, 20);
			getContentPane().add(firstName);
			firstName.setColumns(10);

			lastName = new JTextField(searchResult.getLastName());
			lastName.setColumns(10);
			lastName.setBounds(392, 27, 148, 20);
			getContentPane().add(lastName);

			ssn = new JTextField(searchResult.getSsn());
			ssn.setColumns(10);
			ssn.setBounds(107, 65, 148, 20);
			ssn.setEditable(false);
			getContentPane().add(ssn);

			streetNo = new JTextField(searchResult.getStreetNo());
			streetNo.setColumns(10);
			streetNo.setBounds(107, 109, 148, 20);
			getContentPane().add(streetNo);

			aptNo = new JTextField(searchResult.getAptNo());
			aptNo.setColumns(10);
			aptNo.setBounds(107, 153, 148, 20);
			getContentPane().add(aptNo);

			city = new JTextField(searchResult.getCity());
			city.setColumns(10);
			city.setBounds(43, 193, 94, 20);
			getContentPane().add(city);

			state = new JTextField(searchResult.getState());
			state.setColumns(10);
			state.setBounds(216, 193, 94, 20);
			getContentPane().add(state);

			zip = new JTextField(searchResult.getZip());
			zip.setColumns(10);
			zip.setBounds(388, 193, 94, 20);
			getContentPane().add(zip);

			balanceField = new JTextField(String.valueOf(acc.getBalance()));
			balanceField.setColumns(10);
			balanceField.setBounds(107, 241, 148, 20);
			balanceField.setEditable(false);
			getContentPane().add(balanceField);

			rateField = new JTextField(String.valueOf(acc.getRate()));
			rateField.setColumns(10);
			rateField.setBounds(107, 283, 148, 20);
			getContentPane().add(rateField);

			loanAmountField = new JTextField(String.valueOf(acc.getLoanAmount()));
			loanAmountField.setColumns(10);
			loanAmountField.setBounds(107, 327, 148, 20);
			getContentPane().add(loanAmountField);

			loanPaymentField = new JTextField(String.valueOf(acc.getLoanPayment()));
			loanPaymentField.setColumns(10);
			loanPaymentField.setBounds(107, 370, 148, 20);
			getContentPane().add(loanPaymentField);

			rdbtnNewRadioButton = new JRadioButton("Money Market");
			rdbtnNewRadioButton.setBounds(425, 266, 109, 23);
			rdbtnNewRadioButton.setEnabled(false);
			getContentPane().add(rdbtnNewRadioButton);

			rdbtnLoan = new JRadioButton("Loan");
			rdbtnLoan.setBounds(425, 294, 109, 23);
			rdbtnLoan.setEnabled(false);
			getContentPane().add(rdbtnLoan);

			rdbtnChecking = new JRadioButton("Checking");
			rdbtnChecking.setBounds(317, 266, 109, 23);
			rdbtnChecking.setEnabled(false);
			getContentPane().add(rdbtnChecking);

			rdbtnSaving = new JRadioButton("Saving");
			rdbtnSaving.setBounds(317, 294, 109, 23);
			rdbtnSaving.setEnabled(false);
			getContentPane().add(rdbtnSaving);

			if (accountType.equals("4")) {
				rdbtnChecking.setSelected(true);
			} else if (accountType.equals("1")) {
				rdbtnNewRadioButton.setSelected(true);
			} else if (accountType.equals("2")) {
				rdbtnLoan.setSelected(true);
			} else {
				rdbtnSaving.setSelected(true);
			}
			ButtonGroup group = new ButtonGroup();
			group.add(rdbtnNewRadioButton);
			group.add(rdbtnLoan);
			group.add(rdbtnChecking);
			group.add(rdbtnSaving);
			
			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(158, 401, 89, 23);
			getContentPane().add(deleteButton);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
						Connection con = DriverManager
								.getConnection("jdbc:mysql://localhost/CS631_Project_Fall2021?user=root&password=root");
						Statement deleteStmt = con.createStatement();
						deleteStmt.executeUpdate("delete from customer where ssn = \'" + searchResult.getSsn() + "\'");
						JOptionPane.showMessageDialog(null,
								"Customer Deleted!",
								"Customer Created", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			});
		}

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 30, 67, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(305, 30, 67, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("SSN");
		lblNewLabel_2.setBounds(10, 68, 67, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Address Line 1");
		lblNewLabel_3.setBounds(10, 109, 87, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Address Line 2");
		lblNewLabel_3_1.setBounds(10, 156, 87, 14);
		getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("City");
		lblNewLabel_3_1_1.setBounds(10, 196, 38, 14);
		getContentPane().add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("State");
		lblNewLabel_3_1_1_1.setBounds(174, 196, 38, 14);
		getContentPane().add(lblNewLabel_3_1_1_1);

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Zip");
		lblNewLabel_3_1_1_2.setBounds(359, 196, 27, 14);
		getContentPane().add(lblNewLabel_3_1_1_2);

		lblNewLabel_3_2 = new JLabel("Balance");
		lblNewLabel_3_2.setBounds(10, 244, 87, 14);
		getContentPane().add(lblNewLabel_3_2);

		lblNewLabel_3_3 = new JLabel("Types of Account");
		lblNewLabel_3_3.setBounds(317, 244, 109, 14);
		getContentPane().add(lblNewLabel_3_3);

		lblNewLabel_3_4 = new JLabel("Rate");
		lblNewLabel_3_4.setBounds(10, 286, 87, 14);
		getContentPane().add(lblNewLabel_3_4);

		lblNewLabel_3_5 = new JLabel("Loan Amount");
		lblNewLabel_3_5.setBounds(10, 330, 87, 14);
		getContentPane().add(lblNewLabel_3_5);

		lblNewLabel_3_6 = new JLabel("Loan Payment");
		lblNewLabel_3_6.setBounds(10, 368, 87, 14);
		getContentPane().add(lblNewLabel_3_6);

		JButton btnNewButton = new JButton("Submit");
		if (searchResult == null) {
			btnNewButton.setBounds(244, 401, 89, 23);
		} else {
			btnNewButton.setBounds(325, 401, 89, 23);
		}
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error = 0;
				if (checkEmptyAndNull(firstName.getText()) || checkEmptyAndNull(ssn.getText())
						|| checkEmptyAndNull(streetNo.getText()) || checkEmptyAndNull(aptNo.getText())
						|| checkEmptyAndNull(city.getText()) || checkEmptyAndNull(state.getText())
						|| checkEmptyAndNull(zip.getText()) || checkEmptyAndNull(balanceField.getText())
						|| checkEmptyAndNull(lastName.getText())) {
					error = 1;
				}
				if (checkEmptyAndNull(balanceField.getText()) == false) {
					Double balance = Double.parseDouble(balanceField.getText());
					if (balance < 500) {
						error = 2;
					}
				}

				if ((rdbtnNewRadioButton.isSelected() || rdbtnSaving.isSelected())
						&& checkEmptyAndNull(rateField.getText())) {
					error = 3;
				}
				if (rdbtnLoan.isSelected()
						&& (checkEmptyAndNull(rateField.getText()) || checkEmptyAndNull(loanAmountField.getText())
								|| checkEmptyAndNull(loanPaymentField.getText()))) {
					error = 4;
				}
				if (error == 1) {
					JOptionPane.showMessageDialog(null, "Name, SSN, Address, and Balance must be entered", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (error == 2) {
					JOptionPane.showMessageDialog(null, "Balance must be more than 500", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (error == 3) {
					JOptionPane.showMessageDialog(null,
							"Rate must be selected if it is a saving or money market account.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (error == 4) {
					JOptionPane.showMessageDialog(null,
							"Rate, Loan Amount, Loan Payment must be selected if it is a loan account.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (error == 0) {

					try {
						Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
						Connection con = DriverManager
								.getConnection("jdbc:mysql://localhost/CS631_Project_Fall2021?user=root&password=root");
						if (searchResult == null) {
							Statement stmt = con.createStatement();
							CustomerBean customer = new CustomerBean(firstName.getText(), lastName.getText(),
									streetNo.getText(), aptNo.getText(), city.getText(), state.getText(), zip.getText(),
									ssn.getText());
							java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
							Double balance = Double.parseDouble(balanceField.getText());
							String rate = "";
							if (rdbtnNewRadioButton.isSelected()) {
								rate = rate + "1";
							}
							if (rdbtnLoan.isSelected()) {
								rate = rate + "2";
							}
							if (rdbtnSaving.isSelected()) {
								rate = rate + "3";
							}
							if (rdbtnChecking.isSelected()) {
								rate = rate + "4";
							}
							if (rdbtnNewRadioButton.isSelected() || rdbtnSaving.isSelected()
									|| rdbtnLoan.isSelected()) {
								rate = rate + rateField.getText();
							}
							double loanAmount = 0;
							double loanPayment = 0;

							if (rdbtnLoan.isSelected()) {
								loanAmount = Double.parseDouble(loanAmountField.getText());
								loanPayment = Double.parseDouble(loanPaymentField.getText());
							}
							AccountBean account = new AccountBean(1, balance, sqlDate, 1, rate, loanAmount,
									loanPayment);
							ResultSet rs = stmt
									.executeQuery("Select * from customer where ssn =\'" + customer.getSsn() + "\'");
							// System.out.println(sqlDate);
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "Customer already exists.", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								stmt.executeUpdate("insert into customer values (\'" + customer.getSsn() + "\', \'"
										+ customer.getFirstName() + "\', \'" + customer.getLastName() + "\', \'"
										+ customer.getStreetNo() + "\', \'" + customer.getAptNo() + "\', \'"
										+ customer.getCity() + "\', \'" + customer.getState() + "\', \'"
										+ customer.getZip() + "\')");
								stmt.executeUpdate(
										"insert into myaccount (balance, lastdateaccessed, branchID, rate, loanAmount, loanpayment) values ("
												+ account.getBalance() + ", \'" + account.getLastDateAccessed() + "\', "
												+ account.getBranchID() + ", \'" + account.getRate() + "\', "
												+ account.getLoanAmount() + ", " + account.getLoanPayment() + ")",
										Statement.RETURN_GENERATED_KEYS);
								rs = stmt.getGeneratedKeys();
								rs.next();
								int accountNo = rs.getInt(1);
								stmt.executeUpdate("insert into customer_account values (\'" + customer.getSsn() + "\',"
										+ accountNo + ")");

								JOptionPane.showMessageDialog(null,
										"Customer created. First Name: " + customer.getFirstName() + ", Last Name: "
												+ customer.getLastName() + ", SSN: " + customer.getSsn() + "\nBalance: "
												+ account.getBalance() + ", Account Number: " + accountNo,
										"Customer Created", JOptionPane.INFORMATION_MESSAGE);

							}
						} else {
							CustomerBean customer = new CustomerBean(firstName.getText(), lastName.getText(),
									streetNo.getText(), aptNo.getText(), city.getText(), state.getText(), zip.getText(),
									ssn.getText());
							// System.out.println(customer.toString());
							java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
							Double balance = Double.parseDouble(balanceField.getText());
							String rate = accountType;
							if (rdbtnNewRadioButton.isSelected() || rdbtnSaving.isSelected()
									|| rdbtnLoan.isSelected()) {
								rate = rate + rateField.getText();
							}
							double loanAmount = 0;
							double loanPayment = 0;

							if (rdbtnLoan.isSelected()) {
								loanAmount = Double.parseDouble(loanAmountField.getText());
								loanPayment = Double.parseDouble(loanPaymentField.getText());
							}
							AccountBean account = new AccountBean(1, balance, sqlDate, 1, rate, loanAmount,
									loanPayment);
							Statement stmt2 = con.createStatement();
							Statement stmt3 = con.createStatement();
							String sqlA = "update customer set firstName = \'" + customer.getFirstName() + "\', lastName = \'"
									+ customer.getLastName() + "\', streetNo = \'" + customer.getStreetNo() + "\', aptNo = \'" + customer.getAptNo() + "\', city = \'"
									+ customer.getCity() + "\',  state = \'" + customer.getState() + "\', zip = \'" + customer.getZip() + "\' where ssn = \'"+ customer.getSsn() + "\'";
							// System.out.println(sqlA);
							int a = stmt2.executeUpdate(sqlA);
							int b = stmt3.executeUpdate("update myAccount set balance = " + account.getBalance() + ", lastDateAccessed = \'" + account.getLastDateAccessed()
									+ "\', rate = \'" + account.getRate() + "\', loanAmount = " + account.getLoanAmount() + ", loanPayment = " + account.getLoanPayment()
									+ " where accountNo = " + acc.getAccountNo());
							// System.out.println(a + " " + b);
							JOptionPane.showMessageDialog(null,
									"Information Updated",
									"Customer Created", JOptionPane.INFORMATION_MESSAGE);
						}
						con.close();

					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			}
		});

	}

	private boolean checkEmptyAndNull(String a) {
		if (a == null || a.isBlank())
			return true;
		else
			return false;
	}
}

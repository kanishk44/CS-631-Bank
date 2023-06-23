import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TransactionForm extends JFrame {
	private JTextField accountNo;
	private JTextField balance;
	private JTextField transferTo;
	private JLabel label1;

	public TransactionForm() {
		getContentPane().setBackground(new Color(255, 250, 205));
		setSize(new Dimension(585, 400));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		accountNo = new JTextField();
		accountNo.setBounds(286, 77, 129, 20);
		getContentPane().add(accountNo);
		accountNo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Transaction Details");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(117, 11, 335, 38);
		getContentPane().add(lblNewLabel_1);

		balance = new JTextField();
		balance.setColumns(10);
		balance.setBounds(286, 118, 129, 20);
		getContentPane().add(balance);

		JLabel lblNewLabel_2 = new JLabel("Generate Monthly Service Charge");
		lblNewLabel_2.setBounds(147, 323, 194, 14);
		getContentPane().add(lblNewLabel_2);

		JButton generate = new JButton("Genarate");
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
					Connection con = DriverManager
							.getConnection("jdbc:mysql://localhost/CS631_Project_Fall2021?user=root&password=root");
					java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
					java.sql.Timestamp sqlTime = new java.sql.Timestamp(new java.util.Date().getTime());
					Statement check = con.createStatement();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
					String date = dateFormat.format(new java.util.Date()); 
					LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
					       .with(TemporalAdjusters.lastDayOfMonth());
					LocalDate firstDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
						       .with(TemporalAdjusters.firstDayOfMonth());
					
					
					
					ResultSet checkResult = check.executeQuery("select * from mytransaction where transcode = 'MSC' and transdate >= \'"
							+ firstDayOfMonth + "\' and transdate <= \'" + lastDayOfMonth + "\'");
					
					if (checkResult.next() == false) {
						Statement stmt = con.createStatement();
						Statement stmt2 = con.createStatement();
						ResultSet accounts = stmt.executeQuery("select * from myaccount");
						while (accounts.next()) {
							Double newBalance = accounts.getDouble(2) - 10;
							// System.out.println(newBalance);
							Integer accNo = accounts.getInt(1);
							String a = "insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
									+ sqlDate + "\', \'" + sqlTime + "\', " + 10 + ", " + newBalance + ", " + accNo
									+ ", \'MSC\')";
							// System.out.println(a);
							stmt2.executeUpdate(a);
						}
						JOptionPane.showMessageDialog(null,
								"Monthly Service Charge has been applied to all accounts for this month.",
								"Monthyly Service Charge", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Monthly Service Charge has been completed for this month.\n No more Monthly Service Charge can be applied this month.",
								"Monthyly Service Charge", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex2) {
					// TODO Auto-generated catch block
					ex2.printStackTrace();
				}
			}
		});
		generate.setToolTipText("Applies $10 charge to all acounts");
		generate.setBounds(351, 319, 89, 23);
		getContentPane().add(generate);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 569, 296);
		getContentPane().add(panel);
		panel.setLayout(null);

		JRadioButton deposit = new JRadioButton("Deposit");
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label1.setVisible(false);
				transferTo.setVisible(false);
			}
		});
		deposit.setBounds(95, 199, 129, 20);
		deposit.setOpaque(false);
		panel.add(deposit);

		JRadioButton withdrawal = new JRadioButton("Withdrawal");
		withdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label1.setVisible(false);
				transferTo.setVisible(false);
			}
		});
		withdrawal.setBounds(241, 198, 109, 23);
		withdrawal.setOpaque(false);
		panel.add(withdrawal);

		JRadioButton checkDeposit = new JRadioButton("Check Deposit");
		checkDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label1.setVisible(true);
				transferTo.setVisible(true);
			}
		});
		checkDeposit.setBounds(397, 198, 123, 23);
		checkDeposit.setOpaque(false);
		panel.add(checkDeposit);

		ButtonGroup group = new ButtonGroup();
		group.add(deposit);
		group.add(withdrawal);
		group.add(checkDeposit);

		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
					Connection con = DriverManager
							.getConnection("jdbc:mysql://localhost/CS631_Project_Fall2021?user=root&password=root");
					Statement stmt = con.createStatement();
					if (deposit.isSelected()) {
						Double bal = Double.parseDouble(balance.getText());
						Integer accNo = Integer.parseInt(accountNo.getText());
						ResultSet rs = stmt.executeQuery("select * from myaccount where accountNo = " + accNo);
						rs.next();
						Double currBalance = rs.getDouble(2);
						Double newBalance = currBalance + bal;
						java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
						java.sql.Timestamp sqlTime = new java.sql.Timestamp(new java.util.Date().getTime());
						stmt.executeUpdate(
								"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
										+ sqlDate + "\', \'" + sqlTime + "\', " + bal + ", " + newBalance + ", " + accNo
										+ ", \'DEP\')");
						JOptionPane.showMessageDialog(null,
								"Success. Previous Balance: " + currBalance + ", New Balance: " + newBalance, "Deposit",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (withdrawal.isSelected()) {
						Double bal = Double.parseDouble(balance.getText());
						Integer accNo = Integer.parseInt(accountNo.getText());
						ResultSet rs = stmt.executeQuery("select * from myaccount where accountNo = " + accNo);
						rs.next();
						Double currBalance = rs.getDouble(2);
						java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
						java.sql.Timestamp sqlTime = new java.sql.Timestamp(new java.util.Date().getTime());
						if (currBalance <= bal) {
							Double newBalance = currBalance - 2.00;
							stmt.executeUpdate(
									"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
											+ sqlDate + "\', \'" + sqlTime + "\', 2.00 , " + newBalance + ", " + accNo
											+ ", \'SC\')");
							JOptionPane.showMessageDialog(null,
									"Transaction failed. Not enough balance. Service fees will still be applied.",
									"Withdrawal", JOptionPane.ERROR_MESSAGE);
						} else {
							Double newBalance = currBalance - bal;
							stmt.executeUpdate(
									"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
											+ sqlDate + "\', \'" + sqlTime + "\', " + bal + ", " + newBalance + ", "
											+ accNo + ", \'WD\')");
							java.sql.Timestamp sqlTime2 = new java.sql.Timestamp(new java.util.Date().getTime());
							Double afterTransactionFee = newBalance - 2;
							stmt.executeUpdate(
									"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
											+ sqlDate + "\', \'" + sqlTime2 + "\', 2.00 , " + afterTransactionFee + ", "
											+ accNo + ", \'SC\')");
							JOptionPane.showMessageDialog(null,
									"Success. Previous Balance: " + currBalance + ", New Balance: " + newBalance,
									"Withdrawal", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
						java.sql.Timestamp sqlTime = new java.sql.Timestamp(new java.util.Date().getTime());
						Double bal = Double.parseDouble(balance.getText());
						Integer fromAccount = Integer.parseInt(accountNo.getText());
						Integer toAccount = Integer.parseInt(transferTo.getText());
						ResultSet rs = stmt.executeQuery("select * from myaccount where accountNo = " + fromAccount);
						rs.next();
						Double currBalance = rs.getDouble(2);
						if (currBalance > 0) {
							boolean od = false;
							Double newBalance = currBalance - bal;
							stmt.executeUpdate(
									"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
											+ sqlDate + "\', \'" + sqlTime + "\', " + bal + ", " + newBalance + ", "
											+ fromAccount + ", \'CW\')");
							java.sql.Timestamp sqlTime2 = new java.sql.Timestamp(new java.util.Date().getTime());
							Double afterTransactionFee = newBalance - 2;
							stmt.executeUpdate(
									"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
											+ sqlDate + "\', \'" + sqlTime2 + "\', 2.00 , " + afterTransactionFee + ", "
											+ fromAccount + ", \'SC\')");
							if (afterTransactionFee < 0) {
								od = true;
								stmt.executeUpdate("insert into account_overdraft values (" + fromAccount + ", "
										+ toAccount + ", " + Math.abs(afterTransactionFee) + ", 5)");
							}

							rs = stmt.executeQuery("select * from myaccount where accountNo = " + toAccount);
							rs.next();
							currBalance = rs.getDouble(2);
							newBalance = currBalance + bal;
							stmt.executeUpdate(
									"insert into mytransaction (transdate, transhour, amount, currBalance, accountNo, transcode) values (\'"
											+ sqlDate + "\', \'" + sqlTime + "\', " + bal + ", " + newBalance + ", "
											+ toAccount + ", \'CD\')");
							String message = "Transaction completed. Amount transferred: " + bal;
							if (od) {
								message = message + "\n Account: " + fromAccount + " is overdrafted. A fee will be applied. No further transactions allowed.";
							}

							JOptionPane.showMessageDialog(null,
									message,
									"Transfer", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"Transaction failed.\nThis account: " + fromAccount + " has already been overdrafted.",
									"Overdraft", JOptionPane.ERROR_MESSAGE);
						}
					}
					con.close();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}
		});
		submit.setBounds(250, 251, 89, 23);
		panel.add(submit);

		label1 = new JLabel("Transfer To Account Number");
		label1.setBounds(95, 164, 179, 14);
		label1.setVisible(false);
		panel.add(label1);

		JLabel lblBalance = new JLabel("Amount");
		lblBalance.setBounds(95, 122, 120, 14);
		panel.add(lblBalance);

		JLabel lblNewLabel = new JLabel("Account Number");
		lblNewLabel.setBounds(95, 78, 120, 14);
		panel.add(lblNewLabel);

		transferTo = new JTextField();
		transferTo.setColumns(10);
		transferTo.setBounds(284, 161, 129, 20);
		transferTo.setVisible(false);
		panel.add(transferTo);
	}
}

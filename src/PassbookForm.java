import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class PassbookForm extends JFrame {
	private JTable table;

	public PassbookForm(Integer accountNo) {
		getContentPane().setBackground(new Color(255, 250, 205));
		getContentPane().setLayout(new BorderLayout(0, 0));

		setSize(new Dimension(885, 550));
		setLocationRelativeTo(null);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost/CS631_Project_Fall2021?user=root&password=root");
			Statement stmt = con.createStatement();
			YearMonth thisMonth    = YearMonth.now();
			YearMonth lastMonth    = thisMonth.minusMonths(1);
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
			String lastPassbookUpdate = lastMonth.format(monthYearFormatter) + "-15";
			// System.out.println(lastPassbookUpdate);
			java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());
			ResultSet transactions = stmt.executeQuery("select * from mytransaction where accountNo = " + accountNo + " and transdate > \'" + lastPassbookUpdate
					+ "\' and transdate <= \'" + today + "\'");
			ArrayList<TransactionBean> allTransactions = new ArrayList<>();
			while (transactions.next()) {
				String code = transactions.getString(7);
				String description = "";
				boolean debit = true;
				switch (code) {
				case "CD":
					description = "Check Deposit";
					debit = false;
					break;
				case "CW":
					description = "Check Withdrawal";
					break;
				case "DEP":
					description = "Cash Deposit";
					debit = false;
					break;
				case "MSC":
					description = "Monthly Service Charge";
					break;
				case "OD":
					description = "Overdraft";
					break;
				case "WD":
					description = "Cash Withdrawal";
					break;
				case "SC":
					description = "Service Charge";
					break;
				}

				allTransactions.add(new TransactionBean(transactions.getInt(1), transactions.getDate(2),
						transactions.getTime(3), transactions.getDouble(4), transactions.getDouble(5),
						transactions.getInt(6), transactions.getString(7), description, debit));
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Statement pbstmt = con.createStatement();
			ResultSet pb = pbstmt.executeQuery("select * from mytransaction where accountNo =" + accountNo + " and transdate = \'"
					+ lastPassbookUpdate + "\' and transcode = \'BF\'");
			Double lastbf = 0.0;
			if (pb.next()) {
				lastbf = pb.getDouble(5);
			}
			String[] columnNames = { "Date", "Transaction Code", "Transaction Name", "Debits", "Credits", "Balance" };
			String[][] data = new String[allTransactions.size() + 1][6];
			String[] bf = new String[6];
			bf[0] = lastPassbookUpdate;
			bf[1] = "";
			bf[2] = "Balance Forward";
			bf[3] = "";
			bf[4] = "";
			bf[5] = String.valueOf(lastbf);
			data[0] = bf;
			int i = 1;
			for (TransactionBean transaction : allTransactions) {
				String[] userdata = new String[6];
				userdata[0] = dateFormat.format(transaction.getDate());
				userdata[1] = transaction.getCode();
				userdata[2] = transaction.getDescription();
				userdata[3] = transaction.isDebit() ? String.valueOf(transaction.getAmount()) : "";
				userdata[4] = transaction.isDebit() ? "" : String.valueOf(transaction.getAmount());
				userdata[5] = String.valueOf(transaction.getCurrBalance());

				data[i++] = userdata;
			}
			table = new JTable(data, columnNames);
			getContentPane().add(table, BorderLayout.CENTER);
			getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
			ResultSet customers = stmt.executeQuery(
					"select firstName, lastname from customer, customer_account where customer_account.customerssn = customer.ssn and customer_account.accountno = "
							+ accountNo);
			int count = 0;
			String names = "";
			while(customers.next()) {
				if (count == 0) {
					names = names + customers.getString(1) + " " + customers.getString(2);
					count++;
				} else {
					names = names + ", "+ customers.getString(1) + " " + customers.getString(2);
				}
			}
			getContentPane().add(new JLabel("AccountNo: " + accountNo + ", Customer(s): " + names), BorderLayout.PAGE_END);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

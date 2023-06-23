import java.sql.Time;
import java.sql.Date;

public class TransactionBean {
	private int transactionNo;
	private Date date;
	private Time hour;
	private double amount;
	private double currBalance;
	private int accountNo;
	private String code;
	private String description;
	private boolean debit;
	
	public TransactionBean(int transactionNo, Date date, Time hour, double amount, double currBalance, int accountNo,
			String code, String description, boolean debit) {
		super();
		this.transactionNo = transactionNo;
		this.date = date;
		this.hour = hour;
		this.amount = amount;
		this.currBalance = currBalance;
		this.accountNo = accountNo;
		this.code = code;
		this.description = description;
		this.debit = debit;
	}

	public boolean isDebit() {
		return debit;
	}

	public void setDebit(boolean debit) {
		this.debit = debit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCurrBalance() {
		return currBalance;
	}

	public void setCurrBalance(double currBalance) {
		this.currBalance = currBalance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}

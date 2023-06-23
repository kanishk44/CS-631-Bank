import java.sql.Date;

public class AccountBean {

	@Override
	public String toString() {
		return "AccountBean [accountNo=" + accountNo + ", balance=" + balance + ", lastDateAccessed=" + lastDateAccessed
				+ ", branchID=" + branchID + ", rate=" + rate + ", loanAmount=" + loanAmount + ", loanPayment="
				+ loanPayment + "]";
	}
	private int accountNo;
	private double balance;
	private Date lastDateAccessed;
	private int branchID;
	private String rate;
	private double loanAmount;
	private double loanPayment;
	public AccountBean(int accountNo, double balance, Date lastDateAccessed, int branchID, String rate,
			double loanAmount, double loanPayment) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.lastDateAccessed = lastDateAccessed;
		this.branchID = branchID;
		this.rate = rate;
		this.loanAmount = loanAmount;
		this.loanPayment = loanPayment;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getLastDateAccessed() {
		return lastDateAccessed;
	}
	public void setLastDateAccessed(Date lastDateAccessed) {
		this.lastDateAccessed = lastDateAccessed;
	}
	public int getBranchID() {
		return branchID;
	}
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getLoanPayment() {
		return loanPayment;
	}
	public void setLoanPayment(double loanPayment) {
		this.loanPayment = loanPayment;
	}
	
	
}

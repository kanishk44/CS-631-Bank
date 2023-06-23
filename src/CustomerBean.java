
public class CustomerBean {
	private String firstName;
	private String lastName;
	private String streetNo;
	private String aptNo;
	private String city;
	private String state;
	private String zip;
	private String ssn;
	@Override
	public String toString() {
		return "CustomerBean [firstName=" + firstName + ", lastName=" + lastName + ", streetNo=" + streetNo + ", aptNo="
				+ aptNo + ", city=" + city + ", state=" + state + ", zip=" + zip + ", ssn=" + ssn + "]";
	}
	public CustomerBean(String firstName, String lastName, String streetNo, String aptNo, String city, String state, String zip,
			String ssn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetNo = streetNo;
		this.aptNo = aptNo;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.ssn = ssn;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	

}

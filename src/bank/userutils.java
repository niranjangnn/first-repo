package bank;


public class userutils implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	
	 String name,password,email,actype,rdate;
	
	
	 String bdate;
	 int amount;
	int accno;
	public String getrdate() {
		return rdate;
	}
	public void settrdate(String rdate) {
		this.rdate = rdate;
	}
	
	
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}	
	public String getactype() {
		return actype;
	}
	public void setactype(String actype) {
		this.actype = actype;
	}	
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}

	public int getaccno() {
		return accno;
	}
	public void setaccno(int accno) {
		this.accno = accno;
	}	
	public int getamount() {
		return amount;
	}
	public void setamount(int amount) {
		this.amount = amount;
	}	
	public String getbdate() {
		return bdate;
	}
	public void setbdate(String bdate) {
		this.bdate =bdate;
	}	
	@Override
	public String toString() {
		return "Userutils [name=" + name + ", bdate=" +bdate+ ",password=" + password + ", actype=" + actype + ", amount=" + amount
				+ ", accno=" + accno + ", rdate=" + rdate +"]";
	}
	
}

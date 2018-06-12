package bank;


public class Transaction {
	public int id;
	public int accno;
	public String actype;
	public int amount;
	public int balance;
	public String trdate;
	public String gettrdate() {
		return trdate;
	}
	public void settrdate(String trdate) {
		this.trdate = trdate;
	}
	
	public int getaccno() {
		return accno;
	}
	public void setaccno(int accno) {
		this.accno = accno;
	}
	public String getactype() {
		return actype;
	}
	public void setactype(String actype) {
		this.actype = actype;
	}
	public int getamount() {
		return amount;
	}
	public void setamount(int amount) {
		this.amount = amount;
	}
	public int getbalance() {
		return balance;
	}
	public void setbalance(int balance) {
		this.balance = balance;
	}

}




package bank;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bank.userutils;

public class bankdoa {
	final Logger log = Logger.getLogger(bankdoa.class.getName());
	Connection con = SingleTon.getSingleTonObject().getConnection();
	ResultSet rs;
	PreparedStatement pstmt;
	Statement st;

	public int registration(userutils ut) {
		try {
			log.info("sucessfull registered");
			String qry = "insert into banking(accno,name,bdate,password,actype,amount,email)values(acnoseq.nextval,?,?,?,?,?,?)";
			log.debug("insert into banking(accno,name,bdate,password,actype,amount,email)values(acnoseq.nextval,?,?,?,?,?,?)");
			PreparedStatement pstmt = con.prepareStatement(qry);

			pstmt.setString(1, ut.getname());
			pstmt.setString(2, ut.getbdate());
			pstmt.setString(3, ut.getpassword());
			pstmt.setString(4, ut.getactype());
			pstmt.setInt(5, ut.getamount());
			pstmt.setString(6, ut.getemail());

			pstmt.executeUpdate();

			System.out.print("data stored successfull");
		     log.info("data stored successfull");

			int abc = pstmt.executeUpdate();
			String qry1 = "insert into banktransction (accno,actype,amount,balance,trdate) values ( ?,?,?,?,systimestamp)";
			PreparedStatement pstmt1 = con.prepareStatement(qry1);
			pstmt1.setInt(1, ut.getaccno());
			pstmt1.setString(2, "Credited");
			pstmt1.setInt(3, ut.getamount());
			pstmt1.setInt(4, ut.getamount());
			int x = pstmt1.executeUpdate();
			System.out.println("Transaction updated");
			System.out.println(abc);
			return abc;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String login(String name, String password, String ip) {
		int flag = 0;
		String ip1[] = { "192.168.0.01", "192.168.0.02", "192.168.0.03", "192.168.0.04", "192.168.0.05" };
		for (int i = 0; i < ip1.length; i++) {
			if (ip1[i].equalsIgnoreCase(ip))
				flag = 1;
		}
		if (flag == 1) {
		try {
			log.info("sucessfull login");
			String qry = "Select * from banking where name=? and password=?";
			log.debug("Select * from banking where name=? and password=?");
			PreparedStatement pstmt = con.prepareStatement(qry);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				System.out.println("welcome ");
				System.out.println("name: " + rs.getString("name"));
				name = rs.getString("name");
				String qry1 = "update banking set rdate=systimestamp where name=? and password=?";
				PreparedStatement pstmt1 = con.prepareStatement(qry1);
				pstmt1.setString(1, name);
				pstmt1.setString(2, password);
				System.out.println("Name: " + name);
				System.out.println("password: " + password);
				int x = pstmt1.executeUpdate();
				if (x > 0)
					 log.info("data stored successfull");
				
				else
					System.out.println("No Update");

			}
							
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
		}
		else
		{
			JOptionPane.showMessageDialog(null,
		
			    "Customer IP address is not valid.",
			    "Message",
			    JOptionPane.ERROR_MESSAGE);
		return null;
	}
		}

	public String accountnumber(String name) {

		try {

			String qry = "Select accno from banking where name=?";
			log.debug("Select accno from banking where name=?");
			pstmt = con.prepareStatement(qry);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				String accno = rs.getString("accno");
				System.out.println("<br>" + " your account number: " + accno);
				return accno;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int deposit(int accno, int amount) {

		try {
			log.info("sucessfull deposited");
			String qry = "select * from banking where accno=?";
			log.debug("select * from banking where accno=?");
			PreparedStatement pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, accno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("deposited sucessfull");
				System.out.println("accno" + rs.getInt("accno"));
				int curbalance = rs.getInt(6);
				int balance = curbalance + amount;
				int x = transaction(accno, "credited", amount, balance);
				amount = balance;
				String sql = "update banking set amount=? where accno=?";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setLong(1, amount);
				pstm.setInt(2, accno);
				int rowsUpdated = pstm.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println(" updated successfully!");
					 log.info("deposited sucessfull");
				}
				return x;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int withdraw(int accno, int amount) {

		try {
			log.info("sucessfull withdrwan");
			String qry = "select * from banking where accno=?";
			log.debug("select * from banking where accno=?");
			
			PreparedStatement pstmt = con.prepareStatement(qry);

			pstmt.setInt(1, accno);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("withdraw sucessfull");
				System.out.println("accno" + rs.getInt("accno"));
				int curbalance = rs.getInt(6);
				int balance = curbalance - amount;
				int x = transaction(accno, "debited", amount, balance);
				amount = balance;
				String sql = "update banking set amount=? where accno=?";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setLong(1, amount);
				pstm.setInt(2, accno);
				int rowsUpdated = pstm.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println(" updated successfully!");
				}
				return x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;

	}

	int transaction(int accno, String actype, int amount, int balance) {
		String qry1 = "insert into banktransction (accno,actype,amount,balance,trdate) values ( ?,?,?,?,systimestamp)";

		try {
			PreparedStatement pstmt1 = con.prepareStatement(qry1);
			pstmt1.setInt(1, accno);
			pstmt1.setString(2, actype);
			pstmt1.setInt(3, amount);
			pstmt1.setLong(4, balance);
			int x = pstmt1.executeUpdate();
			System.out.println("Transaction updated");
			return x;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return -1;
	}

	public ArrayList<Transaction> transaction1(int accno) {
		ArrayList<Transaction> transactionList = new ArrayList<>();

		try {
			String qry = "select * from banktransction where accno=?";
			PreparedStatement pstmt = con.prepareStatement(qry);

			pstmt.setInt(1, accno);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("accno") + " " + rs.getString("actype") + " " + rs.getInt("amount") + " "
						+ rs.getInt("balance") + " " + rs.getString("trdate"));
				Transaction transaction = new Transaction();

				transaction.accno = rs.getInt("accno");
				transaction.actype = rs.getString("actype");
				transaction.amount = rs.getInt("amount");
				transaction.balance = rs.getInt("balance");
				transaction.trdate = rs.getString("trdate");
				transactionList.add(transaction);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionList;

	}

	public JSONObject Last_Login(String name) {

		try {
			JSONObject obj = new JSONObject();
			String qry = "Select accno,rdate from banking where name=?";
			log.debug("Select accno,rdate from banking where name=?");
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(qry);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String accno = rs.getString("accno");
				String rdate = rs.getString("rdate");
				System.out.println("<br>" + " your account number: " + accno);
				System.out.println("<br>" + " your rdate " + rdate);
				obj.put("accno", accno);
				obj.put("last_login", rdate);
				System.out.println("Accno: " + obj.get("accno"));
				return obj;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
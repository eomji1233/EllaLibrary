package org.kh.library.model.dao;

import java.sql.*;
import java.util.*;

import org.kh.library.model.vo.Customer;

public class CustomerStoreImpl implements CustomerStore{

	@Override
	public List<Customer> selectAllCustomer(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Customer> cList = new ArrayList<>();
		Customer customer = new Customer();
		String query = "SELECT * FROM CUSTOMER";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			customer = this.rsetToCustomer(rset);
			cList.add(customer);
		}
		pstmt.close();
		rset.close();
		return cList;
	}

	private Customer rsetToCustomer(ResultSet rset) throws SQLException {
		Customer customer = new Customer();
		customer.setUserNo(rset.getInt("USER_NO"));
		customer.setUserId(rset.getString("USER_ID"));
		customer.setUserName(rset.getString("USER_NAME"));
		customer.setUserAge(rset.getInt("USER_AGE"));
		customer.setAddr(rset.getString("ADDR"));
		customer.setGender(rset.getString("GENDER"));
		customer.setDate(rset.getDate("ENROLL_DATE"));
		return customer;
	}

	@Override
	public Customer selectNameSearch(String CName, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Customer result = null;
		String query = "SELECT * FROM CUSTOMER WHERE USER_NAME = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CName);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			result = this.rsetToCustomer(rset);
		}
		pstmt.close();
		rset.close();
		return result;
	}

	@Override
	public Customer selectIdSearch(String CId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Customer result = null;
		String query = "SELECT * FROM CUSTOMER WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CId);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			result = this.rsetToCustomer(rset);
		}
		pstmt.close();
		return result;
	}

	@Override
	public int insertCustomer(Customer customer, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO CUSTOMER VALUES (SEQ_CUS_NO.NEXTVAL, ?, ?, ?, ?, ?, DEFAULT)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, customer.getUserId());
		pstmt.setString(2, customer.getUserName());
		pstmt.setInt(3, customer.getUserAge());
		pstmt.setString(4, customer.getAddr());
		pstmt.setString(5, customer.getGender());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int updateCustomer(Customer customer, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE CUSTOMER SET USER_NAME = ?, ADDR = ? WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, customer.getUserName());
		pstmt.setString(2, customer.getAddr());
		pstmt.setString(3, customer.getUserId());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int deleteCustomer(String CId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CUSTOMER WHERE USER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, CId);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

}

package org.kh.library.model.dao;

import java.sql.*;
import java.util.*;

import org.kh.library.model.vo.Library;

public class LibraryStoreImpl implements LibraryStore{

	@Override
	public List<Library> selectList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		List<Library> lList = new ArrayList<>();
		ResultSet rset = null;
		String query = "SELECT * FROM LIBRARY";
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			Library library = this.rsetToLibrary(rset);
			lList.add(library);
		}
		pstmt.close();
		rset.close();
		return lList;
	}

	private Library rsetToLibrary(ResultSet rset) throws SQLException {
		Library library = new Library();
		library.setLeaseNo(rset.getInt("LEASE_NO"));
		library.setBookNo(rset.getInt("BOOK_NO"));
		library.setUserId(rset.getString("USER_ID"));
		library.setLeaseDate(rset.getDate("LEASE_DATE"));
		library.setReturnDate(rset.getDate("RETURN_DATE"));
		return library;
	}

	@Override
	public Library selectOne(String userId, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library selectOneByName(String bookName, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertLibrary(Library library, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

}

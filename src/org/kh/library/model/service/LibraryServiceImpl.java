package org.kh.library.model.service;

import java.sql.*;
import java.util.*;

import org.kh.library.common.JDBCTemplate;
import org.kh.library.model.dao.LibraryStoreImpl;
import org.kh.library.model.vo.Library;

public class LibraryServiceImpl implements LibraryService{
	LibraryStoreImpl lStore;
	
	public LibraryServiceImpl() {
		lStore = new LibraryStoreImpl();
	}

	@Override
	public List<Library> selectAll() {
		Connection conn = null;
		List<Library> lList = new ArrayList<>();
		try {
			conn = JDBCTemplate.getConnection();
			lList = lStore.selectList(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lList;
	}

	@Override
	public Library selectOne(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library selectOneByName(String bookName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertLibrary(Library library) {
		// TODO Auto-generated method stub
		return 0;
	}

}

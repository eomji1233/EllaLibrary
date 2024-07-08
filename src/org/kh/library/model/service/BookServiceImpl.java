package org.kh.library.model.service;

import java.sql.*;
import java.util.*;

import org.kh.library.common.JDBCTemplate;
import org.kh.library.model.dao.BookStoreImpl;
import org.kh.library.model.vo.Book;

public class BookServiceImpl implements BookService{
	private BookStoreImpl bStore;
	
	public BookServiceImpl() {
		bStore = new BookStoreImpl();
	}
	
	@Override
	public List<Book> selectAllBook() {
		Connection conn = null;
		List<Book> result = null;
		try {
			conn = JDBCTemplate.getConnection();
			result = bStore.selectAllBook(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public Book selectBookOne(int bookNo) {
		Connection conn = null;
		Book rset = null;
		try {
			conn = JDBCTemplate.getConnection();
			rset = bStore.selectBookOne(bookNo, conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rset;
	}

	@Override
	public int insertBook(Book book) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = bStore.insertBook(book, conn);
			// 서비스에서 커밋 롤백 한다는거 알고있기
//			if(result > 0) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int deleteBook(int bookNo) {	
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = bStore.deleteBook(bookNo, conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}

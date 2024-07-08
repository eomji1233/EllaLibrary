package org.kh.library.controller;

import java.util.*;

import org.kh.library.model.service.LibraryServiceImpl;
import org.kh.library.model.vo.Library;

public class LibraryController implements LibraryControllerI{
	LibraryServiceImpl lService;
	
	public LibraryController() {
		lService = new LibraryServiceImpl();
	}

	@Override
	public List<Library> selectAll() {
		List<Library> lList = lService.selectAll();
		return lList;
	}

	@Override
	public void selectOne(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectOneByName(String bookName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertLibrary(Library library) {
		// TODO Auto-generated method stub
		
	}

}

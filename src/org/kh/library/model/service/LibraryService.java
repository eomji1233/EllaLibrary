package org.kh.library.model.service;

import java.util.*;

import org.kh.library.model.vo.Library;

public interface LibraryService {
	public List<Library> selectAll();
	public Library selectOne(String userId);
	public Library selectOneByName(String bookName);
	public int insertLibrary(Library library);
}

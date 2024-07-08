package org.kh.library.view;

import java.util.*;

import org.kh.library.controller.BookController;
import org.kh.library.controller.CustomerController;
import org.kh.library.controller.LibraryController;
import org.kh.library.model.vo.Book;
import org.kh.library.model.vo.Customer;
import org.kh.library.model.vo.Library;

public class LibraryView implements LibraryViewI{
	private BookController bController;
	private CustomerController cController;
	private LibraryController lController;
	
	public Scanner sc = new Scanner(System.in);
	public LibraryView() {
		bController = new BookController();
		cController = new CustomerController();
		lController = new LibraryController();
	}
	
	public void startLibrary() {
		finish:
			while(true) {
				int choice = mainMenu();
				switch(choice) {
				case 1:
					bookMenu();
					
					break;
				case 2:
					customerMenu();
					//System.out.println("2번");
					break;
				case 3:
					libraryMenu();
					//System.out.println("3번");
					break;
				case 0 :
					displayMessage("프로그램을 종료합니다");
					break finish;
				}
			}
	}
	
	@Override
	public int mainMenu() {
		// TODO Auto-generated method stub
		System.out.println("메인메뉴");
		System.out.println("1. 책 관리");
		System.out.println("2. 회원 관리");
		System.out.println("3. 대여 관리");
		System.out.println("0. 종료");
		System.out.print("메뉴 선택 : ");
		return sc.nextInt();
	}
	@Override
	public void bookMenu() {		
		while(true) {
			System.out.println("책관리 서브메뉴");
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 책 코드로 조회");
			System.out.println("3. 책 추가하기");
			System.out.println("4. 책 삭제하기");
			System.out.println("5. 메인 메뉴로 이동");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				List<Book> bList = bController.selectAllBook();
//				if(!bList.isEmpty()) {
				if(bList.size() > 0) {
					this.dispalyBookList(bList);
				} else {
					this.displayError("존재하지 않는 정보입니다");
				}
				break;
			case 2:
				int bookNo = this.inputBookNo();
				Book sBook = bController.selectBookOne(bookNo);
				if(sBook != null) {
					this.displayBook(sBook);					
				} else {
					this.displayError("존재하지 않는 정보입니다");
				}
				break;
			case 3:
				// 책 정보 입력받기
				Book book = this.inputBook();
				// 책 정보 전달 - 컨트롤러
				int result = bController.insertBook(book);
				if(result > 0) {
					this.displaySucess("책 등록 완료!");
				} else {
					this.displayError("책 등록이 완료되지 않았습니다");
				}
				break;
			case 4:
				bookNo = this.inputBookNo();
				sBook = bController.selectBookOne(bookNo);
				if(sBook != null) {
					bController.deleteBook(bookNo);
					this.displaySucess("책 삭제 성공!");
				} else {
					this.displayError("존재하지 않는 정보입니다");
				}
				break;
			case 5:
				return;
			}
		}
		
	}

	

	@Override
	public void customerMenu() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("회원관리 서브메뉴");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 회원 이름으로 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 메인 메뉴로 이동");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				List<Customer> cList = cController.selectAllCustomer();
				if(cList.size() > 0) {
					this.dispalyCustomerList(cList);					
				} else {
					this.displayError("정보가 존재하지 않습니다");
				}
				break;
			case 2:
				String cName = this.inputCName();
				Customer result = cController.selectNameSearch(cName);
				if(result != null) {
					this.diplayCutomerOne(result);
				} else {
					this.displayError("회원정보가 존재하지 않습니다");
				}
				break;
			case 3:
				String cId = this.inputCId();
				result = cController.selectIdSearch(cId);
				if(result != null) {
					this.diplayCutomerOne(result);
				} else {
					this.displayError("회원정보가 존재하지 않습니다");
				}
				break;
			case 4:
				Customer customer = this.inputCustomer();
				int result1 = cController.insertCustomer(customer);
				if(result1 > 0) {
					this.displaySucess("회원가입에 성공했습니다!");
				} else {
					this.displayError("회원가입에 실패했습니다");
				}
				break;
			case 5:
				cId = this.inputCId();
				result = cController.selectIdSearch(cId);
				if(result != null) {
					customer = this.modifyCustomer();
					customer.setUserId(cId);
					result1 = cController.updateCustomer(customer);
					if(result1 > 0) {
						this.displaySucess("회원정보가 수정되었습니다");
					} else {
						this.displayError("회원정보 수정을 실패했습니다");
					}
				} else {
					this.displayError("회원정보가 존재하지 않습니다");
				}
				break;
			case 6:
				cId = this.inputCId();
				result = cController.selectIdSearch(cId);
				if(result != null) {
					result1 = cController.deleteCustomer(cId);
					if(result1 > 0) {
						this.displaySucess("회원탈퇴가 완료되었습니다");
					} else {
						this.displayError("회원탈퇴를 실패했습니다");
					}
				} else {
					this.displayError("회원정보가 존재하지 않습니다");
				}
				break;
			case 7:
				return;
			}
		}
	}

	@Override
	public void libraryMenu() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("대여관리 서브메뉴");
			System.out.println("1. 전체 대여 조회");
			System.out.println("2. 대여 아이디로 조회");
			System.out.println("3. 대여 책이름으로 조회");
			System.out.println("4. 대여정보 추가");
			System.out.println("5. 메인 메뉴로 이동");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				List<Library> lList = lController.selectAll();
				if(lList.size() > 0) {
					this.displayLibraryList(lList);
				} else {
					this.displayError("대여정보가 없습니다");
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			}
		}
	}

	@Override
	public void displayLibraryList(List<Library> lList) {
		System.out.println("===== 전체 대여 정보 출력 =====");
		for(Library library : lList) {
			System.out.printf("대여번호 : %d, 책번호 : %d"
					+ "	, 고객아이디 : %s, 대여일 : %d, 반납일 : %d , ");
		}
	}

	@Override
	public void displayLibraryList(Library library) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySucess(String msg) {
		System.out.println("[SYSTEM SUCCESS] " + msg);
	}

	@Override
	public String inputUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String inputBookName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library insertLibrary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispalyBookList(List<Book> bList) {
		System.out.println("===== 전체 책 정보 출력 =====");
		for(Book book : bList) {
			System.out.printf("번호 : %d, 제목 : %s, 저자 : %s, "
					+ "가격 : %d, 출판사 : %s, 장르 : %s \n",
					book.getBookNo()
					, book.getBookName()
					, book.getBookWriter()
					, book.getBookPrice()
					, book.getPublisher()
					, book.getGenre());
		}
	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void displayError(String message) {
		System.out.println("[SYSTEM ERROR] " + message);
	}

	@Override
	public void dispalyCustomerList(List<Customer> cList) {
		System.out.println("===== 전체 회원 정보 출력 =====");
		for(Customer customer : cList) {
			System.out.printf("번호 : %d, 아이디 : %s, 이름 : %s, "
					+ "나이 : %d, 주소 : %s, 성별 : %s, 가입일 : %s \n",
					customer.getUserNo(),
					customer.getUserId(),
					customer.getUserName(),
					customer.getUserAge(),
					customer.getAddr(),
					customer.getGender(),
					customer.getDate());
		}
	}

	@Override
	public void diplayCutomerOne(Customer customer) {
		System.out.println("===== 회원 정보 출력 =====");
			System.out.printf("번호 : %d, 아이디 : %s, 이름 : %s, "
					+ "나이 : %d, 주소 : %s, 성별 : %s, 가입일 : %s \n",
					customer.getUserNo(),
					customer.getUserId(),
					customer.getUserName(),
					customer.getUserAge(),
					customer.getAddr(),
					customer.getGender(),
					customer.getDate());
	}

	@Override
	public void displayBook(Book book) {
		System.out.println("===== 책 정보 출력 =====");
		System.out.printf("번호 : %d, 제목 : %s, 저자 : %s, "
				+ "가격 : %d, 출판사 : %s, 장르 : %s \n",
				book.getBookNo()
				, book.getBookName()
				, book.getBookWriter()
				, book.getBookPrice()
				, book.getPublisher()
				, book.getGenre());
		}

	@Override
	public String inputCName() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 이름 입력 : ");
		String cName = sc.next();
		return cName;
	}

	@Override
	public String inputCId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 아이디 입력 : ");
		String cId = sc.next();
		return cId;
	}

	@Override
	public Customer inputCustomer() {
		System.out.println("===== 회원가입 =====");
		Customer customer = new Customer();
		System.out.print("아이디 : ");
		String userId = sc.next();
		System.out.print("이름 : ");
		String userName = sc.next();
		System.out.print("나이 : ");
		int userAge = sc.nextInt();
		sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		System.out.print("성별 : ");
		String gender = sc.next();
		customer.setUserId(userId);
		customer.setUserName(userName);
		customer.setUserAge(userAge);
		customer.setAddr(addr);
		customer.setGender(gender);
		return customer;
	}

	@Override
	public Customer modifyCustomer() {
		Customer customer = new Customer();
		System.out.println("===== 수정할 정보 입력 =====");
		System.out.print("이름 : ");
		String userName = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		customer.setUserName(userName);
		customer.setAddr(addr);
		return customer;
	}

	@Override
	public int inputBookNo() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 책 번호 입력 : ");
		int bookNo = sc.nextInt();
		return bookNo;
	}

	@Override
	public Book inputBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 책 정보 등록 =====");
		System.out.print("책 제목 : ");
		String bookName = sc.nextLine();
		System.out.print("책 저자 : ");
		String bookWriter = sc.nextLine();
		System.out.print("가격 : ");
		int    bookPrice = sc.nextInt();
		sc.nextLine();
		System.out.print("출판사 : ");
		String publisher = sc.nextLine();
		System.out.print("장르 : ");
		String genre = sc.nextLine();
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookWriter(bookWriter);
		book.setBookPrice(bookPrice);
		book.setPublisher(publisher);
		book.setGenre(genre);
		return book;
	}

}

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


 class Book {
	 //instance variables of Book class
   private String Title;
   private String Author;
  
   
   private boolean AvailableCopies;
   private int TotalCopies;
   private double RenatlPrice;
  
   
   // parametrized constuctor  of Book class
   public Book(String Title,String Author,boolean AvailableCopies,int TotalCopies,double RenatlPrice){
	   this.Title=Title;
	   this.Author=Author;
	   
	   this.AvailableCopies=true;
	   this.TotalCopies=TotalCopies;
	   this.RenatlPrice=RenatlPrice;
	   
   }
   //getter method for accessing instance variables  of Book class
   public String gettitle(){
	   return Title;
   }
   public String getauthor(){
	return Author;
	}
  
   public boolean getavailablecopies(){
	return AvailableCopies;
	   
   }
   public int gettotalprice(){
	return TotalCopies;
	   
   }public double calculatePrice(int rentalDays) {
       return RenatlPrice * rentalDays;
   }
   public double getrenatlPrice(){
	return RenatlPrice;
	   
   }
   
   public void rent() {
       AvailableCopies = false;
   }
   public void returnBook() {
       AvailableCopies = true;
   }
   
   
 }
 class User{
	 //  instance variables of User class
	 private String UserId;
	 private String UserName;
	
	 private List<Rental> rentals;
	 // constructor of User class
	 public User(String UserId,String UserName){
		this.UserId=UserId;
		this.UserName=UserName;
		
		this.rentals=new ArrayList<>();
		 
	 }
	 //getter method for accessing instance variables  of User class
	 public String getuserId(){
		return UserId;
		 
	 }
	 public String getusername(){
		return UserName;
		 
	 }
	 
	 
 }
 class Rental{
	 // instance variables of Rental Class
	
	 private User user ;
	 private Book book ;
	 private int rentalDays ;
	
	

	   
	 
	 //constructor of Rental class
	 public Rental( User user,Book book, int rentalDays){
		
	    this.user=user;
	    this.book=book;
	    this.rentalDays = rentalDays;
	    
	 }
	 //getter method for accessing instance variables  of Rental class
	 
	 public User getuser(){
		return user;
		 
	 }
	 public Book getbook(){
		return book;
		 
	 }
	 public int getRentalDays() {
	        return rentalDays;
	    }
	 
	 
	 
 }
 
class Service{
	
	private static final String userName = null;
	//instance variables of Service class
	private List<Book> BooksDatabase;
	private List<User>  usersDatabase;
	private List<Rental> rentalDatabase;
	
	//constructor of service class
	
	

	
	public Service() {
	    this.BooksDatabase = new ArrayList<>();
	    this.usersDatabase = new ArrayList<>();
	    this.rentalDatabase = new ArrayList<>();
	}




	public void addBook(Book book) {
		this.BooksDatabase.add(book);
	    }

	    public void addCustomer(User user) {
	    	this.usersDatabase .add(user);
	    }

	    public void rentBook1(Book book, User user, int rentalDays) {
	    	 if (book.getavailablecopies()) {
	             book.rent();
	             rentalDatabase.add(new Rental(user, book, rentalDays));

	         } else {
	             System.out.println("book is not available for rent.");
	         }
	     }
	    public void returnBook(Book book) {
	        book.returnBook();
	        Rental rentalToRemove = null;
	        for (Rental rental :  rentalDatabase ) {
	            if (rental.getbook() == book) {
	                rentalToRemove = rental;
	                break;
	            }
	        }
	        if (rentalToRemove != null) {
	        	  rentalDatabase.remove(rentalToRemove);

	        } else {
	            System.out.println("book was not rented.");
	        }
	    }
	        public void menu() {
	            Scanner scanner = new Scanner(System.in);

	            while (true) {
	                System.out.println("Read smarter, not harder. Rent books online and access a universe of stories.");
	                System.out.println("1. Rent any book of your good choice");
	                System.out.println("2. Return a book");
	                System.out.println("3. Thank you"+"  Exit");
	                System.out.print("Enter your choice: ");

	                int choice = scanner.nextInt();
	                scanner.nextLine(); // Consume newline

	                if (choice == 1) {
	                    System.out.println("*****Rent any  Book in any Time with some benefits*****");
	                    System.out.print("Enter your Good Name: ");
	                    String customerName = scanner.nextLine();

	                    System.out.println("\nAvailable Books for reading:");
	                    for (Book book : BooksDatabase) {
	                        if (book.getavailablecopies()) {
	                            System.out.println(book.gettitle() + " - " + book.getauthor());
	                        }
	                    }

	                    System.out.print("\nEnter the Book title  and Author you want to rent: ");
	                    String carId = scanner.nextLine();

	                    System.out.print("Enter the number of days for rental: ");
	                    int rentalDays = scanner.nextInt();
	                    scanner.nextLine(); 

	                    User newUser = new User("User" + (usersDatabase.size() + 1), userName);
	                    addCustomer(newUser);

	                    Book selectedBook = null;
	                    for (Book book : BooksDatabase) {
	                        if (book.gettitle().equals(selectedBook.gettitle()) && book.getavailablecopies()) {
	                            selectedBook = book;
	                            break;
	                        }
	                    }

	                    if (selectedBook != null) {
	                        double totalPrice = selectedBook.calculatePrice(rentalDays);
	                        System.out.println("\n== Rental Information ==\n");
	                        System.out.println("user ID: " + newUser.getuserId());
	                        System.out.println("user Name: " + newUser.getusername());
	                        System.out.println("book: " + selectedBook.gettitle() + " " + selectedBook.getauthor());
	                        System.out.println("Rental Days: " + rentalDays);
	                        System.out.printf("Total Price: $%.2f%n", totalPrice);

	                        System.out.print("\nConfirm rental (Y/N): ");
	                        String confirm = scanner.nextLine();

	                        if (confirm.equalsIgnoreCase("Y")) {
	                            rentBook1(selectedBook, newUser, rentalDays);
	                            System.out.println("\nBook rented successfully.");
	                        } else {
	                            System.out.println("\nRental canceled.");
	                        }
	                    } else {
	                        System.out.println("\nInvalid Book selection or Books not available for rent.");
	                    }
	                } else if (choice == 2) {
	                    System.out.println("**************** Return a Book**********");
	                    System.out.print("Enter the Book Title you want to return: ");
	                    String Booktitle = scanner.nextLine();

	                    Book bookToReturn = null;
	                    for (Book book : BooksDatabase) {
	                        if (book.gettitle().equals(Booktitle) && book.getavailablecopies()) {
	                            bookToReturn = book;
	                            break;
	                        }
	                    }

	                    if (bookToReturn != null) {
	                        User user = null;
	                        for (Rental rental :  rentalDatabase) {
	                            if (rental.getbook() == bookToReturn) {
	                                user = rental.getuser();
	                                break;
	                            }
	                        }

	                        if (user != null) {
	                            returnBook(bookToReturn);
	                            System.out.println("Book returned successfully by " + user.getusername());
	                        } else {
	                            System.out.println("Book was not rented or rental information is missing.");
	                        }
	                    } else {
	                        System.out.println("Invalid Book Title or book is not rented.");
	                    }
	                } else if (choice == 3) {
	                    break;
	                } else {
	                    System.out.println("Invalid choice. Please enter a valid option.");
	                }
	            }

	            System.out.println("\nThank you for using the Book Rental System!");
	        }

			
	    
			

	    }

class main{
	public static void main(String[]args){
		
		Service rentalSystem = new Service();
		

		    Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", true, 3, 360.0);
	        Book book2 = new Book("Dream of the Red Chamber ", "Agatha Christie", true, 3, 600.0);
	        Book book3 = new Book("Irrfan Khan: A Life in Movies", "	Shubhra Gupta", true, 7, 300.0);
        rentalSystem.addBook(book1);
        rentalSystem.addBook(book2);
        rentalSystem.addBook(book2);

        rentalSystem.menu();
    }
}
	

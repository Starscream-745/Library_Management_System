import java.util.ArrayList;
import java.util.List;
import java.util.*;
class Library {
List<Book> book=new ArrayList<>();
    List<User> user=new ArrayList<>();
    public void addbook(Book b){
         book.add(b);
     }
public void addUser(User u){
         user.add(u);
}
public Book getbookbyID(int id) {
    for (Book b : book) {
        if (id == b.getBookID()) {
            return b;
        }


    }
    return null;
}

    public User getuserbyID(int id){
        for (User u : user) {
            if (id == u.UserID) {
                return u;
            }
        }
        return null;
    }

    public boolean issuebook(int UserId, int BookID) {
        Book a = getbookbyID(BookID);
        User u = getuserbyID(UserId);
        if (a == null || u == null) {
            System.out.println("error 404");
            return false;
        } else {
            u.IssueBook(a);
            return true;
        }
    }

    public void returnbook(int UserId, int BookID){
        Book a=getbookbyID(BookID);
        User u=getuserbyID(UserId);
        if(a==null || u==null){
            System.out.println("error 404");
        }
        else u.ReturnBook(a); }
    public void viewAllBook(){
        for (Book item : book) {
            System.out.println(item+"\n");
        }
    }


    public void viewAllUser(){
          for (User item : user) {
            System.out.println(item+"\n");
        }
    }

}


class Book{

int bookID;
String title;
String author;
boolean isIssued;

    @Override
    public String toString() {
        return "Book{bookID=" + bookID + ", title='" + title + "', author='" + author + "', isIssued=" + isIssued + "}";
    }


    public Book( int bookID, String title,String author, boolean isIssued) {
        this.author = author;
        this.bookID = bookID;
        this.isIssued = isIssued;
        this.title = title;
    }

    public void issue(){
        this.isIssued=true;
//        if (isIssued==true){
            System.out.println("***ISSUED***");
//        }
    }
    public void returnBook(){
        this.isIssued=false;
//        if (isIssued==false){
            System.out.println("***RETURNED***");
//        }
    }

    public String getAuthor() {
        return author;
    }

    public int getBookID() {
        return bookID;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public String getTitle() {
        return title;
    }
}

class User{

    int UserID;
    String name;
    List<Book> issued=new ArrayList<>();

    @Override
    public String toString() {
        return "User{UserID=" + UserID + ", name='" + name + "', booksIssued=" + issued.size() + "}";
    }


    public User( String name, int userID) {
        this.issued = new ArrayList<>();
        this.name = name;
        UserID = userID;

    }
public void IssueBook(Book book){

    if(!book.isIssued){
        issued.add(book);
        book.issue();
        System.out.println("Book issued to: "+ name);

    }
    else System.out.println("Book is already issued...");

}
    public void ReturnBook(Book book){
        if(book.isIssued){
            issued.remove(book);
            book.returnBook();
            System.out.println("book returned by: "+name);

        }
        else System.out.println("Cannot return: Either not issued or not issued by this user.");
    }

    public void getIssuedBooks(){
        if(issued.isEmpty()){
            System.out.println(name+"has no book issued");

        }
        else System.out.println(name+"'s book issued:");
for(Book b:issued){
    System.out.println("- " + b.isIssued + b.getTitle()+"by" + b.getAuthor());

}


    }

}



class Main {
static Scanner x=new Scanner(System.in);
    public static void main(String[] args) {
        Library library=new Library();

        // books

        Book book1 = new Book(101, "The Hobbit", "J.R.R. Tolkien", false);
        Book book2 = new Book(102, "A Brief History of Time", "Stephen Hawking", false);
        Book book3 = new Book(103, "I, Robot", "Isaac Asimov", false);
        Book book4 = new Book(104, "Dune", "Frank Herbert", false);

//        users
        User user1 = new User("Alice", 1);
        User user2 = new User("Bob", 2);
        User user3 = new User("Charlie", 3);
        User user4 = new User("Diana", 4);

        library.addbook(book1);
        library.addbook(book2);
        library.addbook(book3);
        library.addbook(book4);

        library.addUser(user1);
        library.addUser(user2);
        library.addUser(user3);
        library.addUser(user4);
        System.out.println("Welcome to the central library!!!");
        System.out.println("Write ENTER to start");
        String s= x.nextLine();
        if (s.equalsIgnoreCase("ENTER") || s.equalsIgnoreCase("enter") || s.equalsIgnoreCase("enter")){


        int choice=0;
//        System.out.println("1. Add a New Book\n2. Add a New User\n3. Issue a Book\n4. Return a Book\n5. List All Books\n6. List All Users\n7. Exit\n");

        while (true){
            System.out.println("1. Add a New Book\n2. Add a New User\n3. Issue a Book\n4. Return a Book\n5. List All Books\n6. List All Users\n7. Exit\n");
            System.out.print("choice- ");
            choice=x.nextInt();
            System.out.println();

            switch(choice) {
                case 1:
                    System.out.println("enter book id");
                    int id = x.nextInt();
                    x.nextLine();
                    System.out.println("enter book name");
                    String title = x.nextLine();
                    System.out.println("enter book's author");
                    String author = x.nextLine();

                    boolean issued = false;
                    Book navi = new Book(id, title, author, issued);
                    library.addbook(navi);
                    System.out.println("Book added \n");
                    break;

                case 2:
                    System.out.println("enter user id");
                    int userid = x.nextInt();
                    x.nextLine();
                    System.out.println("enter username");
                    String name = x.nextLine();
                    User u = new User(name, userid);
                    library.addUser(u);

                    System.out.println("*********************** User added successful **************************\n");
                    break;

                case 3:
                    System.out.println("enter bookID");
                    int a = x.nextInt();
                    System.out.println("enter UserID");
                    int y = x.nextInt();
                    if(library.issuebook(y, a)) {

                        System.out.println("****** Issued Successfully ************************************\n");
                    }
                    break;

                case 4:
                    System.out.println("Enter Book ID to return:");
                    int returnBookId = x.nextInt();
                    System.out.println("Enter User ID returning the book:");
                    int returnUserId = x.nextInt();
                    library.returnbook(returnUserId, returnBookId);

                    System.out.println("**** Returned Successful ******************************************\n");
                    break;


                case 5:
                    library.viewAllBook();
                    break;


                case 6:
                    library.viewAllUser();
                    break;

                case 7:
                    System.out.println("Thankyou! please visit again...");
                    System.exit(0);

                    break;

            }
            }
        }
else {
            System.out.println("XXXXXXXXXXXXXXXXX INVALID INPUT XXXXXXXXXXXXXXXXXXX");
        }
    }

}
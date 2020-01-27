/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import javax.swing.table.*;
import java.util.*;
/**
 *
 * @author omerali
 * 
 */
public class Connector {

    /**
     * @param args the command line arguments
     */
    static private User CurrentUser;

    static public User getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(User CurrentUser) {
        Connector.CurrentUser = CurrentUser;
    }
    
    
    
    Connector()
    {
    }
    
    public void AddBook(String name,String author,String publisher,String subject,int copies)
    {
        dbConnectivity Db=new dbConnectivity();
        int id=LibraryManagementSystem.NumOfItems+1;
        Db.AddBook(id, name, author, publisher, subject, copies);
        Book b=new Book(id, name, author, publisher, subject, copies, id, 1);
        LibraryManagementSystem.setBook(b);
    }
    
    public void AddDvd(String name,String subject ,int copies)
    {
        dbConnectivity Db=new dbConnectivity();
        int id=LibraryManagementSystem.NumOfItems+1;
        Db.AddDvd(id, name, subject, copies);
        Dvd d=new Dvd(id, name, subject, copies, id, 2);
        LibraryManagementSystem.setDvd(d);
    }
    
    public void RemoveBook(int id)
    {
        dbConnectivity Db=new dbConnectivity();
        LibraryManagementSystem.removeBook(id);
        Db.RemoveBook(id);
    }
    
    public void RemoveDvd(int id)
    {
        dbConnectivity Db=new dbConnectivity();
        LibraryManagementSystem.removeDvd(id);
        Db.RemoveDvd(id);
    }
    public void AddUser(String name,String Email,String Password,String Phone,String Add,int type)
    {
        dbConnectivity Db=new dbConnectivity();
        int id=LibraryManagementSystem.NumOfUsers+1;
        Db.AddUser(id, name, Email, Password, Phone, Add, type);
        User u=new User(id, name, Email, Password, Phone, Add, type);
        LibraryManagementSystem.setUser(u);
    }
    public void RemoveUser(int id)
    {
        dbConnectivity Db=new dbConnectivity();
        LibraryManagementSystem.removeUser(id);
        Db.RemoveUser(id);
    }
    
    public void EditUser(int id,String name,String Email,String phone,String Add,int type)
    {
        dbConnectivity Db=new dbConnectivity();
        User u=LibraryManagementSystem.searchUser(id);
        if(!name.equals(""))
            u.name=name;
        if(!Email.equals(""))
            u.email=Email;
        if(!phone.equals(""))
            u.phoneNo=phone;
        if(!Add.equals(""))
            u.address=Add;
                    
        Db.EditUser(id, u.name, u.email, u.phoneNo, u.address, type);
    }
    
    public void createloan(User u,Item i,String duedate)
    {
        dbConnectivity db=new dbConnectivity();
        int id=LibraryManagementSystem.TotalNumOfLoans++;
        Date CurrentDate=new Date();
        String retDate=null;
        i.copyCheckedout();
        Loan L=new Loan(id,u,i,CurrentDate.toString(),duedate,retDate);
        u.setLoan(L);
        i.setLoan(L);
        db.createloan(id, CurrentDate.toString(), duedate, u.userId, i.id);
    }
    public void Itemreturned(Item i,Loan l,String returndate)
    {
        dbConnectivity db=new dbConnectivity();
        l.ReturnDate=returndate;
        i.copyCheckedin();
    }
    
    public boolean login(int id,String password)
    {
       User found = LibraryManagementSystem.searchUser(id);
       boolean flag=false;
       if(found!=null)
       {
        if(found.password == null ? password == null : found.password.equals(password))
        {
            flag=true;
            CurrentUser=found;
        }
       }
       return flag;
    }
    public static void displayAll()
    {
        dbConnectivity db=new dbConnectivity();
        db.displayAll();
    }
    public int getUserType()
    {
        return CurrentUser.type;
    }
    public DefaultTableModel getBorrowerModel()
    {
        String []Columns={"ID","Name","Email","Password","PhoneNo","Address","Type"};
        DefaultTableModel d=new DefaultTableModel(LibraryManagementSystem.borrowers(),Columns);
        return d;
    }
    
    public DefaultTableModel getClerkModel()
    {
        String []Columns={"ID","Name","Email","Password","PhoneNo","Address","Type"};
        DefaultTableModel d=new DefaultTableModel(LibraryManagementSystem.Clerks(),Columns);
        return d;
    }
    
    public DefaultTableModel getLibrarianModel()
    {
        String []Columns={"ID","Name","Email","Password","PhoneNo","Address","Type"};
        DefaultTableModel d=new DefaultTableModel(LibraryManagementSystem.Librarians(),Columns);
        return d;
    }
    
    public DefaultTableModel getBookModel()
    {
        String []Columns={"ID","Name","Author","Publisher","Subject","Copies"};
        DefaultTableModel d=new DefaultTableModel(LibraryManagementSystem.allbooks(),Columns);
        return d;
    }
    public DefaultTableModel getDvdModel()
    {
        String []Columns={"ID","Name","Subject","Copies"};
        DefaultTableModel d=new DefaultTableModel(LibraryManagementSystem.alldvds(),Columns);
        return d;
    }
    
    
    public static void main(String[] args) {
        dbConnectivity db=new dbConnectivity();
        db.FillListOfUsers();
        db.FillListOfItems();
        db.FillLoans();
        int i=0;
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });

        
//                java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UserDetails().setVisible(true);
//            }
//        });
                
//                      java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CheckInItem().setVisible(true);
//            }
//        });
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
/**
 *
 * @author omerali
 */
public class dbConnectivity {
     Connection con;
    Statement stmt;
    
    dbConnectivity() //cons
    {
        try
        {
             String s = "jdbc:sqlserver://OMER:1433;databaseName=LibraryManagementSystem";
             con=DriverManager.getConnection(s,"user","1234");


            stmt = con.createStatement(); 
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    void displayAll()
    {
        
        try
        {
            ResultSet rs=stmt.executeQuery("select * from Users");
             while(rs.next())
             {
                 
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getInt(5));
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
   // int getPrice(Sting candyName){}
    //int getQuantity(Sting candyName){}
    //void insertCandy(int id, String name, int qun, int price){}
    void FillListOfUsers()
    {
        try
        {
            ResultSet rs=stmt.executeQuery("Select* from Users");
            while(rs.next())
            {
                int id=Integer.parseInt(rs.getString(1));
                String name=rs.getString(2);
                String email=rs.getString(3);
                String password=rs.getString(4);
                String phoneNumber=rs.getString(5);
                String address=rs.getString(6);
                int type=Integer.parseInt(rs.getString(7));
                switch (type) {
                    case 1:
                        {
                            Borrower b=new Borrower(id,name,email,password,phoneNumber,address,type);
                            LibraryManagementSystem.setUser(b);
                            break;
                        }
                    case 2:
                        Clerk c=new Clerk(id,name,email,password,phoneNumber,address,type);
                        LibraryManagementSystem.setUser(c);
                        break;
                    case 3:
                        {
                            Librarian l=new Librarian(id,name,email,password,phoneNumber,address,type);
                            LibraryManagementSystem.setUser(l);
                            break;
                        }
                    default:
                        System.out.println("Entered wrong type");
                        break;
                }
            }
        }
        catch(NumberFormatException | SQLException e)
        {
            System.out.println(e);
        }
    }
    void FillListOfItems()
     {
        try
        {
            ResultSet rs=stmt.executeQuery("Select * from Item inner join Book on Item.ItemId=Book.Id");
            while(rs.next())
            {
                int Itemid=Integer.parseInt(rs.getString(1));
                int type=Integer.parseInt(rs.getString(2));
                String name=rs.getString(3);
                String author=rs.getString(4);
                String publisher=rs.getString(5);
                String subject=rs.getString(6);
                int copies=Integer.parseInt(rs.getString(7));
                int id=Integer.parseInt(rs.getString(8));
                Book b=new Book(id,name,author,publisher,subject,copies,Itemid,type);
                LibraryManagementSystem.setBook(b);
            }
            rs=stmt.executeQuery("Select * from Item inner join Dvd on Item.ItemId=Dvd.Id");
            while(rs.next())
            {
                int Itemid=Integer.parseInt(rs.getString(1));
                int type=Integer.parseInt(rs.getString(2));
                int id=Integer.parseInt(rs.getString(3));
                String name=rs.getString(4);
                String subject=rs.getString(5); 
                int copies=Integer.parseInt(rs.getString(6));
                Dvd d=new Dvd(id,name,subject,copies,Itemid,type);
                LibraryManagementSystem.setDvd(d);
            }
        }
        catch(NumberFormatException | SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void FillLoans()
    {
        try
        {
            ResultSet rs=stmt.executeQuery("Select* from Loan");
            while(rs.next())
            {
                int id=Integer.parseInt(rs.getString(1));
                String Issue=rs.getString(2);
                String Due=rs.getString(3);
                String Return=null;
                if(!rs.getString(4).equals(""))
                    Return=rs.getString(4);
                int Userid=Integer.parseInt(rs.getString(5));
                int Itemid=Integer.parseInt(rs.getString(6));
                User u=null;
                Item i=null;
                if(LibraryManagementSystem.Users!=null &&
                        (LibraryManagementSystem.Books!=null  || LibraryManagementSystem.Dvds!=null))
                {
                    u=LibraryManagementSystem.searchUser(Userid);
                    i=LibraryManagementSystem.searchItem(Itemid);
                }
                Loan l=new Loan(id,u,i,Issue,Due,Return);
                u.setLoan(l);
                i.setLoan(l);
            }
        }
        catch(NumberFormatException | SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void AddBook(int id,String name,String author,String publisher,String subject,int copies)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call AddBook(?,?,?,?,?,?)}");
            cstmt.setInt(1,id);
            cstmt.setString(2, name);
            cstmt.setString(3, author);
            cstmt.setString(4, publisher);
            cstmt.setString(5, subject);
            cstmt.setInt(6, copies);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void AddDvd(int id,String name,String subject,int copies)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call AddDVD(?,?,?,?)}");
            cstmt.setInt(1,id);
            cstmt.setString(2, name);
            cstmt.setString(3, subject);
            cstmt.setInt(4, copies);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void RemoveBook(int id)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call RemoveBook(?)}");
            cstmt.setInt(1, id);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void RemoveDvd(int id)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call RemoveDVD(?)}");
            cstmt.setInt(1, id);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void AddUser(int id,String name,String Email,String Password,String Phone,String Add,int type)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call AddUser(?,?,?,?,?,?,?)}");
            cstmt.setInt(1,id);
            cstmt.setString(2, name);
            cstmt.setString(3, Email);
            cstmt.setString(4, Password);
            cstmt.setString(5, Phone);
            cstmt.setString(6, Add);
            cstmt.setInt(7,type);          
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

    }
    
    void RemoveUser(int id)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call RemoveUser(?)}");
            cstmt.setInt(1, id);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void EditUser(int id,String name,String Email,String phone,String Add,int type)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call EditUser(?,?,?,?,?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, name);
            cstmt.setString(3, Email);
            cstmt.setString(4, phone);
            cstmt.setString(5, Add);
            cstmt.setInt(6, type);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void createloan(int id,String issue, String due, int Uid, int Iid)
    {
        try
        {
            CallableStatement cstmt=con.prepareCall("{call CheckOutItem(?,?,?,?,?)}");
            cstmt.setInt(1,id);
            cstmt.setString(2, issue);
            cstmt.setString(3, due);
            cstmt.setInt(4,Uid);
            cstmt.setInt(5,Iid);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void CheckIn(int id, String RetDate, int Iid)
    {        
        try
        {
            CallableStatement cstmt=con.prepareCall("{call CheckInItem(?,?,?)}");
            cstmt.setInt(1,id);
            cstmt.setString(2, RetDate);
            cstmt.setInt(3,Iid);
            cstmt.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

    }
}

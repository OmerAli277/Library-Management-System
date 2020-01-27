/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.util.*;

/**
 *
 * @author omerali
 */
public class LibraryManagementSystem {
    static public User []Users;
    static public Book []Books;
    static public Dvd []Dvds;
    static public int NumOfUsers=0;
    static private int NumOfBooks=0;
    static private int NumOfDvds=0;
    static public int TotalNumOfLoans=0;
    static public int NumOfItems=0;
    static private int UsersRemoved=0;
    static private int BookRemoved=0;
    static private int DvdRemoved=0;

    /**
     *
     * @param u
     */
    static public void setUser(User u)
    {
        NumOfUsers++;
        if(Users==null)
        {    
            Users=new User[1];
        }
        else
        {
            if(NumOfUsers>Users.length)
                DoubleUserArray();
        }
        Users[NumOfUsers-1]=u;
    }
    
    static public boolean LoanExist(int id)
    {
        boolean found=false;
        if(id<=TotalNumOfLoans)
            found =true;
        return found;
    }
    
    /**
     *
     * @param i
     */
    static public void setBook(Book i)
    {
        NumOfBooks++;
        NumOfItems++;
        if(Books==null)
        {    
            Books=new Book[1];
        }
        else
        {
            if(NumOfBooks>Books.length)
                DoubleBookArray();
        }
        Books[NumOfBooks-1]=i;
    }
    
    static public void setDvd(Dvd i)
    {
        NumOfDvds++;
        NumOfItems++;
        if(Dvds==null)
        {    
            Dvds=new Dvd[1];
        }
        else
        {
            if(NumOfDvds>Dvds.length)
                DoubleDvdArray();
        }
        Dvds[NumOfDvds-1]=i;
    }
    
    static public void removeUser(int id)
    {
        UsersRemoved++;
        int i;
        boolean flag=false;
        for(i=0; i<Users.length  && !flag; i++)
        {
            if(Users[i].userId==id)
                flag=true;
        }
        Users[i]=null;
        if(UsersRemoved==NumOfUsers/2)
        {
            HalfUserArray();
            UsersRemoved=0;
        }
    }
    
    static public void removeBook(int id)
    {
        BookRemoved++;
        int i;
        boolean flag=false;
        for(i=0; i<Books.length  && !flag; i++)
        {
            if(Books[i].id==id)
                flag=true;
        }
        Books[i]=null;
        if(BookRemoved==NumOfBooks/2)
        {
            HalfBookArray();
            BookRemoved=0;
        }
    }
    
    static public void removeDvd(int id)
    {
        DvdRemoved++;
        int i;
        boolean flag=false;
        for(i=0; i<Dvds.length  && !flag; i++)
        {
            if(Dvds[i].id==id)
                flag=true;
        }
        Dvds[i]=null;
        if(DvdRemoved==NumOfDvds/2)
        {
            HalfDvdArray();
            DvdRemoved=0;
        }       
    }
    
    static String[][] borrowers()
    {
        int b=0;
        for(int i=0;i<NumOfUsers;i++)
        {
            if(Users[i].type==1)
            {
                b++;
            }
        }
        String d[][]=new String[b][];
        for(int i=0, k=0;i<NumOfUsers;i++)
        {
            if(Users[i].type==1)
            {
                d[k]=new String[7];
                d[k][0]=Integer.toString(Users[i].userId);
                d[k][1]=Users[i].name;
                d[k][2]=Users[i].email;
                d[k][3]=Users[i].password;
                d[k][4]=Users[i].phoneNo;
                d[k][5]=Users[i].address;
                d[k][6]=Integer.toString(Users[i].type);
                k++;
            }
        }
        return d;
    }
    static String[][] Clerks()
    {
        int b=0;
        for(int i=0;i<NumOfUsers;i++)
        {
            if(Users[i].type==2)
            {
                b++;
            }
        }
        String d[][]=new String[b][];
        for(int i=0, k=0;i<NumOfUsers;i++)
        {
            if(Users[i].type==2)
            {
                d[k]=new String[7];
                d[k][0]=Integer.toString(Users[i].userId);
                d[k][1]=Users[i].name;
                d[k][2]=Users[i].email;
                d[k][3]=Users[i].password;
                d[k][4]=Users[i].phoneNo;
                d[k][5]=Users[i].address;
                d[k][6]=Integer.toString(Users[i].type);
                k++;
            }
        }
        return d; 
    }
    static String[][] Librarians()
    {
        int b=0;
        for(int i=0;i<NumOfUsers;i++)
        {
            if(Users[i].type==3)
            {
                b++;
            }
        }
        String d[][]=new String[b][];
        for(int i=0, k=0;i<NumOfUsers;i++)
        {
            if(Users[i].type==3)
            {
                d[k]=new String[7];
                d[k][0]=Integer.toString(Users[i].userId);
                d[k][1]=Users[i].name;
                d[k][2]=Users[i].email;
                d[k][3]=Users[i].password;
                d[k][4]=Users[i].phoneNo;
                d[k][5]=Users[i].address;
                d[k][6]=Integer.toString(Users[i].type);
                k++;
            }
        }
        return d;
    }
    
    static public User searchUser(int id)
    {
        boolean found=false;
        User temp=null;
        for(int i=0 ; i<Users.length && !found ;i++)
        {
            if(Users[i]!=null && Users[i].userId==id)
            {
                found=true;
                temp=Users[i];
            }
        }
        return temp;
    }
    
    static public Book searchbook(int id)
    {
        boolean found=false;
        Book temp=null;
        for(int i=0 ; i<Books.length && !found ;i++)
        {
            if(Books[i]!=null && Books[i].checkBook(id))
            {
                found=true;
                temp=Books[i];
            }
        }
        return temp;
    }
       static String[][] alldvds()
    {
        String d[][]=new String[NumOfDvds][];
        for(int k=0;k<NumOfDvds;k++)
        {
            
                d[k]=new String[4];
                d[k][0]=Integer.toString(Dvds[k].id);
                d[k][1]=Dvds[k].getName();
                d[k][2]=Dvds[k].getSubject();
                d[k][3]=Integer.toString(Dvds[k].getCopies());
                
            
        }
        return d;
    }
    
        static public Dvd searchDvd(int id)
    {
        boolean found=false;
        Dvd temp=null;
        for(int i=0 ; i<Dvds.length && !found ;i++)
        {
            if(Dvds[i]!=null && Dvds[i].dvdid==id)
            {
                found=true;
                temp=Dvds[i];
            }
        }
        return temp;
    }
    
    static public Item searchItem(int id)
    {
        Book b=LibraryManagementSystem.searchbook(id);
        Dvd d=LibraryManagementSystem.searchDvd(id);
        Item i=null;
        if(b!=null && d==null)
            i=b;
        else
            i=d;
        return i;        
    }
    
    static private void DoubleUserArray()
    {
        User []a=new User [Users.length*2];
        for(int i=0;i<Users.length;i++)
        {
            a[i]=Users[i];
        }
        Users=a;
    }
    
    static private void DoubleBookArray()
    {
        Book []a=new Book [Books.length*2];
        for(int i=0;i<Books.length;i++)
        {
            a[i]=Books[i];
        }
        Books=a;
    }
    
    static private void DoubleDvdArray()
    {
        Dvd []a=new Dvd [Dvds.length*2];
        for(int i=0;i<Dvds.length;i++)
        {
            a[i]=Dvds[i];
        }
        Dvds=a;
    }
    static String[][] allbooks()
    {
        String d[][]=new String[NumOfBooks][];
        for(int k=0;k<NumOfBooks;k++)
        {
            
                d[k]=new String[6];
                d[k][0]=Integer.toString(Books[k].id);
                d[k][1]=Books[k].getName();
                d[k][2]=Books[k].getAuthor();
                d[k][3]=Books[k].getPublisher();
                d[k][4]=Books[k].getSubject();
                d[k][5]=Integer.toString(Books[k].getNumOfCopies());
                
            
        }
        return d;
    }
    
    static private void HalfUserArray()
    {
        User a[]=new User [Users.length/2];
        for(int i=0;i<Users.length;i++)
        {
            if(Users[i]!=null)
                a[i]=Users[i];
        }
        Users=a;
    }
    
    static private void HalfBookArray()
    {
        Book a[]=new Book [Books.length/2];
        for(int i=0;i<Books.length;i++)
        {
            if(Books[i]!=null)
                a[i]=Books[i];
        }
        Books=a;
    }
    
    static private void HalfDvdArray()
    {
        Dvd a[]=new Dvd [Dvds.length/2];
        for(int i=0;i<Dvds.length;i++)
        {
            if(Dvds[i]!=null)
                a[i]=Dvds[i];
        }
        Dvds=a;       
    }
}

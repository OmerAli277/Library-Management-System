/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author omerali
 */
public class Book extends Item {
    private int Bookid;
    private String name;
    private String author;
    private String publisher;
    private String Subject;
    private int NumOfCopies;

    public int getBookid() {
        return Bookid;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSubject() {
        return Subject;
    }

    public int getNumOfCopies() {
        return NumOfCopies;
    }
    
    

    public Book(int bookid, String name, String author, String publisher, String Subject, int NumOfCopies, int id, int type) {
        super(id, type);
        this.Bookid = bookid;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.Subject = Subject;
        this.NumOfCopies = NumOfCopies;
    }
    
  
    
    public boolean checkBook(int id)
    {
        boolean flag=false;
        if(this.Bookid==id)
            flag=true;
        return flag;
    }

    @Override
    void copyCheckedout() {
        this.NumOfCopies--;
    }

    @Override
    void copyCheckedin() {
        this.NumOfCopies++;
    }
    
}

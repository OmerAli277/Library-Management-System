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
public class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    protected String phoneNo;
    protected String address;
    protected int type;
    protected List<Loan> Loans=new ArrayList<>();

    public User(int userId, String name, String email, String password, String phoneNo, String address, int type) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
        this.type = type;
    }
    
    public void setLoan(Loan l)
    {
        boolean add = Loans.add(l);
    }
    
}

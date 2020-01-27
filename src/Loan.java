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
public class Loan {
    int LoanId;
    User U;
    Item I;
    String IssuedDate;
    String DueDate;
    String ReturnDate;

    public Loan(int LoanId, User U, Item I, String IssuedDate, String DueDate, String ReturnDate) {
        this.LoanId = LoanId;
        this.U = U;
        this.I = I;
        this.IssuedDate = IssuedDate;
        this.DueDate = DueDate;
        this.ReturnDate = ReturnDate;
    }
    
    
}

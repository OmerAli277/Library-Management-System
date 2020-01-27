/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author omerali
 */
public abstract class Item {
    int id;
    int type;
    protected List<Loan> Loans=new ArrayList<>();


    public Item(int id, int type) {
        this.id = id;
        this.type = type;
    }
    
    public void setLoan(Loan l)
    {
        boolean add = Loans.add(l);
    }
    
    public Loan searchLoan(int id)
    {
        boolean found=false;
        int size=Loans.size();
        Loan L=null;
        for(int i=0;i<size && !found;i++)
        {
            if(L.LoanId==id)
            {
                found=true;
            }
        }
        return L;
    }
    
    abstract void copyCheckedout();
    abstract void copyCheckedin();
}

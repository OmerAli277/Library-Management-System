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
public class Dvd extends Item {
    int dvdid;
    String name;
    String subject;
    int copies;

    public Dvd(int dvdid, String name, String subject, int copies, int id, int type) {
        super(id, type);
        this.dvdid = dvdid;
        this.name = name;
        this.subject = subject;
        this.copies = copies;
    }

    public int getDvdid() {
        return dvdid;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public int getCopies() {
        return copies;
    }
    
    

    public void DvdCheckedout()
    {
        copies--;
    }
    
    public void DvdCheckedin()
    {
        copies++;
    }

    @Override
    void copyCheckedout() {
        this.copies--;
    }

    @Override
    void copyCheckedin() {
        this.copies++;
    }
    
    
}

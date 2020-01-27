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
public class Borrower extends User {

    public Borrower(int userId, String name, String email, String password, String phoneNo, String address, int type) {
        super(userId, name, email, password, phoneNo, address, type);
    }

}
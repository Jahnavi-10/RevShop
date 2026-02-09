package com.revshop.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	//final keyword is used to define constant values
	private static final String URL ="jdbc:mysql://localhost:3306/revshop_db";
    private static final String USER = "root";     //username 
    private static final String PASSWORD = "Csea@502";  //password

    public static Connection getConnection() 
    {
        try 
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}
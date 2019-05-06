/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchat.networking;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author krithika
 */
public class Database {

    
    String getdate()
     {
         DateFormat dateformat=new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
         Date date=new Date();
         String n=dateformat.format(date);
         return n;         
     }

     String message=null;
     int i=0;
     int sender=0;
     int reciever=0;
     String datentime=getdate();
     
     
    /** 
     * @param args the command line arguments
     */
    public void setdata(String message,int reciever,int sender)
    {
       
        this.message=message;
        this.sender=sender;
        this.reciever=reciever;
        this.datentime=datentime;
        datentime=getdate();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatlog","root","");
            String sql = "insert into chats(Sender,Reciever,datentime,message) values('"+sender+"','"+reciever+"','"+datentime+"','"+message+"')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    public int getID()
    {
        int ID=0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/chatlog","root","");
            Statement stat=conn.createStatement();
            String querry="select * from chats ORDER BY ID DESC LIMIT 1";
            ResultSet rs = stat.executeQuery(querry);
            if(rs.next())
            {
                ID=rs.getInt("ID");
            }
            else
            {
                System.out.println("Cannot get ID");
            }
        }
        catch(Exception ex)
        {
            System.out.println("BAD REQUEST");
        }
        System.out.println("returnng ID: "+ID);
        return ID;
    }
}

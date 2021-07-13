package millennium_airways;
import java.sql.*;
public class Tableprinter {
    public void gap(int a,int b)
    {
        for(int i=b;i<=a;i++)
        {
            System.out.print(" ");
        }
    }
    public void crew(ResultSet rs)
    {
        System.out.print("Crew");
        gap(5,4);
        System.out.println("Crew name");
        try{
            while(rs.next())
            {
                System.out.print(rs.getString(1));
                gap(5,2);
                System.out.println(rs.getString(2));
            }
        }catch (SQLException e) { e.printStackTrace();}
    }
    public void details(ResultSet rs)
    {
        System.out.print("Slno");
        gap(5,4);
        System.out.print("Day");
        gap(11,3);
        System.out.print("Flight");
        gap(7, 6);
        System.out.print("Pilot");
        gap(12,5);
        System.out.print("Co-pilot");
        gap(12,8);
        System.out.println("Crew");
        try {
            while (rs.next()) {
                System.out.print(rs.getInt(1));
                gap(5,String.valueOf(rs.getInt(1)).length());
                System.out.print(rs.getString(2));
                gap(11,rs.getString(2).length());
                System.out.print(rs.getString(3));
                gap(7, rs.getString(3).length());
                System.out.print(rs.getString(4));
                gap(12,rs.getString(4).length());
                System.out.print(rs.getString(5));
                gap(12,rs.getString(5).length());
                System.out.println(rs.getString(6));
            }
        }catch (SQLException e) { e.printStackTrace();}
    }
    void record(ResultSet rs)
    {
        System.out.print("PNR");
        gap(6,3);
        System.out.print("Name");
        gap(15,4);
        System.out.print("From");
        gap(12,4);
        System.out.print("To");
        gap(12,2);
        System.out.print("Flight");
        gap(7, 6);
        System.out.print("Departure Time");
        gap(15, 14);
        System.out.print("Arrival Time");
        gap(13,12 );
        System.out.println("Date of travel");
        try{
            while(rs.next())
            {
                System.out.print(rs.getInt(1));
                gap(6,String.valueOf(rs.getInt(1)).length());
                System.out.print(rs.getString(2));
                gap(15,rs.getString(2).length());
                System.out.print(rs.getString(3));
                gap(12,rs.getString(3).length());
                System.out.print(rs.getString(4));
                gap(12,rs.getString(4).length());
                System.out.print(rs.getString(5));
                gap(7,rs.getString(5).length());
                System.out.print(rs.getInt(6));
                gap(15,String.valueOf(rs.getInt(6)).length());
                System.out.print(rs.getInt(7));
                gap(13,String.valueOf(rs.getInt(7)).length());
                System.out.println(rs.getString(8));
            }
        } catch (SQLException e) { e.printStackTrace();}
    }
    void schedule(ResultSet rs)
    {
        System.out.print("Slno");
        gap(5,4);
        System.out.print("Flight");
        gap(7, 6);
        System.out.print("From");
        gap(12,4);
        System.out.print("To");
        gap(12,2);
        System.out.print("Departure Time");
        gap(15, 14);
        System.out.println("Arrival Time");
        try{
            while(rs.next())
            {
                System.out.print(rs.getInt(1));
                gap(5,String.valueOf(rs.getInt(1)).length());
                System.out.print(rs.getString(2));
                gap(7, rs.getString(2).length());
                System.out.print(rs.getString(3));
                gap(12,rs.getString(3).length());
                System.out.print(rs.getString(4));
                gap(12,rs.getString(4).length());
                System.out.print(rs.getInt(5));
                gap(15, String.valueOf(rs.getInt(5)).length());
                System.out.println(rs.getInt(6));
            }
        }catch (SQLException e) { e.printStackTrace();}
    }
}
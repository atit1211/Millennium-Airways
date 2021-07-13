package millennium_airways;
import java.sql.*;
import static java.sql.Types.NULL;
/*
    note:-
        Example on how to connect and execute a quary
    try{
      Connection connection_DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","atit","atitpatel");
      System.out.println("data conn success");
      Statement statement_DB  = connection_DB.createStatement();
      ResultSet result_set_DB = statement_DB.executeQuery("select *from flt_dtl");
      }catch (SQLException err) {System.out.println(err.getMessage());}
*/
class DBconnection extends Tableprinter
{
    Connection con;
    Statement stat;
    ResultSet rs;
    DBconnection()
    {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","atit","atitpatel");
        }catch (SQLException err) {System.out.println(err.getMessage());}
    }
    //passenger methods
    public void add_record(String[] name,String from ,String to,String date)
    {
        int arival = 0;
        int departure = 0;
        String ftl = null;
        int pnr = 0;

        try {
            stat = con.createStatement();
            String qry = "SELECT Flights,schedule.`A-time`,schedule.`D-time` FROM airline.schedule WHERE schedule.From ='" + from + "' AND schedule.To ='" + to + "'";
            rs = stat.executeQuery(qry);
            while (rs.next()) {
                arival = rs.getByte("A-time");
                departure = rs.getByte("D-time");
                ftl = rs.getString("Flights");
            }
            rs = stat.executeQuery("SELECT *FROM airline.record");
            while (rs.next()) {
                pnr = rs.getInt("pnr");
            }
            if (pnr == NULL) {
                pnr = 1001;
            }
            else {
                pnr = pnr + 1;
            }
            String s1 =String.valueOf(pnr);
            String s2 =String.valueOf(departure);
            String s3 =String.valueOf(arival);
            for (String s : name) {
                String Q1 = "INSERT INTO `record` (`pnr`,`name`,`from`,`to`,`flight`,`d-time`,`a-time`,`date`) VALUES(" + s1 + ",'" + s + "','" + from + "','" + to + "','" + ftl + "'," + s2 + "," + s3 + ",'" + date + "')";
                stat.executeUpdate(Q1);
            }
            String quary = "SELECT * FROM airline.record Where pnr="+s1;
            rs = stat.executeQuery(quary);
            record(rs);
        } catch (SQLException e) { e.printStackTrace();}
    }
    public void del_record(int pnr)
    {
        try{
            stat = con.createStatement();
            String s1 = String.valueOf(pnr);
            String qry = "DELETE FROM airline.record where pnr="+s1;
            stat.executeUpdate(qry);
        } catch (SQLException e) { e.printStackTrace();}
    }
    public void get_record(int pnr)
    {
        try{
            stat = con.createStatement();
            String s1 = String.valueOf(pnr);
            String qry = "SELECT * FROM airline.record WHERE pnr="+s1;
            rs = stat.executeQuery(qry);
            record(rs);
        } catch (SQLException e) { e.printStackTrace();}
    }
    //pilot and co-pilot
    public void get_details(int op,String uname,String day)
    {
        String qry = null;
        String other = null;
        String[] cnames = new String[3];
        if (op == 1)
        {
            qry ="SELECT *FROM airline.details WHERE `Pilot`='"+uname+"' AND Day='"+day+"'";
        }
        else
        {
            qry="SELECT *FROM airline.details WHERE `Co-pilot`='"+uname+"' AND Day='"+day+"'";
        }
        try{
            stat = con.createStatement();
            rs = stat.executeQuery(qry);
            String crew = null;
            String flight=null;
            while(rs.next()) {
                flight = rs.getString("Flights");
                crew = rs.getString("Crew");
                if (op == 1) {
                    other = rs.getString("Co-pilot");
                }
                else {
                    other = rs.getString("Pilot");
                }
            }
            if (flight == null)
            {
                System.out.println("You don't have any Flights today.");
                return;
            }
            System.out.println("Flight Code = "+ flight);
            System.out.println("Co-pilot name->"+other);
            System.out.print("Crew member names:-  ");
            //SELECT *FROM airline.crew WHERE Crew ="crew"
            String qry1 ="SELECT *FROM airline.crew WHERE Crew ='"+crew+"'";
            rs = stat.executeQuery(qry1);
            int i=0;
            while(rs.next())
            {
                cnames[i]=rs.getString(2);
                i++;
            }
            while(i > 0)
            {
                i--;
                System.out.print(cnames[i]+"   ");
            }
            System.out.println();
            String qry2="SELECT * FROM airline.schedule WHERE Flights='"+flight+"'";
            rs = stat.executeQuery(qry2);
            schedule(rs);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    //for crew member
    public void get_details(String uname,String day)
    {
        try{
            stat = con.createStatement();
            String qry = "Select * FROM airline.crew Where `Crew name`='"+uname+"'";
            rs = stat.executeQuery(qry);
            String cname =null;
            while(rs.next())
            {
                cname = rs.getString(1);
            }
            String qry1 = "SELECT *FROM airline.details WHERE Crew='"+cname+"' AND Day='"+day+"'";
            rs = stat.executeQuery(qry1);
            String pilot = null;
            String copilot = null;
            String flight = null;
            while(rs.next())
            {
                pilot = rs.getString("Pilot");
                copilot = rs.getString("Co-pilot");
                flight = rs.getString("Flights");
            }
            if (flight == null)
            {
                System.out.println("You don't have any Flights today.");
                return;
            }
            System.out.println("Pilot ->"+pilot);
            System.out.println("Co-Pilot ->"+copilot);
            String qry2="SELECT * FROM airline.schedule WHERE Flights='"+flight+"'";
            rs = stat.executeQuery(qry2);
            schedule(rs);

        } catch (SQLException e) { e.printStackTrace(); }
    }
    //admin methods
    public void get_table(String tname)                                                                 //print any table
    {
        try{
            stat = con.createStatement();
            String qry = "SELECT *FROM "+tname;
            rs = stat.executeQuery(qry);
            if(tname.equals("crew"))
                crew(rs);
            else if(tname.equals("details"))
                details(rs);
            else if(tname.equals("record"))
                record(rs);
            else
                schedule(rs);
        } catch (SQLException e) { e.printStackTrace();}
    }

    public void add_table(String crew,String crew_name)                                                 //add crew table row
    {
        try{
            stat = con.createStatement();
            String qry = "INSERT INTO `crew` (`crew`,`Crew name`) VALUES('"+crew+"','"+crew_name+"')";
           stat.executeUpdate(qry);
        } catch (SQLException e) { e.printStackTrace();}
    }
    public void add_table(String day,String flights,String pilot,String co_pilot,String crew)           //add details table row
    {
        try{
            stat = con.createStatement();
            int slno = 0;
            //SELECT slno FROM details
            String qry ="SELECT slno FROM details";
            rs = stat.executeQuery(qry);
            while(rs.next())
                slno = rs.getInt(1);
            slno = slno+1;
            String s = String.valueOf(slno);
            String qry1 = "INSERT INTO `details`(`slno`,`Day`,`Flights`,`Pilot`,`Co-pilot`,`Crew`) VALUES("+s+",'"+day+"','"+flights+"','"+pilot+"','"+co_pilot+"','"+crew+"')";
            stat.executeUpdate(qry1);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void add_table(String flights,String from,String to,int dtime,int atime)                     //add schedule table row
    {
        try{
            int slno = 0;
            stat = con.createStatement();
            //SELECT slno FROM schedule
            String qry ="SELECT slno FROM schedule";
            rs = stat.executeQuery(qry);
            while(rs.next())
                slno = rs.getInt(1);
            slno = slno+1;
            String s = String.valueOf(slno);
            String d = String.valueOf(dtime);
            String a = String.valueOf(atime);
            String qry1 = "INSERT INTO `schedule`(`slno`,`Flights`,`From`,`To`,`D-time`,`A-time`) VALUES("+s+",'"+flights+"','"+from+"','"+to+"','"+d+"','"+a+"')";
            stat.executeUpdate(qry1);
        } catch (SQLException e) { e.printStackTrace(); }
    }
                                                                                                        //add record table row is add_record
    public void del_table(String cname)                                                                 //delete crew table row
    {
        try{
            stat = con.createStatement();
            String qry = "DELETE FROM crew WHERE `Crew name`='"+cname+"'";
            stat.executeUpdate(qry);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void del_table(String tname,int slno)                                                        //delete details and schedule table
    {
       try{
           stat = con.createStatement();
           String s =String.valueOf(slno);
           //"DELETE FROM "+tname+" WHERE slno="+s
           String qry = "DELETE FROM "+tname+" WHERE slno="+s;
           stat.executeUpdate(qry);
       } catch (SQLException e) { e.printStackTrace(); }
    }
                                                                                                        //delete record is del_record
}

package millennium_airways;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;
public class Pilot_crew
{
    Scanner sc = new Scanner(System.in);
    DBconnection dbconn = new DBconnection();
    String uname;
    int op;
    public Pilot_crew(int choice)
    {
        if (choice == 2)
            Pilot();
        else
            Crew();
    }
    void Pilot()
    {
        System.out.println("select:-\n(1)Pilot  (2)Co-Pilot");
        op = sc.nextInt();
        System.out.print("Enter Username:- ");
        uname = sc.next();
        String day =get_day();
        dbconn.get_details(op,uname,day);
    }
    void Crew()
    {
        System.out.print("Enter your Username:- ");
        String name = sc.next();
        String day = get_day();
        dbconn.get_details(name,day);
    }
    public String get_day()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 1)
            return "Sunday";
        else if(dayOfWeek == 2)
            return "Monday";
        else if(dayOfWeek == 3)
            return "Tuesday";
        else if(dayOfWeek == 4)
            return "Wednesday";
        else if(dayOfWeek == 5)
            return "Thursday";
        else if(dayOfWeek == 6)
            return "Friday";
        else if(dayOfWeek == 7)
            return "Saturday";
        return null;
    }
}
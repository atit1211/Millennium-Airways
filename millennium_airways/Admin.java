package millennium_airways;
import java.util.Scanner;
public class Admin
{
    Scanner sc = new Scanner(System.in);
    DBconnection dbconn = new DBconnection();
    public Admin()
    {
        int choice;
        System.out.println("Select an option:-\n(1)View Tables \n(2)Edit Tables \n(0)Exit");
        choice = sc.nextInt();
        if (choice == 1)
            view_table();
        else if(choice == 2)
            edit_table();
    }
    public void view_table()
    {
        System.out.println("Which table do you want to view :-\n(1)Crew\n(2)Details\n(3)Record\n(4)Schedule");
        int choice = sc.nextInt();
        if(choice == 1)
            dbconn.get_table("crew");
        else if(choice == 2)
            dbconn.get_table("details");
        else if(choice == 3)
            dbconn.get_table("record");
        else
            dbconn.get_table("schedule");
        new Admin();
    }
    public void edit_table()
    {
        System.out.println("Which table do you want to edit :-\n(1)Crew\n(2)Details\n(3)Record\n(4)Schedule");
        int  choice = sc.nextInt();
        if(choice == 1)//crew table
        {
            System.out.println("Do you want to:- \n(1)ADD Record \n(2)DELETE Record");
            choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.println("Enter the details:-");
                System.out.print("Enter the Crew code:-");
                String crew = sc.next();
                System.out.print("Enter the crew member name:-");
                String crew_name = sc.next();
                dbconn.add_table(crew,crew_name);
            }
            else
            {
                System.out.print("Enter the name of the crew member:-");
                String cname = sc.next();
                dbconn.del_table(cname);
            }
        }
        else if(choice == 2)//details table
        {
            System.out.println("Do you want to:- \n(1)ADD Record \n(2)DELETE Record");
            choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.println("Enter the details:-");
                System.out.print("Enter the day of the week:-");
                String day = sc.next();
                System.out.print("Enter the Flight Code:-");
                String flights = sc.next();
                System.out.print("Enter the Pilot name:-");
                String pilot = sc.next();
                System.out.print("Enter the Co-pilot name:-");
                String co_pilot = sc.next();
                System.out.print("Enter the Crew code:-");
                String crew = sc.next();
                dbconn.add_table(day,flights,pilot,co_pilot,crew);
            }
            else
            {
                System.out.print("Enter the serial number of the record to delete it:-");
                int slno = sc.nextInt();
                dbconn.del_table("details",slno);
            }
        }
        else if(choice == 3)//record table
        {
            System.out.println("Do you want to:- \n(1)ADD Record \n(2)DELETE Record");
            choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.print("Enter the number of passengers:-");
                int no = sc.nextInt();
                String[] name = new String[no];
                for(int n = 0 ; n < no ; n++ )
                {
                    System.out.print("Enter the Name of passenger "+(n+1)+" :-");
                    name[n] = sc.next();
                }
                System.out.println("Available cities: (1).BANGALORE (2).PUNE (3).MUMBAI (4).HYDRABAD (5).UDAIPUR");
                System.out.print("Select the Departure City No.:-");
                int pick_from = sc.nextInt();
                String from = null;
                switch(pick_from)
                {
                    case 1:from = "Bangalore";
                        break;
                    case 2:from = "Pune";
                        break;
                    case 3:from = "Mumbai";
                        break;
                    case 4:from = "Hydrabad";
                        break;
                    case 5:from = "Udaipur";
                        break;
                }
                System.out.print("Select the Destination City No:-");
                int pick_to = sc.nextInt();
                String to = null;
                switch(pick_to)
                {
                    case 1:to = "Bangalore";
                        break;
                    case 2:to = "Pune";
                        break;
                    case 3:to = "Mumbai";
                        break;
                    case 4:to = "Hydrabad";
                        break;
                    case 5:to = "Udaipur";
                        break;
                }
                System.out.print("Enter the date of travel in dd/mm/yy format:-");
                String date = sc.next();
                dbconn.add_record(name,from,to,date);
            }
            else
            {
                System.out.print("Enter the PNR:-");
                int pnr = sc.nextInt();
                System.out.println("Are you sure YES/NO");
                String conf = sc.next();
                //if yes clear the record with the matching pnr
                if (conf.equals("yes") || conf.equals("YES"))
                {
                    dbconn.del_record(pnr);
                }
            }
        }
        else//schedule table
        {
            System.out.println("Do you want to:- \n(1)ADD Record \n(2)DELETE Record");
            choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.println("Enter the details:-");
                System.out.print("Enter the Flight code:-");
                String flight = sc.next();
                System.out.print("Enter the From location:-");
                String from = sc.next();
                System.out.print("Enter the To location:-");
                String to = sc.next();
                System.out.print("Enter the Departure time:-");
                int dtime = sc.nextInt();
                System.out.print("Enter the arrival time:-");
                int atime = sc.nextInt();
                dbconn.add_table(flight,from,to,dtime,atime);
            }
            else
            {
                System.out.print("Enter the serial number of the record to delete it:-");
                int slno = sc.nextInt();
                dbconn.del_table("schedule",slno);
            }

        }
        System.out.println("Edit Successful!!!");
        new Admin();
    }
}

package millennium_airways;
import java.util.Scanner;
public class Passenger
{
    Scanner sc = new Scanner(System.in);
    DBconnection dbconn = new DBconnection();
    public Passenger()
    {
        System.out.println("Select an option \n(1)Book tickets \n(2)Cancel tickets \n(3)Get details");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Book_tickets();
                break;
            case 2:
                Cancel_tickets();
                break;
            case 3:
                Get_details();
                break;
            default:
        }
    }

    void Book_tickets()
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
        //pass the input of names from and to ,to the db connection methods
        System.out.println("Booking Successful!!!");
        dbconn.add_record(name,from,to,date);
    }
    void Cancel_tickets()
    {
        System.out.print("Enter the PNR:-");
        int pnr = sc.nextInt();
        System.out.println("Are you sure YES/NO");
        String choice = sc.next();
        if (choice.equals("yes") || choice.equals("YES"))
        {
            dbconn.del_record(pnr);
            System.out.println("!! Tickets successfully Canceled !!");
            return;
        }
        System.out.println("!! Tickets Failed to cancel !!");
    }
    void Get_details()
    {
        System.out.print("Enter the PNR:-");
        int pnr = sc.nextInt();
        dbconn.get_record(pnr);
    }
}

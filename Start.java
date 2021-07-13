import millennium_airways.*;
import java.util.Scanner;
public class Start
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("select an option:-\n(1)Passenger\n(2)Pilot OR Co-Pilot\n(3)Crew member\n(4)Admin");
        try{
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    Passenger pass = new Passenger();
                    break;
                case 2:
                case 3:
                    Pilot_crew pilotcrew = new Pilot_crew(choice);
                    break;
                case 4:
                    Admin admin = new Admin();
                    break;
                default:
                    System.out.println("you selected the wrong option");
                    break;
            }
        } catch (Exception e) { System.out.println("Correct option not selected!!!"); }

        Start finish = new Start();
        finish.end();
    }
    void end()
    {
        System.out.println("-----THANK------------------ . ---------------------------------");
        System.out.println("-----YOU-------------------- . ---------------------------------");
        System.out.println("-----FOR------------------- . . --------------------------------");
        System.out.println("-----USING-------------- . - . - . -----------------------------");
        System.out.println("-----THE------------- . ---- . ---- . --------------------------");
        System.out.println("-----PROGRAM------ . ------- . ------- . -----------------------");
        System.out.println("---------------------------- . ---------------------------------");
        System.out.println("-------------------------- . . . -------------------------------");
        System.out.println("------------------------- . --- . ------------------------------");

    }
}

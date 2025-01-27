import java.util.*;
import java.text.*;

public class Main {

    static int displayMenu(Scanner scanner) {
        System.out.println("[1] Check Current Time & Date");
        System.out.println("[2] Add Event");
        System.out.println("[3] Check Upcoming Events");
        System.out.println("[4] Check Previous Events");
        System.out.println("[5] Exit");
        System.out.print("> ");
        return scanner.nextInt();
    }

    static void checkCurrentTime(SimpleDateFormat sdf) {
        Date currentDate = new Date();
        System.out.println("---Check Current Time & Date---");
        System.out.println(sdf.format(currentDate));
        System.out.println();
    }

    static void addEvents(Scanner scanner, ArrayList<String> eventdetails, ArrayList<Date> eventdates, SimpleDateFormat sdf) {
        System.out.println("---Add Event---");
        System.out.println("Amount of Events to Add");
        System.out.print("> ");
        int num = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < num; i++) {
            System.out.println("Name of Event (#" + (i + 1) + ")");
            System.out.print("> ");
            String detail = scanner.nextLine();

            System.out.println("Scheduled Date (MM/DD/YY HH:MM(AM/PM))");
            System.out.print("> ");
            String sched = scanner.nextLine();
            try {
                Date date = sdf.parse(sched);
                eventdetails.add(detail);
                eventdates.add(date);
                System.out.println("Event Added Successfully!");
                System.out.println();
            } catch (ParseException e) {
                System.out.println("Invalid Format. Use MM/DD/YY HH:MM(AM/PM).");
                System.out.println();
            }
        }
    }

    static void checkUpcomingEvents(ArrayList<String> eventdetails, ArrayList<Date> eventdates, SimpleDateFormat sdf) {
        Date currentDate = new Date();
        boolean upcoming = false;
        System.out.println("---Upcoming Events---");
        for (int i = 0; i < eventdetails.size(); i++) {
            Date date = eventdates.get(i);
            if (date.after(currentDate)) {
                System.out.println(eventdetails.get(i) + " on " + sdf.format(date));
                System.out.println();
                upcoming = true;
            }
        }
        if (!upcoming) {
            System.out.println("No Upcoming Events.");
            System.out.println();
        }
    }

    static void checkPreviousEvents(ArrayList<String> eventdetails, ArrayList<Date> eventdates, SimpleDateFormat sdf) {
        Date currentDate = new Date();
        boolean previous = false;
        System.out.println("---Previous Events---");
        for (int i = 0; i < eventdetails.size(); i++) {
            Date date = eventdates.get(i);
            if (date.before(currentDate)) {
                System.out.println(eventdetails.get(i) + " on " + sdf.format(date));
                System.out.println();
                previous = true;
            }
        }
        if (!previous) {
            System.out.println("No Previous Events.");
            System.out.println();
        }
    }
    // Sample input
    /* static void sampleEvent() {
        System.out.println("Feast of St. Cerberi of Hellhounds on 08/28/24");
    } */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> eventdetails = new ArrayList<>();
        ArrayList<Date> eventdates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy hh:mma");

        while (true) {
            int input = displayMenu(scanner);

            if (input == 1) {
                checkCurrentTime(sdf);
            } else if (input == 2) {
                addEvents(scanner, eventdetails, eventdates, sdf);
            } else if (input == 3) {
                checkUpcomingEvents(eventdetails, eventdates, sdf);
            } else if (input == 4) {
                checkPreviousEvents(eventdetails, eventdates, sdf);
            } else if (input == 5) {
                System.out.println("Exiting...");
                System.out.println("Simple Text-Based Events Manager [Version 1.0]" + "\n(c) Puting Lobo Studios. All rights reserved.");
                break;
            // Sample output
            /* } else if (input == 6) {
                System.out.println("---Sample Event Output---");
                sampleEvent(); */
            } else {
                System.out.println("Invalid Option. Please Try Again.");
                System.out.println();
            }
        }

        scanner.close();
    }
}
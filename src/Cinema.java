import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final int SHOW_THE_SEATS = 1;
    private static final int BUY_A_TICKET = 2;
    private static final int STATISTICS = 3;
    private static final int EXIT = 0;
    private static final String FREE_SEAT = "S";
    private static final String TAKEN_SEAT = "B";

    public static void main(String[] args) {
        int soldTicketsCount = 0;
        int currentIncome = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int numberOfRows = sc.nextInt();

        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int numberOfSeats = sc.nextInt();

        String[][] seats = new String[numberOfRows][numberOfSeats];

        for (String[] seat : seats) {
            Arrays.fill(seat, FREE_SEAT);      //fill cinema map with free seats
        }

        while (true) {
            System.out.print("""

                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    >\s""");
            int menuInput = sc.nextInt();
            switch (menuInput) {
                case SHOW_THE_SEATS:
                    showTheSeats(seats);
                    continue;
                case BUY_A_TICKET:
                    currentIncome += buyATicket(sc, seats, numberOfRows, numberOfSeats);
                    soldTicketsCount++;
                    continue;
                case STATISTICS:
                    showStatistics(soldTicketsCount, seats, currentIncome);
                    continue;
                case EXIT:
                    break;
            }
            break;
        }


    }

    public static void showStatistics(int soldTickets, String[][] seats, int income) {
        int rows = seats.length;
        int seatsInRow = seats[0].length;
        int seatsCount = rows * seatsInRow;
        int totalIncome;

        if (seatsCount >= 60) {
            totalIncome = ((int)(rows / 2) * seatsInRow * 10) + ((rows - (int)(rows / 2)) * seatsInRow * 8);
        } else {
            totalIncome = seatsCount * 10;
        }

        System.out.printf("%nNumber of purchased tickets: %d%n", soldTickets);
        System.out.printf("Percentage: %.2f%%%n", (float) soldTickets * 100 / seatsCount);
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public static void showTheSeats(String[][] seats) {
        System.out.println("\nCinema:");
        System.out.print(" ");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.print(" " + (i + 1));
        }

        for (int i = 0; i < seats.length; i++) {
            System.out.print("\n" + (i + 1));
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(" " + seats[i][j]);
            }
        }
        System.out.println();
    }

    public static int buyATicket(Scanner sc, String[][] map, int numberOfRows, int numberOfSeats) {
        int selectedRow;
        int selectedSeat;

        while (true) {
            System.out.println("\nEnter a row number:");
            System.out.print("> ");
            selectedRow = sc.nextInt();

            System.out.println("Enter a seat number in that row:");
            System.out.print("> ");

            selectedSeat = sc.nextInt();

            if (selectedRow > numberOfRows || selectedSeat > numberOfSeats) {
                System.out.println("\nWrong input!");
            } else if (map[selectedRow - 1][selectedSeat - 1].equals("B")) {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                break;
            }
        }

        map[selectedRow - 1][selectedSeat - 1] = TAKEN_SEAT;
        int price = (numberOfRows * numberOfSeats) <= 60 ? 10 : (selectedRow <= (int) (numberOfRows / 2) ? 10 : 8);

        System.out.print("\nTicket price: ");
        System.out.println("$" + price);
        return price;
    }
}
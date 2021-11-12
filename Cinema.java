package cinema;
import java.util.Scanner;


public class Cinema {
    static int rows;
    static int seats;
    static char [][] cinemaHall;
    static boolean isNull = false;
    static int ticketSold;
    static int income;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seats = sc.nextInt();
        setCinemaHall();
        while (!isNull) {
            menu();
            int operation = sc.nextInt();
            switch (operation) {
                case 1:
                    printCinemaHall();
                    break;
                case 2:
                    buyATicket();
                    break;

                case 3:
                    statistics();
                    break;
                case 0:
                    isNull = true;
                    break;
            }
        }

    }

    public static void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void setCinemaHall() {
        cinemaHall = new char[rows][seats];
        for (int i = 0; i  < rows; i++) {
            for (int j = 0; j < seats; j++) cinemaHall[i][j] = 'S';
        }
    }

    public static void statistics() {
        System.out.printf("Number of purchased tickets: %d %n", ticketSold);
        System.out.printf("Percentage: %.2f%% %n", (double) ticketSold / ((double) (rows * seats)) * 100);
        System.out.printf("Current income: $%d %n", income);
        if (rows * seats <= 60) {
            System.out.printf("Total income: $%d %n", (rows * seats * 10));
        } else {
            if (rows % 2 == 0) {
                System.out.printf("Total income: $%d %n", (rows / 2) * seats * 10 + (rows / 2) * seats * 8);
            } else {
                System.out.printf("Total income: $%d %n", (rows / 2) * seats * 10 + (rows / 2 + 1) * seats * 8);
            }
        }

    }

    public static void printCinemaHall() {
        System.out.println("Cinema:");
        for (int i = 0; i <= seats; i++) {
            if (i == 0) {
                System.out.print(" " + " ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i  < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinemaHall[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buyATicket() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number: ");
            int rowTaken = sc.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seatTaken = sc.nextInt();
            if (rowTaken > rows || seatTaken > seats) {
                System.out.println("Wrong input!");
                continue;
            }
            else if (cinemaHall[rowTaken - 1][seatTaken - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            } else {
                int ticketPrice = 0;
                if (rows * seats <= 60) {
                    ticketPrice = 10;
                } else {
                    if (rowTaken <= rows / 2) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                }
                System.out.println("Ticket price: $" + ticketPrice);
                ++ticketSold;
                income += ticketPrice;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < seats; j++) {
                        if (i == rowTaken - 1 && j == seatTaken - 1) {
                            cinemaHall[i][j] = 'B';
                        }
                    }
                }
                break;
            }
        }

    }
}

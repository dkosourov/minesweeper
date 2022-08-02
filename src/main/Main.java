package main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char MINE_CELL = 'x';
    private static final char EMPTY_CELL = '-';
    private static final Random RANDOM = new Random();
    private static char[][] minefield;
    private static int width = 0;
    private static int height = 0;
    private static int minesAmount = 0;

    public static void main(String[] args) {
        try {
            extractArguments(args);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Please enter numbers as args next time!");
            return;
        }
        if (minesAmount > width * height) {
            System.out.println("Amount of mines is too big!");
            return;
        }

        buildMinefield();
        processNeighbourCells();
        printMinefield();
    }

    /**
     * Implements two scenarios of getting arguments from user
     *
     * @param args Parameters of minefield: Width, height and amount of mines
     */
    private static void extractArguments(String[] args) {

        if (args.length == 3) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            minesAmount = Integer.parseInt(args[2]);
        } else {
            System.out.println("Please enter 3 parameters next");
            Scanner in = new Scanner(System.in);
            System.out.print("Input amount of columns (width): ");
            width = in.nextInt();
            System.out.print("Input amount of rows (height): ");
            height = in.nextInt();
            System.out.print("Input mines amount: ");
            minesAmount = in.nextInt();
        }
    }

    /**
     * Sets size to 2-dimensional array, fills it with mines and empty fields and then shuffles their positions
     */
    private static void buildMinefield() {
        minefield = new char[height][width];
        int minesLeft = minesAmount;
        for (int row = 0; row < minefield.length; ++row) {
            for (int col = 0; col < minefield[row].length; ++col) {
                minefield[row][col] = getMineOrEmpty(minesLeft);
                if (minefield[row][col] == MINE_CELL) {
                    minesLeft--;
                }
            }
        }
        shuffle(minefield);
    }

    /**
     * Fills the variable with MINE_CELL value until mines amount reaches its limit
     *
     * @param minesLeft How many mines left to be placed on the minefield
     * @return MINE_CELL value or EMPTY_CELL value
     */
    private static char getMineOrEmpty(int minesLeft) {
        return minesLeft != 0 ? MINE_CELL : EMPTY_CELL;
    }

    /**
     * Shuffles a 2-dimensional array with Fisher–Yates algorithm
     *
     * @param a 2-dimensional array
     */
    private static void shuffle(char[][] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = RANDOM.nextInt(i + 1);
                int n = RANDOM.nextInt(j + 1);

                char temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }


    /**
     * Searches for cells with mines in shuffled array and changes values of 8 cells around them
     */
    private static void processNeighbourCells() {
        for (int row = 0; row < minefield.length; ++row) {
            for (int col = 0; col < minefield[row].length; ++col) {
                if (minefield[row][col] == MINE_CELL) {
                    processCell(row - 1, col - 1);
                    processCell(row - 1, col);
                    processCell(row - 1, col + 1);

                    processCell(row, col - 1);
                    processCell(row, col + 1);

                    processCell(row + 1, col - 1);
                    processCell(row + 1, col);
                    processCell(row + 1, col + 1);
                }
            }
        }
    }

    /**
     * Validates position of the cell and, if it is valid and it is not cell with a mine, increments its value
     *
     * @param row row index of the cell
     * @param col column index of the cell
     */
    private static void processCell(int row, int col) {
        if (isCellPositionValid(row, col) && minefield[row][col] != MINE_CELL) {
            char neighbourCellValue = minefield[row][col];
            if (neighbourCellValue == EMPTY_CELL) {
                minefield[row][col] = '1';
            } else {
                int neighbourCellIntValue = Integer.parseInt(String.valueOf(neighbourCellValue));
                neighbourCellIntValue++;
                minefield[row][col] = Character.forDigit(neighbourCellIntValue, 10);
            }
        }
    }

    /**
     * Checks if position of the cell exists within array's bounds
     *
     * @param row row index of the cell
     * @param col column index of the cell
     * @return TRUE if indexes are within array's bounds
     */
    private static boolean isCellPositionValid(int row, int col) {
        return row >= 0 && row < height
            && col >= 0 && col < width;
    }

    /**
     * Puts array into console output
     */
    private static void printMinefield() {
        for (char[] chars : minefield) {
            System.out.println(chars);
        }
    }

}
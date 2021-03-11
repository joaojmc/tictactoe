package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String line = "_________";
        drawBoard(line);
        for (int i = 0; i < 9; i++) {
            line = processPlayerTurn(line, i);
            drawBoard(line);
            if (checkWinner(line)) break;
        }
    }

    private static String processPlayerTurn(String line, int i) {
        Scanner scanner = new Scanner(System.in);
        boolean destinationCellIsEmpty = false;
        do {
            System.out.println("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            if (!coordinates.matches("[1-3] [1-3]")) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            String[] coordinatesAsArray = coordinates.split(" ");
            int firstCoordinate = Integer.parseInt(coordinatesAsArray[0]);
            int secondCoordinate = Integer.parseInt(coordinatesAsArray[1]);
            destinationCellIsEmpty = line.charAt((firstCoordinate - 1) * 3 + (secondCoordinate - 1)) == '_';
            if (!destinationCellIsEmpty) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                line = line.substring(0, ((firstCoordinate - 1) * 3 + secondCoordinate - 1)) +
                        ((i % 2 == 0) ? 'X' : 'O') +
                        line.substring((firstCoordinate - 1) * 3 + secondCoordinate - 1 + 1);
            }
        } while (!destinationCellIsEmpty);
        return line;
    }

    private static void drawBoard(String line) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("| ");
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(line.charAt((3 * i) + j)).append(" ");
            }
            stringBuilder.append("|");
            System.out.println(stringBuilder.toString());
        }
        System.out.println("---------");
    }

    private static boolean checkWinner(String line) {
        boolean matchedX = false;
        boolean matchedO = false;

        for (int i = 0; i < 3; i++) {
            String linePiece = line.substring((i * 3), (i * 3) + 3);
            if (linePiece.matches("X{3}")) {
                matchedX = true;
            } else if (linePiece.matches("O{3}")) {
                matchedO = true;
            }
        }

        if (line.matches(".*X..X..X.*")) {
            matchedX = true;
        }

        if (line.matches(".*O..O..O.*")) {
            matchedO = true;
        }

        if (line.matches("X...X...X.*") || line.matches("..X.X.X.*")) {
            matchedX = true;
        } else if (line.matches("O...O...O.*") || line.matches("..O.O.O.*")) {
            matchedO = true;
        }

        if (matchedX) {
            System.out.println("X wins");
            return true;
        }

        if (matchedO) {
            System.out.println("O wins");
            return true;
        }

        if (!line.contains("_")) {
            System.out.println("Draw");
            return true;
        }

        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseph
 */
public class SudokuChecker {

    private final int SIZE = 9;
    private int[][] gameBoard = /*{

    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,7,2,8,4},
    {2,8,7,4,1,9,6,3,5},
    {3,4,5,2,8,6,1,7,9}
    };*/ new int[SIZE][SIZE];
     
    private Set<Integer> numbers = new HashSet<>();// Each method tries to add the numbers to the set 
                                                   //since set does not store the same number again if number already exists it returns false

    public boolean horizontalCheck() { 
        boolean bool = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (numbers.contains(gameBoard[i][j]) || !isInRange(gameBoard[i][j])) 
                    return false;
                numbers.add(gameBoard[i][j]);
            }
            numbers.clear();
        }
        return bool;
    }

    public boolean verticalCheck() { 
        boolean bool = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (numbers.contains(gameBoard[j][i]) || !isInRange(gameBoard[j][i]))
                    return false;
                numbers.add(gameBoard[j][i]);
            }
            numbers.clear();
        }
        return bool;
    }

    public boolean cellCheck() {
        boolean bool = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = i * 3; k < 3 * (i + 1); k++) {
                    for (int l = j * 3; l < 3 * (j + 1); l++) {
                        if (numbers.contains(gameBoard[k][l]) || !isInRange(gameBoard[k][l]))
                            return false;                        
                        numbers.add(gameBoard[k][l]);
                    }
                }
                numbers.clear();
            }
        }
        return bool;
    }

    public boolean isCorrect() {
        return horizontalCheck() && verticalCheck() && cellCheck();
    }
    
    public void getNumbersFromConsole(){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameBoard[i][j] = scanner.nextInt();
            }
        }
    }
    
    public void getNumbersFromFile(String fileName) {
        try (Scanner reader = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while ((reader.hasNextInt())) {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                         gameBoard[i][j] = reader.nextInt();
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SudokuChecker.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(gameBoard[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
    
    public boolean isInRange(int cell){
        return cell <= SIZE && cell > 0;
    }
    
    public static void main(String[] args) {
        //SudokuChecker test = new SudokuChecker();
        //test.getNumbersFromFile("C:\\\\Users\\\\joseph\\\\Desktop\\\\sudokuExample.txt");
        //System.out.println(test.isCorrect()?"This Solution is Correct":"This Solution is incorrect");
        //test.print();
    
    }
}
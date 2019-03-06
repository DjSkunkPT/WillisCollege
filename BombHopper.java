
package bombhopper;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author BrunoSilva
 */
public class BombHopper 
{
    private static int nDimension = 0;
    private static int nGuessRow = 0;
    private static int nGuessCol = 0;
    private static String cBoard[][];
    private static String cGuessBoard[][];
    private static int nBombs = 0;
    

    public static void main(String[] args) 
    {
        int nOption = 0;
        while (nOption == JOptionPane.YES_OPTION)
        {
        
            nDimension = (getInteger("Enter the dimension of the square board: ", nDimension)) + 1;
            nBombs = getInteger("How many bombs do you want to place?", ((nDimension - 1) * (nDimension - 1) - 2));
            cBoard = createBoard(cBoard, nDimension);
            cGuessBoard = createBoard(cGuessBoard, nDimension);
            placeBomb(cBoard, nDimension, nBombs);

            System.out.println(Arrays.deepToString(cBoard));
            drawBoard(cGuessBoard, nGuessRow, nGuessCol);


            for (int nCount = 1; nCount < nDimension; nCount++)
            {
                nGuessRow = (getInteger("Enter the row number: ", nDimension));
                nGuessCol = (getInteger("Enter the column number", nDimension));
                drawBoard(cGuessBoard, nGuessRow, nGuessCol);

                if (cBoard[nGuessRow][nGuessCol] == "b")
                {
                    JOptionPane.showMessageDialog(null, "BOOM!!!");
                    nCount = nDimension;
                }
                else if (nCount == nDimension - 2)
                {
                    JOptionPane.showMessageDialog(null, "You're safe! Last try...");

                }
                else
                {   
                    JOptionPane.showMessageDialog(null, "You're safe!");
                }
            }
        nOption = JOptionPane.showConfirmDialog(null, "Play again?", null, JOptionPane.YES_NO_OPTION);
        nDimension = 0;
        cGuessBoard = new String [nDimension][nDimension];
        nGuessRow = 0;
        nGuessCol = 0;
        }
    }
    
    private static int getInteger(String strMessage, int nDimension)
    {
        int nValue = 0;
        boolean bValidNumber = false;
        
        if (nDimension == 0)
        {
            while (!bValidNumber)
            {
                try
                {
                    nValue = Integer.parseInt(JOptionPane.showInputDialog(strMessage));
                    bValidNumber = (nValue > 0);
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null,"That's not a valid number.");
                }
            }
        }
        else
        {
            while (!bValidNumber)
            {
                try
                {
                    nValue = Integer.parseInt(JOptionPane.showInputDialog(strMessage));
                    bValidNumber = (nValue <= (nDimension - 1) && nValue > 0);
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null,"That's not a valid number.");
                }
            }
        }
        
        return nValue;
        }
    
    private static String[][] createBoard(String cBoard[][], int nDimension)
    {
        cBoard = new String [nDimension][nDimension];
        return cBoard;
    }
    
    private static void placeBomb(String cBoard[][], int nDimension, int nBombs)
    {
        int nRandomRow = 0;
        int nRandomCol = 0;
        for (int iIndex = 0; iIndex < nBombs; iIndex++)
        {
        nRandomRow = (int) (Math.random() * nDimension - 2) + 2;
        nRandomCol = (int) (Math.random() * nDimension - 2) + 2;
            if (cBoard [nRandomRow][nRandomCol] == "b")
            {
                iIndex--;
            }
        cBoard [nRandomRow][nRandomCol] = "b";
        }
    }
    
    public static void drawBoard(String cBoard[][], int nGuessRow, int nGuessCol)
    {
        for (int nRowIndex = 0; nRowIndex < cBoard.length; nRowIndex++)
        {
            for (int nColIndex = 0; nColIndex < cBoard.length; nColIndex++)
            {
                if (cBoard[nRowIndex][nColIndex] != "x| " && cBoard[nRowIndex][nColIndex] != "x\n")
                {
                buildCell(cBoard, nRowIndex, nColIndex, nGuessRow, nGuessCol);
                }
                System.out.print(cBoard[nRowIndex][nColIndex]);
            }
        }
    }
    
    private static void buildCell(String cBoard[][], int nRowIndex, int nColIndex, int nGuessRow, int nGuessCol)
    {
        
        if (nRowIndex == 0 && nColIndex == 0)
        {
            cBoard[nRowIndex][nColIndex] = "  ";
        }
        
        if (nRowIndex == 0 && nColIndex > 0)
        {
        cBoard[nRowIndex][nColIndex] = nColIndex + "  ";
            if (nColIndex == cBoard.length - 1)
            {
                cBoard[nRowIndex][nColIndex] = nColIndex + "\n";
            }
        }
        
        if (nRowIndex > 0 && nColIndex == 0)
            {
                cBoard[nRowIndex][nColIndex] = nRowIndex + " ";
            }
        
        if (nRowIndex > 0 && nColIndex > 0)
        {
        cBoard[nRowIndex][nColIndex] = " | ";
            if (nColIndex == cBoard.length - 1)
            {
                cBoard[nRowIndex][nColIndex] =" \n";
            }
                if (nGuessRow == nRowIndex && nGuessCol == nColIndex)
                {
                    cBoard[nRowIndex][nColIndex] = "x| ";

                    if (nColIndex == cBoard.length - 1)
                    {
                        cBoard[nRowIndex][nColIndex] ="x\n";
                    }
                }
        }
    }
}


    
    
       


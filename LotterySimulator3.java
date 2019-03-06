
package lotterysimulator3;

import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

public class LotterySimulator3 
{

    public static void main(String[] args)
    {
        
        int nTotalNumbers = promptUserForInteger();
        while (nTotalNumbers <= 0)
        {
            JOptionPane.showMessageDialog(null, "Pick a number greater than 0.");
            nTotalNumbers = promptUserForInteger();
        }
        
        int nPickedNumbers = promptUserForInteger();
        while (nPickedNumbers >= nTotalNumbers || nPickedNumbers <= 0)
        {
            JOptionPane.showMessageDialog(null, "Your first number was " + nTotalNumbers + ". Pick a smaller one and different than 0.");
            nPickedNumbers = promptUserForInteger();
        }
        
        
        long nOdds = (long)(calculatePermutations(nTotalNumbers, nPickedNumbers));

        System.out.println("The odds of picking the winning numbers are 1 : " + nOdds);
        
        long nAttempts = simulateLottery(nTotalNumbers, nPickedNumbers, nOdds);
        System.out.println("Draws = " + (long)nAttempts);
        

        if (nAttempts < nOdds)

        {
            System.out.println("You won the lottery in " + nAttempts + " draws!");
            JOptionPane.showMessageDialog(null,"You won the lottery in " + nAttempts + " draws!");

        }

        else

        {
            System.out.println("You did not beat the odds...");
            JOptionPane.showMessageDialog(null, "You did not beat the odds...");

        }
        
    }
    private static int promptUserForInteger()
    {
        int nValue = Integer.parseInt(JOptionPane.showInputDialog("Enter a number"));
        return nValue;
    }
    private static double calculatePermutations(double nTotalNumbers, double nPickedNumbers)
    {
        double nTotalNumbersFactorial = calcFactorial(nTotalNumbers);
        double nPickedNumbersFactorial = calcFactorial(nPickedNumbers);
        double nSubtraction = nTotalNumbers - nPickedNumbers;
        double nSubtractionFactorial = calcFactorial(nSubtraction);
        
        double nOdds = nTotalNumbersFactorial / (nPickedNumbersFactorial * nSubtractionFactorial);
        return nOdds;
    }
    private static double calcFactorial(double nValue)
    {
        double nMultiplyResult = 1;
        
        while (nValue > 1)
        {   
        nMultiplyResult = nValue * nMultiplyResult;
        nValue = nValue - 1;
        }
        return nMultiplyResult;
    }
    private static long simulateLottery(int nTotalNumbers, int nPickedNumbers, double nOdds)
    {
        int nTicket[] = pickNumbers(nTotalNumbers, nPickedNumbers);
        int nDraw[] = pickNumbers(nTotalNumbers, nPickedNumbers);
        long nCount = 1;
        
        System.out.println("Ticket numbers: " + Arrays.toString(nTicket));
        System.out.println("First draw's numbers: " + Arrays.toString(nDraw));
        
        while (!(Arrays.equals(nTicket, nDraw)) && (nCount < nOdds))
        {   
            nDraw = pickNumbers(nTotalNumbers, nPickedNumbers);
            ++nCount;
        }
        
        System.out.println("Last draw's numbers: " + Arrays.toString(nDraw));
        return nCount;
    }
    private static int[] pickNumbers(int nTotalNumbers, int nPickedNumbers)
    {
        
        int nTicket[] = new int[nPickedNumbers];
        Random obRandom = new Random();
        
        for (int i = 0; i < nPickedNumbers; i++)
        {
           int nRandom = obRandom.nextInt(nTotalNumbers) + 1;
           nTicket[i] = nRandom;
           
           for (int j = 0; j < nTicket.length; j++)
            {
                while (nTicket[i] == nTicket[j] && i != j)
                {
                    nRandom = obRandom.nextInt(nPickedNumbers) + 1;
                    nTicket[i] = nRandom;
                    j = 0;
                    
                }
            }
        }
        
        Arrays.sort(nTicket);
        return nTicket;
    }
    
}

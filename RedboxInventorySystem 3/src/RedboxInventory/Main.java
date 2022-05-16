package RedboxInventory;

import java.io.*;
import java.util.Scanner;

import RedboxInventory.Common.BinarySearchTree;
import RedboxInventory.Common.ScannerFactory;
import RedboxInventory.Movie.Movies;

//dxb126530 2021156501
public class Main 
{
    public static void main(String[] args) throws IOException
    {
        File inventory = new File("inventory.dat");
        ScannerFactory.setScannerFileInstance(inventory);
        String line = "";
        String title = "";
        String available = "";
        String rented = "";
        int avail = 0;
        int rent = 0;
        BinarySearchTree<Movies> BST = new BinarySearchTree<>();
        
        if(inventory.exists())
        {
            while(ScannerFactory.getScannerInstance().hasNext())
            {
                line = ScannerFactory.getScannerInstance().nextLine();
                
                line = line.substring(1);
                title = line.substring(0, line.indexOf('\"'));
                line = line.substring(line.indexOf('\"') + 1);
                line = line.substring(line.indexOf(',') + 1);
                
                available = line.substring(0, line.indexOf(','));
                available = available.trim();
                avail = Integer.parseInt(available);
                line = line.substring(line.indexOf(',') + 1);
                
                rented = line;
                rented = rented.trim();
                rent = Integer.parseInt(rented);
                Movies M = new Movies(title, avail, rent);
                BST.insert(M);
            }
            ScannerFactory.getScannerInstance().close();
        }
        else
        {
            System.out.println("The inventory.dat file doesn't exist.");
        }

        File transaction = new File("transaction.log");
        ScannerFactory.setScannerFileInstance(transaction);
        PrintWriter error = new PrintWriter(new File("error.log"));
        
        if(transaction.exists())
        {
            while(ScannerFactory.getScannerInstance().hasNext())
            {
                line = ScannerFactory.getScannerInstance().nextLine();
                
                if(line.substring(0, 3).equalsIgnoreCase("add"))
                {
                    checkAddRemove(line, "add", error, BST);
                }
                else if(line.substring(0, 6).equalsIgnoreCase("remove"))
                {
                    checkAddRemove(line, "remove", error, BST);
                }
                else if(line.substring(0, 4).equalsIgnoreCase("rent"))
                {
                    checkRentReturn(line, "rent", error, BST);
                }
                else if(line.substring(0, 6).equalsIgnoreCase("return"))
                {
                    checkRentReturn(line, "return", error, BST);
                }
                else
                {
                    error.println(line);
                    continue;
                }
            }
            ScannerFactory.getScannerInstance().close();
            error.close();

            
        }
        else
        {
            System.out.println("The transaction.log file doesn't exist.");
        }
        
        PrintWriter kiosk = new PrintWriter(new File("redbox_kiosk.txt")); 
        kiosk.printf("%-25s%-11s%-8s", "Title", "Available", "Rented");
        kiosk.println();
        BST.printTree(kiosk);
            
        kiosk.close();
    }
    
    public static void checkAddRemove(String line, String s, PrintWriter output, BinarySearchTree<Movies> BST)
    {
        boolean valid = false;
        String line2 = "", title = "", numAR = "";
        int num = 0;
        
        if(s.equalsIgnoreCase("add"))
        {
            line2 = line.substring(4);
        }
        else if(s.equalsIgnoreCase("remove"))
        {
            line2 = line.substring(7);
        }
        
        if(line2.equals(""))
        {
            output.println(line);
            return;
        }
        else if(line2.charAt(0) == '\"')
        {
            line2 = line2.substring(1);
            if(line2.equals("")) 
            {
                output.println(line);
                return;
            }
            if(line2.indexOf('\"') == -1)   
            {
                output.println(line);
                return;
            }
            
            title = line2.substring(0, line2.indexOf('\"'));
            valid = true;
            
            if(line2.indexOf(',') == -1)
            {
                output.println(line);
                return;
            }
            
            line2 = line2.substring(line2.indexOf('\"') +1);
            line2 = line2.substring(line2.indexOf(',') + 1);
            
            if(line2.equals(""))
            {
                output.println(line);
                return;
            }
        
            numAR = line2.trim();
            try
            {
                num = Integer.parseInt(numAR);
            }
            catch(NumberFormatException E)
            {
                output.println(line);
                return;
            }
        }
        
        if(valid == false)
        {
            output.println(line);
            return;
        }
        
        Movies M = new Movies(title, num);
        
        if(s.equals("add"))
        {
            Movies search = BST.searchTree(M);
            if(search == null)
            {
                BST.insert(M);
            }
            else
            {
                search.add(num);
            }
            
        }
        else
        {
            Movies search = BST.searchTree(M);
            if(search == null)
            {
                output.println(line);
                return;
            }
            else
            {
                search.remove(num);
                if(search.available <= 0 && search.rented <= 0)
                {
                    BST.deleteNode(M);
                }
            }
            
        }
    }
    
    public static void checkRentReturn(String line, String RR, PrintWriter output, BinarySearchTree<Movies> BST)
    {
        boolean valid = false;
        String line2 = "", title = "";
        
        if(RR.equalsIgnoreCase("rent"))
        {
            line2 = line.substring(5);
        }
        else if(RR.equalsIgnoreCase("return"))
        {
            line2 = line.substring(7);
        }
        
        if(line2.equals(""))
        {
            output.println(line);
            return;
        }
        else if(line2.charAt(0) == '\"')
        {
            line2 = line2.substring(1);
            if(line2.equals(""))
            {
                output.println(line);
                return;
            }
            if(line2.indexOf('\"') == -1)
            {
                output.println(line);
                return;
            }
            
            title = line2.substring(0, line2.indexOf('\"'));
            line2 = line2.substring(line2.indexOf('\"') + 1);
            valid = true;
        }
        
        if(valid == false)
        {
            output.println(line);
            return;
        }
        
        if(!(line2.equals("")))
        {
            output.println(line);
            return;
        }
        
        Movies M = new Movies(title);

        if(RR.equals("rent"))
        {
            Movies search = BST.searchTree(M);
            if(search == null)
                output.println(line);
            else
                search.rent();
        }
        else
        {
            Movies search = BST.searchTree(M);
            if(search == null)
                output.println(line);
            else
                search.returned();
        }
        
    }
}
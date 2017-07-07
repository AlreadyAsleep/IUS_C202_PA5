/**
 *
 * @author BenHeil
 * @since 07032017
 */
import java.util.*;
import java.io.*;
public class SpellCheck 
{
    BinarySearchTree<String>[] dic;
    Scanner in;
    File file;
    String temp;
    int[] i_temp;
    int found, notFound, compsFound, compsNotFound;
    //Loads the dictionary with words from a file
    //initializes an array of BSTs to be used by the checker
    public SpellCheck(String path)
    {
        dic = new BinarySearchTree[26];
        i_temp= new int[1];
        for(int i = 0; i < dic.length; i++)
        {
            dic[i] = new BinarySearchTree<>();
        }
        try
        {
            file =new File(path);
            in = new Scanner(file);
            while(in.hasNext())
            {
                temp = in.next().trim().toLowerCase();
                dic[temp.charAt(0) - 97].insert(temp);
            }
            in.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error loading dictionary... Shutting down");
            System.exit(1);
        }
        found = notFound = compsFound = compsNotFound  = 0;
    }
    //checks the file requested and outputs statistics about the search
    public void run(String path)    
    {
        try
        {
            file = new File(path);
            in = new Scanner(file);
            i_temp[0] = 0;
            while(in.hasNext())
            {
              
                temp = in.next().trim().toLowerCase();
                if(temp.charAt(0) > 96 && temp.charAt(0) < 124)
                {
                    boolean there = dic[temp.charAt(0) - 97].search(temp, i_temp);
                    if(there)
                    {
                        compsFound += i_temp[0];
                        found++;
                    }
                    else
                    {
                        compsNotFound += i_temp[0];
                        notFound++;
                    }
                }
                  i_temp[0] = 0;
            }
            System.out.println("Words found : " + found);
            System.out.println("Words not found: " + notFound);
            System.out.println("Comparisons made to found words: " + compsFound);
            System.out.println("Comparisons made to missing words: " + compsNotFound);
            System.out.println("Average comparisons for found words: " + ((float)compsFound / found));
            System.out.println("Average comparisons for missing words: " +((float) compsNotFound / notFound));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error loading file... Shutting down");
            System.exit(1);
        }
    }
}

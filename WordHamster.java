import java.io.*;
import java.util.*;

/**
 *
 * @author Katz9
 */
public class WordHamster
{

    private ArrayList<String> words = new ArrayList<>();

    public boolean gatherWords(String filePath)
    {
        boolean retVal = true;
        try (Scanner wordsIn = new Scanner(new FileReader(filePath)))
        {
            wordsIn.useDelimiter("[^a-zA-Z']+");
            while (wordsIn.hasNext())      // while more words to process
            {
                String word = wordsIn.next();
                word = word.toLowerCase();
                words.add(word);
            }
        }
        catch (IOException ioex)
        {
            retVal = false;
            System.out.println(ioex.getMessage());
        }
        return retVal;
    }

    public ArrayList<String> exposeWords()
    {
        return words;
    }
}


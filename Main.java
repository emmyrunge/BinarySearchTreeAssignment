import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static <AnyType extends Comparable<? super AnyType>> void main(String[] args)
    {
        BinarySearchTree<String> wordTree = new BinarySearchTree<>();
        WordHamster doppleganger = new WordHamster();


        File textfile = new File(args[0]);

        doppleganger.gatherWords(String.valueOf(textfile));
        ArrayList<String> nakedWords = doppleganger.exposeWords();

        for (int ix = 0; ix < nakedWords.size(); ix++)
        {
            wordTree.insert(nakedWords.get(ix));
        }
        wordTree.printTree();

    }
}

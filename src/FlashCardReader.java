import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used for reading questions from a text file to create flash cards
 */
public class FlashCardReader {
    BufferedReader reader;

    /**
     * Default class constructor
     */
    FlashCardReader(){
        try {
            reader = new BufferedReader(new FileReader("Questions.txt"));
        }catch (FileNotFoundException e){
            System.err.println("Questions file not found!");
        }
    }

    /**
     * Getter for the next line in the loaded file
     * @return next line in file as a string
     * @throws IOException the exception thrown if the file cannot be read
     */
    public String getLine() throws IOException{
        return reader.readLine();
    }

    /**
     * Boolean method to indicate whether or not the file is ready to be read
     * @return boolean indicating whether the file is ready
     * @throws IOException the exception thrown if the file cannot be read
     */
    public boolean isReady() throws IOException{
        return reader.ready();
    }

    /**
     * Creates and returns an ArrayList of flash cards constructed from the questions and answers
     * in the loaded questions file.
     * @return ArrayList of flashcards with questions and answers
     * @throws IOException the exception thrown if the file cannot be read
     */
    public ArrayList<FlashCard> getFlashCards() throws IOException{
        ArrayList<FlashCard> flashCardArrayList = new ArrayList<>();
        String nextLine;
        while((nextLine = getLine())!=null){
            String[] line = nextLine.split(":");
            FlashCard flashCard = new FlashCard(line[0],line[1]);
            flashCardArrayList.add(flashCard);
        }
        return flashCardArrayList;
    }
}

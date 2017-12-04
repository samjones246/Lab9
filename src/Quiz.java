import java.io.*;
import java.util.ArrayList;

/**
 * Handles user interaction for a quiz using questions and answers stored on FlashCards.
 */
class Quiz {
    private ArrayList<FlashCard> flashCards;

    /**
     * Class constructor which populates the flashCards ArrayList and then starts the quiz.
     */
    private Quiz(){
        FlashCardReader flashCardReader = new FlashCardReader();
        try {
            flashCards = flashCardReader.getFlashCards();
        }catch (IOException e){
            System.err.println("Error creating questions list!");
        }
        play();
    }

    /**
     * Quiz the user using the questions and answers loaded by the FlashCardReader.
     * This method will iterate through the FlashCards contained in the flashCards ArrayList and ask the user each
     * question. They are then able to enter an answer which is compared with the correct answer, and if they are
     * the same then the user will be told that they are correct. If not, the correct answer is shown. At the end of
     * the quiz, the user is shown their score and given the option to save their results.
     */
    private void play(){
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        int questionNumber = 1, score = 0;
        ArrayList<String> saveData = new ArrayList<>();
        String answer = "";
        String correct = "";
        for(FlashCard flashCard: flashCards){
            System.out.println("Question " + questionNumber + ":");
            System.out.println(flashCard.getQuestion());
            System.out.print("> ");
            try {
                answer = userInput.readLine();
                if(answer.equals(flashCard.getAnswer())){
                    System.out.println("Correct!");
                    score ++;
                    correct = "correct";
                }else{
                    System.out.println("Incorrect! The correct answer was: " + flashCard.getAnswer());
                    correct = "incorrect";
                }
            }catch (IOException e){
                System.err.println("Could not read user input");
            }
            saveData.add(flashCard.getQuestion() + "," + answer + "," + correct);
            questionNumber ++;
        }
        double percentage = 100*(score / flashCards.size());
        saveData.add("Score: " + score + "/" + flashCards.size() + " (" + percentage + "%)");
        System.out.println("You have finished the Quiz! Your score was: " + score + "/" + flashCards.size());
        System.out.println("Would you like to save your results? (Y/N)");
        try {
            String doSave = userInput.readLine();
            switch (doSave.toUpperCase()){
                case "Y":
                    System.out.println("Data will be saved to save.txt");
                    save(saveData);
                    break;
                default:
                    System.out.println("Data will not be saved");
                    break;
            }
        }catch (IOException e){
            System.err.println("Could not read user input!");
        }
    }

    /**
     * Saves the results of a quiz to a text file.
     * Writes to the file save.txt using the format [question],[user's answer],[correct/incorrect] for each line.
     * Also writes the user's score, the total number of questions and the percentage of questions the user
     * got correct after this.
     * @param saveData ArrayList containing the results of the quiz to be written to the file.
     */
    private void save(ArrayList<String> saveData){
        try {
            PrintStream writer = new PrintStream("save.txt");
            for(String result : saveData){
                writer.println(result);
            }
        }catch (FileNotFoundException e){
            System.err.println("Could not find save file!");
        }
    }

    /**
     * Starts the quiz by creating an instance of the quiz class.
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
    }
}

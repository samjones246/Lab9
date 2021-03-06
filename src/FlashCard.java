/**
 * A flash card contains a question and answer pairing for use in a quiz
 */
public class FlashCard {
    private String question, answer;

    /**
     * Class constructor specifying the question and answer
     * @param question the question to ask the user
     * @param answer the answer to the question for this flash card
     */
    FlashCard(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    /**
     * Getter method for the question on this flash card
     * @return the question on this flash card as a string
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getter method for the answer on this flash card
     * @return the answer on this flash card as a string
     */
    public String getAnswer() {
        return answer;
    }
}

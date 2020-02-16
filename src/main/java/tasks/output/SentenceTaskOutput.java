package tasks.output;

/**
 * @author xiaohan
 * @since 2020/2/2 10:47 AM
 */
public class SentenceTaskOutput extends TaskOutput{
    public String getSentence() {
        return sentence;
    }

    String sentence;

    public SentenceTaskOutput(String sentence) {
        this.sentence = sentence;
    }
}

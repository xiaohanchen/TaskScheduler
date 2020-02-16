package tasks.instance;

import jobexe.context.JobContext;
import org.springframework.stereotype.Component;
import tasks.SingleTaskService;
import tasks.TaskAnnotation;
import tasks.output.CountingTaskOutput;
import tasks.output.SentenceTaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/16 5:09 PM
 */
@Component
@TaskAnnotation(
    in = {JameSaysTaskService.class, AlyssaSaysTaskService.class},
    out = CountingTaskOutput.class,
    taskDesc = "count how many words they say")
public class CountWordsTaskService implements SingleTaskService<JobContext, CountingTaskOutput> {

    @Override
    public CountingTaskOutput runTask(JobContext context) {
        SentenceTaskOutput james = (SentenceTaskOutput)context.getTaskResult(JameSaysTaskService.class.getSimpleName());
        SentenceTaskOutput alyssa = (SentenceTaskOutput)context.getTaskResult(AlyssaSaysTaskService.class.getSimpleName());
        int totalLength = james.getSentence().length() + alyssa.getSentence().length();

        CountingTaskOutput countingTaskOutput = new CountingTaskOutput(totalLength);
        context.addTaskResult(this.getClass().getSimpleName(), countingTaskOutput);
        return countingTaskOutput;
    }
}

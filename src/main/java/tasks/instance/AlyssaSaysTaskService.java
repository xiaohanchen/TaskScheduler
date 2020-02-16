package tasks.instance;

import jobexe.context.JobContext;
import org.springframework.stereotype.Component;
import tasks.SingleTaskService;
import tasks.TaskAnnotation;
import tasks.output.SentenceTaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/16 5:09 PM
 */
@Component
@TaskAnnotation(in = {}, out = SentenceTaskOutput.class, taskDesc = "Alyssa says something")
public class AlyssaSaysTaskService implements SingleTaskService<JobContext, SentenceTaskOutput> {

    @Override
    public SentenceTaskOutput runTask(JobContext context) {
        SentenceTaskOutput sentenceTaskOutput = new SentenceTaskOutput("hi Im Alyssa");
        context.addTaskResult(this.getClass().getSimpleName(), sentenceTaskOutput);
        return sentenceTaskOutput;
    }
}

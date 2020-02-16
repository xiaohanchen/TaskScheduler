package tasks.output;

import java.math.BigDecimal;

import tasks.output.TaskOutput;

/**
 * @author xiaohan
 * @since 2020/2/16 5:10 PM
 */
public class CountingTaskOutput extends TaskOutput {

    private Integer value;

    public CountingTaskOutput(Integer value) {
        this.value = value;
    }
}

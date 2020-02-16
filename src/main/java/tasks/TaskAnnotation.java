package tasks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import tasks.output.TaskOutput;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TaskAnnotation {

    @Nullable
    String taskDesc();

    @NonNull
    Class<? extends SingleTaskService> [] in();

    @NonNull
    Class<? extends TaskOutput> out();
}

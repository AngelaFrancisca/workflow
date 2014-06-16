package module.workflow.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import module.workflow.domain.WorkflowProcess;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkflowProcessViewer {

    Class<? extends WorkflowProcess>[] value();

}

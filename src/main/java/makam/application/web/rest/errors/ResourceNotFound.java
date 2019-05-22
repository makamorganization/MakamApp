package makam.application.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ResourceNotFound extends AbstractThrowableProblem {
    public ResourceNotFound() {
    }

    public ResourceNotFound(String message) {
        super(ErrorConstants.RESOURCE_NOT_FOUND, message, Status.NOT_FOUND);
    }
}

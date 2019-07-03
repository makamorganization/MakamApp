package makam.application.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class FullNumberOfParticipantsInCourseException extends AbstractThrowableProblem {

    public FullNumberOfParticipantsInCourseException() {
    }

    public FullNumberOfParticipantsInCourseException(String message) {
        super(ErrorConstants.DEFAULT_TYPE, message, Status.CONFLICT);
    }
}

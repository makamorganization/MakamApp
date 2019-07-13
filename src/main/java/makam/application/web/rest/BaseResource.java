package makam.application.web.rest;

import makam.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseResource {

    protected final UserService userService;

    @Autowired
    public BaseResource(UserService userService) {
        this.userService = userService;
    }


}

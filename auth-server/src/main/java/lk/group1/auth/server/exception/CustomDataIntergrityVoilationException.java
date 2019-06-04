package lk.group1.auth.server.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomDataIntergrityVoilationException extends DataIntegrityViolationException {


    public CustomDataIntergrityVoilationException(String msg) {
        super(msg);
    }
}

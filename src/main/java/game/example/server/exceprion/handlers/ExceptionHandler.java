package game.example.server.exceprion.handlers;

import game.example.server.dto.ExceptionMessage;
import game.example.server.exceprion.FileException;
import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice("ExceptionHandler")
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ModelExist.class)
    public ResponseEntity<ExceptionMessage> handleRegisterException(
            @NotNull ModelExist e) {
        var response = ExceptionMessage.builder()
                .message("model exist").build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ModelNoFound.class ,
            UsernameNotFoundException.class})
    public ResponseEntity<ExceptionMessage> handleResourceNotFoundException() {
        var response = ExceptionMessage.builder()
                .message("model not found ").build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FileException.class)
    public ResponseEntity<ExceptionMessage> handleResourceNotDeletedException() {
        var response = ExceptionMessage.builder()
                .message("file conflict").build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                 @NotNull HttpHeaders headers,
                                 @NotNull HttpStatusCode status,
                                 @NotNull WebRequest request) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", status.value());
        objectBody.put("Name","Validate");
        List<String> exceptionalErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        objectBody.put("Errors", exceptionalErrors);
        return new ResponseEntity<>(objectBody, status);
    }
}

package scheduleApi.schedule.common.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.resource.NoResourceFoundException;
import scheduleApi.schedule.common.exception.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    private static class GlobalException extends CustomException {
        public GlobalException(ErrorCode errorCode) {
            super(errorCode);
        }
    }

    @RequiredArgsConstructor
    public enum GlobalErrorCode implements ErrorCode {
        NOT_FOUND("요청 URL을 다시 확인해 주세요!", HttpStatus.NOT_FOUND),
        BAD_REQUEST("파람, 바디 등 양식을 다시 확인해 주세요!", HttpStatus.BAD_REQUEST),
        INTERNAL_SERVER_ERROR("서버에 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

        // HttpRequestMethodNotSupportedException도 추가해야 할 듯

        @Override
        public String message() {
            return message;
        }

        @Override
        public HttpStatus httpStatus() {
            return httpStatus;
        }

        @Override
        public RuntimeException exception() {
            return new GlobalException(this);
        }

        public CustomException toCustomException() {
            return new CustomException(this);
        }

        private final String message;
        private final HttpStatus httpStatus;
    }

    @Getter
    @RequiredArgsConstructor
    public static class GlobalValidationErrorCode implements ErrorCode {
        private final String message;
        private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        private final static String MESSAGE_HOLDER = " 필드의 값 검증에 실패했습니다!";

        @Override
        public String message() {
            return this.message;
        }

        @Override
        public HttpStatus httpStatus() {
            return this.httpStatus;
        }

        @Override
        public RuntimeException exception() {
            return new GlobalException(this);
        }

        public CustomException toCustomException() {
            return new CustomException(this);
        }
    }

    // 나머지 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerException(Exception e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                GlobalErrorCode.INTERNAL_SERVER_ERROR.toCustomException());

        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }

    // 요청 URL 매칭 불가한 상황
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                GlobalErrorCode.NOT_FOUND.toCustomException());

        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }

    // 바인딩 예외 처리(요청 객체의 타입이 필드와 일치하지 않을 때)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ErrorResponse> handleBindException() {
        final ErrorResponse errorResponse = new ErrorResponse(
                GlobalErrorCode.BAD_REQUEST.toCustomException());

        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }


    // 벨리데이션 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        // 벨리데이션 실패 메시지 추출
        // * getAllErrors = 클래스 레벨 예외도 포함해서 가져오기
        final List<String> errorMessages = e.getBindingResult().getAllErrors().stream().map(
                fieldError -> fieldError.getDefaultMessage() != null ?
                        fieldError.getDefaultMessage() :
                        // 벨리데이션 실패 메시지가 따로 정의되지 않은 경우
                        // 기본 메시지(xx 필드 값 검증에 실패했습니다!) 사용
                        fieldError.getObjectName() + GlobalValidationErrorCode.MESSAGE_HOLDER).collect(Collectors.toList());

        final ErrorResponse errorResponse = new ErrorResponse(
                new GlobalValidationErrorCode(String.join(", ", errorMessages)).toCustomException());

        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        final ErrorResponse errorResponse = new ErrorResponse(e);
        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }
}



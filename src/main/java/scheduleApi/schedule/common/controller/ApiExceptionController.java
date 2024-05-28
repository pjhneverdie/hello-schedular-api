package scheduleApi.schedule.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import scheduleApi.schedule.common.exception.*;

@Slf4j
@RestControllerAdvice
public class ApiExceptionController {

    private static class GlobalException extends CustomException {
        public GlobalException(ErrorCode errorCode) {
            super(errorCode);
        }
    }

    @RequiredArgsConstructor
    public enum GlobalErrorCode implements ErrorCode {
        NOT_FOUND("요청 URL을 다시 확인해 주세요!", HttpStatus.NOT_FOUND),
        BAD_REQUEST("파람, 바디 등 양식을 다시 확인해 주세요!", HttpStatus.BAD_REQUEST),
        VALIDATION_FAILED("벨리데이션 실패", HttpStatus.BAD_REQUEST);

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

        private final String message;
        private final HttpStatus httpStatus;
    }

    // 요청 URL 매칭 불가한 상황 등 잡다한 예외 처리
    // 너무 포괄하는 느낌이라 음.. 고민 필요
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("error", e);
        final GlobalException globalException = new GlobalException(GlobalErrorCode.NOT_FOUND);
        final ErrorResponse errorResponse = new ErrorResponse(globalException);
        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }


    // 바인딩 예외 처리(요청 객체의 타입이 필드와 일치하지 않을 때)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ErrorResponse> handleBindException() {
        final GlobalException globalException = new GlobalException(GlobalErrorCode.BAD_REQUEST);
        final ErrorResponse errorResponse = new ErrorResponse(globalException);
        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }

    // 벨리데이션 예외 처리
    // 동적으로 뭔 필드가 잘못됐는지 메시지를 추가할 수 있는 방안을 찾고 있음
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException() {
        final GlobalException globalException = new GlobalException(GlobalErrorCode.VALIDATION_FAILED);
        final ErrorResponse errorResponse = new ErrorResponse(globalException);
        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        final ErrorResponse errorResponse = new ErrorResponse(e);
        return new ResponseEntity<>(errorResponse, errorResponse.getEnumHttpStatus());
    }
}

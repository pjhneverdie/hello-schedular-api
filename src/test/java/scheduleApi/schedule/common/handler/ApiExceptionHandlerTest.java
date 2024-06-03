package scheduleApi.schedule.common.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import scheduleApi.schedule.domain.Schedule;
import scheduleApi.schedule.repository.dto.ScheduleSaveDto;
import scheduleApi.schedule.service.ScheduleService;
import scheduleApi.schedule.exception.ScheduleErrorCode;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static scheduleApi.schedule.common.handler.ApiExceptionHandler.*;

@WebMvcTest()
public class ApiExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    void handleNotFoundExceptionTest() throws Exception {
        mockMvc.perform(get("/some-invalid-url"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(GlobalErrorCode.NOT_FOUND.message()));
    }

    @Test
    void handleBindExceptionTest() throws Exception {
        // 타입 미스매치
        final String requestBody = "{\n" +
                "    \"dateTime\": \"dateTime\",\n" +
                "    \"title\": \"title\",\n" +
                "    \"memo\": \"memo\"\n" +
                "}";

        mockMvc.perform(post("/schedule/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(GlobalErrorCode.BAD_REQUEST.message()));
    }

    @Test
    void handleValidationExceptionTest() throws Exception {
        // 제목 누락
        final String requestBody = "{\n" +
                "    \"dateTime\": \"2007-12-03T10:15:30\",\n" +
                "    \"memo\": \"memo\"\n" +
                "}";

        mockMvc.perform(post("/schedule/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("제목을 입력해 주세요."));
    }


    @Test
    void handleCustomExceptionTest() throws Exception {
        doThrow(ScheduleErrorCode.SOME_SCHEDULE_ERROR.exception())
                .when(scheduleService)
                .save(any(ScheduleSaveDto.class));

        // 클라이언트는 잘못이 없음
        final String requestBody = "{\n" +
                "    \"dateTime\": \"2007-12-03T10:15:30\",\n" +
                "    \"title\": \"valid title\",\n" +
                "    \"memo\": \"valid memo\"\n" +
                "}";

        mockMvc.perform(post("/schedule/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ScheduleErrorCode.SOME_SCHEDULE_ERROR.message()));
    }
}
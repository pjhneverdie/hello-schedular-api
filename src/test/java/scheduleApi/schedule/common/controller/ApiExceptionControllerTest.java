package scheduleApi.schedule.common.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import scheduleApi.schedule.service.ScheduleService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static scheduleApi.schedule.common.controller.ApiExceptionController.*;

@WebMvcTest()
public class ApiExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    void handleExceptionTest() throws Exception {
        mockMvc.perform(get("/some-invalid-url"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(GlobalErrorCode.NOT_FOUND.message()));
    }

    @Test
    void handleBindExceptionTest() throws Exception {
        mockMvc.perform(post("/schedule/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"dateTime\": \"dateTime\",\n" +
                                "    \"title\": \"title\",\n" +
                                "    \"memo\": \"memo\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(GlobalErrorCode.BAD_REQUEST.message()));
    }

    @Test
    void handleValidationExceptionTest() throws Exception {
        mockMvc.perform(post("/schedule/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"dateTime\": \"2007-12-03T10:15:30\",\n" +
                                "    \"memo\": \"memo\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(GlobalErrorCode.VALIDATION_FAILED.message()));
    }

    // 아직 테스트 방법을 모르겠음 ㅠ
//    @Test
//    void handleCustomExceptionTest() throws Exception {
//        final Schedule schedule = new Schedule();
//        schedule.setDateTime(LocalDateTime.now());
//        schedule.setTitle(null);
//        schedule.setMemo(null);
//
//        doThrow(ScheduleErrorCode.DATABASE_EXCEPTION.toException()).when(scheduleService).save(schedule);
//
//        scheduleService.save(schedule);
//
//    }
}

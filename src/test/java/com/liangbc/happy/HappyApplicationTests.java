package com.liangbc.happy;

import com.liangbc.happy.domain.City;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyApplicationTests extends BaseRestDocTest {


    @Test
    public void contextLoads() throws Exception {
        MvcResult resul = this.mockMvc.perform(get("/v1/user/{id}", 1).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(CoreMatchers.instanceOf(City.class)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        mockMvc.perform(asyncDispatch(resul))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(document("查询用户",
                        pathParameters(parameterWithName("id").description("用户id")),
                                relaxedResponseFields(
                                    fieldWithPath("id").type("String").description("城市id")
                                )
                        )
                );

    }

}

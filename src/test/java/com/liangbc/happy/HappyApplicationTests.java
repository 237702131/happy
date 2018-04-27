package com.liangbc.happy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyApplicationTests extends BaseRestDocTest {


    private Gson gson = new GsonBuilder().create();

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

    @Test
    public void dielte() throws Exception {
        MvcResult resul = this.mockMvc.perform(put("/v1/user/{id}", 1).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(CoreMatchers.instanceOf(City.class)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        mockMvc.perform(asyncDispatch(resul))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(document("删除用户",
                        pathParameters(parameterWithName("id").description("用户id"))
                        )
                );

    }


    @Test
    public void create() throws Exception {
        City city = new City();
        city.setProvinceId(121212);
        city.setCityName("测试");
        city.setDescription("描述");

        MvcResult resul = this.mockMvc.perform(post("/v1/user", 1).contentType(MediaType.APPLICATION_JSON_UTF8).content(gson.toJson(city)))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult(CoreMatchers.instanceOf(Integer.class)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        mockMvc.perform(asyncDispatch(resul))
                .andExpect(status().isOk())
                .andDo(document("创建用户", requestFields(
                        fieldWithPath("cityName").description("结算凭证"),
                        fieldWithPath("description").description("结算描述"),
                        fieldWithPath("provinceId").description("结算描述")
                        )
                        )
                );

    }
}

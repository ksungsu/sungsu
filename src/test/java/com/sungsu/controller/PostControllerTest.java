package com.sungsu.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("/posts 요청 결과 hello world")
    void test() throws Exception {
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"제목입니다.\", \"content\":\"내용입니다.\"}")
                ).andExpect(status().isOk())
                .andExpect(content().string("{}"))
                .andDo(print());
    }
    @Test
    @DisplayName("/posts title 값은 필수")
    void test2() throws Exception {
      mockMvc.perform(post("/posts")
              .contentType(MediaType.APPLICATION_JSON)
              .content("{\"title\":\"\", \"content\":\"내용입니다.\"}")
              ).andExpect(status().isOk())
              .andExpect(jsonPath("$.title").value("타이틀을 입력해주세요"))
              .andDo(print());
    }

}
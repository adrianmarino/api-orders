package com.navent.api.orders.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.navent.api.orders.controller.JSONUtils.toJson;
import static org.springframework.http.MediaType.APPLICATION_JSON;


public class MockMvcUtils {

    public static String post(
            MockMvc client,
            String url,
            Object requestBody,
            ResultMatcher resultMatcher
    ) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(url)
                .contentType(APPLICATION_JSON)
                .content(toJson(requestBody));

        return client.perform(request)
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    public static String get(
            MockMvc client,
            String url,
            ResultMatcher resultMatcher
    ) throws Exception {
        return client.perform(
                MockMvcRequestBuilders
                        .get(url)
                        .contentType(APPLICATION_JSON)
        )
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    public static String put(
            MockMvc client,
            String url,
            Object requestBody,
            ResultMatcher resultMatcher
    ) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(url)
                .contentType(APPLICATION_JSON)
                .content(toJson(requestBody));

        return client.perform(request)
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    public static String delete(
            MockMvc client,
            String url,
            ResultMatcher resultMatcher
    ) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(url)
                .contentType(APPLICATION_JSON);

        return client.perform(request)
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();

    }
}

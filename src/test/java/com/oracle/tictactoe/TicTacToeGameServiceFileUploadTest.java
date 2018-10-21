package com.oracle.tictactoe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class TicTacToeGameServiceFileUploadTest {

    @Rule
    public MySQLContainer mysql = new MySQLContainer();

    @Autowired
    private WebApplicationContext context;

    @Test
    public void testUpload() throws Exception {

        MockMultipartFile tempFile = new MockMultipartFile("file", "logback-test.xml", "text/plain", "some xml".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.getContext()).build();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/tictactoe/uploadFile")
                .file(tempFile)
                .param("some-random", "4"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    /**
     * Answer my context
     * @return WebApplicationContext
     */
    private WebApplicationContext getContext() {
        return context;
    }

}

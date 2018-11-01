package com.oracle.tictactoe;

import com.oracle.tictactoe.TicTacToeApplication;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.testcontainers.containers.MySQLContainer;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TicTacToeApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TicTacToeGameServiceRestTemplateIT {

    @LocalServerPort
    private int port;

    @Rule
    public MySQLContainer mysql = new MySQLContainer();


    /**
     * File upload test
     */
    @Test
    public void fileUploadTest() throws Exception {

        String                  tempBaseUrl;
        boolean                 tempGameComplete;
        RestTemplate            tempTemplate  = new RestTemplate();
        String                  tempResult;
        ResponseEntity<String>  tempResponse;

        //Create base url
        System.out.println("Random port generated for test: " + this.getPort());
        tempBaseUrl = "http://localhost:" + this.getPort() + "/tictactoe/uploadFile";

        tempResponse = tempTemplate
                .postForEntity(tempBaseUrl, this.createFormData(), String.class);
        assertTrue("Result is null", tempResponse != null);



    }

    /**
     * Create form data with a file
     * @return
     */
    private   HttpEntity<MultiValueMap<String, Object>> createFormData() throws IOException {

        HttpHeaders                                tempHeaders;
        MultiValueMap<String, Object>              tempParams;
        HttpEntity<MultiValueMap<String, Object>>  tempRequest;

        tempHeaders = new HttpHeaders();
        tempHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        tempParams = new LinkedMultiValueMap<String, Object>();
        tempParams.add("file", new FileSystemResource(new ClassPathResource("logback-test.xml").getFile()));

        tempRequest = new HttpEntity<MultiValueMap<String, Object>>(tempParams, tempHeaders);

        return tempRequest;

    }
    /**
     * Answer the random port I need to connect to the endpoint
     * @return int
     */
    private int getPort() {
        return port;
    }
}

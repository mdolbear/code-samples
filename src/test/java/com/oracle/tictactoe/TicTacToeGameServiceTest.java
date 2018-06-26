package com.oracle.tictactoe;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.Predicate;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.oracle.tictactoe.gamemodel.Marker;
import com.oracle.tictactoe.gamemodel.Outcome;

/**
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicTacToeApplication.class)
@WebAppConfiguration
public class TicTacToeGameServiceTest {

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJacksonwHttpMessageConverter;
    private MediaType contentType;
    
    @Autowired
    private WebApplicationContext webApplicationcontext;
    
   
    /**
     * Answer an instance for the following arguments
     */
    public TicTacToeGameServiceTest() {
        
        super();
        this.setContentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                                          MediaType.APPLICATION_JSON.getSubtype(),
                                          Charset.forName("utf8")));
        
    }
    
    /**
     * Perform simple game test
     */
    @Test
    public void simpleGameTest() throws Exception {
        
        MvcResult               tempResult;
        String                  tempMockResult;
        MockHttpServletResponse tempHttpResponse;
        Long                    tempGameId;
        Outcome                 tempOutcome;
        
        //Create game
        tempResult = this.performCreateGameInvocation();

        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        tempGameId = Long.decode(tempMockResult);
        
        //Make sure that there is at least one game
        tempResult = this.performGetAllGamesInvocation(1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        //Start game
        tempResult = this.performStartGameInvocation(tempGameId);
        
        //Choose markers for players
        tempResult = this.performChoosePlayerInvocation(tempGameId, Marker.O, 0);
        tempResult = this.performChoosePlayerInvocation(tempGameId, Marker.X, 1);
        
        //Get starting game state
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 0, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId, 1, 1, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 1, 1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId, 1, 2, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 2, 2);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = new String(tempHttpResponse.getContentAsByteArray());
        System.out.println("Move result outcome: " + tempMockResult.toString());
        tempOutcome = Outcome.valueOf(tempMockResult.replaceAll("\"", ""));
        assertTrue("Player 0 should have won", tempOutcome.equals(Outcome.WIN));
        this.dumpCurrentGameState(tempGameId);
        
        //Delete game
        tempResult = this.performDeleteInvocation(tempGameId);
        
        //Make sure that there are no games left
        tempResult = this.performGetAllGamesInvocation(0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        

        
    }
    
    /**
     * Perform tie game test
     */
    @Test
    public void tieGameTest() throws Exception {
        
        MvcResult               tempResult;
        String                  tempMockResult;
        MockHttpServletResponse tempHttpResponse;
        Long                    tempGameId;
        Outcome                 tempOutcome;
        
        //Create game
        tempResult = this.performCreateGameInvocation();

        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        tempGameId = Long.decode(tempMockResult);
        
        //Make sure that there is at least one game
        tempResult = this.performGetAllGamesInvocation(1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        //Start game
        tempResult = this.performStartGameInvocation(tempGameId);
        
        //Choose markers for players
        tempResult = this.performChoosePlayerInvocation(tempGameId, Marker.O, 0);
        tempResult = this.performChoosePlayerInvocation(tempGameId, Marker.X, 1);
        
        //Get starting game state
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 0, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId, 1, 1, 1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 2, 2);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId, 1, 1, 2);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 1, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = new String(tempHttpResponse.getContentAsByteArray());
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId, 1, 2, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
   
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 0, 2);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = new String(tempHttpResponse.getContentAsByteArray());
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
 
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId, 1, 0, 1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = new String(tempHttpResponse.getContentAsByteArray());
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId, 0, 2, 1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = new String(tempHttpResponse.getContentAsByteArray());
        System.out.println("Move result outcome: " + tempMockResult.toString());
        tempOutcome = Outcome.valueOf(tempMockResult.replaceAll("\"", ""));
        assertTrue("Player 1 should have caused a tie", tempOutcome.equals(Outcome.TIE));
        this.dumpCurrentGameState(tempGameId);
        
        //Delete game
        tempResult = this.performDeleteInvocation(tempGameId);
        
        //Make sure that there are no games left
        tempResult = this.performGetAllGamesInvocation(0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        

        
    }
    
    /**
     * Two games going at the same time test
     */
    @Test
    public void parallelGamesTest() throws Exception {
        
        MvcResult               tempResult;
        String                  tempMockResult;
        MockHttpServletResponse tempHttpResponse;
        Long                    tempGameId1;
        Long                    tempGameId2;
        
        tempGameId1 = this.createStartGameAndPickPlayers(1);
        tempGameId2 = this.createStartGameAndPickPlayers(2);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId1, 0, 0, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId1);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId1, 1, 1, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId1);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId2, 0, 1, 1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId2);
        
        //Make move for player 1
        tempResult = this.performMakeMoveInvocation(tempGameId2, 1, 2, 0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId2);
        
        //Make move for player 0
        tempResult = this.performMakeMoveInvocation(tempGameId1, 0, 2, 2);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = new String(tempHttpResponse.getContentAsByteArray());
        System.out.println("Move result outcome: " + tempMockResult.toString());
        this.dumpCurrentGameState(tempGameId1);
        
        //Delete game 2
        tempResult = this.performDeleteInvocation(tempGameId2);
        
        //Make sure that there is 1 game left
        tempResult = this.performGetAllGamesInvocation(1);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        //Delete game 1
        tempResult = this.performDeleteInvocation(tempGameId1);
        
        //Make sure that there is 0 game left
        tempResult = this.performGetAllGamesInvocation(0);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        

        
    }
    
    /**
     * Create start game and pick players. Answer the game id
     * @return Long
     */
    protected Long createStartGameAndPickPlayers(int aNumberOfGames) throws Exception {
        
        MvcResult               tempResult;
        String                  tempMockResult;
        MockHttpServletResponse tempHttpResponse;
        Long                    tempGameId;
        
        //Create game
        tempResult = this.performCreateGameInvocation();

        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        tempGameId = Long.decode(tempMockResult);
        
        //Make sure that there is at least aNumberOfGames games
        tempResult = this.performGetAllGamesInvocation(aNumberOfGames);
        assertTrue("No result", tempResult != null);
        tempHttpResponse = tempResult.getResponse();        
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock result: " + tempMockResult.toString());
        
        //Start game
        tempResult = this.performStartGameInvocation(tempGameId);
        
        //Choose markers for players
        tempResult = this.performChoosePlayerInvocation(tempGameId, Marker.O, 0);
        tempResult = this.performChoosePlayerInvocation(tempGameId, Marker.X, 1);
        
        //Get starting game state
        this.dumpCurrentGameState(tempGameId);
        
        return tempGameId;
    }
    
    
    
    
    /**
     * @param tempGameId
     * @throws Exception
     * @throws UnsupportedEncodingException
     */
    protected void dumpCurrentGameState(Long tempGameId)
                                throws Exception, UnsupportedEncodingException {
        
        MvcResult               tempResult;
        String                  tempMockResult;       
        MockHttpServletResponse tempHttpResponse;
        
        tempResult = this.performGetStateInvocation(tempGameId);
        assertTrue("No result", tempResult != null);
        
        tempHttpResponse = tempResult.getResponse();
        tempMockResult = tempHttpResponse.getContentAsString();
        System.out.println("Mock current game state for gameId: " + tempGameId.longValue());
        System.out.println(tempMockResult.toString());
        
    }
    
    /**
     * Perform get all games invocation
     * @throws Exception
     */
    protected MvcResult performGetAllGamesInvocation(int aNumberOfElements) throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.get("/tictactoe/allgames"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.iterableWithSize(aNumberOfElements)))
            .andReturn();
        
    }

    /**
     * Perform create game invocation
     * @return
     * @throws Exception
     */
    protected MvcResult performCreateGameInvocation() throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.post("/tictactoe/creategame"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
    }
    
    /**
     * Perform start game invocation
     * @param anId Long
     * @return MvcResult
     * @throws Exception
     */
    protected MvcResult performStartGameInvocation(Long anId) throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.post("/tictactoe/startgame")
                                           .param("game_id", anId.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
    }
    
    /**
     * Perform choose player invocation
     * @param anGameId Long
     * @param aMarker Marker
     * @param aPlayerIndex int
     * @return MvcResult
     * @throws Exception
     */
    protected MvcResult performChoosePlayerInvocation(Long aGameId,
                                                      Marker aMarker,
                                                      int aPlayerIndex) throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.put("/tictactoe/chooseplayer")
                                           .param("game_id", aGameId.toString())
                                           .param("marker", aMarker.name())
                                           .param("pindex", String.valueOf(aPlayerIndex)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
    }
    
    /**
     * Perform make move invocation
     * @param anGameId Long
     * @param aMarker Marker
     * @param aPlayerIndex int
     * @return MvcResult
     * @throws Exception
     */
    protected MvcResult performMakeMoveInvocation(Long aGameId,
                                                  int aPlayerIndex,
                                                  int aRowIndex,
                                                  int aColumnIndex) throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.put("/tictactoe/move")
                                           .param("game_id", aGameId.toString())
                                           .param("pindex", String.valueOf(aPlayerIndex))
                                           .param("row", String.valueOf(aRowIndex))
                                           .param("column", String.valueOf(aColumnIndex)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
    }
    
    /**
     * Perform get state invocation
     * @param anGameId Long
     * @param aMarker Marker
     * @param aPlayerIndex int
     * @return MvcResult
     * @throws Exception
     */
    protected MvcResult performGetStateInvocation(Long aGameId) throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.get("/tictactoe/state")
                                           .param("game_id", aGameId.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
    }
    
    /**
     * Perform delete invocation
     * @param anGameId Long
     * @return MvcResult
     * @throws Exception
     */
    protected MvcResult performDeleteInvocation(Long aGameId) throws Exception {
        
        return this.getMockMvc()
            .perform(MockMvcRequestBuilders.delete("/tictactoe/delete")
                                           .param("game_id", aGameId.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
    }
    
    

    /**
     * Method to set action jackson converters
     * @param aConverters HttpMessageConverter<?>[]
     */
    @Autowired
    public void setConverters(HttpMessageConverter<?>[] aConverters) {
        
        this.setMappingJacksonwHttpMessageConverter(
                Arrays.asList(aConverters).stream().filter(this.createMappingJackson2HttpMessageConverterFilter()).findAny().get());
        
    }

    /**
     * Answer a filter lambda for grabbing the proper covnerter
     * @return
     */
    protected Predicate<? super HttpMessageConverter<?>> createMappingJackson2HttpMessageConverterFilter() {
        
        return (aConverter) -> (aConverter instanceof MappingJackson2HttpMessageConverter);
        
    }
    
    
    /**
     * Test setup
     */
    @Before
    public void setup() throws Exception {
        
        this.setMockMvc(MockMvcBuilders.webAppContextSetup(this.getWebApplicationcontext()).build());
    }
    
    /**
     * Answer my mockMvc
     * @return MockMvc
     */
    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    /**
     * Set my mockMvc
     * @param mockMvc MockMvc
     */
    protected void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    /**
     * Answer my mappingJacksonwHttpMessageConverter
     * @return HttpMessageConverter
     */
    protected HttpMessageConverter getMappingJacksonwHttpMessageConverter() {
        return mappingJacksonwHttpMessageConverter;
    }

    /**
     * Set my mappingJacksonwHttpMessageConverter
     * @param mappingJacksonwHttpMessageConverter HttpMessageConverter
     */
    protected void setMappingJacksonwHttpMessageConverter(
            HttpMessageConverter mappingJacksonwHttpMessageConverter) {
        this.mappingJacksonwHttpMessageConverter = mappingJacksonwHttpMessageConverter;
    }

    /**
     * Answer my contentType
     * @return MediaType
     */
    protected MediaType getContentType() {
        return contentType;
    }

    /**
     * Set my contentType
     * @param contentType MediaType
     */
    protected void setContentType(MediaType contentType) {
        this.contentType = contentType;
    }

    /**
     * Answer my webApplicationcontext
     * @return WebApplicationContext
     */
    protected WebApplicationContext getWebApplicationcontext() {
        return webApplicationcontext;
    }

    /**
     * Set my webApplicationcontext
     * @param webApplicationcontext WebApplicationContext
     */
    protected void setWebApplicationcontext(
            WebApplicationContext webApplicationcontext) {
        this.webApplicationcontext = webApplicationcontext;
    }
    
    

}

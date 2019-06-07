package com.stackroute.muzixservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixControllerAdvice;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@WebMvcTest
public class MuzixControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Muzix muzix;

    @MockBean
    private MuzixServiceImpl muzixService;
    @InjectMocks
    private MuzixController muzixController;
    List<Muzix> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).setControllerAdvice(new MuzixControllerAdvice()).build();
       // mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
        muzix = new Muzix();
        muzix.setTrackId(1);
        muzix.setTrackName("ye h mohabbate");
        muzix.setTrackComment("good song");
        list = new ArrayList<>();
        list.add(muzix);
    }

// save track method is tested
    @Test
    public void saveTrack() throws Exception,TrackAlreadyExistsException{
        when(muzixService.saveTrack(any())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
// save track failure method is tested
    @Test
    public void saveTrackFailure() throws Exception {
        when(muzixService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
// display all tracks method is closed
    @Test
    public void displayAllTracks() throws Exception {
        when(muzixService.displayTrack()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
// get by name track is tested
    @Test
    public void getByNameTrack() throws Exception {
        when(muzixService.displayTrackByName(muzix.getTrackName())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/trackName")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    //get by name track failure is called
    @Test
    public void getByNameTrackFailure() throws Exception {
        when(muzixService.displayTrackByName(any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/trackName")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrackSuccess() throws Exception{
        when(muzixService.removeTrack(muzix.getTrackId())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tracks/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateTrackTest() throws Exception{
        when(muzixService.updateTrackComments(muzix,muzix.getTrackId())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/trackId")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
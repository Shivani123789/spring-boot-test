package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.controller.MuzixController;
import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@DataJpaTest
//@WebMvcTest(MuzixController.class)
public class MuzixSerivceTest {
    private Muzix muzix;

    @Mock
    //this specify which mock obiject to be injected
    private MuzixRepository muzixRepository;
    Optional optional;
    @InjectMocks
    private MuzixServiceImpl muzixService;
    List<Muzix> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        muzix = new Muzix();
       muzix.setTrackId(1);
       muzix.setTrackName("ye h mohabbate");
       muzix.setTrackComment("good song");
        list = new ArrayList<>();
        list.add(muzix);
        optional=Optional.of(muzix);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedTrack = muzixService.saveTrack(muzix);
        Assert.assertEquals(muzix,savedTrack);
        System.out.println(savedTrack);
        //verify here verifies that muzixRepository save method is only called once
        verify(muzixRepository,times(1)).save(muzix);
    }
    @Test
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(muzixRepository.save((Muzix) any())).thenReturn(null);
        Muzix savedTrack = muzixService.saveTrack(muzix);
        System.out.println(muzix);
        System.out.println("savedTrack" + savedTrack);
        Assert.assertNotEquals(muzix,savedTrack);
    }

    @Test
    public void updateTrackTest() throws TrackNotFoundException {
        when(muzixRepository.findById(muzix.getTrackId())).thenReturn(optional);
        Muzix muzix1=new Muzix(muzix.getTrackId(),muzix.getTrackName(),muzix.getTrackComment());
        System.out.println("aaa11  "+muzix1);
        muzixRepository.save(muzix);
        System.out.println("aaaaa  "+muzix);
       Muzix savedTrack = muzixService.updateTrackComments(muzix1,muzix.getTrackId());

       // System.out.println("savedTrack" + savedTrack);
        Assert.assertEquals(muzix1,savedTrack);
    }
////throws TrackAlreadyExistsException
    @Test
    public void getByNameTrackTestSuccess() throws TrackNotFoundException {
        when(muzixRepository.displayTrackByName(muzix.getTrackName())).thenReturn(muzix);
        Muzix savedTrack = muzixService.displayTrackByName(muzix.getTrackName());
        Assert.assertEquals(1,savedTrack.getTrackId());

    }
    @Test
    public void deleteTrackTest() throws TrackNotFoundException {
        when(muzixRepository.findById(muzix.getTrackId())).thenReturn(optional);
        Muzix deletedtrack=muzixService.removeTrack(muzix.getTrackId());
        Assert.assertEquals(1,deletedtrack.getTrackId());
        verify(muzixRepository,times(1)).deleteById(muzix.getTrackId());
    }

    @Test
    public void getAllTrack() throws TrackNotFoundException {

        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(list);
        System.out.println(list);
        List<Muzix> userlist = muzixService.displayTrack();
        System.out.println(userlist);
        Assert.assertEquals(list,userlist);
    }



}

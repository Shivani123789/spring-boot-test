package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Muzix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Track;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTest {
    @Autowired
    private MuzixRepository muzixRepository;
    private Muzix muzix;

    @Before
    public void setUp() {
        muzix = new Muzix();
        muzix.setTrackId(1);
        muzix.setTrackName("hum sath sath h");
        muzix.setTrackComment("this is good");
    }

    @After
    public void tearDown() {

        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack() {
        muzixRepository.save(muzix);
        Muzix fetchMuzix = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertEquals(1, fetchMuzix.getTrackId());

    }


    @Test
    public void testSaveTrackFailure() {
        Muzix testMuzix = new Muzix(1, "hello brother", "this is not good");
        muzixRepository.save(muzix);
        Muzix fetchUser = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertNotSame(testMuzix, muzix);
    }

    @Test
    public void testDisplayAllTrack()
    {
     Muzix muzix1=new Muzix(2,"do you want me","awesome");
     Muzix muzix2=new Muzix(3,"amplifier","this is good");
     muzixRepository.save(muzix1);
     muzixRepository.save(muzix2);
        List<Muzix> list=muzixRepository.findAll();
        Assert.assertEquals(2,list.get(0).getTrackId());

    }
    @Test
    public void testDisplayAllTrackFailure()
    {
        Muzix muzix1=new Muzix(2,"do you want me","awesome");
        Muzix muzix2=new Muzix(3,"amplifier","this is good");
        muzixRepository.save(muzix1);
        muzixRepository.save(muzix2);
        List<Muzix> list=muzixRepository.findAll();
        Assert.assertNotEquals(3,list.get(0).getTrackId());

    }

    @Test
    public void testRemoveTrack_givenTrackDetials_shouldRemoveTrack()
    {    Muzix muzix1=new Muzix(4,"do you want me","awesome");
        muzixRepository.save(muzix1);
        muzixRepository.deleteById(muzix1.getTrackId());
       Assert.assertEquals(0, muzixRepository.count());
    }

    @Test
    public void testRemoveTrackfailure()
    {    Muzix muzix1=new Muzix(4,"do you want me","awesome");
        muzixRepository.save(muzix1);
        muzixRepository.deleteById(muzix1.getTrackId());
        Assert.assertNotEquals(1, muzixRepository.count());
    }
    @Test
    public void testDisplyTrackByname_givenTrackDetails_shouldDisplayTrack() {
        Muzix muzix = new Muzix(1, "pal pal pal", "awesome");
        muzixRepository.save(muzix);
        Muzix muzix1=muzixRepository.displayTrackByName("pal ");
        System.out.println("aaaaaaaaaaa" + muzix1);
        Assert.assertNotEquals(muzix,muzix1);
    }
}
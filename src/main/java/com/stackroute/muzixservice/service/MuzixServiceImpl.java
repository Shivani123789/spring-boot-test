package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sound.midi.Track;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "muzix")
@Service
@Primary
public class MuzixServiceImpl implements MuzixService {
    private MuzixRepository muzixRepository;
    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository=muzixRepository;
    }

    @Override
    public Muzix saveTrack(Muzix muzix)throws TrackAlreadyExistsException {
        if(muzixRepository.existsById(muzix.getTrackId()))
        {
            throw new TrackAlreadyExistsException("tack is not found");
        }
        Muzix savedMuzix =muzixRepository.save(muzix);
        return savedMuzix;
    }
// save tack method is closed
    @PostConstruct
    public void loadData()
    {
        muzixRepository.save(Muzix.builder().trackId(3).trackName("kal ho na ho").trackComment("very good").build());
        muzixRepository.save(Muzix.builder().trackId(4).trackName("kal ho ").trackComment("very very good").build());
    }
    @Override
    public Muzix displayTrackByName(String trackName) throws TrackNotFoundException
    {
        return muzixRepository.displayTrackByName(trackName);

    }
// display by track method is closed
    @Override
    @Cacheable
    public List<Muzix> displayTrack() throws TrackNotFoundException {
    return muzixRepository.findAll();
    }
//display track method closed
    @Override
    public Muzix removeTrack(int trackId) throws TrackNotFoundException
    {
        Muzix muzix1=null;
        Optional optional=muzixRepository.findById(trackId);
        if(optional.isPresent())
        {
            muzix1=muzixRepository.findById(trackId).get();
            muzixRepository.deleteById(trackId);
        }
        return muzix1;
    }
// remove track method is closed
    @Override
    @CachePut
    public Muzix updateTrackComments(Muzix muzix, int trackId) throws TrackNotFoundException {
        Muzix muzix1=null;
        Optional optional=muzixRepository.findById(trackId);
        if(optional.isPresent())
        {
            muzix1=muzixRepository.findById(trackId).get();
        }
        else
        {
            throw new TrackNotFoundException("Track does not exist");
        }
        return muzix1;
     }
// update track mwethod is closed
    public void simulatedelay(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

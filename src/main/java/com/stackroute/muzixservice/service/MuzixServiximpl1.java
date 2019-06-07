package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MuzixServiximpl1 implements MuzixService {
    @Override
    public Muzix saveTrack(Muzix muzix) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public List<Muzix> displayTrack() throws TrackNotFoundException {
        return null;
    }

    @Override
    public Muzix removeTrack(int trackId) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Muzix displayTrackByName(String trackName) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Muzix updateTrackComments(Muzix muzix, int trackId) throws TrackNotFoundException {
        return null;
    }
}


package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;

import java.util.List;

public interface MuzixService {
    public Muzix saveTrack (Muzix muzix) throws TrackAlreadyExistsException;
    public List<Muzix> displayTrack() throws TrackNotFoundException;
    public Muzix removeTrack(int trackId) throws TrackNotFoundException;
    public Muzix displayTrackByName(String trackName) throws TrackNotFoundException;
    public Muzix updateTrackComments(Muzix muzix, int trackId) throws TrackNotFoundException;

}

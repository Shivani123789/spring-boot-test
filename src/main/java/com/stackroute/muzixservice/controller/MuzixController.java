package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
    private MuzixService muzixService;

    @Autowired
    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }

    @PostMapping("/tracks")
    public ResponseEntity<?> saveTrack(@RequestBody Muzix muzix) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        try {
            muzixService.saveTrack(muzix);
            responseEntity = new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
// save track method is closed

    @GetMapping("/tracks")
    public ResponseEntity<?> displayTracks() throws TrackNotFoundException {
        List<Muzix> musicList = muzixService.displayTrack();
        return new ResponseEntity<List<Muzix>>(musicList, HttpStatus.OK);
    }

    // display track method is closed
    @GetMapping("/tracks/{trackName}")
    public ResponseEntity<?> displayTracksByName(@PathVariable String trackName) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        try {
            Muzix musicList = muzixService.displayTrackByName(trackName);
            responseEntity = new ResponseEntity<Muzix>(musicList, HttpStatus.CREATED);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //display track by name is closed
    @DeleteMapping("/tracks/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int trackId) throws TrackNotFoundException {

        ResponseEntity responseEntity;
        try {
            muzixService.removeTrack(trackId);
            responseEntity = new ResponseEntity<String>("Deleted successfully", HttpStatus.CREATED);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CREATED);
        }
        return responseEntity;
    }

    //deletetrack mwthod is closed
    @PatchMapping("/tracks/{trackId}")
    public ResponseEntity<?> updateTracks(@RequestBody Muzix muzix, @PathVariable int trackId) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        Muzix musicList = muzixService.updateTrackComments(muzix, trackId);
           responseEntity = new ResponseEntity<Muzix>(musicList, HttpStatus.CREATED);
//        ResponseEntity responseEntity;
//        try {
//            Muzix musicList = muzixService.updateTrackComments(muzix, trackId);
//            responseEntity = new ResponseEntity<Muzix>(musicList, HttpStatus.CREATED);
//        } catch (Exception exception) {
//            responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
//        }
//        return responseEntity;
        //update track method is closed
        return responseEntity;
    }
}
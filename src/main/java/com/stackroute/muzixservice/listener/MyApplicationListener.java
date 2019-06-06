package com.stackroute.muzixservice.listener;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:configure.properties")
public class MyApplicationListener implements CommandLineRunner {
    @Value("${muzix1.id}")
    private int id;
    @Value("${muzix1.name}")
    private String name;
    @Value("${muzix1.comment}")
    private String comment;

    Muzix music=new Muzix();
    @Autowired
    MuzixRepository muzixRepository;
    @Override
    public void run(String... args) throws Exception {
        music.setTrackId(id);
        music.setTrackName(name);
        music.setTrackComment(comment);
        muzixRepository.save(music);
    }
}
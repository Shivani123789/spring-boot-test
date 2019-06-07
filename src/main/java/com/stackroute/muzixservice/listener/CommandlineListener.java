package com.stackroute.muzixservice.listener;
import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class CommandlineListener implements ApplicationListener<ContextRefreshedEvent> {
    Muzix music=new Muzix();
    @Autowired
    MuzixRepository muzixRepository;
    @Autowired
    private Environment environment;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        music.setTrackId(Integer.parseInt(environment.getProperty("track2.id")));
        music.setTrackName(environment.getProperty("track2.name"));
        music.setTrackComment(environment.getProperty("track2.comment"));
        muzixRepository.save(music);
//        Muzix.builder()
//                .trackId(Integer.parseInt(environment.getProperty("track2.idr")))
    }
}

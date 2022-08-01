package com.oc.dandfriends.util;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Random;

@Log4j2
@Component
@NoArgsConstructor
public class GenerateARandomUtil {


    public int generateARandom(int spellCastingOutcomesSize) {
        log.info("in generateARandomUtil in generateARandom method");
        //Generate a random number between min and max
        Random r = new Random();
        return r.nextInt(spellCastingOutcomesSize);
    }

}

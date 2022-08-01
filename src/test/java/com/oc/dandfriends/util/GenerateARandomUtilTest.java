package com.oc.dandfriends.util;

import com.oc.dandfriends.services.AdditionalRandomOutcomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenerateARandomUtilTest {


    private GenerateARandomUtil generateARandomUtilUnderTest;

    @BeforeEach
    void setup() {
        generateARandomUtilUnderTest = new GenerateARandomUtil();
    }

    @Test
    void checkGenerateARandom_whenGivenAValidspellCastingOutcomesSize_shouldReturnAnIntBetweenZeroAndspellCastingOutcomesSize() {
        //GIVEN
        int spellCastingOutcomesSize = 5;
        int generatedInt1 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt2 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt3 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt4 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt5 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt6 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt7 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt8 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt9 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);
        int generatedInt10 = generateARandomUtilUnderTest.generateARandom(spellCastingOutcomesSize);

        //WHEN THEN
        assertThat(generatedInt1).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt1).isLessThanOrEqualTo(4);
        assertThat(generatedInt2).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt2).isLessThanOrEqualTo(4);
        assertThat(generatedInt3).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt3).isLessThanOrEqualTo(4);
        assertThat(generatedInt4).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt4).isLessThanOrEqualTo(4);
        assertThat(generatedInt5).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt5).isLessThanOrEqualTo(4);
        assertThat(generatedInt6).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt6).isLessThanOrEqualTo(4);
        assertThat(generatedInt7).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt7).isLessThanOrEqualTo(4);
        assertThat(generatedInt8).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt8).isLessThanOrEqualTo(4);
        assertThat(generatedInt9).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt9).isLessThanOrEqualTo(4);
        assertThat(generatedInt10).isGreaterThanOrEqualTo(0);
        assertThat(generatedInt10).isLessThanOrEqualTo(4);
    }
}
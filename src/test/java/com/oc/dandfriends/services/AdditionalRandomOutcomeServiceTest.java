package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.AdditionalRandomOutcome;
import com.oc.dandfriends.repositories.AdditionalRandomOutcomeRepository;
import com.oc.dandfriends.util.GenerateARandomUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionalRandomOutcomeServiceTest {
    private AutoCloseable autoCloseable;
    private AdditionalRandomOutcomeService additionalRandomOutcomeServiceUnderTest;
    @Mock
    private AdditionalRandomOutcomeRepository additionalRandomOutcomeRepository;
    @Mock
    private GenerateARandomUtil generateARandomUtil;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        additionalRandomOutcomeServiceUnderTest = new AdditionalRandomOutcomeService(additionalRandomOutcomeRepository,generateARandomUtil);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void checkGeneratetAnAdditionalRandomOutcome_shouldReturnARandomOutcome(){
        //GIVEN
        AdditionalRandomOutcome additionalRandomOutcome1 = new AdditionalRandomOutcome(1,"effet1",null);
        AdditionalRandomOutcome additionalRandomOutcome2 = new AdditionalRandomOutcome(2,"effet2",null);
        AdditionalRandomOutcome additionalRandomOutcome3 = new AdditionalRandomOutcome(3,"effet3",null);
        AdditionalRandomOutcome additionalRandomOutcome4 = new AdditionalRandomOutcome(4,"effet4",null);
        AdditionalRandomOutcome additionalRandomOutcome5 = new AdditionalRandomOutcome(5,"effet5",null);
        AdditionalRandomOutcome additionalRandomOutcome6 = new AdditionalRandomOutcome(6,"effet6",null);
        AdditionalRandomOutcome additionalRandomOutcome7 = new AdditionalRandomOutcome(7,"effet7",null);
        List<AdditionalRandomOutcome> randomOutcomesUnderTest = new ArrayList<>();
        randomOutcomesUnderTest.add(additionalRandomOutcome1);
        randomOutcomesUnderTest.add(additionalRandomOutcome2);
        randomOutcomesUnderTest.add(additionalRandomOutcome3);
        randomOutcomesUnderTest.add(additionalRandomOutcome4);
        randomOutcomesUnderTest.add(additionalRandomOutcome5);
        randomOutcomesUnderTest.add(additionalRandomOutcome6);
        randomOutcomesUnderTest.add(additionalRandomOutcome7);

        //WHEN

        when(additionalRandomOutcomeRepository.findAll()).thenReturn(randomOutcomesUnderTest);
        when(generateARandomUtil.generateARandom(randomOutcomesUnderTest.size())).thenReturn(5);
        //THEN
        AdditionalRandomOutcome additionalRandomOutcomeUnderTest = additionalRandomOutcomeServiceUnderTest.generateAnAdditionalRandomOutcome();
        assertThat(additionalRandomOutcomeUnderTest.getDescription()).isEqualTo(additionalRandomOutcome6.getDescription());
    }

    @Test
    public void checkFindAllAdditionalRandomOutcomes_shouldCallAdditionalRandomOutcomeRepository() {
        //GIVEN WHEN
        additionalRandomOutcomeServiceUnderTest.findAllAdditionalRandomOutcome();
        //THEN
        verify(additionalRandomOutcomeRepository).findAll();
    }



    @Test
    public void checkFindAnAdditionalRandomOutcomeById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            additionalRandomOutcomeServiceUnderTest.findAnAdditionalRandomOutcomeById(null);
        }).withMessage("Invalid id");
    }

    @Test
    public void checkSaveAnAdditionalRandomOutcome_WhenAdditionalRandomOutcomeIsValid_shouldCallAdditionalRandomOutcomeRepository() throws Exception {
        //GIVEN WHEN

        AdditionalRandomOutcome additionalRandomOutcome = new AdditionalRandomOutcome(1,"effet","123");
        additionalRandomOutcomeServiceUnderTest.saveAnAdditionalRandomOutcome(additionalRandomOutcome);
        //THEN
        verify(additionalRandomOutcomeRepository).save(additionalRandomOutcome);
    }

    @Test
    public void checkSaveAnAdditionalRandomOutcome_WhenAdditionalRandomOutcomeIsNull_shouldThrowAnException() throws Exception {
        AdditionalRandomOutcome additionalRandomOutcome = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            additionalRandomOutcomeServiceUnderTest.saveAnAdditionalRandomOutcome(additionalRandomOutcome);
        }).withMessage("AdditionalRandomOutcome can't be null");
    }

    @Test
    public void checkDeleteAAdditionalRandomOutcome_shouldCallAdditionalRandomOutcomeRepository() throws Exception {
        //GIVEN WHEN
        AdditionalRandomOutcome additionalRandomOutcome1 = new AdditionalRandomOutcome(1,"effet1",null);
        additionalRandomOutcomeServiceUnderTest.deleteAnAdditionalRandomOutcome(additionalRandomOutcome1.getId());
        //THEN
        verify(additionalRandomOutcomeRepository).deleteById(1);
    }

    @Test
    public void checkDeleteAnAdditionalRandomOutcome_WhenAdditionalRandomOutcomeIsNull_ShouldThrowAnException() throws Exception {
        //GIVEN WHEN
        AdditionalRandomOutcome additionalRandomOutcome1 = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            additionalRandomOutcomeServiceUnderTest.deleteAnAdditionalRandomOutcome(null);
        }).withMessage("Invalid id");
    }



}
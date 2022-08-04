package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.entities.Spell;
import com.oc.dandfriends.enums.School;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpellRepositoryTest {

    private CustomTypeOfSpell customTypeOfSpell1;
    private CustomTypeOfSpell customTypeOfSpell2;
    private CustomTypeOfSpell customTypeOfSpell3;

    private Spell spell1;
    private Spell spell2;
    private Spell spell3;
    private Spell spell4;
    private Spell spell5;
    private Spell spell6;
    @Autowired
    SpellRepository spellRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        spellRepositoryUnderTest.deleteAll();
    }

    @Test
    void checkFindSpellsByKeyword_shouldReturnAListOfSpellsWithTitleContainingKeyword_WhenGivenAKeyword() {
        //GIVEN
        customTypeOfSpell1 = new CustomTypeOfSpell(1, "offensif", null);
        customTypeOfSpell2 = new CustomTypeOfSpell(2, "defensif", null);
        customTypeOfSpell3 = new CustomTypeOfSpell(3, "autre", null);

        List<CharacterClass> characterClasses = null;
        List<ComponentOfSpell> componentOfSpell = null;

        spell1 = new Spell(1, "Boule de feu", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpell, characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "A", "A", null);
        spell2 = new Spell(2, "Flèche acide", customTypeOfSpell2, School.EVOCATION, 3, componentOfSpell,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "B", "B", null);
        spell3 = new Spell(3, "Projectile magique", customTypeOfSpell3, School.EVOCATION, 3, componentOfSpell,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "C", "C", null);

        spell1.setCustomTypeOfSpell(customTypeOfSpell1);
        spell1.setCustomTypeOfSpell(customTypeOfSpell2);
        spell1.setCustomTypeOfSpell(customTypeOfSpell3);

        String keywordUnderTest = "MaGiqUe";

        spellRepositoryUnderTest.save(spell1);
        spellRepositoryUnderTest.save(spell2);
        spellRepositoryUnderTest.save(spell3);

        //WHEN
        List<Spell> spellsUnderTest = spellRepositoryUnderTest.findSpellsByKeywordIgnoreCase(keywordUnderTest);

        //THEN
        assertThat(spellsUnderTest.get(0).getTitle()).isEqualTo("Projectile magique");
    }

    @Test
    void checkFindSpellsByKeyword_shouldReturnAListOfSpellsWithShortDescriptionContainingKeyword_WhenGivenAKeyword() {
        //GIVEN
        customTypeOfSpell1 = new CustomTypeOfSpell(1, "offensif", null);
        customTypeOfSpell2 = new CustomTypeOfSpell(2, "defensif", null);
        customTypeOfSpell3 = new CustomTypeOfSpell(3, "autre", null);
        List<CharacterClass> characterClasses = null;
        List<ComponentOfSpell> componentOfSpellList = null;

        spell1 = new Spell(1, "A", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "un boule de feu explosant dans un bruit sourd et infligeant 1d6 points de dégâts de feu par niveau de lanceur de sorts", "A", null);
        spell2 = new Spell(2, "B", customTypeOfSpell2, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "Une flèche magique entièrement constituée d’acide jaillit de la main tendue du personnage, elle inflige 2d4 points de dégâts d’acide", "B", null);
        spell3 = new Spell(3, "C", customTypeOfSpell3, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "Un projectile d’énergie magique jaillit du doigt tendu du mage et va frapper sa cible, lui infligeant 1d4+1 points de dégâts", "C", null);

        String keywordUnderTest = "fEu";

        spellRepositoryUnderTest.save(spell1);
        spellRepositoryUnderTest.save(spell2);
        spellRepositoryUnderTest.save(spell3);

        //WHEN
        List<Spell> spellsUnderTest = spellRepositoryUnderTest.findSpellsByKeywordIgnoreCase(keywordUnderTest);

        //THEN
        assertThat(spellsUnderTest.get(0).getShortDescription()).isEqualTo(spell1.getShortDescription());
    }

    @Test
    void checkFindSpellsByKeyword_shouldReturnAListOfSpellsWithFullDescriptionContainingKeyword_WhenGivenAKeyword() {
        //GIVEN
        customTypeOfSpell1 = new CustomTypeOfSpell(1, "offensif", null);
        customTypeOfSpell2 = new CustomTypeOfSpell(2, "defensif", null);
        customTypeOfSpell3 = new CustomTypeOfSpell(3, "autre", null);
        List<CharacterClass> characterClasses = null;
        List<ComponentOfSpell> componentOfSpellList = null;

        spell1 = new Spell(1, "A", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "A", "un boule de feu explosant dans un bruit sourd et infligeant 1d6 points de dégâts de feu par niveau de lanceur de sorts", null);
        spell2 = new Spell(2, "B", customTypeOfSpell2, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "B", "Une flèche magique entièrement constituée d’acide jaillit de la main tendue du personnage, elle inflige 2d4 points de dégâts d’acide", null);
        spell3 = new Spell(3, "C", customTypeOfSpell3, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "C", "Un projectile d’énergie magique jaillit du doigt tendu du mage et va frapper sa cible, lui infligeant 1d4+1 points de dégâts", null);

        String keywordUnderTest = "énergiE magIqUe";

        spellRepositoryUnderTest.save(spell1);
        spellRepositoryUnderTest.save(spell2);
        spellRepositoryUnderTest.save(spell3);

        //WHEN
        List<Spell> spellsUnderTest = spellRepositoryUnderTest.findSpellsByKeywordIgnoreCase(keywordUnderTest);

        //THEN
        assertThat(spellsUnderTest.get(0).getShortDescription()).isEqualTo(spell3.getShortDescription());
    }

    @Test
    void checkFindSpellsByCustomTypeOfSpell_shouldReturnAListOfSpellsWithASpecificCustomTypeOfSpell_WhenGivenAnId() {
        //GIVEN
        customTypeOfSpell1 = new CustomTypeOfSpell(1, "offensif", null);
        customTypeOfSpell2 = new CustomTypeOfSpell(2, "defensif", null);
        customTypeOfSpell3 = new CustomTypeOfSpell(3, "autre", null);
        List<CharacterClass> characterClasses = null;
        List<ComponentOfSpell> componentOfSpellList = null;

        spell1 = new Spell(1, "Boule de feu 1", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "A", "un boule de feu explosant dans un bruit sourd et infligeant 1d6 points de dégâts de feu par niveau de lanceur de sorts", null);
        spell2 = new Spell(2, "B", customTypeOfSpell2, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "B", "Une flèche magique entièrement constituée d’acide jaillit de la main tendue du personnage, elle inflige 2d4 points de dégâts d’acide", null);
        spell3 = new Spell(3, "C", customTypeOfSpell3, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "C", "Un projectile d’énergie magique jaillit du doigt tendu du mage et va frapper sa cible, lui infligeant 1d4+1 points de dégâts", null);
        spell4 = new Spell(4, "Boule de feu 2", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "A", "un boule de feu explosant dans un bruit sourd et infligeant 1d6 points de dégâts de feu par niveau de lanceur de sorts", null);
        spell5 = new Spell(5, "Boule de feu 3", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "A", "un boule de feu explosant dans un bruit sourd et infligeant 1d6 points de dégâts de feu par niveau de lanceur de sorts", null);
        spell6 = new Spell(6, "Boule de feu 4", customTypeOfSpell1, School.EVOCATION, 3, componentOfSpellList,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "A", "un boule de feu explosant dans un bruit sourd et infligeant 1d6 points de dégâts de feu par niveau de lanceur de sorts", null);
        String keywordUnderTest = "énergiE magIqUe";

        spellRepositoryUnderTest.save(spell1);
        spellRepositoryUnderTest.save(spell2);
        spellRepositoryUnderTest.save(spell3);
        spellRepositoryUnderTest.save(spell4);
        spellRepositoryUnderTest.save(spell5);
        spellRepositoryUnderTest.save(spell6);

        //WHEN
        List<Spell> spellsUnderTest = spellRepositoryUnderTest.findSpellsByCustomTypeOfSpell(1);

        //THEN
        assertThat(spellsUnderTest.size()).isEqualTo(4);
        assertThat(spellsUnderTest.get(0).getTitle()).isEqualTo("Boule de feu 1");
        assertThat(spellsUnderTest.get(1).getTitle()).isEqualTo("Boule de feu 2");
        assertThat(spellsUnderTest.get(2).getTitle()).isEqualTo("Boule de feu 3");
        assertThat(spellsUnderTest.get(3).getTitle()).isEqualTo("Boule de feu 4");
    }
}

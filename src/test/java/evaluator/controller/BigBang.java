package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BigBang {

    private AppController appController;
    private static final String domenii_4 = "src/main/resources/domenii_4.txt";
    private static final String intrebari = "src/main/resources/intrebari1.txt";

    @Before
    public void init() {
        appController = new AppController("src/main/resources/intrebari1.txt");
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void functionalitate1() throws InputValidationFailedException, DuplicateIntrebareException {
        Intrebare intrebare = new Intrebare("Ala?", "1) porto", "2) cala", "3) ala", "1", "Random");
        appController.addNewIntrebare(intrebare);
        assertEquals(1, appController.getIntrebariRepository().getIntrebari().size());
    }

    @Test
    public void functionalitate2() {

        appController.loadIntrebariFromFile(domenii_4);

        try {
            evaluator.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",e.getMessage());
        }
    }

    @Test
    public void functionalitate3() {

        appController.loadIntrebariFromFile(intrebari);
        try {
            Statistica statistica = appController.getStatistica();
            assertEquals(5,statistica.getIntrebariDomenii().size());
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!",e.getMessage());
        }
    }

    @Test
    public void test() throws DuplicateIntrebareException {

        //P->A->B->C A valid, B invalid, C valid
        Intrebare intrebare = new Intrebare("Ala?", "1) porto", "2) cala", "3) ala", "1", "Random");
        Intrebare intrebare1 = new Intrebare("Ala?", "1) porto", "2) cala", "3) ala", "1", "Random1");
        Intrebare intrebare2 = new Intrebare("Ala?", "1) porto", "2) cala", "3) ala", "1", "Random2");
        appController.addNewIntrebare(intrebare);
        appController.addNewIntrebare(intrebare1);
        appController.addNewIntrebare(intrebare2);
        assertEquals(3, appController.getIntrebariRepository().getIntrebari().size());

        appController.loadIntrebariFromFile(intrebari);
        try {
            evaluator.model.Test test = appController.createNewTest();
            assertEquals(test.getIntrebari().size(),5);
        } catch (NotAbleToCreateTestException e) {
            assertEquals("Nu exista suficiente domenii pentru crearea unui test!(5)",e.getMessage());
        }

        appController.loadIntrebariFromFile(domenii_4);
        try {
            Statistica statistica = appController.getStatistica();
            assertEquals(statistica.getIntrebariDomenii().size(),4);
        } catch (NotAbleToCreateStatisticsException e) {
            assertEquals("Repository-ul nu contine nicio intrebare!",e.getMessage());
        }
    }
}
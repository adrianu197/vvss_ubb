package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class AppControllerTest {
    AppController ctrl;
    AppController ctrl2;
    String domenii_4 = "/home/adrian/Documents/Documents/school/vvss/lab1/src/main/resources/domenii_4.txt";
    String intrebari_4 = "/home/adrian/Documents/Documents/school/vvss/lab1/src/main/resources/intrebari_4.txt";
    String intrebari_correct = "/home/adrian/Documents/Documents/school/vvss/lab1/src/main/resources/intrebari1.txt";
    @Before
    public void setUp() throws Exception {
        this.ctrl = new AppController("/home/adrian/Documents/Documents/school/vvss/lab1/src/main/resources/repoTest.txt");
        this.ctrl2 = new AppController("/home/adrian/Documents/Documents/school/vvss/lab1/src/main/resources/repoTest.txt");
    }

    @Test
    public void addNewIntrebare() {

        //this should not fail
        try {
            Intrebare intr1 = this.ctrl.addNewIntrebare(
                    "Cum esti1?",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    "Stare"
            );
        if(!this.ctrl.exists(intr1))
                assert false;
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assert false;
        }
    }

    @Test
    public void addNewIntrebare2() {
        try {
            Intrebare intr1 = this.ctrl.addNewIntrebare(
                    "error",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    "Stare"
            );
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assertEquals(e.getMessage(),"Prima litera din enunt nu e majuscula!");
        }
    }
    @Test
    public void addNewIntrebare3() {
        try {
            Intrebare intr1 = this.ctrl.addNewIntrebare(
                    "Cum esti3?",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    "stare"
            );
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assertEquals(e.getMessage(),"Prima litera din domeniu nu e majuscula!");
        }
    }
    @Test
    public void addNewIntrebare4() {
        try {
            Intrebare intr1 = this.ctrl.addNewIntrebare(
                    String.join("", Collections.nCopies(101, String.valueOf('A'))) + "?",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    "Stare"
            );
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assertEquals(e.getMessage(),"Lungimea enuntului depaseste 100 de caractere!");
        }
    }
    @Test
    public void addNewIntrebare5() {
        try {
            Intrebare intr1 = this.ctrl.addNewIntrebare(
                    "Cum esti5?",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    String.join("", Collections.nCopies(31, String.valueOf('A')))
            );
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assertEquals(e.getMessage(),"Lungimea domeniului depaseste 30 de caractere!");
        }
    }
    @Test
    public void addNewIntrebare6() {
        try {
            Intrebare intr1 = ctrl.addNewIntrebare(
                    "A",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    "Stare"
            );
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assertEquals(e.getMessage(),"Ultimul caracter din enunt nu e '?'!");
        }
    }
    @Test
    public void addNewIntrebare7() {
        try {
            Intrebare intr1 = this.ctrl.addNewIntrebare(
                    "Cum esti8?",
                    "1)bine?",
                    "2)foarte bine?",
                    "3)rau",
                    "1",
                    ""
            );
        } catch (InputValidationFailedException | DuplicateIntrebareException e) {
            assertEquals(e.getMessage(),"Domeniul este vid!");
        }
    }


    @Test
    public void createNewTest() {
        ctrl2.loadIntrebariFromFile(domenii_4);
        try {
            evaluator.model.Test test = ctrl2.createNewTest();
            assert false;
        } catch (NotAbleToCreateTestException e) {
            assertEquals(e.getMessage(),"Nu exista suficiente domenii pentru crearea unui test!(5)");
        }
    }
    @Test
    public void createNewTest1() {
        ctrl2.loadIntrebariFromFile(intrebari_4);
        try {
            evaluator.model.Test test = ctrl2.createNewTest();
            assert false;
        } catch (NotAbleToCreateTestException e) {
            assertEquals(e.getMessage(),"Nu exista suficiente intrebari pentru crearea unui test!(5)");
        }
    }
    @Test
    public void createNewTest2() {
        ctrl2.loadIntrebariFromFile(intrebari_correct);
        try {
            evaluator.model.Test test = ctrl2.createNewTest();
            if(!ctrl2.getIntrebariRepository().getIntrebari().containsAll(test.getIntrebari())){
                assert false;
            }else {
                assert true;
            }
        } catch (NotAbleToCreateTestException e) {
            assert false;
        }
    }
}
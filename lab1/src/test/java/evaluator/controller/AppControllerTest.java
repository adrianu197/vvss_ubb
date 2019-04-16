package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class AppControllerTest {
    AppController ctrl;

    @Before
    public void setUp() throws Exception {
        this.ctrl = new AppController("tests.txt");
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
}
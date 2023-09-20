import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumatorTest {
    Sumator sumator = new Sumator();

    //коли все ок
    @Test
    void correctData() throws Exception {
        double res;
        try {
            res = sumator.calculate(1, 3, 1,4, 5, 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(-10.5, res);
    }


    //a = n; b =m
    @Test
    void evenLowerUpperLimits() throws Exception {
        double res;
        try {
            res = sumator.calculate(1, 1, 1,4, 4, 8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(-3, res);
    }


    //ділення на нуль
    @Test
    public void divisionByZero() {
        String exeptionMessage = "Division by 0";
        Exception exception = assertThrows(Exception.class, () -> sumator.calculate( -1,1,(float)0.1,2,3, (float)0.1));
        assertEquals(exeptionMessage, exception.getMessage());
    }

    //a > n => помилка
    @Test
    public void whenFirstUpperLimitSmallerThanLower() {
        String exeptionMessage = "First Lower limit (a) > First Upper limit(n)";
        Exception exception = assertThrows(Exception.class, () -> sumator.calculate(2,1,(float)0.1,1,7, (float)0.1));
        assertEquals(exeptionMessage, exception.getMessage());
    }

    // b > m => помилка
    @Test
    public void whenSecondUpperLimitSmallerThanLower() {
        String exeptionMessage = "Second Lower limit (b) > Second Upper limit(m)";
        Exception exception = assertThrows(Exception.class, () -> sumator.calculate(1,2,(float)0.1,7,1, (float)0.1));
        assertEquals(exeptionMessage, exception.getMessage());
    }


    //крок може бути не цілим і не ламатися
    @Test
    void correctFloatStep() throws Exception {
        double res;
        try {
            res = sumator.calculate( -2, -1.8f,0.2f,4.9f, 5.2f, 0.3f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(14.6, res);
    }

    //невалідний крок (lower-upper/step != integer) => помилка
    @Test
    void invalidStep() throws Exception {
        String exeptionMessage = "step is invalid";
        Exception exception = assertThrows(Exception.class, () -> sumator.calculate( -2, -1.8f,0.3f,4.9f, 5.2f, 0.3f));
        assertEquals("First " +exeptionMessage, exception.getMessage());

        exception = assertThrows(Exception.class, () -> sumator.calculate( -2, -1.8f,0.2f,4.9f, 5.2f, 0.2f));
        assertEquals("Second " + exeptionMessage, exception.getMessage());
    }

    //крок < 0
    @Test
    void negativeStep() throws Exception {
        String exeptionMessage = "step < 0";
        Exception exception = assertThrows(Exception.class, () -> sumator.calculate( -2, -1.8f,-0.2f,4.9f, 5.2f, 0.3f));
        assertEquals("First " +exeptionMessage, exception.getMessage());

        exception = assertThrows(Exception.class, () -> sumator.calculate( -2, -1.8f,0.2f,4.9f, 5.2f, -0.3f));
        assertEquals("Second " + exeptionMessage, exception.getMessage());
    }

    //крок == 0
    @Test
    void zeroStep() throws Exception {
        String exeptionMessage = "step == 0";
        Exception exception = assertThrows(Exception.class, () -> sumator.calculate( -2, -1.8f,0,4.9f, 5.2f, 0.3f));
        assertEquals("First " +exeptionMessage, exception.getMessage());

        exception = assertThrows(Exception.class, () -> sumator.calculate( -2, -1.8f,0.2f,4.9f, 5.2f, 0));
        assertEquals("Second " + exeptionMessage, exception.getMessage());
    }
}
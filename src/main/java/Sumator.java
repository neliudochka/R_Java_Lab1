import java.math.BigDecimal;
import java.math.RoundingMode;

class Sumator {
    public double calculate(float a, float n, float stepOne, float b, float m, float stepTwo) throws Exception {
        /*
        від а до n
        від b до m
         */
        if (a > n){
            throw  new Exception("First Lower limit (a) > First Upper limit(n)");
        }
        if (b > m){
            throw  new Exception("Second Lower limit (b) > Second Upper limit(m)");
        }

        //що тут забули BigDecimal див у рідмі

        BigDecimal sum = new BigDecimal("0");
        BigDecimal aBD = new BigDecimal(Float.toString(a));
        BigDecimal nBD = new BigDecimal(Float.toString(n));
        BigDecimal bBD = new BigDecimal(Float.toString(b));
        BigDecimal mBD = new BigDecimal(Float.toString(m));
        BigDecimal soBD = new BigDecimal(Float.toString(stepOne));
        BigDecimal stBD = new BigDecimal(Float.toString(stepTwo));


        //перевірити коректність кроків
        checkStep(aBD, nBD, soBD, "First");
        checkStep(bBD, mBD, stBD, "Second");

        for (BigDecimal i = aBD; i.compareTo(nBD) <= 0; i = i.add(soBD)) {
            if (new BigDecimal("0").compareTo(i) == 0){
                throw new Exception("Division by 0");
            }
            for (BigDecimal j=bBD; j.compareTo(mBD) <= 0; j = j.add(stBD)) {
                // 1-j/1
                sum = sum.add(new BigDecimal("1").subtract(j.divide(i, RoundingMode.HALF_EVEN)));
            }
        }
        return sum.doubleValue();
    }

    private void checkStep(BigDecimal lower, BigDecimal upper, BigDecimal step, String numberOfStep) throws Exception {
        //flag == 0 => step = 0
        //flag == -1 => step < 0
        int flag = step.compareTo(new BigDecimal("0"));
        if (flag == 0) {
            throw new Exception(numberOfStep + " step == 0");
        }
        if (flag < 0) {
            throw new Exception(numberOfStep + " step < 0");
        }

        //проміжок поділити на крок == цілочисельне число => нормальний крок
        BigDecimal n = upper.subtract(lower).divide(step, RoundingMode.HALF_EVEN);
        if ( n.compareTo(n.setScale(0, RoundingMode.DOWN)) > 0){
            throw new Exception(numberOfStep + " step is invalid");
        }
    }
}
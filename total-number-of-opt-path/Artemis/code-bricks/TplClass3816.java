public class TplClass3816 {

    private static final void method(java.lang.String CLASS_PATH, boolean validated, java.lang.String line) throws Throwable {
        String lineTrimmed = line.trim();
        String[] lineSplit = lineTrimmed.split(" +");
        String sizeUsuallyInKb = lineSplit[lineSplit.length - 2];
        sizeUsuallyInKb = sizeUsuallyInKb.trim();
        if (!sizeUsuallyInKb.equals("0")) {
        } else {
            validated = true;
        }
    }
}


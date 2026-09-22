public class TplClass3813 {

    private static final void method(java.lang.String CLASS_PATH, boolean validated, java.lang.String line) throws Throwable {
        if (line.startsWith("Shared_Dirty") || line.startsWith("Private_Dirty")) {
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
}


import java.io.FileOutputStream;
import java.text.DecimalFormatSymbols;
import java.io.ObjectOutputStream;

public class TplClass5756 {

    private static final void method() throws Throwable {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("*SpecialCurrencySymbol*");
        FileOutputStream ostream = new FileOutputStream("DecimalFormatSymbols.142");
        ObjectOutputStream p = new ObjectOutputStream(ostream);
        p.writeObject(dfs);
        ostream.close();
    }
}


import java.util.WeakHashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

public class TplClass6252 {

    private static final void method() throws Throwable {
        boolean testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            Hashtable bad1 = new Hashtable(100, -3);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            Hashtable bad1 = new Hashtable(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            HashMap bad1 = new HashMap(100, -3);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            HashMap bad1 = new HashMap(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            HashSet bad1 = new HashSet(100, -3);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            HashSet bad1 = new HashSet(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            WeakHashMap bad1 = new WeakHashMap(100, -3);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            // this should generate an IllegalArgumentException
            WeakHashMap bad1 = new WeakHashMap(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        // Make sure that legal creates don't throw exceptions
        Map goodMap = new Hashtable(100, .69f);
        goodMap = new HashMap(100, .69f);
        Set goodSet = new HashSet(100, .69f);
        goodMap = new WeakHashMap(100, .69f);
    }
}


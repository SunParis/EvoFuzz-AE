import java.util.Arrays;
import java.util.TreeMap;
import java.util.SortedSet;
import java.util.List;
import java.util.SortedMap;
import java.util.Map;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;

public class TplClass7706 {

    private static final void method() throws Throwable {
        boolean testSucceeded = false;
        try {
            List l = Arrays.asList(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            Collection c = Collections.unmodifiableCollection(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            Set c = Collections.unmodifiableSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            List c = Collections.unmodifiableList(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            Map c = Collections.unmodifiableMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            SortedSet c = Collections.unmodifiableSortedSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            SortedMap c = Collections.unmodifiableSortedMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            Collection c = Collections.synchronizedCollection(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            Set c = Collections.synchronizedSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            List c = Collections.synchronizedList(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            Map c = Collections.synchronizedMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            SortedSet c = Collections.synchronizedSortedSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        testSucceeded = false;
        try {
            SortedMap c = Collections.synchronizedSortedMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
        if (!testSucceeded)
            ;
        // Make sure that non-null arguments don't throw exc.
        List l = Arrays.asList(new Object[0]);
        Collection c = Collections.unmodifiableCollection(Collections.EMPTY_SET);
        Set s = Collections.unmodifiableSet(Collections.EMPTY_SET);
        l = Collections.unmodifiableList(Collections.EMPTY_LIST);
        Map m = Collections.unmodifiableMap(Collections.EMPTY_MAP);
        SortedSet ss = Collections.unmodifiableSortedSet(new TreeSet());
        SortedMap sm = Collections.unmodifiableSortedMap(new TreeMap());
        c = Collections.synchronizedCollection(Collections.EMPTY_SET);
        s = Collections.synchronizedSet(Collections.EMPTY_SET);
        l = Collections.synchronizedList(Collections.EMPTY_LIST);
        m = Collections.synchronizedMap(Collections.EMPTY_MAP);
        ss = Collections.synchronizedSortedSet(new TreeSet());
        sm = Collections.synchronizedSortedMap(new TreeMap());
    }
}


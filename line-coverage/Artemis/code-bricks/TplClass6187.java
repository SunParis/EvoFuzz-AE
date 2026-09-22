import java.util.TreeMap;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class TplClass6187 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap();
            m.headMap(new Object());
        } catch (ClassCastException e) {
        }
        try {
            SortedMap m = new TreeMap();
            m.tailMap(new Object());
        } catch (ClassCastException e) {
        }
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.headMap(new Integer(0));
        } catch (ClassCastException e) {
        }
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.tailMap(new Integer(0));
        } catch (ClassCastException e) {
        }
        try {
            SortedSet m = new TreeSet();
            m.headSet(new Object());
        } catch (ClassCastException e) {
        }
        try {
            SortedSet m = new TreeSet();
            m.tailSet(new Object());
        } catch (ClassCastException e) {
        }
        try {
            SortedSet m = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            m.headSet(new Integer(0));
        } catch (ClassCastException e) {
        }
        try {
            SortedSet m = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            m.tailSet(new Integer(0));
        } catch (ClassCastException e) {
        }
        try {
            SortedMap m = new TreeMap();
            m.headMap(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedMap m = new TreeMap();
            m.tailMap(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.headMap(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.tailMap(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedSet m = new TreeSet();
            m.headSet(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedSet m = new TreeSet();
            m.tailSet(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedSet m = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            m.headSet(null);
        } catch (NullPointerException e) {
        }
        try {
            SortedSet m = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            m.tailSet(null);
        } catch (NullPointerException e) {
        }
        // These should not fail
        SortedMap m = new TreeMap();
        m.headMap(new Integer(0));
        m.tailMap(new Integer(0));
        m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        m.headMap("llama");
        m.tailMap("llama");
        SortedSet s = new TreeSet();
        s.headSet(new Integer(0));
        s.tailSet(new Integer(0));
        s = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        s.headSet("drama");
        s.tailSet("drama");
    }
}


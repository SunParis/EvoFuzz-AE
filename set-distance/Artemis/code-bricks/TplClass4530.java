import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TplClass4530 {

    private static final void method() throws Throwable {
        try {
            /* this is the "alternate" DEX/Jar file */
            String DEX_FILE = System.getenv("DEX_LOCATION") + "/068-classloader-ex.jar";
            /* on Dalvik, this is a DexFile; otherwise, it's null */
            Class<?> mDexClass = Class.forName("dalvik.system.DexFile");
            Constructor<?> ctor = mDexClass.getConstructor(String.class);
            Object mDexFile = ctor.newInstance(DEX_FILE);
            Method meth = mDexClass.getMethod("loadClass", String.class, ClassLoader.class);
            Object klass = meth.invoke(mDexFile, "Mutator", null);
            if (klass == null) {
            }
        } catch (Exception e) {
        }
    }
}


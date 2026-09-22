import java.net.URLClassLoader;

public class TplClass1793 {

    private static final void method(java.lang.String classname) throws Throwable {
        Class cl = Class.forName(classname);
        URLClassLoader apploader = (URLClassLoader) cl.getClassLoader();
        ClassLoader loader = new URLClassLoader(apploader.getURLs(), apploader.getParent());
        Class c = loader.loadClass(classname);
        Runnable r = (Runnable) c.newInstance();
        r.run();
    }
}


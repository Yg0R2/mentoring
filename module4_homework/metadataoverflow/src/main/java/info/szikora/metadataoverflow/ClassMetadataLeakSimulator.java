package info.szikora.metadataoverflow;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

//
// Source: https://dzone.com/articles/java-8-permgen-metaspace
// 
// TODO Investigate app behavior using JVisualVm and play around with command arguments. A starting point may be something like below.
//
// Run it with command arguments like below 
// 
// -XX:MaxMetaspaceSize=150m
// -XX:+HeapDumpOnOutOfMemoryError
// -XX:+PrintGCDetails 
// -XX:+PrintGCDateStamps 
// -Xloggc:gclog.log
//
//

/**
 * PermGenRemovalValidator
 * 
 * @author Pierre-Hugues Charbonneau
 * 
 */
public class ClassMetadataLeakSimulator {

    private static Map<String, ClassA> classLeakingMap = new WeakHashMap<>();
    private final static int NB_ITERATIONS_DEFAULT = 50000;

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Class metadata leak simulator");
        System.out.println("Author: Pierre-Hugues Charbonneau");
        System.out.println("http://javaeesupportpatterns.blogspot.com");

        int nbIterations = (args != null && args.length == 1) ? Integer.parseInt(args[0]) : NB_ITERATIONS_DEFAULT;

        try {

            for (int i = 0; i < nbIterations; i++) {

                String fictiousClassloaderJAR = "file:" + i + ".jar";

                URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderJAR) };

                // Create a new classloader instance
                URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);

                // Create a new Proxy instance
                ClassA t = (ClassA) Proxy.newProxyInstance(newClassLoader,
                        new Class<?>[] { ClassA.class },
                        new ClassAInvocationHandler(new ClassAImpl()));

                // Add the new Proxy instance to the leaking HashMap
                classLeakingMap.put(fictiousClassloaderJAR, t);
            }
        } catch (Throwable any) {
            System.out.println("ERROR: " + any);
        }

        System.out.println("Done!");
    }
}
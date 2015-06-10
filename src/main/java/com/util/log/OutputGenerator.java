/**
* General Utils
* Performs util actions for test
*
* @author ASIH
*
*/

package com.util.log;

import com.agent.AgentService;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OutputGenerator {

	/**
	 * Private constructor
	 *
	 */

	private OutputGenerator() {

	}

	/**
	 * Create Generic Class Desc. Create the test class description
	 *            -
	 *
	 @param desc
	 *            -
	 *
	 * @throws java.net.MalformedURLException
	 */

	public static <T> String createGenericClassDesc(Class<T> testClass, StringBuilder desc) {
		StringBuilder out = new StringBuilder();
		out.append("\n");
		out.append("============================================================================").append("\n");
		out.append("New Test Class Started ").append("\n");
		out.append("============================================================================").append("\n");
		out.append("Test Class Name :  " + testClass.getName()).append("\n");
		;
		out.append(testClass.getName()).append("\n");
		;
		out.append("\n");
		out.append("Theses are the tests included : ").append("\n");
		out.append("\n");
		int i = 1;
        List<Method> testMethods = getMethodsAnnotatedWith(testClass, Test.class);
        for (Method m : testMethods) {
            out.append(i).append(".").append(m.getName()).append("\n");
            i++;
        }
		out.append("\n");
		out.append(desc).append("\n");
		out.append("============================================================================").append("\n");
		return out.toString();
	}

	/**
	 * Create Generic Method Desc. Create the test method description
	 *
	 * @param testClassName
	 *            -
	 *
	 * @param testName
	 *            -
	 *
	 * @param desc
	 *            -
	 *
	 *            - all test flows
	 *
	 * @throws java.net.MalformedURLException
	 */

	public static String createGenericMethodDesc(String testClassName, String testName, StringBuilder desc, List<String> flowDesc) {
		StringBuilder out = new StringBuilder();
		out.append("\n");
		out.append("-------------------------------------------").append("\n");
		out.append("New Test Started ").append("\n");
		out.append("-------------------------------------------").append("\n");
		out.append("Test Class Name :  " + testClassName).append("\n");
		out.append("Test Name :  " + testName).append("\n");
		out.append("---------------------------------------------").append("\n");
		if (flowDesc != null) {
			out.append("Theses are the test flow : ").append("\n");
			out.append("\n");
			int i = 1;
			for (String element : flowDesc) {
				out.append(i).append(".").append(element).append("\n");
				i++;
			}
		}
		if (desc != null) {
			out.append(desc).append("\n");
			out.append("---------------------------------------------").append("\n");
		}
		return out.toString();
	}

    private static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        while (klass != Object.class) { // need to iterated thought hierarchy in order to retrieve methods from above the current instance
            // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
            final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (annotation == null || method.isAnnotationPresent(annotation)) {
                    Annotation annotInstance = method.getAnnotation(annotation);
                    // TODO process annotInstance
                    methods.add(method);
                }
            }
            // move to the upper class in the hierarchy in search for more methods
            klass = klass.getSuperclass();
        }
        return methods;
    }

}

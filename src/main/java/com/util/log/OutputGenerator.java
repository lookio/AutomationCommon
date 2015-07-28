/**
* General Utils
* Performs util actions for test
*
* @author ASIH
*
*/

package com.util.log;

import com.agent.AgentService;
import com.util.genutil.GeneralUtils;
import org.junit.Test;
import com.util.log.TestSteps;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OutputGenerator {

	private static Class testClass;


	private OutputGenerator() {

	}

	public static <T> String createGenericClassDesc(final Class<T> testClass, StringBuilder desc) {
		setTestClass(testClass);
		StringBuilder out = new StringBuilder();
		out.append("\n");
		out.append("============================================================================").append("\n");
		out.append("New Test Class Started ").append("\n");
		out.append("============================================================================").append("\n");
		out.append("Test Class Name :  " + testClass.getName()).append("\n");
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

	public static String createGenericMethodDesc(final String testName) {
		StringBuilder out = new StringBuilder();
		out.append("\n");
		out.append("-------------------------------------------").append("\n");
		out.append("New Test Started ").append("\n");
		out.append("-------------------------------------------").append("\n");
		out.append("Test Class Name :  " + testClass.getName()).append("\n");
		out.append("Test Name :  " + testName).append("\n");
		out.append("---------------------------------------------").append("\n");
		String[] flowDesc = new String[0];
		try {
			flowDesc = getFlowDesc(testClass.getMethod(testName, null));
		} catch (NoSuchMethodException e) {
			GeneralUtils.handleError("", e);
		}
		if (flowDesc != null) {
			out.append("Theses are the test flow : ").append("\n");
			out.append("\n");
			int i = 1;
			for (String element : flowDesc) {
				out.append(i).append(".").append(element).append("\n");
				i++;
			}
		}
		out.append("\n");
		out.append("---------------------------------------------").append("\n");
		return out.toString();
	}

	private static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
		final List<Method> methods = new ArrayList<Method>();
		Class<?> klass = type;
		while (klass != Object.class) {
			final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
			for (final Method method : allMethods) {
				if (annotation == null || method.isAnnotationPresent(annotation)) {
					methods.add(method);
				}
			}
			klass = klass.getSuperclass();
		}
		return methods;
	}

	private static String[] getFlowDesc(Method m){
		if (m.isAnnotationPresent(Test.class)) {
			TestSteps testSteps = null;
			Annotation[] annotations = m.getDeclaredAnnotations();
			for(Annotation annotation : annotations){
				if(annotation instanceof TestSteps){
					testSteps = (TestSteps) annotation;
				}
			}
			if (testSteps != null) {
				return testSteps.steps();
			}else {
				return null;
			}
		}else{
			return null;
		}
	}

	public static <T> void setTestClass(Class<T> testClass) {
		OutputGenerator.testClass = testClass;
	}

}

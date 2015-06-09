///**
// * General Utils
// * Performs util actions for test
// *
// * @author ASIH
// *
// */
//
//package com.util.log;
//
//import java.net.MalformedURLException;
//import java.util.List;
//
//
//public class OutputGenerator {
//
//	/**
//	 * Private constructor
//	 *
//	 */
//
//	private OutputGenerator() {
//
//	}
//
//	/**
//	 * Create Generic Class Desc. Create the test class description
//	 *
//	 * @param testClassName
//	 *            -
//	 *
//	 @param desc
//	 *            -
//	 *
//	 * @param testsDesc
//	 *            - all tests description
//	 *
//	 * @throws java.net.MalformedURLException
//	 */
//
//	public static String createGenericClassDesc(String testClassName, StringBuilder desc, List<String> testsDesc, Sites site) {
//		StringBuilder out = new StringBuilder();
//		out.append("\n");
//		desc.append(Sites.IL.name() + " product regression.").append("\n");
//		out.append("============================================================================").append("\n");
//		out.append("New Test Class Started ").append("\n");
//		out.append("============================================================================").append("\n");
//		out.append("Test Class Name :  " + testClassName).append("\n");
//		;
//		out.append(testClassName).append("\n");
//		;
//		out.append("\n");
//		out.append("Theses are the tests included : ").append("\n");
//		out.append("\n");
//		int i = 1;
//		for (String element : testsDesc) {
//			out.append(i).append(".").append(element).append("\n");
//			i++;
//		}
//		out.append("\n");
//		out.append(desc).append("\n");
//		out.append("============================================================================").append("\n");
//		return out.toString();
//	}
//
//	/**
//	 * Create Generic Method Desc. Create the test method description
//	 *
//	 * @param testClassName
//	 *            -
//	 *
//	 * @param testName
//	 *            -
//	 *
//	 * @param desc
//	 *            -
//	 *
//	 *            - all test flows
//	 *
//	 * @throws java.net.MalformedURLException
//	 */
//
//	public static String createGenericMethodDesc(String testClassName, String testName, StringBuilder desc, List<String> flowDesc) {
//		StringBuilder out = new StringBuilder();
//		out.append("\n");
//		out.append("-------------------------------------------").append("\n");
//		out.append("New Test Started ").append("\n");
//		out.append("-------------------------------------------").append("\n");
//		out.append("Test Class Name :  " + testClassName).append("\n");
//		out.append("Test Name :  " + testName).append("\n");
//		out.append("---------------------------------------------").append("\n");
//		if (flowDesc != null) {
//			out.append("Theses are the test flow : ").append("\n");
//			out.append("\n");
//			int i = 1;
//			for (String element : flowDesc) {
//				out.append(i).append(".").append(element).append("\n");
//				i++;
//			}
//		}
//		if (desc != null) {
//			out.append(desc).append("\n");
//			out.append("---------------------------------------------").append("\n");
//		}
//
//		return out.toString();
//	}
//
//}

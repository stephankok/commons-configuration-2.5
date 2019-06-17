import org.junit.runner.Result;

import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.beanutils.*;
import org.apache.commons.configuration2.builder.*;
import org.apache.commons.configuration2.builder.combined.*;
import org.apache.commons.configuration2.builder.fluent.*;
import org.apache.commons.configuration2.convert.*;
import org.apache.commons.configuration2.event.*;
import org.apache.commons.configuration2.interpol.*;
import org.apache.commons.configuration2.io.*;
import org.apache.commons.configuration2.plist.*;
import org.apache.commons.configuration2.reloading.*;
import org.apache.commons.configuration2.spring.*;
import org.apache.commons.configuration2.sync.*;
import org.apache.commons.configuration2.test.*;
import org.apache.commons.configuration2.tree.*;
import org.apache.commons.configuration2.tree.xpath.*;
import org.apache.commons.configuration2.web.*;

public class TestRunner {
	private final static int AMOUNTOFTESTS = 2806;
	
	public static void main(String[] args) 
	{
//		Check arguments
		int repeats = validateArgument(args);
		
		System.out.println("Repeating " + repeats + " times.");
		runTests(repeats);
		
		System.exit(0);
	}

	public static int validateArgument(String[] args) {
		if (args.length != 1) {
			System.out.println("You must specify the amount of loops by parsing one argument");
			System.exit(1);
		}
		
		try {
			return Integer.parseInt(args[0]);			
		}
		catch (NumberFormatException e) {
			System.out.println("Parsed argument is not an integer: " + e.getMessage());
			System.exit(2);
		}
		
		return 0;
	}

	public static void runTests(int repeats) {
		for (int i = 0; i < repeats; i++) {
			Result result = runtests();
			if (!result.wasSuccessful() || result.getRunCount() != AMOUNTOFTESTS) {
				printResult(result, i);
				System.exit(3);
			}
		}
	}
	
	private static void printResult(Result result, int run) {
		System.out.println("Failed to run tests all " + AMOUNTOFTESTS + " tests succesfull");
		System.out.println("Run: " + run);
		System.out.println("Fails: " + result.getFailureCount());
		System.out.println("Runtime: " + result.getRunTime());
		System.out.println("RunCount: " + result.getRunCount());
		System.out.println("IgnoreCount: " + result.getIgnoreCount() + "\n");
		System.out.print("Failed runs: ");
		for (int i = 0; i < result.getFailures().size(); i++) {			
			System.out.println("    " + result.getFailures().get(i));			
			System.out.println("    " + result.getFailures().get(i).getMessage());
			System.out.println("    " + result.getFailures().get(i).getTrace());
			System.out.println("    " + result.getFailures().get(i).getTestHeader());
			System.out.println("    ");
		}
	}
	
	private static Result runtests() {	
		return org.junit.runner.JUnitCore.runClasses(
//				BaseNonStringProperties.class,
//				ConfigurationAssert.class,
//				DatabaseConfigurationTestHelper.class,
//				FileURLStreamHandler.class,
//				InterpolationTestHelper.class,
//				Logging.class,
//				MockInitialContextFactory.class,
//				NonCloneableConfiguration.class,
//				NonStringTestHolder.class,
//				SynchronizerTestImpl.class,
//				TestAbstractConfiguration.class,
				TestAbstractConfigurationBasicFeatures.class,
				TestAbstractConfigurationSynchronization.class,
				TestAbstractHierarchicalConfiguration.class,
				TestBaseConfiguration.class,
				TestBaseConfigurationXMLReader.class,
				TestBaseHierarchicalConfigurationSynchronization.class,
				TestBaseNullConfiguration.class,
				TestCatalogResolver.class,
				TestCombinedConfiguration.class,
				TestCompositeConfiguration.class,
				TestCompositeConfigurationNonStringProperties.class,
				TestConfigurationConverter.class,
				TestConfigurationLookup.class,
				TestConfigurationMap.class,
				TestConfigurationSet.class,
				TestConfigurationUtils.class,
				TestDatabaseConfiguration.class,
				TestDataConfiguration.class,
				TestDynamicCombinedConfiguration.class,
				TestEnvironmentConfiguration.class,
				TestEqualBehaviour.class,
				TestEqualsProperty.class,
				TestHierarchicalConfiguration.class,
				TestHierarchicalConfigurationXMLReader.class,
				TestHierarchicalXMLConfiguration.class,
				TestImmutableConfiguration.class,
				TestINIConfiguration.class,
				TestJNDIConfiguration.class,
				TestJNDIEnvironmentValues.class,
				TestJSONConfiguration.class,
				TestMapConfiguration.class,
				TestNonStringProperties.class,
				TestNullCompositeConfiguration.class,
				TestNullJNDIEnvironmentValues.class,
				TestPatternSubtreeConfiguration.class,
				TestPropertiesConfiguration.class,
				TestPropertiesConfigurationLayout.class,
				TestPropertiesSequence.class,
				TestStrictConfigurationComparator.class,
				TestSubnodeConfiguration.class,
				TestSubsetConfiguration.class,
				TestSystemConfiguration.class,
				TestThreesomeConfiguration.class,
				TestXMLConfiguration_605.class,
				TestXMLConfiguration.class,
				TestXMLDocumentHelper.class,
				TestXMLListHandling.class,
				TestXMLPropertiesConfiguration.class,
				TestYAMLConfiguration.class,
//				org.apache.commons.configuration2.beanutils
//				BeanCreationTestBean.class,
//				BeanCreationTestBeanWithListChild.class,
//				BeanCreationTestCtorBean.class,
//				BeanDeclarationTestImpl.class, INVISIBLE
				TestBeanHelper.class,
				TestCombinedBeanDeclaration.class,
				TestConfigurationDynaBean.class,
				TestConfigurationDynaBeanXMLConfig.class,
				TestConstructorArg.class,
				TestDefaultBeanFactory.class,
				TestXMLBeanDeclaration.class,
//				org.apache.commons.configuration2.builder
//				BuilderEventListenerImpl.class,
//				ParametersBeanTestImpl.class,
				TestAutoSaveListener.class,
				TestBasicBuilderParameters.class,
				TestBasicConfigurationBuilder.class,
				TestBasicConfigurationBuilderEvents.class,
				TestBuilderConfigurationWrapperFactory.class,
				TestCopyObjectDefaultHandler.class,
				TestDatabaseBuilderParametersImpl.class,
				TestDefaultParametersManager.class,
				TestDefaultReloadingDetectorFactory.class,
				TestEventListenerParameters.class,
				TestFileBasedBuilderParameters.class,
				TestFileBasedConfigurationBuilder.class,
				TestHierarchicalBuilderParametersImpl.class,
				TestJndiBuilderParametersImpl.class,
				TestPropertiesBuilderParametersImpl.class,
				TestReloadingBuilderSupportListener.class,
				TestReloadingFileBasedConfigurationBuilder.class,
				TestXMLBuilderParametersImpl.class,
//				org.apache.commons.configuration2.builder.combined
//				AbstractMultiFileConfigurationBuilderTest.class,
				TestBaseConfigurationBuilderProvider.class,
				TestCombinedBuilderParametersImpl.class,
				TestCombinedConfigurationBuilder.class,
				TestCombinedConfigurationBuilderVFS.class,
				TestConfigurationDeclaration.class,
				TestFileExtensionConfigurationBuilderProvider.class,
				TestMultiFileBuilderParametersImpl.class,
				TestMultiFileConfigurationBuilder.class,
				TestMultiWrapDynaBean.class,
				TestReloadingCombinedConfigurationBuilder.class,
				TestReloadingCombinedConfigurationBuilderFileBased.class,
				TestReloadingMultiFileConfigurationBuilder.class,
//				org.apache.commons.configuration2.builder.fluent
				TestConfigurations.class,
				TestParameters.class,
//				org.apache.commons.configuration2.convert
				TestDefaultConversionHandler.class,
				TestDefaultListDelimiterHandler.class,
				TestDisabledListDelimiterHandler.class,
				TestPropertyConverter.class,
//				org.apache.commons.configuration2.event
//				AbstractEventListenerTestImpl.class,
//				AbstractTestConfigurationEvents.class,
//				ErrorListenerTestImpl.class,
//				EventListenerTestImpl.class,
				TestConfigurationEventTypes.class,
				TestDatabaseConfigurationEvents.class,
				TestEvent.class,
				TestEventListenerList.class,
				TestEventSource.class,
				TestEventType.class,
				TestHierarchicalConfigurationEvents.class,
				TestMapConfigurationEvents.class,
				TestPropertiesConfigurationEvents.class,
				TestSubsetConfigurationEvents.class,
				TestXMLConfigurationEvents.class,
//				org.apache.commons.configuration2.interpol
				TestConfigurationInterpolator.class,
				TestConstantLookup.class,
				TestDummyLookup.class,
				TestEnvironmentLookup.class,
				TestExprLookup.class,
				TestInterpolatorSpecification.class,
				TestSystemPropertiesLookup.class,
//				org.apache.commons.configuration2.io
				TestAbsoluteNameLocationStrategy.class,
				TestBasePathLocationStrategy.class,
				TestClasspathLocationStrategy.class,
				TestCombinedLocationStrategy.class,
				TestConfigurationLogger.class,
				TestDefaultFileSystem.class,
				TestFileHandler.class,
				TestFileLocator.class,
				TestFileLocatorUtils.class,
				TestFileSystemLocationStrategy.class,
				TestHomeDirectoryLocationStrategy.class,
				TestProvidedURLLocationStrategy.class,
//				org.apache.commons.configuration2.plist
//				AbstractTestPListEvents.class,
				TestPropertyListConfiguration.class,
				TestPropertyListConfigurationEvents.class,
				TestPropertyListParser.class,
				TestXMLPropertyListConfiguration.class,
				TestXMLPropertyListConfigurationEvents.class,
//				org.apache.commons.configuration2.reloading
//				AlwaysReloadingDetector.class,
//				RandomReloadingDetector.class,
				TestCombinedReloadingController.class,
				TestFileHandlerReloadingDetector.class,
				TestManagedReloadingDetector.class,
				TestPeriodicReloadingTrigger.class,
				TestReloadingController.class,
				TestVFSFileHandlerReloadingDetector.class,
//				org.apache.commons.configuration2.spring
				TestConfigurationPropertiesFactoryBean.class,
				TestConfigurationPropertySource.class,
//				org.apache.commons.configuration2.sync
				TestReadWriteSynchronizer.class,
//				org.apache.commons.configuration2.test
//				HsqlDB.class,
//				org.apache.commons.configuration2.tree
//				AbstractCombinerTest.class,
//				AbstractImmutableNodeHandlerTest.class,
//				NodeStructureHelper.class,
				TestDefaultConfigurationKey.class,
				TestDefaultExpressionEngine.class,
				TestDefaultExpressionEngineSymbols.class,
				TestImmutableNode.class,
				TestInMemoryNodeModel.class,
				TestInMemoryNodeModelReferences.class,
				TestInMemoryNodeModelTrackedNodes.class,
				TestMergeCombiner.class,
				TestNodeAddData.class,
				TestNodeHandlerDecorator.class,
				TestNodeNameMatchers.class,
				TestNodeSelector.class,
				TestNodeTreeWalker.class,
				TestNodeUpdateData.class,
				TestOverrideCombiner.class,
				TestQueryResult.class,
				TestTrackedNodeHandler.class,
				TestTrackedNodeModel.class,
				TestTreeData.class,
				TestUnionCombiner.class,
//				org.apache.commons.configuration2.tree.xpath
//				AbstractXPathTest.class,
				TestConfigurationAttributePointer.class,
				TestConfigurationIteratorAttributes.class,
				TestConfigurationNodeIteratorChildren.class,
				TestConfigurationNodePointer.class,
				TestConfigurationNodePointerFactory.class,
				TestXPathContextFactory.class,
				TestXPathExpressionEngine.class,
				TestXPathExpressionEngineInConfig.class,
//				org.apache.commons.configuration2.web
				TestAppletConfiguration.class,
				TestServletConfiguration.class,
				TestServletContextConfiguration.class,
				TestServletFilterConfiguration.class,
				TestServletRequestConfiguration.class
				);
	}
}
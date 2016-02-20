package eu.blumenstingl.jackson.jsonschema.anttask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildFileTest;

public class JsonSchemaTaskTest extends BuildFileTest {

	private Path tempDir;
	
	public void setUp() throws Exception {
		super.setUp();

		tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "jsonschema-anttask-test");
		configureProject("src/test/resources/ant-jsonschema-tests.xml");
	}

	public void tearDown() throws Exception {
		super.tearDown();

		FileUtils.deleteDirectory(tempDir.toFile());
	}

	public void testTask_noClassnameGiven() throws IOException {
		expectBuildException("noClassnameGiven", "no classname property is given");
	}

	public void testTask_noDestfileGiven() throws IOException {
		expectBuildException("noDestfileGiven", "no destfile property is given");
	}

	public void testTask_emptyClassnameGiven() throws IOException {
		expectBuildException("emptyClassnameGiven", "a non-empty classname is required");
	}

	public void testTask_destfileIsADirectory() {
		expectBuildException("destfileIsADirectory", "destfile points to a directory");
	}

	public void testTask_mustAllowDestfileInExistingSubdirectory() {
		executeTarget("mustAllowDestfileInExistingSubdirectory");

		final File result = tempDir.resolve(Paths.get("subdir", "test.jsonschema")).toFile();

		assertTrue("The JSON schema must be written to the existing subdir", result.isFile());
	}

	public void testTask_createMissingParentDirectories() {
		executeTarget("createMissingParentDirectories");

		final File result = tempDir.resolve(Paths.get("subdir", "test.jsonschema")).toFile();
		
		assertNotNull(result.getParentFile());
		assertTrue("The parent directory must have been created", result.getParentFile().isDirectory());
		assertTrue("The JSON schema must be written to the new directory", result.isFile());
	}

	public void testTask_createJsonSchema() {
		executeTarget("createJsonSchema");

		final File result = tempDir.resolve(Paths.get("test.jsonschema")).toFile();

		assertTrue("The JSON schema must be written to the new directory", result.isFile());
		assertTrue("The JSON schema file must not be empty", result.length() > 0);
	}
}

package cmd.commands.dir;

import cmd.SimpleCmd;
import cmd.commands.AbstractTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirCommandTest extends AbstractTest {

    // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
    @Test
    public void testDirWithF(@TempDir Path tempDir) throws IOException {
        // given
        prepareTestFolder(tempDir);
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"-f"};
        DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
        // when
        dirCommand.run();
        // then
        String expected = tempDir.toAbsolutePath().toString() + "\\" + "myFile.txt";
        String actual = getOutStreamCaptor().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    private void prepareTestFolder(@TempDir Path tempDir) throws IOException {
        Path myFile = tempDir.resolve("myFile.txt");
        Files.write(myFile, Collections.singletonList(""));

        Path folder1 = tempDir.resolve("folder1");
        Path directory = Files.createDirectory(folder1, noAttributes);

        Path myFile2 = directory.resolve("myFile2.txt");
        Files.write(myFile2, Collections.singletonList(""));
    }

    @Test
    public void testDirWithoutArguments(@TempDir Path tempDir) throws IOException {
        // given
        prepareTestFolder(tempDir);
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {};
        DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
        // when
        dirCommand.run();
        // then
        String expected = tempDir.toAbsolutePath().toString() + "\\" + "myFile.txt";
        String actual = getOutStreamCaptor().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    // some additonal picocli specific examples can be found here
    // https://github.com/remkop/picocli/blob/master/src/test/java/picocli/CommandLineTest.java
    @Test
    public void testMockWithoutAnnotations() {
        DirCommand dir = Mockito.mock(DirCommand.class, Mockito.withSettings().withoutAnnotations());
        CommandLine cmdLine = new CommandLine(dir);
        assertEquals(0, cmdLine.execute("-f"));
    }
}
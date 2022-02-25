package cmd.commands.cd;

import cmd.SimpleCmd;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

@Command(
        name = "cd",
        description = "command can be used to change the directory",
        mixinStandardHelpOptions = true)
public class CdCommand implements Runnable {

    @CommandLine.Parameters(index = "0", defaultValue = "", description = "path of the target directory")
    private File targetDir;

    public CdCommand() {
    }

    @Override
    public void run() {
        if(targetDir.length() > 0) {
            SimpleCmd.setCurrentLocation(targetDir);
        } else {
            System.out.println(SimpleCmd.getCurrentLocation().getAbsolutePath());
        }
    }
}

package cmd.commands.time;


import picocli.CommandLine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static picocli.CommandLine.*;

@Command(
        name = "time",
        description = "command can be used to print current time",
        mixinStandardHelpOptions = true)
public class TimeCommand implements Runnable {

    @Option(names = {"-f", "--format"},
            description =  "output current time in desired format",
            defaultValue = "HH:mm")
    String timeFormat;

    public TimeCommand() {
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(timeFormat)));
    }

}

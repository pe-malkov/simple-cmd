package cmd.commands.time;


import picocli.CommandLine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CommandLine.Command(
        name = "time",
        description = "command can be used to print current time",
        mixinStandardHelpOptions = true)
public class TimeCommand implements Runnable {

    public TimeCommand() {
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

}

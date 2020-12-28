package by.belhard.BH26.kirill.casinoProject.casinoApp.io;

import java.io.IOException;

public interface IOInterface {
    String MENU_LEGEND =
            "\t1. balance\n" +
                    "\t2. put money\n" +
                    "\t3. get money\n" +
                    "\t4. choose game\n" +
                    "\t5. View Statistic\n" +
                    "\te. exit\n";

     String readStringValue() throws IOException;

    int readIntValue() throws IOException, NumberFormatException;

}

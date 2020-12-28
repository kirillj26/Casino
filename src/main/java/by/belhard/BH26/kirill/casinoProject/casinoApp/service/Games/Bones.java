package by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games;

import by.belhard.BH26.kirill.casinoProject.casinoApp.io.IOInterface;
import by.belhard.BH26.kirill.casinoProject.casinoApp.model.Account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class Bones {
    public static void start(Account account, IOInterface ioInterface) throws SQLException {


        String MENU_LEGEND =
                "\t Press Enter to play\n" +
                        "\te. exit\n";


        String input = "0";
        while (input != "e") {
            System.out.println(MENU_LEGEND);
            try {
                input = ioInterface.readStringValue();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            switch (input) {
                case "":
                    System.out.println("\tPlay\n");
                    Bones.play(account, ioInterface);
                    break;
                case "e":
                    input = "e";
                    break;
                default:
                    System.err.println("Wrong input");
                    break;
            }


        }

    }

    private static void play(Account account, IOInterface ioInterface) {
        System.out.println("ПОКА НЕТ РЕАЛИЗАЦИИ");
/*
        Random random = new Random();
        int[] playerCubes = new int[5];
        int[] dealerCubes = new int[5];
        for (int i = 0; i < 5; i++) {
            playerCubes[i] = random.nextInt(6) + 1;
            dealerCubes[i] = random.nextInt(6) + 1;
        }
        System.out.println("\tКости дилера");
        for (int i : dealerCubes) System.out.print("\t " + i + " ");
        System.out.println();

        System.out.println("\tКости игрока");
        for (int i : playerCubes) System.out.print("\t " + i + " ");
        System.out.println("\n");

        String input = "0";
            System.out.println("Какие кости перебросить?\n" + "Формат '12345'");
            try {
                input = ioInterface.readStringValue();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            String[] s = input.split("");
            for (String x:s) {
                playerCubes[Integer.parseInt(x)-1] = random.nextInt(6) + 1;

            }
            System.out.println("\tКости игрока");
            for (int i : playerCubes) System.out.print("\t " + i + " ");
            System.out.println("\n");*/

            



    }


}

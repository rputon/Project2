package br.ucs.poo.cinema.main;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Help {
    private String erro = "Valor informado é inválido";

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int returnInt(Scanner in, String print) {
        int value = 0;
        boolean test = false;

        do {
            try {
                System.out.println(print);
                value = in.nextInt();
                in.nextLine();

                test = true;
                break;
            } catch (Exception e) {
                System.out.println(erro);
                in.next();
            }
        } while (test == false);
        return value;
    }

    public int returnInt(Scanner in, String print, int rangeMin, int rangeMax) {
        int value = 0;
        boolean test = false;

        do {
            try {
                System.out.println(print);
                value = in.nextInt();
                in.nextLine();

                if (value >= rangeMin && value <= rangeMax) {
                    test = true;
                    break;
                }
                else{
                    System.out.println(erro);
                }
            } catch (Exception e) {
                System.out.println(erro);
                in.next();
            }
        } while (test == false);
        return value;
    }

    public double returnDouble(Scanner in, String print) {
        double value = 0;
        boolean test = false;

        do {
            try {
                System.out.println(print);
                value = in.nextDouble();

                test = true;
                break;
            } catch (Exception e) {
                System.out.println(erro);
                in.next();
            }

        } while (test == false);
        return value;
    }

    public String returnString(Scanner in, String print) {
        String value = "";
        boolean test = false;

        do {
            System.out.println(print);
            value = in.nextLine();

            if (!value.matches(".*[!@#$%^&´~*\\[\\]+=<>?/{}|].*") && !value.matches("^V")) {
                test = true;
                break;
            } else {
                System.out.println(erro);
            }
        } while (test == false);
        return value;
    }

    public char returnChar(Scanner in, String print) {
        char value = ' ';
        boolean test = false;

        do {
            System.out.println(print);
            value = Character.toUpperCase(in.next().charAt(0));
            in.nextLine();
            if (value == 'S' || value == 'N') {
                test = true;
                break;
            } else {
                System.out.println(erro);
            }
        } while (test == false);
        return value;
    }

    public boolean validFile(String file) {
        File myFile = new File(String.format("files\\%s.dat", file));
        try {
            if (myFile.createNewFile()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao validar o arquivo.");
        }
        return true;
    }

    public String testUser(Scanner in, String print, List<String> users) {
        String user = "";

        do {
            user = returnString(in, print);
            if (!users.contains(user)) {
                System.out.println("Usuário não encontrado.\n");
            }
        } while (!users.contains(user));

        return user;
    }

    public LocalDate returnDate(Scanner in, String print) {
        String value = "";
        boolean test = false;
        LocalDate formattedString = LocalDate.now();

        do {
            try {
                System.out.println(print);
                value = in.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy");
                formattedString = LocalDate.parse(value, formatter);

                if (formattedString.isAfter(LocalDate.of(2023, 11, 27))
                        && formattedString.isBefore(LocalDate.of(2024, 12, 31))) {
                    test = true;
                    break;
                } else {
                    System.out.println(erro);
                }
            } catch (DateTimeParseException e) {
                System.out.println(erro);
            }
        } while (test == false);

        return formattedString;
    }

    public LocalTime returnTime(Scanner in, String print, int tempo) {
        String value = "";
        boolean test = false;
        LocalTime formattedString = LocalTime.now();

        do {
            try {
                System.out.println(print);
                value = in.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                formattedString = LocalTime.parse(value, formatter);

                if ((formattedString.isAfter(LocalTime.of(12, 59)) && formattedString.isBefore(LocalTime.of(23, 59)))
                        && formattedString.plusMinutes(tempo).isBefore(LocalTime.of(00,16))) {
                    test = true;
                    break;
                } else {
                    System.out.println(erro);
                }
            } catch (DateTimeParseException e) {
                System.out.println(erro);
                System.out.println(e);
            }

        } while (test == false);

        return formattedString;
    }

}
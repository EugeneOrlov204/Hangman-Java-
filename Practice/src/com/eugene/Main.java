package com.eugene;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String secretWord = Hangman.getSecretWord();
        String hidenSecretWord = "";

        for (int i = 0; i < secretWord.length(); i++)
            hidenSecretWord += '*';

        int attempts = Hangman.getAttempts();

        do {
            System.out.printf(
                    "Секретное слово: %s. Осталось попыток: %d\nВведите букву: ",
                    hidenSecretWord, attempts
            );

            try {
                Scanner in = new Scanner(System.in);
                char c = in.nextLine().toCharArray()[0]; //считываем только первый символ введенной строки

                if (secretWord.indexOf(c) != -1 && hidenSecretWord.indexOf(c) == -1) {
                    String tempStr = "";

                    for (int i = 0; i < secretWord.length(); i++) {
                        if (secretWord.toCharArray()[i] == c)
                            tempStr += c;

                        else
                            tempStr += hidenSecretWord.toCharArray()[i];
                    }
                    hidenSecretWord = tempStr;

                    if (hidenSecretWord.indexOf('*') == -1) {
                        break;
                    }

                } else
                    attempts--;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (attempts > 0);




        if (hidenSecretWord.indexOf('*') == -1) {
            System.out.printf(
                    "Загаданное слово: %s. Поздравляю вы выиграли!\n",
                    secretWord
            );

        } else {
            System.out.printf(
                    "Загаданное слово: %s. Вы проиграли!\n",
                    secretWord
            );
        }

    }


}
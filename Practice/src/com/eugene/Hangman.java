package com.eugene;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    private static int attempts;
    private static String secretWord;
    private static ArrayList<String> vocabulary;


    private static boolean readFromFile()
    {
        vocabulary = new ArrayList<String>();

        try (FileReader reader = new FileReader("E:\\MyCode\\Java\\Practice\\src\\com\\eugene\\word_rus.txt")) {
            // читаем посимвольно
            int c;
            String temp = "";
            while ((c = reader.read()) != -1) {
                if ((char) c != '\r' && (char) c != '\n') //не считываем escape-последовательности
                    temp += ((char) c);
                else if ((char) c != '\n'){ //конец слова начинается с символа \r, а не \n
                    vocabulary.add(temp);
                    temp = "";
                }
            }

            return true; //удалось получить данные из файла

        } catch (IOException ex) {

            return false; //не удалось получить данные из файла
        }
    }

    private static void setSecretWord()
    {
        boolean fileReading = readFromFile();
        if(fileReading) {
            Random random = new Random();

            //Выбираем случайным образом слово из словаря
            secretWord = vocabulary.get(random.nextInt(vocabulary.size() - 1));

            //если длина слова выше 9, тогда кол-во попыток - 5.
            attempts = secretWord.length() < 10 ? secretWord.length() : 5;
        }

    }

    public static int getAttempts() {
        return attempts;
    }

    public static String getSecretWord() {
        setSecretWord();
        return secretWord;
    }
}

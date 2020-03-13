package AMT;


import AMT.exception.InterruptOperationException;

import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.IOException;

import static AMT.Operation.getAllowableOperationByOrdinal;


public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()
                    + ".resources.common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        while (true) {
            try {
                String line = bis.readLine();
                if (line.trim().equalsIgnoreCase("exit")) {
                    writeMessage(res.getString("the.end"));
                    throw new InterruptOperationException();
                }
                return line;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String string = "";
//        writeMessage("Введите код валюты:");
        writeMessage(res.getString("choose.currency.code"));

        while (string.length() != 3) {
            string = readString();

            if (string.length() == 3) {
                break;
            }
//            writeMessage("данные некорректны, повторите ввод:");
            writeMessage(res.getString("invalid.data"));

        }
        return string.toUpperCase();
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("");
//        writeMessage("Выбирите желаемую операцию:");
        writeMessage(res.getString("choose.operation"));
        writeMessage(res.getString("operation.INFO") + " - 1");
        writeMessage(res.getString("operation.DEPOSIT") + " - 2");
        writeMessage(res.getString("operation.WITHDRAW") + " - 3");
        writeMessage(res.getString("operation.EXIT") + " - 4");
        writeMessage("");

        String string = "";

        while ((!string.matches("[1-4]"))) {
            string = readString();

            if (string.matches("[1-4]")) {
                break;
            }
//            writeMessage("Данные некорректны, повторите ввод:");
            writeMessage(res.getString("invalid.data"));
        }

        if(Integer.parseInt(string) == 0) {
            throw new IllegalArgumentException();
        }

        return getAllowableOperationByOrdinal(Integer.parseInt(string));
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String string = "";
//        writeMessage("Введите номинал и количество банкнот:");
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

        while (!string.matches("^[1-9]\\d*\\s[1-9]\\d*")) {
            string = readString();

            if (string.matches("^[1-9]\\d*\\s[1-9]\\d*")) {
                break;
            }
//            writeMessage("данные некорректны, повторите ввод:");
            writeMessage(res.getString("invalid.data"));
        }
        return string.split(" ");
    }

    public static int getValidDigits() throws InterruptOperationException {
        while (true) {
            String data = readString();

            if (data.matches("\\d+")) {
                return Integer.parseInt(data);
            }
//            writeMessage("Данные некорректны, повторите ввод:");
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static void printExitMessage() {
        writeMessage("Buy");
    }
}

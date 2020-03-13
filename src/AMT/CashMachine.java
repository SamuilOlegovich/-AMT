package AMT;


import AMT.exception.InterruptOperationException;

import java.util.*;

import static AMT.command.CommandExecutor.execute;
import static AMT.ConsoleHelper.printExitMessage;
import static AMT.ConsoleHelper.askOperation;
import static AMT.Operation.LOGIN;
import static AMT.Operation.EXIT;


public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";


    public static void main(String[] args) {

        try {
            Locale.setDefault(Locale.ENGLISH);
            execute(LOGIN);
            Operation operation;

            do {
                operation = askOperation();
                execute(operation);
            } while (!operation.equals(EXIT));
        } catch (InterruptOperationException e) {
            printExitMessage();
        }
    }
}

// Можно добавить.
//
// Например:
// Исправить выводимые тексты.
// Добавить ресурсы для нескольких локалей. Например, еще и для русской.
// Добавить валидацию вводимых номиналов.
//
// Достижения:
// 1. разобрался с паттерном Command.
// 2. подружился с Жадным алгоритмом.
// 3. познакомился с локализацией.
// 4. стал больше знать и уметь.

package AMT.command;


import AMT.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static AMT.ConsoleHelper.writeMessage;
import static AMT.CashMachine.RESOURCE_PATH;
import static AMT.ConsoleHelper.readString;


public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        writeMessage(res.getString("before"));
        String cardNumber = "";
        String cardPin = "";
        boolean flag = false;

        while (true) {
//            writeMessage("Введите номер карты:");
            if (!flag) {
                writeMessage(res.getString("specify.data"));
                flag = true;
            } else {
                writeMessage(res.getString("try.again.with.details"));
            }
            cardNumber = readString();
//            writeMessage("Введите пин:");
            cardPin = readString();

            if (!cardNumber.matches("^\\d{12}$") || !cardPin.matches("^\\d{4}$")){
//                writeMessage("данные некорректны, повторите ввод:");
                writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            if (validCreditCards.containsKey(cardNumber) && validCreditCards.getString(cardNumber).equals(cardPin)) {
//                writeMessage("верификация прошла успешно");
                writeMessage(String.format(res.getString("success.format"), cardNumber));
                break;
            }
        }
    }
}

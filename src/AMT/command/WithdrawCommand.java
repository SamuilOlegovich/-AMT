package AMT.command;


import AMT.exception.InterruptOperationException;
import AMT.exception.NotEnoughMoneyException;
import AMT.CurrencyManipulator;

import java.util.ResourceBundle;
import java.util.Map;

import static AMT.CurrencyManipulatorFactory.getManipulatorByCurrencyCode;
import static AMT.CashMachine.RESOURCE_PATH;
import static AMT.ConsoleHelper.*;


class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        writeMessage(res.getString("before"));
        String cc = askCurrencyCode();
        CurrencyManipulator cm = getManipulatorByCurrencyCode(cc);
        Map<Integer, Integer> withdrawMap = null;
        int amount;

        while (true) {
            writeMessage(res.getString("specify.amount"));
            String amountS = readString();
            try {
                amount = Integer.parseInt(amountS);
            } catch (NumberFormatException nfe){
//                writeMessage("Вы ввели некоректное значение, повторите запрос.");
                writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            if (amount <= 0) {
//                writeMessage("Мы не можем выдать нужную сумму. Повторите запрос.");
                writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            if (!cm.isAmountAvailable(amount)){
//                writeMessage("На вашем счету недостаточно средств!");
                writeMessage(res.getString("not.enough.money"));
                continue;
            }

            try {
                withdrawMap = cm.withdrawAmount(amount);
            } catch (NotEnoughMoneyException e) {
//                writeMessage("Сумма недоступна");
                writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }

            for (Map.Entry<Integer, Integer> pair : withdrawMap.entrySet()) {
                writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
            }
//            writeMessage("Операция выполнена успешно!");
            writeMessage(res.getString(String.format(res.getString("exact.amount.not.available"), amount, cc)));
            break;
        }
    }
}

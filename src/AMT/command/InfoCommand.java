package AMT.command;


import AMT.exception.InterruptOperationException;
import AMT.CurrencyManipulator;

import java.util.ResourceBundle;

import static AMT.CurrencyManipulatorFactory.getAllCurrencyManipulators;
import static AMT.ConsoleHelper.writeMessage;
import static AMT.CashMachine.RESOURCE_PATH;


class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "info_en");

    @Override
    public void execute() throws InterruptOperationException {
        boolean flag = false;
        writeMessage(res.getString("before"));

        for (CurrencyManipulator manipulator : getAllCurrencyManipulators()) {

            if (manipulator.hasMoney()) {
                System.out.println(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                flag = true;
            }
        }
        
        if (!flag) {
            writeMessage(res.getString("no.money"));
        }
    }
}

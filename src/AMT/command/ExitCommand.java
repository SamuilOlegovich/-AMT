package AMT.command;


import AMT.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static AMT.ConsoleHelper.writeMessage;
import static AMT.CashMachine.RESOURCE_PATH;
import static AMT.ConsoleHelper.readString;


class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        writeMessage(res.getString("exit.question.y.n"));

        if (readString().equals("y")) {
            writeMessage(res.getString("thank.message"));
        }
    }
}

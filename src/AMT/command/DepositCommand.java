package AMT.command;


import AMT.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static AMT.CurrencyManipulatorFactory.getManipulatorByCurrencyCode;
import static AMT.CashMachine.RESOURCE_PATH;
import static AMT.ConsoleHelper.*;


class DepositCommand implements Command {
    private ResourceBundle res =
            ResourceBundle.getBundle(RESOURCE_PATH
                    + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        writeMessage(res.getString("before"));
        String cc = askCurrencyCode();
        String[] cash = getValidTwoDigits(cc);

        try {
            int amount = Integer.parseInt(cash[0])*Integer.parseInt(cash[1]);
            getManipulatorByCurrencyCode(cc).addAmount(Integer.parseInt(cash[0]), Integer.parseInt(cash[1]));
            writeMessage(String.format(res.getString("success.format"), amount, cc));
        } catch (NumberFormatException e) {
            writeMessage(res.getString("invalid.data"));
        }
    }
}

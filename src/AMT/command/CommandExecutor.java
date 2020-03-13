package AMT.command;


import AMT.exception.InterruptOperationException;
import AMT.Operation;

import java.util.HashMap;
import java.util.Map;

import static AMT.Operation.*;


public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(EXIT, new ExitCommand());
        allKnownCommandsMap.put(INFO, new InfoCommand());
        allKnownCommandsMap.put(WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(LOGIN, new LoginCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        allKnownCommandsMap.get(operation).execute();
    }

    private CommandExecutor() {
    }
}

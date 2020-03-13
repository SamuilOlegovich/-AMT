package AMT.command;


import AMT.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}

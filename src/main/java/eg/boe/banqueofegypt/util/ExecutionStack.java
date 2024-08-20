package eg.boe.banqueofegypt.util;

import java.util.Stack;

public class ExecutionStack {
    private final Stack<Command> toExecute = new Stack<>();
    private final Stack<Command> done = new Stack<>();

    public void push(Command command) {
        toExecute.push(command);
    }

    public void execute() {
        try {
            for (Command command : toExecute) {
                command.execute();
                done.push(command);
            }
        } catch (Exception e) {
            undo();
            toExecute.clear();
            done.clear();
            throw e;
        }
    }

    // TODO: Handle scheduling of failing undos
    private void undo() {
        for (Command command : done)
            command.undo();
    }
}

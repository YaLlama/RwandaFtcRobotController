package org.firstinspires.ftc.teamcode.Examples.Classes;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.println;

public class StateMachineExample {

    private Direction direction;

    /** creation of an enum
     * An Enum is a variable that is processed by the computer as a number
     * You cannot set the number like with ints or doubles, it is set by the computer
     * and is used for readability of code, specifically with switch statements - what are used to make state machines
     */
    public enum Direction{
        Up, Down, Left, Right
    }

    public StateMachineExample(Direction initialDirection){
        direction = initialDirection;
    }

    public void changeDirection(Direction newDirection){
        direction = newDirection;
    }

    public Direction getDirection(){
        return direction;
    }
    public void outputCurrentDirection(){
        /** A "switch" takes an input integer, then each "case" checks if the inputted variable is equal,
         * then runs the code after it if it is.
         * A switch functions the same as a series of if statements.
         * Typing "break;" will exit the switch, if break isn't called it will execute the code of the next state
         */
        switch (direction){
            case Left:
                println("Left");
                break;
            case Up:
                println("Up");
                break;
            default:
            case Down:
                println("Down");
                break;
            case Right:
                println("Right");
        }
    }

}

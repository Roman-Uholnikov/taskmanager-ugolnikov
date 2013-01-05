/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Exceptions;

/**
 *
 * @author Роман
 */
public class UserInputException extends Exception{
     public UserInputException() {
    }

    public UserInputException(String message) {
        super(message);
    }

    public UserInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInputException(Throwable cause) {
        super(cause);
    }

    public UserInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
    
    
    
}

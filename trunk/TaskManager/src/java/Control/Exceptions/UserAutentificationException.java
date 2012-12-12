/*
 * ошибка аутентификации пользователя
 */
package Control.Exceptions;

/**
 * ошибка аутентификации пользователя. Либо его нет в базе данных, либо пароли не совпали либо сессия закончилась
 * @author admin4eg
 */
public class UserAutentificationException extends Exception{

    public UserAutentificationException() {
    }

    public UserAutentificationException(String message) {
        super(message);
    }

    public UserAutentificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAutentificationException(Throwable cause) {
        super(cause);
    }

    public UserAutentificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
    
    
    
}

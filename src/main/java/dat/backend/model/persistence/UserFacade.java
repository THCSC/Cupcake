package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User createUser(String email, String password, String confirmationPassword, String role, ConnectionPool connectionPool) throws DatabaseException
    {
        if (UserMapper.UserExists(email, connectionPool)) {
            throw new DatabaseException("A user with that email, already exists.");
        } else if (!password.equals(confirmationPassword)) {
            throw new DatabaseException("Passwords did not match");
        }

        return UserMapper.createUser(email, password, role, connectionPool);
    }
}

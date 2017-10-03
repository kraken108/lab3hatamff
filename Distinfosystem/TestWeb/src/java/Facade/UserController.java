package Facade;

import Model.User;
import DBManager.DBAdmin;
import DBManager.DBManager;
import DBManager.MysqlManager;
import Model.Rights;
import ViewModel.RightsInfo;
import ViewModel.UserInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * UserController is used to retrieve user information from the database.
 */
public class UserController {

    /**
     * Returns an array of all users in the database.
     *
     * @return Array of users
     * @throws SQLException
     * @throws NamingException
     */
    public ArrayList<UserInfo> getUsers() throws SQLException, NamingException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            ArrayList<User> users = dbAdmin.getUsers();

            ArrayList<UserInfo> userInfo = new ArrayList<>();

            for (User user : users) {
                ArrayList<RightsInfo> rightsInfo = new ArrayList<>();
                for (Rights r : user.getRights()) {
                    switch (r) {
                        case ADMINISTRATOR:
                            rightsInfo.add(RightsInfo.ADMINISTRATOR);
                        case CUSTOMER:
                            rightsInfo.add(RightsInfo.CUSTOMER);
                        case STOCK:
                            rightsInfo.add(RightsInfo.STOCK);
                        default:
                            break;
                    }
                }
                userInfo.add(new UserInfo(user.getName(),rightsInfo));
            }

            return userInfo;
        } catch (SQLException ex) {
            throw (ex);
        } catch (NamingException ex) {
            throw (ex);
        }
    }

    /**
     * Returns information about a single user in the database.
     *
     * @param username
     * @return User object
     * @throws SQLException
     * @throws NamingException
     */
    public UserInfo getSingleUser(String username) throws SQLException, NamingException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            User user = dbAdmin.getSingleUser(username);
            
            ArrayList<RightsInfo> rightsInfo = new ArrayList<>();
            
            for(Rights r : user.getRights()){
                switch (r) {
                        case ADMINISTRATOR:
                            rightsInfo.add(RightsInfo.ADMINISTRATOR);
                        case CUSTOMER:
                            rightsInfo.add(RightsInfo.CUSTOMER);
                        case STOCK:
                            rightsInfo.add(RightsInfo.STOCK);
                        default:
                            break;
                    }
            }
            
            UserInfo userInfo = new UserInfo(user.getName(),rightsInfo);
            return userInfo;
        } catch (SQLException ex) {
            throw (ex);
        } catch (NamingException ex) {
            throw (ex);
        }

    }

    /**
     * Changes the username of a user in the database.
     *
     * @param currentUsername
     * @param newUsername
     * @throws SQLException
     * @throws NamingException
     */
    public void changeUsername(String currentUsername, String newUsername) throws SQLException, NamingException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.changeUsername(currentUsername, newUsername);
        } catch (SQLException ex) {
            throw (ex);
        }

    }

    /**
     * Changes the password of a user in the database.
     *
     * @param username
     * @param newPassword
     * @throws SQLException
     * @throws NamingException
     */
    public void changePassword(String username, String newPassword) throws SQLException, NamingException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.changePassword(username, newPassword);
        } catch (SQLException ex) {
            throw (ex);
        }

    }

    /**
     * Adds a right to a user in the database.
     *
     * @param username
     * @param rightToAdd
     * @throws SQLException
     * @throws NamingException
     */
    public void addRights(String username, String rightToAdd) throws SQLException, NamingException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.addRights(username, rightToAdd);
        } catch (SQLException ex) {
            throw (ex);
        }
    }

    /**
     * Removes a right from a user in the database.
     *
     * @param username
     * @param rightToRemove
     * @throws NamingException
     * @throws SQLException
     */
    public void removeRights(String username, String rightToRemove) throws NamingException, SQLException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.removeRights(username, rightToRemove);
        } catch (SQLException ex) {
            throw (ex);
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param username
     * @throws NamingException
     * @throws SQLException
     */
    public void deleteUser(String username) throws NamingException, SQLException {
        try {
            DBAdmin dbAdmin = new DBAdmin();
            dbAdmin.deleteUser(username);
        } catch (SQLException ex) {
            throw (ex);
        }
    }

}

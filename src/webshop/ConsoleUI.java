package webshop;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.09.21..
 */

public class ConsoleUI {

    private SalesCommands salesCommands;
    private UserCommands userCommands;
    private ProductsCommands productsCommands;
    private ReportsCommands reportsCommands;
    private User currentUser;

    public ConsoleUI() throws SQLException, ClassNotFoundException {
        this.salesCommands = new SalesCommands(DatabaseTypes.SQLITE);
        this.userCommands = new UserCommands(DatabaseTypes.SQLITE);
        this.productsCommands = new ProductsCommands(DatabaseTypes.SQLITE);
        this.reportsCommands = new ReportsCommands(DatabaseTypes.SQLITE);
        this.currentUser = userCommands.getDefaultUser();
    }

    public void showMainMenu() throws SQLException {

        User user = this.userCommands.getDefaultUser();
        boolean toContinue = true;

        while (toContinue) {

            ConsoleIO.showMessage("======== User Menu ========");

            ConsoleIO.showMessage("1 - User menu");
            ConsoleIO.showMessage("2 - Product menu");
            ConsoleIO.showMessage("3 - Sales menu");
            ConsoleIO.showMessage("4 - Reports menu");
            ConsoleIO.showMessage(Constants.EXIT + " - Exit");

            ConsoleIO.showMessage("Please select: ");

            int usersSelected = ConsoleIO.getUserInputInt();
            switch (usersSelected) {
                case 1:
                    FormUser userForm = new FormUser(user, userCommands);
                    userForm.showFormUser();
                    this.currentUser = userForm.getCurrentUser();
                    this.setNewDbConnectionUser();
                    break;
                case 2:
                    ConsoleIO.showMessage("Not implemented");
                    break;
                case 3:
                    ConsoleIO.showMessage("Not implemented");
                    break;
                case 4:
                    ConsoleIO.showMessage("Not implemented");
                    break;
                case Constants.EXIT:
                    toContinue = false;
                    break;
            }
        }
    }

    private void setNewDbConnectionUser() {
        this.userCommands.setCurrentUser(this.currentUser);
        this.productsCommands.setCurrentUser(this.currentUser);
        this.reportsCommands.setCurrentUser(this.currentUser);
        this.salesCommands.setCurrentUser(this.currentUser);
    }
}

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
        this.setNewDbConnectionUser();
    }

    public void showMainMenu() throws SQLException {

        User user = this.userCommands.getDefaultUser();
        this.currentUser = user;
        boolean toContinue = true;

        while (toContinue) {
            try {
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
                        FormUser userForm = new FormUser(this.currentUser, userCommands);
                        userForm.showFormUser();
                        this.currentUser = userForm.getCurrentUser();
                        this.setNewDbConnectionUser();
                        break;
                    case 2:
                        FormProducts formProducts = new FormProducts(this.currentUser, productsCommands);
                        formProducts.showFormProducts();
                        break;
                    case 3:
                        FormSales formSales = new FormSales(this.currentUser, this.salesCommands,
                                this.productsCommands);
                        formSales.showFormBuySales();
                        break;
                    case 4:
                        FromReports formReports = new FromReports(this.currentUser, this.reportsCommands);
                        formReports.showReportForm();
                        break;
                    case Constants.EXIT:
                        toContinue = false;
                        break;
                }
            } catch (WebShopSqlException e) {
                System.err.println(e.getClass().getName() + "Webshop exception: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println(e.getClass().getName() + "Security violation: " + e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
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

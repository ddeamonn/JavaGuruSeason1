package webshop;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Amir on 27.09.2016..
 */
public class FromReports implements ReportsUI {

    private ReportsCommands reportsCommands;
    private User currentUser;

    public FromReports(User user, ReportsCommands reportsCommands) {
        this.currentUser = user;
        this.reportsCommands = reportsCommands;
    }

    public void showReportForm() throws SQLException {
        boolean toContinue = true;
        while (toContinue) {

            ConsoleIO.showMessage("======== Reports Menu ========");

            Map<Integer, String> methods = this.reportsCommands.allowedMethods(this.currentUser);
            for (Map.Entry<Integer, String> method : methods.entrySet()) {
                ConsoleIO.showMessage(method.getKey() + " - " + method.getValue());
            }

            ConsoleIO.showMessage(Constants.EXIT + " - Return to main menu ");
            ConsoleIO.showMessage("Please select: ");

            int usersSelected = ConsoleIO.getUserInputInt();
            switch (usersSelected) {
                case Constants.REPORT_ALL_SALES_BY_USER:
                    this.showAllSalesByUser();
                    break;
                case Constants.REPORT_ALL_SALES:
                    this.showAllSales();
                    break;
                case Constants.EXIT:
                    toContinue = false;
                    break;
            }
        }
    }

    private void showAllSales() throws SQLException {
        ConsoleIO.showMessage("======== All Sales  ========");
        try {
            Map<Integer, Float> allSales = this.reportsCommands.getAllSales();
            for (Map.Entry<Integer, Float> sales : allSales.entrySet()) {
                ConsoleIO.showMessage("Sale ID: " + sales.getKey() + " and Sum: " + sales.getValue());
            }
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to get reports data. Reason: " + e.getMessage());
        }
    }

    private void showAllSalesByUser() throws SQLException {
        ConsoleIO.showMessage("======== All Sales ========");
        ConsoleIO.showMessage("User: " + this.currentUser.getUserName());
        try {
            Map<Integer, Float> allSales = this.reportsCommands.getSalesByUser(this.currentUser);
            for (Map.Entry<Integer, Float> sales : allSales.entrySet()) {
                ConsoleIO.showMessage("Sale ID: " + sales.getKey() + " and Sum: " + sales.getValue());
            }
        } catch (SQLException e) {
            ConsoleIO.showMessage("Failed to get reports data. Reason: " + e.getMessage());
        }
    }
}

package View;

import Exceptions.NameExceptions.InvalidUserNameException;
import Model.Names.UserName;

import javax.swing.*;

public class UserNameAsker {


    public UserName ask() {
        JOptionPane jOptionPane = new JOptionPane();
        JFrame jFrame = new JFrame();
        JDialog jDialog = new JDialog(jFrame);
        jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        jDialog.setContentPane(jOptionPane);
        UserName userName = null;
        String info = "Please enter your name";
        while (true) {
            try {
                userName = new UserName(JOptionPane.showInputDialog(info));
                break;
            }
            catch (InvalidUserNameException ex) {
                info = "Please enter your name. Name must not be a number";
            }
        }
        return userName;
    }
}

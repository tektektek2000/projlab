package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class QuestionDialog extends JPanel {
    JDialog dialog;
    JComboBox optionsList;
    JButton ok;

    public QuestionDialog(String message, ArrayList<String> options){
        dialog = new JDialog();
        ok = new JButton("OK");
        optionsList = new JComboBox();

        init();
    }

    private void init(){

    }



}

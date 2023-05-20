import java.awt.event.*;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.*;

// Define all UI components and visitor array
public class VisitorFrame extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;

    JMenuItem addItem;
    JMenuItem modifyItem;
    JMenuItem historyVItem;
    JMenuItem todayReportItem;
    JMenuItem exitItem;
    JMenuItem helpItem;
    JMenuItem aboutItem;
    private JPanel panel1;
    private JPanel visitorPanel;
    private JPanel modifyPanel;
    private JPanel todayPanel;
    private JPanel displayPanel;
    private JPanel mainPanel;
    private JTextField toText1;
    private JTextField vidText1;
    private JTextField idText2;
    private JButton addButton;
    private JButton modifyButton;
    private JButton showInfoButton;
    private JTextField fnText1;
    private JTextField lnText1;
    private JTextField cText1;
    private JTextField onText1;
    private JTextField svText1;
    private JTextField tiText1;
    private JTextField fnText2;
    private JTextField lnText2;
    private JTextField cText2;
    private JTextField onText2;
    private JTextField svText2;
    private JTextField tiText2;
    private JTextField toText2;
    private JTextArea vtTextArea;
    private JTextArea vhtextArea;
    private JTextField vIDText2;
    private JTextField phText1;
    private JTextField phText2;
    Visitor[] visitor; // Array to store visitor objects

    VisitorFrame() {
        // JFrame configurations
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(610, 400);
        this.setContentPane(panel1);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        this.setVisible(true);

        // Initialize menu items
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        // Add action listeners to menu items
        addItem = new JMenuItem("Add Visitor");
        modifyItem = new JMenuItem("Modify Visitor");
        historyVItem = new JMenuItem("Visitor History");
        todayReportItem = new JMenuItem("Today's Report");
        exitItem = new JMenuItem("Exit");
        helpItem = new JMenuItem("Help");
        aboutItem = new JMenuItem("About");

        addItem.addActionListener(this);
        modifyItem.addActionListener(this);
        historyVItem.addActionListener(this);
        todayReportItem.addActionListener(this);
        exitItem.addActionListener(this);
        helpItem.addActionListener(this);
        aboutItem.addActionListener(this);

        // Add menu items to menus and menus to menu bar
        fileMenu.add(addItem);
        fileMenu.add(modifyItem);
        fileMenu.add(historyVItem);
        fileMenu.add(todayReportItem);
        fileMenu.add(exitItem);
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Set initial panel visibility
        this.panel1.setVisible(true);
        visitorPanel.setVisible(false);
        todayPanel.setVisible(false);
        displayPanel.setVisible(false);
        modifyPanel.setVisible(false);
        mainPanel.setVisible(true);

        visitor = new Visitor[20];// Initialize visitor array

        // Add action listeners to other UI components
        // These action listeners are responsible for database operations
        // (adding and modifying visitor information)
        // and displaying visitor information on the screen

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisitorQueries addVisitor = new VisitorQueries();
                String fName = fnText1.getText();
                String lName = lnText1.getText();
                String company = cText1.getText();
                String ofNum = onText1.getText();
                String sVisiting = svText1.getText();
                Timestamp tIn = Timestamp.valueOf(tiText1.getText());
                Timestamp tOut = Timestamp.valueOf(toText1.getText());
                String vID = vidText1.getText();
                String photo = phText1.getText();

                int result = addVisitor.addVisitor(fName, lName, company, ofNum, sVisiting, tIn, tOut, vID, photo);
                if(result == 1){
                    JOptionPane.showMessageDialog(null, "Record Succesfully Inserted");
                } else{
                    JOptionPane.showMessageDialog(null, "Record Unsuccesfully Inserted");
                }
            }
        });
        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisitorQueries visitorQueries = new VisitorQueries();
                int id = Integer.parseInt(idText2.getText());

                List<Visitor> list = visitorQueries.getVisitorByID(id);
                Visitor visitor = (Visitor) list.get(0);
                fnText2.setText(visitor.getFirstName());
                lnText2.setText(visitor.getLastName());
                cText2.setText(visitor.getCompany());
                onText2.setText(visitor.getOfficeNo());
                svText2.setText(visitor.getStaffVisiting());
                tiText2.setText(visitor.getTimeIn());
                toText2.setText(visitor.getTimeOut());
                vIDText2.setText(visitor.getVisitorsID());
                phText2.setText(visitor.getPhoto());
            }
        });
        idText2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisitorQueries visitorQueries = new VisitorQueries();
                int id = Integer.parseInt(idText2.getText());

                List<Visitor> list = visitorQueries.getVisitorByID(id);
                Visitor visitor = (Visitor) list.get(0);
                fnText2.setText(visitor.getFirstName());
                lnText2.setText(visitor.getLastName());
                cText2.setText(visitor.getCompany());
                onText2.setText(visitor.getOfficeNo());
                svText2.setText(visitor.getStaffVisiting());
                tiText2.setText(visitor.getTimeIn());
                toText2.setText(visitor.getTimeOut());
                vIDText2.setText(visitor.getVisitorsID());
                phText2.setText(visitor.getPhoto());
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisitorQueries updateVisitor = new VisitorQueries();

                String fName = fnText2.getText();
                String lName = lnText2.getText();
                String company = cText2.getText();
                String ofNum = onText2.getText();
                String sVisiting = svText2.getText();
                Timestamp tIn = Timestamp.valueOf(tiText2.getText());
                Timestamp tOut = Timestamp.valueOf(toText2.getText());
                String vID = vIDText2.getText();
                String photo = phText2.getText();
                int id = Integer.parseInt(idText2.getText());

                int result = updateVisitor.updateVisitor(fName, lName, company, ofNum, sVisiting, tIn, tOut, vID, photo, id);
                if(result == 1){
                    JOptionPane.showMessageDialog(null, "Record Successfully Updated");
                } else{
                    JOptionPane.showMessageDialog(null, "Record Unsuccessfully Updated");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addItem){
            visitorPanel.setVisible(true);
            todayPanel.setVisible(false);
            displayPanel.setVisible(false);
            modifyPanel.setVisible(false);
            mainPanel.setVisible(false);
        }
        if(e.getSource()==modifyItem){
            visitorPanel.setVisible(false);
            todayPanel.setVisible(false);
            displayPanel.setVisible(false);
            modifyPanel.setVisible(true);
            mainPanel.setVisible(false);

        }
        if(e.getSource()==historyVItem){

            VisitorQueries visitorQueries = new VisitorQueries();
            String list = String.valueOf(visitorQueries.getAllVisitors());
            visitorPanel.setVisible(false);
            todayPanel.setVisible(false);
            displayPanel.setVisible(true);
            modifyPanel.setVisible(false);
            mainPanel.setVisible(false);
            vhtextArea.setText(list);
        }
        if(e.getSource()==todayReportItem){
            VisitorQueries visitorQueries = new VisitorQueries();
            String list = visitorQueries.getVisitorByDate();
            visitorPanel.setVisible(false);
            todayPanel.setVisible(true);
            displayPanel.setVisible(false);
            modifyPanel.setVisible(false);
            mainPanel.setVisible(false);
            vtTextArea.setText(list);

        }
        if(e.getSource()==exitItem){
            this.dispose();
        }
        if(e.getSource()==helpItem){
            visitorPanel.setVisible(false);
            todayPanel.setVisible(false);
            displayPanel.setVisible(false);
            modifyPanel.setVisible(false);
            mainPanel.setVisible(true);
            JOptionPane.showMessageDialog(null, "Use the menu on the top of the frame to manage the visitor data");
        }
        if(e.getSource()==aboutItem){
            JOptionPane.showMessageDialog(null, "Developers: Jose Rosado / Daniel Ovalle / Alexander Yodice");
        }
    }
}

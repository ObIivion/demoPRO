import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminPanel extends JFrame{
    private JPanel adminPanel;
    private JTable dataTable;
    private JComboBox filter;
    private JButton add;
    private JButton refresh;
    private JButton delbtn;
    private JButton editbtn;
    private JTextField search;
    private DefaultTableModel model;
    String qu = "";
    private Connection connect;
    private ResultSet result;


    AdminPanel(){

        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/service_logo.png"));

        setContentPane(adminPanel);
        setPreferredSize(new Dimension(1200, 600));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2)-1200/2, (Toolkit.getDefaultToolkit().getScreenSize().width/2)-600/2);
        setTitle("Панель администратора");

        DBClass db = new DBClass();
        Connection connection = db.connect();

        model = new DefaultTableModel();
        dataTable.setModel(model);
        model.addColumn("ID");
        model.addColumn("Имя");
        model.addColumn("Фамилия");
        model.addColumn("Отчество");
        model.addColumn("Дата Рождения");
        model.addColumn("Дата Регистрации");
        model.addColumn("Эл.почта");
        model.addColumn("Телефон");
        model.addColumn("Пол");
        model.addColumn("Дата добавления");

        filter.addItem("Все");
        filter.addItem("ж");
        filter.addItem("м");
        filter.setSelectedItem("Все");

        filter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                if(filter.getSelectedItem() == "Все")
                createStatement(search.getText(), "");
                else createStatement(search.getText(), filter.getSelectedItem().toString());

//                String chose = filter.getSelectedItem().toString();
//                System.out.println(chose);
//                if (chose == "Все") {
//                    updateTable(model, "Select * from client", connection);
//                } else {
//                    updateTable(model, "Select * from client where GenderCode = '" + chose + "'", connection);
//                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddForm af = new AddForm(false, null);
                af.pack();
                af.setVisible(true);
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        delbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DBClass db = new DBClass();
                Connection connection = db.connect();
                try {
                    connection.prepareStatement("Delete FROM client WHERE ID = " + dataTable.getValueAt(dataTable.getSelectedRow(), 0)).executeUpdate();
                    model.removeRow(dataTable.getSelectedRow());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        editbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                client client = new client();
                client.setId(Integer.parseInt((String) dataTable.getValueAt(dataTable.getSelectedRow(), 0)));
                client.setFirstName(dataTable.getValueAt(dataTable.getSelectedRow(), 1)+"");
                client.setLastName(dataTable.getValueAt(dataTable.getSelectedRow(), 2)+"");
                client.setPatronymic(dataTable.getValueAt(dataTable.getSelectedRow(), 3)+"");
                client.setBirthday(dataTable.getValueAt(dataTable.getSelectedRow(), 4)+"");
                client.setRegistrationDate(dataTable.getValueAt(dataTable.getSelectedRow(), 5)+"");
                client.setEmail(dataTable.getValueAt(dataTable.getSelectedRow(), 6)+"");
                client.setPhone(dataTable.getValueAt(dataTable.getSelectedRow(), 7)+"");
                client.setGenderCode(dataTable.getValueAt(dataTable.getSelectedRow(), 8)+"");
                client.setDateOfAdd(new Date().toString());

                AddForm af = new AddForm(true, client);
                af.pack();
                af.setVisible(true);

            }
        });

        search.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent caretEvent) {
                if(filter.getSelectedItem() == "Все")
                    createStatement(search.getText(), "");
                else createStatement(search.getText(), filter.getSelectedItem().toString());
            }
        });

    }

    public static void main(String[] args) {
        AdminPanel am = new AdminPanel();
        am.pack();
        am.setVisible(true);
    }

    public void updateTable(DefaultTableModel model, String query, Connection connection){

        dataTable.removeAll();
        try {
            while(model.getRowCount()>0) model.removeRow(0);
            result = connection.prepareStatement(query).executeQuery();

            while (result.next()){
                model.addRow(new String[] {
                        result.getString("ID"),
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("Patronymic"),
                        result.getDate("Birthday").toString(),
                        result.getString("RegistrationDate"),
                        result.getString("Email"),
                        result.getString("Phone"),
                        result.getString("GenderCode"),
                        new Date().toString()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataTable.setModel(model);
    }

    public void createStatement(String searchbox, String gender){
        String ns= "SELECT * FROM client";
        if(searchbox ==""&&gender=="")
        {}
        else if(gender=="")
        {
            ns+=" WHERE (FirstName LIKE '%"+searchbox+"%' OR LastName LIKE '%"+searchbox+"%')";
        }
        else if ((searchbox=="")&&(gender!=""))
        {
            ns+=" WHERE GenderCode = '"+gender+"'";
        }
        else
        {
            ns+="  WHERE (FirstName LIKE '%"+searchbox+"%' OR LastName LIKE '%"+searchbox+"%') AND GenderCode = '"+gender+"'";
        }
        qu=ns;

        DBClass db = new DBClass();
        Connection connect = db.connect();

        updateTable(model, qu, connect);
    }
}

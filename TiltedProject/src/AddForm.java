import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AddForm extends JFrame{
    private JTextField name;
    private JTextField surname;
    private JTextField patronymic;
    private JTextField birthday;
    private JTextField registrationDate;
    private JTextField email;
    private JTextField phone;
    private JRadioButton male;
    private JRadioButton female;
    private JButton save;
    private JPanel AddForm;
    private ButtonGroup gender;
    private DefaultTableModel model;

    AddForm(boolean editor, client client) {

        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/service_logo.png"));

        if (!editor) {

            setContentPane(AddForm);
            setPreferredSize(new Dimension(600, 800));
            setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 800 / 2, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 600 / 2);
            setTitle("Панель добавление");

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {


                        gender = new ButtonGroup();
                        gender.add(male);
                        gender.add(female);
                        String gen;
                        if (male.isSelected()) gen = male.getText();
                        else gen = female.getText();

                        try {
                            DBClass db = new DBClass();
                            Connection connect = db.connect();
                                    connect.prepareStatement("INSERT INTO client (FirstName, LastName, Patronymic, Birthday, RegistrationDate, Email, Phone, GenderCode, PhotoPath) values ('" + name.getText() + "', '" + surname.getText() + "', '" + patronymic.getText() + "', '" + birthday.getText() + "', '" + registrationDate.getText() + "', '" + email.getText() + "', '" + phone.getText() + "', '" + gen + "', 'null')").executeUpdate();
                            System.out.println("INSERT COMPLETED");
                            dispose();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            showMessage("Ошибка ввода даты");
                    }
                }
            });
        }

        else {
            setContentPane(AddForm);
            setPreferredSize(new Dimension(600, 500));
            setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 800 / 2, (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 600 / 2);
            setTitle("Панель редактирования");

            gender = new ButtonGroup();
            gender.add(male);
            gender.add(female);

            name.setText(client.getFirstName());
            surname.setText(client.getLastName());
            patronymic.setText(client.getPatronymic());
            birthday.setText(client.getBirthday());
            registrationDate.setText(client.getRegistrationDate());
            email.setText(client.getEmail());
            phone.setText(client.getPhone());
            if (client.getGenderCode() == "м") male.setSelected(true);
            else female.setSelected(true);

        }

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String gen;
                if (male.isSelected()) gen = male.getText();
                else gen = female.getText();

                DBClass db = new DBClass();
                db.connect();

                if (name.getText().length() == 0) showMessage("Имя не должно быть пустым");
                if (surname.getText().length() == 0) showMessage("Фамилия не должна быть пустой");
                if (patronymic.getText().length() == 0) showMessage("Отчество не должно быть пустым");
                if (email.getText().length() == 0) showMessage("Поле почты не должно быть пустым");
                if (phone.getText().length() == 0) showMessage("Поле телефона не должно быть пустым");

                try {
                    Connection connect = db.connect();
                    connect.prepareStatement("update client set FirstName = '" + name.getText() + "', LastName = '" + surname.getText() + "', Patronymic = '" + patronymic.getText() + "', Birthday = '" + birthday.getText() + "', RegistrationDate = '" + registrationDate.getText() + "', Email = '" + email.getText() + "', Phone = '" + phone.getText() + "', GenderCode = '" + gen + "' where ID = " + client.getId()).executeUpdate();
                    System.out.println("UPDATE COMPLETED");
                    dispose();
                } catch (SQLException e) {
                    e.printStackTrace();
                    showMessage("Ошибка ввода даты");
                }

            }
        });
    }

        public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
        return;
        }

    }


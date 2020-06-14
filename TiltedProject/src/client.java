public class client {

    private int id;
    private String FirstName;
    private String LastName;
    private String Patronymic;
    private String Birthday;
    private String RegistrationDate;
    private String Email;
    private String Phone;
    private String GenderCode;
    private String DateOfAdd;

    client(){
        id=-1;
        FirstName = "";
        LastName = "";
        Patronymic = "";
        Birthday = "";
        RegistrationDate = "";
        Email = "";
        Phone = "";
        GenderCode = "";
        DateOfAdd = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.Patronymic = patronymic;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        this.Birthday = birthday;
    }

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.RegistrationDate = registrationDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getGenderCode() {
        return GenderCode;
    }

    public void setGenderCode(String genderCode) {
        this.GenderCode = genderCode;
    }

    public String getDateOfAdd() {
        return DateOfAdd;
    }

    public void setDateOfAdd(String dateOfAdd) {
        this.DateOfAdd = dateOfAdd;
    }
}

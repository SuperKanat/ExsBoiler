public class MainFormDTO {
    private int id;
    private String type;
    private String name;
    private String director;
    private String address;
    private String email;
    private String phone;
    private String inn;
    private int rating;
    private int discount;

    // Конструктор, геттеры и сеттеры
    public MainFormDTO(int id, String type, String name, String director, String address, String email, String phone,
                       String inn, int rating, int discount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.director = director;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.inn = inn;
        this.rating = rating;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
public class MainFormDTO {

    private Integer id;
    private String prefix;
    private String name;
    private String director;
    private String phone;
    private Integer rating;
    private Integer discount;

    public MainFormDTO(Integer id, String prefix, String name, String director, String phone, Integer rating, Integer discount) {
        this.id = id;
        this.prefix = prefix;
        this.name = name;
        this.director = director;
        this.phone = phone;
        this.rating = rating;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "MainFormDTO{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", phone='" + phone + '\'' +
                ", rating=" + rating +
                ", discount=" + discount +
                '}';
    }
}

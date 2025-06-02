import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MainFormDAO {

    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "observer";
    String password = "gomodrilnya";

    // Метод для получения всех записей из таблицы
    public List<MainFormDTO> getAllRecords() {
        List<MainFormDTO> records = new ArrayList<>();

        // Подключение к базе данных

        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // SQL-запрос
            String sql = """
                        select id, partner_type, name, director, director_email, partner_phone, partner_legal_address, inn, rating,
                         CASE
                                         WHEN amount <= 10000 THEN 0
                                         WHEN amount > 10000 AND amount <= 50000 THEN 5
                                         WHEN amount > 50000 AND amount <= 300000 THEN 10
                                         WHEN amount > 300000 THEN 15
                                     END AS discount
                                     from 
                        (SELECT id, partner_type, name, director, director_email, partner_phone, partner_legal_address, inn, rating, 
                         (select sum (quantity) from partner_production pp where p.id = pp.partner_id) as amount                 
                          FROM partner p) as source order by id
                    """;

//            String sql = """
//                    Select p.id,
//                    p.partner_type,
//                    p.name,
//                    p.director,
//                    p.director_email,
//                    p.partner_phone,
//                    p.partner_legal_address,
//                    p.inn,
//                    p.rating,
//                    COALESCE(SUM(pp.quantity), 0) AS total_quantity
//                FROM partner p
//                LEFT JOIN partner_production pp ON p.id=pp.partner_id
//                GROUP BY
//                p.id,
//                p.partner_type,
//                p.name,
//                p.director_email,
//                p.partner_phone,
//                p.rating
//                ORDER BY p.id;
//                """;

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String type = resultSet.getString("partner_type");
                    String name = resultSet.getString("name");
                    String director = resultSet.getString("director");
                    String email = resultSet.getString("director_email");
                    String phone = resultSet.getString("partner_phone");
                    String inn = resultSet.getString("inn");
                    Integer rating = resultSet.getInt("rating");
                    Integer discount = resultSet.getInt("discount");

                    MainFormDTO dto = new MainFormDTO(id, type, name, director, null, email, phone, inn, rating, discount);
                    records.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public MainFormDTO getById(final int myId) {




        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String sql = """
                    SELECT id, partner_type, name, director, partner_legal_address, director_email, partner_phone, inn, rating
                    from partner where id = ?                  
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, myId);

                try (ResultSet resultSet = statement.executeQuery()) {

                    resultSet.next();
                    Integer id = resultSet.getInt("id");
                    String type = resultSet.getString("partner_type");
                    String name = resultSet.getString("name");
                    String director = resultSet.getString("director");
                    String email = resultSet.getString("director_email");
                    String address = resultSet.getString("partner_legal_address");
                    String phone = resultSet.getString("partner_phone");
                    String inn = resultSet.getString("inn");
                    Integer rating = resultSet.getInt("rating");

                    MainFormDTO dto = new MainFormDTO(id, type, name, director, address, email, phone, inn, rating, 0);
                    return dto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createNew(final MainFormDTO dto) {




        try (Connection connection = DriverManager.getConnection(url, user, password)) {


            String sql = """
                    insert into partner (id, partner_type, name, director, partner_legal_address, director_email, partner_phone, inn, rating)
                    values (
                    (select max(id)+1 from partner),
                    ?, ?, ?, ?, ?, ?,  ?, ?)                   
                    """;

            // Создание PreparedStatement
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, dto.getType());
                statement.setString(2, dto.getName());
                statement.setString(3, dto.getDirector());
                statement.setString(4, dto.getAddress());
                statement.setString(5, dto.getEmail());
                statement.setString(6, dto.getPhone());
                statement.setString(7, dto.getInn());
                statement.setInt(8, dto.getRating());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateExisting(final MainFormDTO dto) {



        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String sql = """
                    update partner 
                    set partner_type=?, name=?, director=?, partner_legal_address=?, director_email=?, partner_phone=?, inn=?, rating=?                    
                    where id = ?                   
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, dto.getType());
                statement.setString(2, dto.getName());
                statement.setString(3, dto.getDirector());
                statement.setString(4, dto.getAddress());
                statement.setString(5, dto.getEmail());
                statement.setString(6, dto.getPhone());
                statement.setString(7, dto.getInn());
                statement.setInt(8, dto.getRating());
                statement.setInt(9, dto.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllPartnerTypes() {
        List<String> records = new ArrayList<>();

        // Подключение к базе данных


        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // SQL-запрос
            String sql = """
                        select distinct (partner_type) as partner_type from partner order by partner_type
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {


                while (resultSet.next()) {
                    String type = resultSet.getString("partner_type");

                    records.add(type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }


}
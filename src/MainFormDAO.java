import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainFormDAO {

    public List<MainFormDTO> getAllRecords() {

    List<MainFormDTO> records = new ArrayList<>();

    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "observer";
    String passwd = "gomodrilnya";

    try(
    Connection connection = DriverManager.getConnection(url, user, passwd))

    {

        String sql = """
                    Select p.id,
                    p.partner_type,
                    p.name,
                    p.director,
                    p.director_email,
                    p.partner_phone,
                    p.partner_legal_address,
                    p.inn,
                    p.rating,
                    COALESCE(SUM(pp.quantity), 0) AS total_quantity
                FROM partner p
                LEFT JOIN partner_production pp ON p.id=pp.partner_id
                GROUP BY
                p.id,
                p.partner_type,
                p.name,
                p.director_email,
                p.partner_phone,
                p.rating
                ORDER BY p.id;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String type = resultSet.getString("partner_type");
                String name = resultSet.getString("name");
                String director = resultSet.getString("director");
                String phone = resultSet.getString("partner_phone");
                Integer rating = resultSet.getInt("rating");
                Integer totalQuantity = resultSet.getInt("total_quantity");

                int discount = 0;
                if(totalQuantity >= 300000) {
                    discount = 15;
                } else if (totalQuantity >= 50000) {
                    discount = 10;
                } else if (totalQuantity >= 10000) {
                    discount = 5;
                }

                MainFormDTO dto = new MainFormDTO(id, type, name, director, phone, rating, discount);
                records.add(dto);
            }
        }
    } catch(
    SQLException e)

    {
        e.printStackTrace();
    }
    return records;
    }
}

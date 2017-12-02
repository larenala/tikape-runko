
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Ingredient;

/**
 *
 * @author Laura
 */


public class IngredientDao implements Dao<Ingredient, Integer> {

    private Database database;

    public IngredientDao(Database database) {
        this.database = database;
    }

    @Override
    public Ingredient findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Ingredient WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Ingredient o = new Ingredient(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Ingredient> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Ingredient");

        ResultSet rs = stmt.executeQuery();
        List<Ingredient> ingredients = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            ingredients.add(new Ingredient(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return ingredients;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Ingredient WHERE id=" + key);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void add(Ingredient i) throws SQLException {
        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Ingredient (nimi) VALUES (?) ");
        stmt.setString(1, i.getNimi());
        stmt.executeUpdate();
        stmt.close();
        conn.close();       
    }
}


package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.DrinkIngredient;
import tikape.runko.domain.Ingredient;

/**
 *
 * @author Laura
 */


public class DrinkIngredientDao implements Dao<DrinkIngredient, Integer> {

    private Database database;

    public DrinkIngredientDao(Database database) {
        this.database = database;
    }

    @Override
    public DrinkIngredient findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM DrinkIngredient");

        ResultSet rs = stmt.executeQuery();
        List<Ingredient> ingredients = new ArrayList<>();
        while (rs.next()) {
        
        }
        return null;
    }
    
    @Override
    public List<DrinkIngredient> findAll() throws SQLException {
        //not implemented
        
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM DrinkIngredient WHERE DrinkIngredient.drink_id=(?)");
            stmt.setInt(1, key);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    public void saveOrUpdate(DrinkIngredient i) throws SQLException {   
        Integer ingredient_id = i.getIngredient_id();
        Integer drink_id = i.getDrink_id();
        if (byID(ingredient_id, drink_id) != null) {
            return;
        }
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO DrinkIngredient VALUES (?, ?, ?, ?, ?) ");
        stmt.setInt(1, i.getIngredient_id());
        stmt.setInt(2, i.getDrink_id());
        stmt.setInt(3, i.getAddingOrder());
        stmt.setString(4, i.getAmount());
        stmt.setString(5, i.getRecipeText());
        stmt.executeUpdate();
        stmt.close();
        conn.close();       
    }
    
    public List<String> getIngredientNames(Integer drinkId) throws SQLException {
        List<String>ainesosienNimet = new ArrayList<>();
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT Ingredient.nimi FROM Ingredient, DrinkIngredient WHERE DrinkIngredient.Drink_id =(?) "
                    + "AND Ingredient.id = DrinkIngredient.ingredient_id GROUP BY DrinkIngredient.addingOrder;");
            stmt.setInt(1, drinkId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String nimi = rs.getString("nimi");
                ainesosienNimet.add(nimi);
            }
        }
        return ainesosienNimet;
    }

    
    public List<DrinkIngredient>findAllByKey(Integer drinkId) throws SQLException {
        List drinkIngredients = new ArrayList<>();
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM DrinkIngredient WHERE drink_id= (?)");
            stmt.setInt(1, drinkId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer ingredient_id = rs.getInt("ingredient_id");
                Integer addingOrder = rs.getInt("addingOrder");
                String amount = rs.getString("amount");
                String recipe = rs.getString("recipeText");
                DrinkIngredient di = new DrinkIngredient(ingredient_id, drinkId, addingOrder, amount, recipe);
                drinkIngredients.add(di);
            }
            return drinkIngredients;
        }
    }
    
    private DrinkIngredient byID(Integer ingredientID, Integer drinkID) throws SQLException {
        try(Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM DrinkIngredient WHERE ingredient_id = (?) AND drink_id = (?)");
            stmt.setInt(1, ingredientID);
            stmt.setInt(2, drinkID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer ingredient_id = rs.getInt("ingredient_id");
                Integer drink_id = rs.getInt("drink_id");
                Integer addingOrder = rs.getInt("addingOrder");
                String amount = rs.getString("amount");
                String ohje = rs.getString("recipeText");
                return (new DrinkIngredient(ingredient_id, drink_id, addingOrder, amount, ohje));
            }
            
        }
        return null;
    }
}

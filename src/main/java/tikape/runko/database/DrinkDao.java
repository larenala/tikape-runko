/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Drink;

public class DrinkDao implements Dao<Drink, Integer> {

    private Database database;

    public DrinkDao(Database database) {
        this.database = database;
    }

    @Override
    public Drink findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Drink WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Drink o = new Drink(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Drink> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Drink");

        ResultSet rs = stmt.executeQuery();
        List<Drink> drinks = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            drinks.add(new Drink(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return drinks;
    }
    

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Drink WHERE Drink.id=(?)");
            stmt.setInt(1, key);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    public void add(Drink d) throws SQLException {
        Integer drinkId = d.getId();
        String drinkNimi = d.getNimi();
        
        if (findByName(drinkNimi) != null) {
            return;
        }
        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Drink (nimi) VALUES (?)");
        stmt.setString(1, drinkNimi);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    private Drink findByName(String s) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Drink WHERE nimi= (?)");
            stmt.setString(1, s);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                Integer drinkId = rs.getInt("id");
                return new Drink(drinkId, s);
            }
        }
        return null;
    }
}

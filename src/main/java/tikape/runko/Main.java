package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.DrinkDao;
import tikape.runko.database.DrinkIngredientDao;
import tikape.runko.database.IngredientDao;
import tikape.runko.domain.Drink;
import tikape.runko.domain.DrinkIngredient;
import tikape.runko.domain.Ingredient;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:drinks.db");
        //database.init();

        DrinkDao drinks = new DrinkDao(database);
        IngredientDao ingredients = new IngredientDao(database);
        DrinkIngredientDao drinkIngredients = new DrinkIngredientDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinks", drinks.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/drinks", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinks", drinks.findAll());
            map.put("ingredients", ingredients.findAll());
            return new ModelAndView(map, "drinks");
        }, new ThymeleafTemplateEngine());
        
        post("/drinks", (req,res) -> {
            String btnName = req.queryParams("action");
            if (btnName.equals("Lisää")) {
                String nimi = req.queryParams("nimi");
                Drink d = new Drink(-1, nimi);
                drinks.add(d);
            } else if (btnName.equals("Lisää ohjeeseen")) {
                Integer id = Integer.parseInt(req.queryParams("drinkID"));
                Integer ingredientId = Integer.parseInt(req.queryParams("ingredientID"));
                Integer jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
                String maara = req.queryParams("maara");
                String ohje = req.queryParams("ohje");
                DrinkIngredient di = new DrinkIngredient(ingredientId, id, jarjestys, maara, ohje);
                drinkIngredients.saveOrUpdate(di);
            } else if (btnName.equals("Poista")) {
                Integer id = Integer.parseInt(req.queryParams("id"));
                drinks.delete(id);
                drinkIngredients.delete(id);
            } 
            res.redirect("/drinks");
            return "";
        });
        
        get("/drinks/:id", (req, res) -> {
            Integer drinkid = Integer.parseInt(req.params(":id"));
            HashMap map = new HashMap<>();
            map.put("drink", drinks.findOne(drinkid));
            map.put("names", drinkIngredients.getIngredientNames(drinkid));
            map.put("ingredients", drinkIngredients.findAllByKey(drinkid));
            return new ModelAndView(map, "drink");
        }, new ThymeleafTemplateEngine());
        
        
        get("/ingredients", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("ingredients", ingredients.findAll());

            return new ModelAndView(map, "ingredients");
        }, new ThymeleafTemplateEngine());

        post("/ingredients", (req, res) -> {
            String btnName = req.queryParams("action");
            if (btnName.equals("Lisää")) {
                String nimi = req.queryParams("nimi");
                Ingredient i = new Ingredient(-1, nimi);
                ingredients.add(i);
            } else {
                Integer aineId = Integer.parseInt(req.queryParams("aineId"));              
                ingredients.delete(aineId);
            }
            res.redirect("/ingredients");
            return "";
        });
        

    }
}


package tikape.runko.domain;

public class DrinkIngredient {
    private Integer id;
    private Integer ingredient_id;
    private Integer drink_id;
    private Integer addingOrder;
    private String amount;
    private String recipeText;
            
    public DrinkIngredient(Integer ingredient_id, Integer drink_id, Integer addingOrder, String amount, String recipeText) {
        this.ingredient_id = ingredient_id;
        this.drink_id = drink_id;
        this.addingOrder = addingOrder;
        this.amount = amount;
        this.recipeText = recipeText;
    }
    
    public Integer getIngredient_id () {
        return this.ingredient_id;
    }
    
    public Integer getDrink_id () {
        return this.drink_id;
    }
    public Integer getAddingOrder () {
        return this.addingOrder;
    }
    public String getAmount () {
        return this.amount;
    }
    public String getRecipeText () {
        return this.recipeText;
    }
    public void setIngredient_id() {}
    
}

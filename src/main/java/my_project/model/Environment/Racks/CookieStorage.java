package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Cookie;
import my_project.model.Ingredients.Ingredient;
/**
 * the cookies storage
 */
public class CookieStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public CookieStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Cookies");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Cookie(x, y);
    }
}

package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Cookie;
import my_project.model.Ingredients.Ingredient;
/**
 * the cookies storage
 */
public class CookieStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public CookieStorage(double pX, double pY) {
        super(pX, pY,"Cookies");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new Cookie(x, y);
    }
}

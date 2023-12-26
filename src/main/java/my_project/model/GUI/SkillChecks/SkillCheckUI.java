package my_project.model.GUI.SkillChecks;

import my_project.model.GUI.UI;

/**
 * UI element that is created on cooking: "testing ('skill-checking') the player"  if you will
 */
public abstract class SkillCheckUI extends UI {
    protected double progress; // progress value between 0 and 1

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public SkillCheckUI(double pX, double pY) {
        super(pX, pY);
        width = 80;
        height = 200;
        progress = 0;
    }

    /**
     * increases the progress attribute and returns whether the skillcheck has not reached 100% completion or yes
     *
     * @return true if not finished (i.e. if progress was increased) and false otherwise
     */
    public boolean increaseProgress() {
        progress += 0.1;

        return !(progress + 0.1 >= 1);
    }
}

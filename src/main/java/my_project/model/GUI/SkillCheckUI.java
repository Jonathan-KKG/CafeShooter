package my_project.model.GUI;

import KAGO_framework.view.DrawTool;


public class SkillCheckUI extends UI {
    private String type;
    private double progress; // progress value between 0 and 1

    public SkillCheckUI(double pX, double pY, String pType){
        super(pX,pY);
        type = pType;
        width = 80;
        height = 200;
        y = absolutePosition[1];
        progress = 0;
    }

    public boolean increaseProgress(){
        progress += 0.1;

        return !(progress + 0.1 >= 1);
    }

    public void draw(DrawTool drawTool){
        drawTool.setLineWidth(2);

        // Standard UI Template
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawRectangle(absolutePosition[0] - width * 0.5 + 16, absolutePosition[1] - height * 0.9, width, height);
        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledRectangle(absolutePosition[0] - width * 0.5 + 16, absolutePosition[1] - height * 0.9, width, height);

        switch(type) {
            case "spaghet.png":
                drawOvenSC(drawTool);
                break;

        }
    }

    private void drawOvenSC(DrawTool drawTool){
        // "Progress Bar"
        drawTool.setCurrentColor(120, 117, 117, 255);
        drawTool.drawRectangle(absolutePosition[0] - width * 0.45 + 16 + width / 4, absolutePosition[1] - height * 0.8, width * 0.4, height * 0.8);
        drawTool.setCurrentColor(120, 117, 117, 80);
        drawTool.drawFilledRectangle(absolutePosition[0] - width * 0.45 + 16 + width / 4, absolutePosition[1] - height * 0.8, width * 0.4, height * 0.8);

        // moveable part of the "Progress Bar"
        drawTool.setCurrentColor(255, 0, 0, 255);
        drawTool.drawFilledRectangle(absolutePosition[0] - width * 0.45 + 16 + width / 4 + 2, y - height * 0.8 * progress + 2,width * 0.4 - 4, height * 0.8 * progress - 4);
    }

    public String getType() {
        return type;
    }
}

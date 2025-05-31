package ui.pages;

import org.openqa.selenium.WebDriver;
import ui.core.BasePage;
import ui.helperUI.ParameterizedDesign;
import ui.pages.fragments.ContextPanel;
import ui.pages.fragments.LeftToolMenu;
import ui.pages.fragments.ViewPanel;
import ui.pages.fragments.panelmenu.PanelMenu;
import ui.pages.fragments.rightmenu.RightMenu;

public class ScenePage extends BasePage {

    public PanelMenu panelMenu;
    public RightMenu rightMenu;
    public LeftToolMenu leftToolMenu;
    public ViewPanel viewPanel;
    public ContextPanel contextPanel;
    public ParameterizedDesign parameterizedDesign;


    public ScenePage(WebDriver driver) {
        super(driver);
        panelMenu = new PanelMenu(driver);
        rightMenu = new RightMenu(driver);
        leftToolMenu = new LeftToolMenu(driver);
        viewPanel = new ViewPanel(driver);
        contextPanel = new ContextPanel(driver);
        parameterizedDesign = new ParameterizedDesign(driver);
    }

}

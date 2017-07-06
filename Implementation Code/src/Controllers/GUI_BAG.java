package Controllers;

import javafx.scene.Parent;
import javafx.stage.Stage;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.HashMap;

/**
 * Created by Elhosany on 3/25/2017.
 */
public class GUI_BAG
{
    static GUI_BAG bag;
    static HashMap<String,Object> components;
    private static double xOffset = 0;
    private static double yOffset = 0;

    private GUI_BAG(){}
    public static GUI_BAG get_Bag() {
        if (bag == null){
            bag = new GUI_BAG();
            components = new HashMap<>();
        }
        return bag;
    }
    public void print_BagItems(){
        System.out.println(components.toString());
    }
    public void Register_Component(String name, Object Component)
    {
        components.put(name,Component);
    }
    public Object get_Registered_Component(String name) {
        if (!components.containsKey(name)){
            System.out.println("Error Component Not found in GUI BAG !");
            System.exit(0);
        }
        return components.get(name);
    }
    public Boolean Contains(String name){
        return components.containsKey(name);
    }
    //to make any frame draggable
    public void setDraggable(Stage primaryStage , Parent layout){
        layout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
        });
        layout.setOnMouseDragged(event -> {
            if (!primaryStage.isFullScreen()) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
            //if in full screen not need to draggable
        });
    }
    public void ModifyRegistered_Component(String name , Object newComponent){
        if (components.containsKey(name))
            components.put(name,newComponent);
    }
}

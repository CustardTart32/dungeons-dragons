package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image swordImage;
    private Image potionImage;
    private Image keyImage;
    private Image enemyImage;
    private Image treasureImage;
    private Image exitImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image portalImage;
    private Image boulderImage;
    private Image switchImage;


    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        swordImage = new Image("/greatsword_1_new.png");
        potionImage = new Image("/brilliant_blue_new.png");
        keyImage = new Image("/key.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        treasureImage = new Image("/gold_pile.png");
        exitImage = new Image("/exit.png");
        closedDoorImage =  new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        portalImage = new Image("/portal.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }

    @Override 
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override 
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
    	addEntity(exit, view);
    }
    
    @Override 
    public void onLoad(Door door) {
    	ImageView view = new ImageView(closedDoorImage);
    	addEntity(door, view);
    	Door openDoor = new Door(door.getX(), door.getY(), door.getId());
    	ImageView view2 = new ImageView(openDoorImage);
    	addEntity(openDoor, view2);
    }
    
    @Override 
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
    	addEntity(portal, view);
    }
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override 
    public void onLoad(Switch floor_switch) {
    	ImageView view = new ImageView(switchImage);
    	addEntity(floor_switch, view);
    }
    
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
        //System.out.println(entity);
    }
    
    
    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.visible().addListener(new ChangeListener<Boolean>() {
        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable,
        			Boolean oldValue, Boolean newValue) {
        		node.setVisible(false);
        	}
        });
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}

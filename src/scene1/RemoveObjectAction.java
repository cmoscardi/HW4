package scene1;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

public class RemoveObjectAction extends AbstractAction {
	public RemoveObjectAction(SceneComponent scene){
		this.scene = scene;
		opposites = new ArrayList<AddObjectAction>();
	}
	public void actionPerformed(ActionEvent e){
		scene.removeSelected();
		if(scene.getNumber()==0){
			this.setEnabled(false);
			setOpposites(true);
		}
		else if(scene.getNumber()<SceneComponent.MAX_OBJECTS){
			setOpposites(true);
		}
	}
	
	public void setOpposites(boolean b){
		for (AddObjectAction a : opposites){
			a.setEnabled(b);
		}
	}
	public void addOpposite(AddObjectAction a){
		opposites.add(a);
	}
	
	private SceneComponent scene;
	private ArrayList<AddObjectAction> opposites;
}


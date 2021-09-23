package src;

import src.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listener implements MouseListener, MouseMotionListener {
    private Scene scene;

    public void changeScene(Scene scene){
        this.scene = scene;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(scene!=null) scene.processClick(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(scene!=null) scene.processDrag(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

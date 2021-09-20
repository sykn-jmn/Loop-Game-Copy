package src;

import src.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements MouseListener {
    private Game game;
    public Listener(Game game){
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.updateLoops(e.getX(),e.getY());
        game.check();
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
}

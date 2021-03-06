package controller;

import javax.swing.JPanel;
import model.Game;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;


public class Controller extends JPanel{
	private static final long serialVersionUID = 1L;
	KeyFunctions k = new KeyFunctions(); 
	Game game;
	int count=0;
	public Controller(){
		game = new Game();
		count = 0;
		bindKeyWith("y.up", KeyStroke.getKeyStroke("UP"), new VerticalAction(-(this.game.getPlayer().getYvel())));
        bindKeyWith("y.down", KeyStroke.getKeyStroke("DOWN"), new VerticalAction(this.game.getPlayer().getYvel()));
        bindKeyWith("x.left", KeyStroke.getKeyStroke("LEFT"), new HorizontalAction(-(this.game.getPlayer().getXvel())));
        bindKeyWith("x.right", KeyStroke.getKeyStroke("RIGHT"), new HorizontalAction(this.game.getPlayer().getXvel()));
	}
	
	protected void bindKeyWith(String name, KeyStroke keyStroke, Action action) {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(keyStroke, name);
        am.put(name, action);
    }

	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect(game.getPlayer().getXpos(), game.getPlayer().getYpos(), 30, 30);
		String salt = "Salt: ";
		salt = salt + game.getPlayer().getSalinity();
		g2d.drawString(salt, 10, 20);
		for(int i=0; i<game.getPossibleHazards().getHazardsList().size(); i++){
			if(game.getPossibleHazards().getHazardsList().get(i).getSpawntime() < count)
				//game.getPossibleHazards().getHazardsList().get(i).setxpos(400);
			//System.out.println("Spawning Hazard... x: " + game.getPossibleHazards().getHazardsList().get(i).getXpos() + "y: " + 
				//	game.getPossibleHazards().getHazardsList().get(i).getYpos());
			g2d.drawOval(game.getPossibleHazards().getHazardsList().get(i).getxpos()+300, game.getPossibleHazards().getHazardsList().get(i).getypos(), 20, 20);
		}
		int x=20;
		for(int i=0; i<game.getPlayer().getLife(); i++){
			g2d.drawOval(x, 20, 30, 20);
			x+=40;
		}
		if(game.isGameOver()){
			g2d.drawString("GAME OVER", 150, 150);
		}
	}
	
	public void onCollision(){
		for(int i=0; i<game.getPossibleHazards().getHazardsList().size(); i++){
			if(game.getPossibleHazards().getHazardsList().get(i).getBounds().intersects(game.getPlayer().getBounds())){
				System.out.println("One less life");
				game.getPossibleHazards().getHazardsList().get(i).setXpos(900);
				game.getPossibleHazards().getHazardsList().get(i).setYpos(900);
				game.getPlayer().setLife(game.getPlayer().getLife()-1);
			}
		}
	}
	
	public void saltOnMovement() {
		double xsaltindexprep = game.getPlayer().getXpos()/((double)game.FRAMEWIDTH);
		int xsaltindex = (int) (40*xsaltindexprep);
		double ysaltindexprep = game.getPlayer().getYpos()/((double) game.FRAMEHEIGHT);
		int ysaltindex = (int) (20*ysaltindexprep);
		game.getPlayer().setSaldelta(game.getBoard().getTile(xsaltindex, ysaltindex));
		System.out.print(xsaltindexprep+" ");
		System.out.println(game.getPlayer().getXpos());
		game.getPlayer().updateSalinity();
	}
	
	public void update(){
		count++;
		//System.out.println("Count: " + count);
		for(int i = 0; i<game.getPossibleHazards().getHazardsList().size(); i++){
			if(game.getPossibleHazards().getHazardsList().get(i).getSpawntime() < count) {
			game.getPossibleHazards().getHazardsList().get(i).moveLeft();
			}
		}
		repaint();
		//saltOnMovement();
		onCollision();
	}
    private int getCount() {
		// TODO Auto-generated method stub
		return this.count;
	}
    
    private void setCount(int count) {
		// TODO Auto-generated method stub
		this.count = count;
	}

	private void upCount() {
		count++;
		
	}
	public abstract class MoveAction extends AbstractAction {

        private int delta;

        public MoveAction(int delta) {
            this.delta = delta;
        }

        public int getDelta() {
            return delta;
        }

        protected abstract void applyDelta();

        @Override
        public void actionPerformed(ActionEvent e) {
            applyDelta();
        }

    }
	

    public class VerticalAction extends MoveAction {

        public VerticalAction(int delta) {
            super(delta);
        }

        @Override
        protected void applyDelta() {
            int delta = getDelta();
            game.getPlayer().setYpos(game.getPlayer().getYpos()+delta);
            if (game.getPlayer().getYpos() < 0) {
                game.getPlayer().setYpos(0);
            } else if (game.getPlayer().getYpos() + 30 > getHeight()) {
                game.getPlayer().setYpos(getHeight() - 30);
            }
            //repaint();
        }

    }
    public class HorizontalAction extends MoveAction {

        public HorizontalAction(int delta) {
            super(delta);
        }

        @Override
        protected void applyDelta() {
            int delta = getDelta();
            game.getPlayer().setXpos(game.getPlayer().getXpos()+delta);
            if (game.getPlayer().getXpos() < 0) {
                game.getPlayer().setXpos(0);
            } else if (game.getPlayer().getXpos() +30 > getWidth()) {
                game.getPlayer().setXpos(getWidth() - 30);
            }
           // repaint();
        }

    }
    
    public Game getGame() {
    	return this.game;
    }
}

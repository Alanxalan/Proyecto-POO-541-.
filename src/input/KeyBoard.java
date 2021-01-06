package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Esta clase se encarga de todo lo que tenga que ver con el teclado y para esto debemos implementar la interfaz KeyListener.
public class KeyBoard implements KeyListener{
	
	private boolean[] keys = new boolean[256];
	
	public static boolean UP, LEFT, RIGHT, SHOOT;
	
	public KeyBoard()
	{
		UP = false;
		LEFT = false;
		RIGHT = false;
		SHOOT = false;
	}
	
	public void update()
	{
		UP = keys[KeyEvent.VK_UP];
		LEFT = keys[KeyEvent.VK_LEFT];
		RIGHT = keys[KeyEvent.VK_RIGHT];
		SHOOT = keys[KeyEvent.VK_Z];
	}
	
	@Override   //Cada vez que se presione una tecla, se llamara a este metodo y la informacion qeu fue precionada se almacenara en el objeto KeyEvent.
	public void keyPressed(KeyEvent e) {
            keys[e.getKeyCode()] = true;
	}

	@Override   //Se hace lo mismo que el metodo anterior solo que en este caso es al soltar la tecla.
	public void keyReleased(KeyEvent e) {
            keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
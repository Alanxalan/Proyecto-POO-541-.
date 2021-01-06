package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
//De esta clase derivan la mayoria de nuestros objetos del juego.
public abstract class GameObject {
    protected BufferedImage texture;    //la imagen de nuestros objetos.
    protected Vector2D position;    //posicion que se le dara a nuestros objetos.
    
    public GameObject(Vector2D position, BufferedImage texture){
        this.position = position;
        this.texture = texture;
    }
    
    public abstract void update(float dt);
    public abstract void draw(Graphics g);

    //Estos metodos piden y regresan la posicion, sera empleada en otros paquetes y clases.
    public Vector2D getPosition() {
        return position;
    }
    public void setPosition(Vector2D position) {
        this.position = position;
    }
    
    
}
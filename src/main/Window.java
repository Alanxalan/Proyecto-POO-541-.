package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import gameObjects.Constants;
import graphics.Assets;
import input.KeyBoard;
import input.MouseInput;
import java.awt.FontFormatException;
import java.util.logging.Level;
import java.util.logging.Logger;
import states.LoadingState;
import states.State;

/*  Esta clase se encarga de gestionar todos los eventos de la ventana, asi que aqui no hay mucha logica del juego. */
public class Window extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;  //Con el canvas nos es posible dibujar en el metodo Draw.
	private Thread thread;
	private boolean running = false;
	//Objetos necesarios para dibujar.
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60; //Velocidad del juego. Num de fotogramas por seg.
	private double TARGETTIME = 1000000000/FPS; //Se almacena el tiempo requerido para aumentar 1 fotograma.
	private double delta = 0;   //Almacena temporalmente el tiempo que vaya pasando.
	private int AVERAGEFPS = FPS;   //Nos permitira saber a cuantos fps estara corriendo el juego.
	
	private KeyBoard keyBoard;
	private MouseInput mouseInput;
	
	public Window()
	{
		setTitle("Space X");    //Titulo del juego.
		setSize(Constants.WIDTH, Constants.HEIGHT); //Dimensiones que tendra la pantalla.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Esto permite que la pesta;a se pueda cerrar al dar clic en X.
		setResizable(false);    //Para no redimensionar durante la ejecucion del juego.
		setLocationRelativeTo(null);    //Permite que la pesta;a se despliegue en el centro de la pantalla.
		
		
		canvas = new Canvas();  //Inicializacion
		keyBoard = new KeyBoard();
		mouseInput = new MouseInput();
		
		canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setFocusable(true);  //Nos permite recibir entradas por parte del teclado.
		
		add(canvas);
		canvas.addKeyListener(keyBoard);
		canvas.addMouseListener(mouseInput);
		canvas.addMouseMotionListener(mouseInput);
		setVisible(true);   //Despliega la ventana y la hace visible.
	}
	
	
        //Main
	public static void main(String[] args) {
		new Window().start();
	}
	
	
	private void update(float dt){
		keyBoard.update();
		State.getCurrentState().update(dt);
	}

	private void draw(){
		bs = canvas.getBufferStrategy();    //Nos retornara null.
		
		if(bs == null){
                    canvas.createBufferStrategy(3); //Es el numero de buffers.
                    return;
		}
		
		g = bs.getDrawGraphics();
		
		//-----------------------   "Aqui empieza el dibujo".
		
		g.setColor(Color.BLACK);    //Color negro de la pantalla.
		
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);    //Limpia la pantalla
		
		State.getCurrentState().draw(g);
		
		g.setColor(Color.WHITE);
                //Caracteres de los fps, posicion en X, posicicon en Y.
		g.drawString(""+AVERAGEFPS, 10, 20);    //Dibujar contador de frames
		
		//-----------------------   "Aqui termina el dibujo".
		g.dispose();
		bs.show();
	}
	
	private void init() throws FontFormatException {	
            Thread loadingThread = new Thread(new Runnable() {

            @Override
            public void run() { //Este metodo llama a assets y carga todos los recursos.
		Assets.init();
		}
            });
		
            State.changeState(new LoadingState(loadingThread));
	}
	
	//Sobrescrito de un metodo de la implementacion de Runnable
	@Override
	public void run() {
		
            long now = 0;   //Registro del tiempo que vaya pasando.
            long lastTime = System.nanoTime();  //Nos dara la hora actual del sistema en nanosegundos
            int frames = 0; 
            long time = 0;  //Para volver a calcular el tiempo.
		
            try {
                init();
            } catch (FontFormatException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
		
            while(running)
            {
		now = System.nanoTime();  //Almacena un valor diferente que lastTime ya que se toma un timpo al pasar al valor de esta variable.  
		delta += (now - lastTime)/TARGETTIME;   //Almacenara el tiempo que haya pasado hasta este momento.
		time += (now - lastTime); 
		lastTime = now;
			
		if(delta >= 1){	
                    update((float) (delta * TARGETTIME * 0.000001f));   //Actualizamos
                    draw(); //Dibujamos
                    delta --;   //Restamos una unidad a delta para volver a cronometrar el siguiente fotograma. 
                    frames ++;
		}
		if(time >= 1000000000){
                    AVERAGEFPS = frames;    //Almacena los fotogramas.
                    frames = 0; //Volver a contar
                    time = 0;
		}	
            }
		
            stop();
	}
	//Empieza el thread (hilo)
	private void start(){
            thread = new Thread(this);  //Esta clase recibira como parametro el constructor de una clase que implemente la interfaz Runnable.
            thread.start(); //Llamara al metodo run.
            running = true;	
	}
        //Termina el thread (hilo)
	private void stop(){
            try {
		thread.join();
		running = false;
            } catch (InterruptedException e) {
		e.printStackTrace();
            }
	}
}
package framework;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

import static framework.Draw.*;

public abstract class GameObject {
	protected float x,y,xSpeed,ySpeed,width,height;
	protected Texture texture;
	protected Rectangle hitbox;
	protected ObjectID id;
	
	public GameObject(float x, float y, float width, float height, ObjectID id)
	{
		this.x = x;
		this.y = y ;
		this.width = width;
		this.height = height;
		this.id = id;
		texture = quickLoad(id.texture);
	}
	
	public abstract void tick();
	
	public abstract void render();

	public abstract void collision();
	
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}

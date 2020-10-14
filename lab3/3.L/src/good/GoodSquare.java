package good;

public class GoodSquare extends GoodRectangle implements AreaCalculator {

	public GoodSquare(int height, int width) {
		super(height, width);
		if (height!=width){
			System.out.println("Warning! height =/= width\nUsed height as width too");
		}
		this.setWidth(height);
	}

	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}

	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}

	@Override
	public int getArea() {
		return this.getHeight() * this.getHeight();
	}

	@Override
	public String toString() {
		return "I am a square";
	}


}

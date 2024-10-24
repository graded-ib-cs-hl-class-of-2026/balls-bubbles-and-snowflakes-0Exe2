
class Bubble {

    //MISSING MANY INSTANCE VARIABLES
    // ONLY THE DRAW METHOD IS INCLUDED. YOU MUST FINISH THE REST.
    private Sketch s;
    /** the radius of the ball */
    private float radius;
    private float x;
    private float y;
    /** The number of pixels the ball moves right per frame */
    private float xSpeed;
    /** The number of pixels the ball moves down per frame */
    private float ySpeed;
    /** The color of the inside of the ball */
    private int fillColor;
    /** The color of the outside of the ball */
    private int borderColor;

    //MISSING CONSTRUCTORS
    public Bubble(Sketch s, int radius) {
        this.s = s;

        this.radius = radius;
        this.x = 400; //(float)Math.random() * 500f;
        this.y = s.height;
        this.xSpeed = 0.5f; //((float)Math.random() - 0.5f) * 2f;
        this.ySpeed = -2;

        this.fillColor = s.color(255, 255, 255, 0);
    }

    // accessors for the radius, diameter, x, and y values
    public float getRadius() {
        return radius;
    }

    public float getDiameter() {
        return radius * 2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw() {
        s.stroke(borderColor);
        s.fill(fillColor);
        s.circle(x, y, radius*2);
    }

    public void move() {
        x = x + xSpeed;
        y = y + ySpeed;

        boolean collided = false;

        if (x > s.width - radius) {
            collided = true;
            //x = (float)Math.random() * 500f;
            //y = radius;
            //xSpeed = (float)Math.random() * -3f;
        } else if(x < radius) {
            collided = true;
            //x = s.width - radius;
            //x = (float)Math.random() * 500f;
            //y = radius;

            //xSpeed = (float)Math.random() * -3f;
        } else if (y > s.height - radius) {
            // Collided with the bottom
            //collided = true;
            //y = s.height - radius;
        } else if (y < radius) {
            collided = true;
            //y = s.height - radius;
        }

        if (collided) {
            y = s.height; // + (float)Math.random() * 200;
            x = 300; //(float)Math.random() * 500f;
            xSpeed = 0.5f; //((float)Math.random() - 0.5f) * 2f;
        }
    }

}

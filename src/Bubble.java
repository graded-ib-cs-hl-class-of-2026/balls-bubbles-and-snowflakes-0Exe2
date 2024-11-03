
class Bubble {

    //MISSING MANY INSTANCE VARIABLES
    // ONLY THE DRAW METHOD IS INCLUDED. YOU MUST FINISH THE REST.
    private Sketch s;
    /** the radius of the bubble */
    public float radius;
    public float x;
    public float y;
    /** The number of pixels the bubble moves right per frame */
    public float xSpeed;
    /** The number of pixels the bubble moves down per frame */
    public float ySpeed;
    /** The color of the inside of the bubble */
    private int fillColor;
    /** The color of the outside of the bubble */
    private int borderColor;

    /**
     * Creates a new bubble with the given sketch and radius
     */
    public Bubble(Sketch s, int radius) {
        this.s = s;

        this.radius = radius;
        this.x = (float)Math.random() * s.width;
        this.y = s.height;
        this.xSpeed = ((float)Math.random() - 0.5f) * 2f;
        this.ySpeed = -2;

        this.fillColor = s.color(255, 255, 255, 0);
    }

    /**
     * Helper method to get the radius of the bubble
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Helper method to get the diameter of the bubble
     */
    public float getDiameter() {
        return radius * 2;
    }

    /**
     * Helper method to get the x pos of the bubble
     */
    public float getX() {
        return x;
    }

    /**
     * Helper method to get the y pos of the bubble
     */
    public float getY() {
        return y;
    }

    /**
     * Method that is called to draw the bubble in each frame
     */
    public void draw() {
        s.stroke(borderColor);
        s.fill(fillColor);
        s.circle(x, y, radius*2);
    }

    /**
     * Method that is called to move the bubble in each frame
     */
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
            burst();
        }
    }

    /**
     * Method that is called whenever we want to pop / reset the bubble
     */
    public void burst() {
        y = s.height + (float)Math.random() * 200;
        x = (float)Math.random() * 500f;
        xSpeed = ((float)Math.random() - 0.5f) * 2f;
    }

}

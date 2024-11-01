class Ball {

    /*
     * In Processing, the Sketch works a lot like the World in the turtles. You
     * can't
     * draw a ball without knowing which sketch to draw it on, so you always need to
     * specify the sketch for a Ball object.
     */

    /** The sketch that the ball is inside */
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
    public boolean frozen = false;

    /** Empty constructor to keep the defaults. Only sets up our link. */
    public Ball(Sketch sketch) {
        s = sketch;
        radius = 20;
        x = 100;
        y = 100;
        xSpeed = 2;
        ySpeed = -1;
        fillColor = s.color(255, 100, 100);
        borderColor = s.color(0, 0, 0);
    }

    /** Fully specified constructor to allow changes to size, position, speed */
    /** Does NOT allow changing color! Need to use setColors() for that. */

    /**
     * Creates a new ball with the given sketch, radius, x speed, and y speed
    */
    public Ball(Sketch sketch, float radius, float xspeed, float yspeed) {
        this.s = sketch;
        this.radius = radius;
        this.x = ((float) Math.random() * (sketch.width - 200)) + 100;
        this.y = ((float) Math.random() * (sketch.height - 200)) + 100;
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;

        fillColor = s.color(255, 100, 100);
        borderColor = s.color(0, 0, 0);
    }

    // Accessors (getters) go here

    /**
     * Returns the radius of the ball
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Returns the diameter of the ball
     */
    public float getDiameter() {
        return radius * 2;
    }

    /**
     * Returns the x position of the ball
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y position of the ball
     */
    public float getY() {
        return y;
    }

    /**
     * Returns a boolean indicating if the ball is frozen or not
     */
    public boolean isFrozen() {
        return frozen;
    }

    // Setters that you need go here - by default, only colors

    /**
     * Sets the colors of the ball
     */
    public void setColors(int fill, int border) {
        borderColor = border;
        fillColor = fill;
    }

    /**
     * Sets whether the ball is frozen or not
     */
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * Draws the ball on the given sketch
     */
    public void draw() {
        s.stroke(borderColor);
        s.fill(fillColor);
        s.circle(x, y, radius * 2);
    }

    /**
     * Moves the ball so that the next time it draws it will be in a different place
     */
    public void move() {
        if (!frozen) {
            x = x + xSpeed;
            y = y + ySpeed;

            if (x >= s.width - radius - 5 || x <= radius + 5) {
                xSpeed = -xSpeed;
            }
            if (y >= s.height - radius - 5 || y <= radius + 5) {
                ySpeed = -ySpeed;
            }
        }
    }

    /**
     * Returns true if the ball is colliding with another ball
    */
    public boolean collidesWith(Ball other) {
        float dx = x - other.x;
        float dy = y - other.y;

        // pythagoeran theorem
        // tried to use processing's dist() function outside of the Sketch class and was being weird
        float distance = (float)Math.sqrt((dx * dx) + (dy * dy));

        // return if the distance between the two balls is less than the sum of their radiuses
        return distance < (radius + other.radius);
    }

    /**
     * Returns true if the ball is colliding with a bubble
    */
    public boolean collidesWithBubble(Bubble other) {
        float dx = x - other.x;
        float dy = y - other.y;

        // pythagoeran theorem
        float distance = (float)Math.sqrt((dx * dx) + (dy * dy));

        // return if the distance between the two balls is less than the sum of their radiuses
        return distance <= (radius + other.radius);
    }

    /**
     * Randomizes the colors of the ball
    */
    public void randomizeColors() {
        setColors(
            s.color(
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255)),
            s.color(
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255))
        );
    }

    /**
     * Bounces the ball off of another ball
    */
    public void bounce(Ball other) {

        float dx = x - other.x;
        float dy = y - other.y;

        // pythagoeran theorem again
        float distance = (float) Math.sqrt((dx * dx) + (dy * dy));

        // inverse tangent in polar form https://www.w3schools.com/java/ref_math_atan2.asp
        // literally perfect for getting the angle between two points in "rectangular coordinates"
        float angle = (float) Math.atan2(dy, dx);

        // the target x and y positions that we want the balls to move to
        float targetX = x + (float) Math.cos(angle) * (radius + other.radius - distance);
        float targetY = y + (float) Math.sin(angle) * (radius + other.radius - distance);

        // the movement that we want to apply to the balls to get to the target positions
        float ax = (targetX - other.x) * -0.1f;
        float ay = (targetY - other.y) * -0.1f;

        // apply the movement to the ball (in opposite direction of the other's movement)
        xSpeed -= ax;
        ySpeed -= ay;

        // to regulate max speed so it doesnt get too crazy
        xSpeed *= 0.5f;
        ySpeed *= 0.5f;

        // apply the same movement to the other ball but in the opposite direction
        other.xSpeed += ax;
        other.ySpeed += ay;

        // to regulate max speed so it doesnt get too crazy
        other.xSpeed *= 0.5f;
        other.ySpeed *= 0.5f;
    }

}

class Snowflake {
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

    public Snowflake(Sketch sketch) {
        s = sketch;
        radius = 20;

        resetSnowflakePos();

        xSpeed = -0.5f;
        ySpeed = 2;
    }

    /**
     * Resets the snowflake's position to a random location above the screen
     */
    public void resetSnowflakePos() {
        this.x = ((float) Math.random() * (s.width - 200)) + 100;
        this.y = ((float) Math.random() * (s.height - 200)) + 100;

        while (this.x < 0 || this.x > s.width) {
            this.x = ((float) Math.random() * (s.width - 200)) + 100;
        }

        while (this.y < 0 || this.y > s.height) {
            this.y = ((float) Math.random() * (s.height - 200)) + 100;
        }
    }

    /**
     * Resets the snowflake's position to a random location above the screen
     */
    public void resetSnowflakePos(int y) {
        this.x = ((float) Math.random() * (s.width - 200)) + 100;
        this.y = y;

        while (this.x < 0 || this.x > s.width) {
            this.x = ((float) Math.random() * (s.width - 200)) + 100;
        }
    }

    /**
     * Returns the radius of the snowflake
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Returns the diameter of the snowflake
     */
    public float getDiameter() {
        return radius * 2;
    }

    /**
     * Returns the x position of the snowflake
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y position of the snowflake
     */
    public float getY() {
        return y;
    }

    /**
     * Draws the snowflake in each frame
     */
    public void draw() {
        s.stroke(s.color(255, 255, 255));
        s.line(x+radius,y,x-radius,y);
        s.line(x,y+radius,x,y-radius);
        s.line(x+radius*.707f,y+radius*.707f,x-radius*.707f,y-radius*.707f);
        s.line(x+radius*.707f,y-radius*.707f,x-radius*.707f,y+radius*.707f);
    }

    /**
     * Moves the snowflake in each frame
     */
    public void move() {
        x = x + xSpeed;
        y = y + ySpeed;

        if (y > (s.height + radius)) {
            resetSnowflakePos(-50);
            //ySpeed = -2;
        }

        if (x > s.width - radius || x < radius) {
            xSpeed = -xSpeed;
        }
    }
}

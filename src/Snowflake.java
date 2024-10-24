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
    /** The color of the inside of the ball */
    private int fillColor;
    /** The color of the outside of the ball */
    private int borderColor;

    public Snowflake(Sketch sketch, int x, int y) {
        s = sketch;
        radius = 20;

        this.x = x;
        this.y = y;

        xSpeed = -0.5f;
        ySpeed = 2;
        // fillColor = s.color(255, 255, 255);
        // borderColor = s.color(255, 255, 255);
    }

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

    // Setters that you need go here - by default, only colors

    // public void setColors(int fill, int border) {
    //     borderColor = border;
    //     fillColor = fill;
    // }


    public void draw() {
        s.stroke(s.color(255, 255, 255));
        s.line(x+radius,y,x-radius,y);
        s.line(x,y+radius,x,y-radius);
        s.line(x+radius*.707f,y+radius*.707f,x-radius*.707f,y-radius*.707f);
        s.line(x+radius*.707f,y-radius*.707f,x-radius*.707f,y+radius*.707f);
    }

    public void move() {
        x = x + xSpeed;
        y = y + ySpeed;

        if (y == s.height + (radius * 2)) {
            y = radius; //-50;
            //ySpeed = -2;
        }

        if (x > s.width - radius || x < radius) {
            xSpeed = -xSpeed;
        }
        // if (y < radius) { // y > s.height - radius ||
        //     ySpeed = -ySpeed;
        // }
    }
}

import processing.core.PApplet;

/**
 * For my program, I added several new features to the original code. I added
 * - randomly generating snowflakes', balls', and bubbles' positions
 * - randomly generating balls' colors (and associated method)
 * - bubbles that burst when clicked (and associated method)
 * - balls that freeze when hovered over (and associated attribute)
 * - balls that bounce off of each other (and associated method) in addition
 *   to distance calculations
 * Code changes:
 * - added all these objects on the screen to arrays to make code easier to manipulate
 *   for all objects at the same time
 */
public class Sketch extends PApplet {

    /** Represents one ball */
    private Ball[] ballsArray;

    private Bubble[] bubblesArray;

    private Snowflake[] snowflakesArray;

    /**
     * This method can only be used to change the window size. It runs before the
     * window is created.
     */
    public void settings() {
        size(1000, 700);
    }

    /**
     * Runs once at the beginning of the program, after the window is created. Use
     * this to initialize the sketch.
     */
    public void setup() {
        Ball ball1 = new Ball(this, 15, -2f, 1f);
        Ball ball2 = new Ball(this, 30, -2f, 1f);
        Ball ball3 = new Ball(this, 45, -2f, 1f);
        Ball ball4 = new Ball(this, 37, -2f, 1f);

        ballsArray = new Ball[] {ball1, ball2, ball3, ball4};

        for (Ball ball : ballsArray) {
            ball.randomizeColors();
        }

        Snowflake snowflake1 = new Snowflake(this);
        Snowflake snowflake2 = new Snowflake(this);
        Snowflake snowflake3 = new Snowflake(this);
        Snowflake snowflake4 = new Snowflake(this);

        snowflakesArray = new Snowflake[] {snowflake1, snowflake2, snowflake3, snowflake4};

        Bubble bubble1 = new Bubble(this, 20);
        Bubble bubble2 = new Bubble(this, 25);
        Bubble bubble3 = new Bubble(this, 10);
        Bubble bubble4 = new Bubble(this, 35);

        bubblesArray = new Bubble[] {bubble1, bubble2, bubble3, bubble4};
    }

    /**
     * This method runs over and over and over, approximately 60 times per second!
     * By moving objects a tiny bit each frame, you can get the appearance of
     * movement.
     */
    public void draw() {
        background(180, 180, 255);

        for (Ball ball : ballsArray) {
            ball.draw();
            ball.move();
        }

        // for each of the 4 balls, check if their radius collides with another ball
        // if it does, bounce the balls away from each other
        // make an array of balls
        for (int i = 0; i < ballsArray.length; i++) {
            for (int j = i + 1; j < ballsArray.length; j++) {
                if (ballsArray[i].collidesWith(ballsArray[j])) {
                    ballsArray[i].bounce(ballsArray[j]);

                    ballsArray[i].randomizeColors();
                    ballsArray[j].randomizeColors();
                }
            }
        }

        for (int i = 0; i < ballsArray.length; i++) {
            for (int j = 0; j < ballsArray.length; j++) {
                if (ballsArray[i].collidesWithBubble(bubblesArray[j])) {
                    bubblesArray[j].burst();
                }
            }
        }

        for (Bubble bubble : bubblesArray) {
            bubble.draw();
            bubble.move();
        }

        for (Snowflake snowflake : snowflakesArray) {
            snowflake.draw();
            snowflake.move();
        }
    }

    /**
     * Runs whenever the mouse is moved. Checks if cusor is on a ball to set it to
     * frozen
     */
    public void mouseMoved() {
        for (Ball ball : ballsArray) {
            if (mouseX > ball.getX() - ball.getRadius() && mouseX < ball.getX() + ball.getRadius() && mouseY > ball.getY() - ball.getRadius() && mouseY < ball.getY() + ball.getRadius()) {
                ball.setFrozen(true);
            } else {
                ball.setFrozen(false);
            }
        }
    }

    /**
     * burst bubble on click (needed to be pressed instead of clicked so..
     * even would work after frame of click ends
     * https://processing.org/reference/mouseClicked_.html
     */
    public void mousePressed() {

        // https://www.w3schools.com/java/java_foreach_loop.asp for each loop
        for (Bubble bubble : bubblesArray) {
            // https://processing.org/reference/dist_.html dist method (distance between two points)
            float distance = dist(mouseX, mouseY, bubble.getX(), bubble.getY());
            if (distance - 5 <= bubble.getRadius()) { // dist - 5 gives a bit of error room
                bubble.burst();
            }
        }
    }

    /** All processing sketches have to use this main method. Don't touch this! */
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}

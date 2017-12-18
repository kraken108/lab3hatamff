package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The world contains a snake, apples (and later on obstacles). The method move
 * updates the world, i.e. moves the snake one step, checks for apples to eat,
 * whether the snake is out of bounds et c.
 *
 * Created by anderslm@kth.se on 2015-03-10. Based on the Android Open Source
 * Project Snake.
 */
public class World {

    public static final int POINTS_PER_APPLE = 10,
            POINTS_PER_POWERED_APPLE = 20;

    private final int boardWidth, boardHeight;

    private Snake snake;
    private final LinkedList<Apple> apples;
    private final LinkedList<Obstacle> obstacles;

    private State state = State.READY;
    private long score = 0;

    private static final Random RANDOM = new Random();

    /**
     * Creates a new World with the stated dimensions measured in snake
     * segments.
     *
     * @param boardWidth The board width, measured in segments (integer)
     * @param boardHeight The board height, measured in segments (integer)
     */
    public World(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        apples = new LinkedList<>();
        obstacles = new LinkedList<>();
        initNewGame();
    }

    /**
     * Initializes a new game. A new snake trail of five segments and two apples
     * (randomized) are created. Game state is set to READY.
     */
    public final void initNewGame() {
        Point head = new Point(boardWidth / 2, boardHeight / 2);
        snake = new Snake(head, 5);

        apples.clear();
        addApple();
        addApple();

        Point pos = null;
        for(int i=0;i<2;i++){
            pos = new Point(RANDOM.nextInt(boardWidth),
                RANDOM.nextInt(boardHeight));
        Obstacle o = new Obstacle(pos, 3);
        obstacles.add(o);
        }
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(3, 8));
        points.add(new Point(4, 8));
        points.add(new Point(4, 9));
        Obstacle obs = new Obstacle(points);
        obstacles.add(obs);

        score = 0;
        state = State.READY;
    }

    /**
     * Returns a <em>copy</em> of the list representing the snake trail
     * (positions head to tail).
     */
    public List<Point> getSnakeTrail() {
        return (List<Point>) snake.getBody();
    }

    /**
     * Returns a <em>copy</em> of the list of apples.
     */
    public List<Apple> getApples() {
        return (List<Apple>) apples.clone();
    }

    /**
     * Returns a <em>copy</em> of the list of obstacles.
     */
    public List<Obstacle> getObstacles() {
        return (List<Obstacle>) obstacles.clone();
    }

    public Direction getDirection() {
        return snake.getDirection();
    }

    public State getState() {
        return state;
    }

    public long getScore() {
        return score;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Set the new direction for the snake. A direction anti parallel with the
     * current direction is ignored, to prevent the snake from turning back on
     * itself.
     *
     * @param direction The new direction
     */
    public void setDirection(Direction direction) {
        snake.setDirection(direction);
    }

    /**
     * Set the new state for this snake game: READY, RUNNING, PAUSED or LOSE
     *
     * @param newState The new state.
     */
    public void setState(State newState) {
        if (newState == state) {
            return;
        }

        if (state == State.PAUSED && newState == State.RUNNING) {
            state = State.RUNNING;
        } else if (state == State.READY && newState == State.RUNNING) {
            state = State.RUNNING;

        } else if (state == State.RUNNING && newState == State.GAME_OVER
                || newState == State.PAUSED) {
            state = newState;
        } else if (state != State.RUNNING && newState == State.READY) {
            initNewGame();
            state = State.READY;
        }
    }

    /**
     * Move this snake one step in the current direction. If an apple is found,
     * remove it and update the score, the add a new apple.
     */
    public void move() {
        if (state == State.RUNNING) {
            snake.move();
            Point newHead = snake.getHead();
            if (snakeIsOutsideBoard(newHead)) {
                state = State.GAME_OVER;
            }
            if (snake.collisionWithSelf()) {
                state = State.GAME_OVER;
            }
            for(Obstacle o : obstacles){
                if(o.intersects(newHead)){
                    state = State.GAME_OVER;
                }
            }
            // TODO: Check for collision between snakes head and 
            // obstacles (use obstacles method instersects). If so,
            // set state GAME_OVER.
            // ...
            for (Apple apple : apples) {
                if (apple.getPosition().equals(newHead)) {
                    apples.remove(apple);
                    snake.eat(apple);
                    addApple(); // add a new apple to the board
                    break;
                }
            }
        }
    }

    /**
     * Add a new apple in a randomized position.
     */
    private void addApple() {
        Point pos = null;
        List<Point> segments = snake.getBody();
        List<Point> obs = new LinkedList<>();
        for(Obstacle o : obstacles){
            obs.addAll(o.getPoints());
        }
        // Randomize a new position, avoid the snake body
        do {
            pos = new Point(RANDOM.nextInt(boardWidth),
                    RANDOM.nextInt(boardHeight));
            // Make sure we do not select a point under the snake trail
        } while (segments.contains(pos) || segments.contains(obs));

        Random randomno = new Random();

        boolean powered = randomno.nextBoolean();

        Apple a = new Apple(pos, powered);
        apples.add(a);
    }

    /**
     * Return true if the head of the snake is outside the board, otherwise
     * return false.
     */
    private boolean snakeIsOutsideBoard(Point head) {
        if (head.getX() > this.boardWidth || head.getY() > this.boardHeight || head.getX() < 0 || head.getY() < 0) {
            return true;
        }
        return false; // unimplemented
    }
}

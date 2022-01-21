public class Enemy {
    private int health = 1;
    private int pathIndex = 0;
    private int moveSpeed = 1;

    Enemy(){

    }

    public void move(){
        pathIndex += moveSpeed;
    }

    public void damage(int damage){
        health -= damage;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public int getPathIndex(){
        return pathIndex;
    }
}

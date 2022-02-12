public class Tower implements BoardPiece{
    private int attackRadius = 2;
    private int attackSpeed = 4;
    private int attackCoolDown = 0;
    private int damage = 1;
    private CoordVector coord;

    Tower(int x, int y){
        coord = new CoordVector(x,y);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public char asChar() {
        return 'T';
    }

    @Override
    public String getImageDir() {
        return "Tower.png";
    }

    public int getAttackRadius(){
        return attackRadius;
    }

    public void reduceCoolDown(){
        attackCoolDown --;
    }

    public void reduceCoolDown(int reduction){
        attackCoolDown -= reduction;
    }

    public int attack(){
        if (attackCoolDown <= 0){
            attackCoolDown = attackSpeed;
            return damage;
        }
        return 0;
    }

    public CoordVector getCoord(){
        return coord;
    }

}

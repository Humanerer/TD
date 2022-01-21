public class Tower implements BoardPiece{
    private int attackRadius = 1;
    private int attackSpeed = 1;
    private int attackCoolDown = 0;
    private int damage = 1;

    @Override
    public void tick() {
        
    }

    @Override
    public char asChar() {
        return 'T';
    }

    public int getAttackRadius(){
        return attackRadius;
    }

    public int attack(){
        if (attackCoolDown <= 0){
            attackCoolDown = attackSpeed;
            return damage;
        }
        attackCoolDown--;
        return 0;
    }

}

public class MarbleNode {
    public char marble;
    private char friendMarble;
    private char enemyMarble;
    private boolean avail;
    
    public MarbleNode(){
        this.friendMarble = this.marble = '-';
        this.enemyMarble = ' ';
        this.avail = false;
    }

    public MarbleNode(char marble, char enemyMarble, boolean availability){
        this.friendMarble = this.marble = marble;
        this.enemyMarble = enemyMarble;
        this.avail = availability;
    }

    public char getFriendMarble(){
        return this.friendMarble;
    }

    public char getEnemyMarble(){
        return this.enemyMarble;
    }

    public boolean getAvailability(){
        return this.avail;
    }

    public MarbleNode copy(){
        return new MarbleNode(this.marble, this.enemyMarble, this.avail);
    }

    @Override
    public String toString() {
        return "Marble: " + this.marble + '\n' + 
        "Enemy Marble: " + this.enemyMarble + '\n' +
        "Availability: " + this.avail;
    }
}

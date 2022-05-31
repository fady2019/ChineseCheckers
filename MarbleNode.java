package ChineseCheckers;

public class MarbleNode {
    public char marble;
    public char enemyMarble;
    private boolean avail;
    
    public MarbleNode(){
        this.marble = '-';
        this.enemyMarble = ' ';
        this.avail = false;
    }

    public MarbleNode(char marble, char enemyMarble, boolean availability){
        this.marble = marble;
        this.enemyMarble = enemyMarble;
        this.avail = availability;
    }

    public boolean getAvailability(){
        return this.avail;
    }

    public MarbleNode copy(){
        return new MarbleNode(this.marble, this.enemyMarble, this.avail);
    }
}

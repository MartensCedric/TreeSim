package Tree.Leaf;

import javafx.scene.paint.Color;

/**
 * Created by Cedric Martens on 2016-11-26.
 */
public class LeafInfo {
    private int amount;
    private Color leafColor;

    public LeafInfo(Color leafColor, int amount)
    {
        this.amount = amount;
        this.leafColor = leafColor;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(Color leafColor) {
        this.leafColor = leafColor;
    }

    public LeafInfo clone()
    {
        return new LeafInfo(leafColor, amount);
    }
}

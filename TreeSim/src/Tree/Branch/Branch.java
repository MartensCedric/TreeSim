package Tree.Branch;

import Tree.Leaf.LeafInfo;
import javafx.scene.paint.Color;


/**
 * Created by Cedric Martens on 2016-11-26.
 */
public class Branch {

    private BranchPosition branchPosition;
    private BranchInfo branchInfo;
    private LeafInfo leafInfo;
    private int maxAge;


    public Branch(BranchPosition branchPosition, BranchInfo branchInfo, LeafInfo leafInfo, int maxAge)
    {
        this.maxAge = maxAge;
        this.branchPosition = branchPosition;
        this.branchInfo = branchInfo;
        this.leafInfo = leafInfo;
    }

    public BranchPosition getBranchPosition() {
        return branchPosition;
    }

    public void setBranchPosition(BranchPosition branchPosition) {
        this.branchPosition = branchPosition;
    }


    public int getGen() {
        return branchInfo.getBranchGen();
    }

    public BranchInfo getBranchInfo() {
        return branchInfo;
    }

    public void setBranchInfo(BranchInfo branchInfo) {
        this.branchInfo = branchInfo;
    }

    public LeafInfo getLeafInfo() {
        return leafInfo;
    }

    public void setLeafInfo(LeafInfo leafInfo) {
        this.leafInfo = leafInfo;
    }

    public void nextGen()
    {
        branchInfo.nextGen();
    }

    public boolean isLeafGen()
    {
        return getGen() > maxAge - leafInfo.getAmount();
    }

    public boolean isDead()
    {
        return getGen() > maxAge;
    }

    public Color branchColor()
    {
        return branchInfo.getBranchColor();
    }

    public Color leafColor()
    {
        return leafInfo.getLeafColor();
    }

    public int getMaxAge() {
        return maxAge;
    }
}

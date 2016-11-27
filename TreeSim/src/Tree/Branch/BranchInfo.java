package Tree.Branch;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Cedric Martens on 2016-11-26.
 */
public class BranchInfo {
    private int branchLength;
    private int branchThickness;
    private int branchGen;
    private Color branchColor;


    public BranchInfo(int branchLength, int branchThickness, Color branchColor) {
        this(branchLength, branchThickness, branchColor, 0);
    }

    public BranchInfo(int branchLength, int branchThickness, Color branchColor, int age)
    {
        this.branchLength = branchLength;
        this.branchThickness = branchThickness;
        this.branchColor = branchColor;
        this.branchGen = age;
    }

    public int getBranchLength() {
        return branchLength;
    }

    public void setBranchLength(int branchLength) {
        this.branchLength = branchLength;
    }

    public int getBranchThickness() {
        return branchThickness;
    }

    public void setBranchThickness(int branchThickness) {
        this.branchThickness = branchThickness;
    }

    public Color getBranchColor() {
        return branchColor;
    }

    public void setBranchColor(Color branchColor) {
        this.branchColor = branchColor;
    }

    public int getBranchGen() {
        return branchGen;
    }

    public void setBranchGen(int branchGen) {
        this.branchGen = branchGen;
    }

    public void nextGen()
    {
        Random random = new Random();
        branchGen++;
        branchLength *= random.nextFloat()/20 + 0.9;
        branchThickness *= random.nextFloat()/20 + 0.9;
    }

    public BranchInfo clone()
    {
        return new BranchInfo(branchLength, branchThickness, branchColor, branchGen);
    }
}

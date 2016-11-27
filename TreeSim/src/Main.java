import Tree.Branch.Branch;
import Tree.Branch.BranchInfo;
import Tree.Branch.BranchPosition;
import Tree.Leaf.LeafInfo;
import Utils.TreeMath;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.util.Random;

public class Main extends Application {

    private final int CANVAS_WIDTH = 800;
    private final int CANVAS_HEIGHT = 800;
    private final int GUI_HEIGHT = 100;
    private final Point DEFAULT_POINT = new Point(CANVAS_WIDTH/2, CANVAS_HEIGHT);

    private static GraphicsContext graphics;
    private static Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("TreeSim");

        Group root = getInitializedRoot();

        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphics = canvas.getGraphicsContext2D();

        clearCanvas();

        root.getChildren().add(canvas);

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("treeSim.png")));
        primaryStage.setScene(new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + GUI_HEIGHT, Color.GREY));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void generateBranch(Branch bd)
    {
        Color color = bd.isLeafGen() ?  bd.leafColor() : bd.branchColor();

        int thickness = bd.getBranchInfo().getBranchThickness();

        Point point1 = bd.getBranchPosition().getStartingPoint();
        Point point2 = bd.getBranchPosition().getEndingPoint();

        graphics.setStroke(color);
        graphics.setLineWidth(thickness);
        graphics.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());

        bd.nextGen();

        if(bd.isDead())
            return;

        int branchLength = bd.getBranchInfo().getBranchLength();

        TreeMath treeMath = new TreeMath(point2, branchLength);

        Random random = new Random();
        BranchPosition childrenPosition1 = new BranchPosition(point2, treeMath.endPoint(Math.PI + (random.nextFloat()*Math.PI)));
        BranchInfo branchInfo = bd.getBranchInfo().clone();
        LeafInfo leafInfo = bd.getLeafInfo().clone();

        generateBranch(new Branch(childrenPosition1, branchInfo, leafInfo, bd.getMaxAge()));

        branchInfo = bd.getBranchInfo().clone();
        leafInfo = bd.getLeafInfo().clone();

        BranchPosition childrenPosition2 = new BranchPosition(point2, treeMath.endPoint(Math.PI + (random.nextFloat()*Math.PI)));
        generateBranch(new Branch(childrenPosition2, branchInfo, leafInfo, bd.getMaxAge()));
    }

    private Group getInitializedRoot()
    {
        Group root = new Group();

        Button btnGenerate = new Button("Generate");
        Label lblBranchLength = new Label("Branch length : ");
        Label lblBranchThickness = new Label("Branch thickness : ");
        Label lblBranchGen = new Label("Amount of generations : ");
        Label lblAmountOfLeaves = new Label("Amount of leaves : ");
        Label lblBranchColor = new Label("Branch color : ");
        Label lblLeavesColor = new Label("Leaves color : ");
        Slider sliBranchLength = new Slider();
        Slider sliBranchThickness = new Slider();
        Slider sliBranchGenerations = new Slider();
        Slider sliAmountOfLeaves = new Slider();
        ColorPicker cpBranchColor = new ColorPicker();
        ColorPicker cpLeavesColor = new ColorPicker();

        cpBranchColor.setValue(Color.rgb(95, 28, 0));
        cpLeavesColor.setValue(Color.GREEN);

        btnGenerate.setLayoutX(CANVAS_WIDTH - CANVAS_WIDTH / 8);
        btnGenerate.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 2);

        sliBranchLength.setLayoutX(CANVAS_WIDTH / 7);
        sliBranchLength.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 5);
        sliBranchLength.setMaxWidth(500);
        sliBranchLength.setMin(5);
        sliBranchLength.setMax(100);
        sliBranchLength.setValue(40);

        sliBranchThickness.setLayoutX(CANVAS_WIDTH / 2.2);
        sliBranchThickness.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 5);
        sliBranchThickness.setMaxWidth(500);
        sliBranchThickness.setMin(1);
        sliBranchThickness.setMax(25);
        sliBranchThickness.setValue(10);

        sliBranchGenerations.setLayoutX(CANVAS_WIDTH / 1.65);
        sliBranchGenerations.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 1.85);
        sliBranchGenerations.setMaxWidth(50);
        sliBranchGenerations.setMin(3);
        sliBranchGenerations.setMax(20);
        sliBranchGenerations.setValue(10);

        sliAmountOfLeaves.setLayoutX(CANVAS_WIDTH - CANVAS_WIDTH / 5);
        sliAmountOfLeaves.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 5);
        sliAmountOfLeaves.setMaxWidth(50);
        sliAmountOfLeaves.setMin(0);
        sliAmountOfLeaves.setMax(1);
        sliAmountOfLeaves.setValue(0.15);

        lblBranchLength.setLayoutX(CANVAS_WIDTH / 72);
        lblBranchLength.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 5);

        lblBranchThickness.setLayoutX(CANVAS_WIDTH/3);
        lblBranchThickness.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 5);

        cpBranchColor.setLayoutX(CANVAS_WIDTH / 7);
        cpBranchColor.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 2);
        cpBranchColor.setMaxWidth(50);

        cpLeavesColor.setLayoutX(CANVAS_WIDTH / 2.75);
        cpLeavesColor.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 2);
        cpLeavesColor.setMaxWidth(50);

        lblBranchColor.setLayoutX(CANVAS_WIDTH / 72);
        lblBranchColor.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 1.85);

        lblLeavesColor.setLayoutX(CANVAS_WIDTH / 4);
        lblLeavesColor.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 1.85);

        lblBranchGen.setLayoutX(CANVAS_WIDTH / 2.25);
        lblBranchGen.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 1.85);

        lblAmountOfLeaves.setLayoutX(CANVAS_WIDTH - CANVAS_WIDTH / 3);
        lblAmountOfLeaves.setLayoutY(CANVAS_HEIGHT + GUI_HEIGHT / 5);

        btnGenerate.setOnAction(x ->
        {
            BranchInfo branchInfo = new BranchInfo((int)sliBranchLength.getValue(), (int)sliBranchThickness.getValue(), cpBranchColor.getValue());
            int branchGens = (int) sliBranchGenerations.getValue();
            int leafGens = (int) ((sliAmountOfLeaves.getValue() * branchGens));
            LeafInfo leafInfo = new LeafInfo(cpLeavesColor.getValue(), leafGens);

            BranchPosition rootBranch = new BranchPosition(DEFAULT_POINT, new Point((int)DEFAULT_POINT.getX(), (int)DEFAULT_POINT.getY() - branchInfo.getBranchLength()));

            clearCanvas();
            generateBranch(new Branch(rootBranch, branchInfo, leafInfo, branchGens));
        });

        root.getChildren().add(btnGenerate);
        root.getChildren().add(sliBranchLength);
        root.getChildren().add(sliBranchThickness);
        root.getChildren().add(lblBranchLength);
        root.getChildren().add(cpBranchColor);
        root.getChildren().add(cpLeavesColor);
        root.getChildren().add(lblBranchColor);
        root.getChildren().add(lblLeavesColor);
        root.getChildren().add(lblBranchThickness);
        root.getChildren().add(sliBranchGenerations);
        root.getChildren().add(lblBranchGen);
        root.getChildren().add(lblAmountOfLeaves);
        root.getChildren().add(sliAmountOfLeaves);

        return root;
    }

    private void clearCanvas()
    {
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(3);
        graphics.strokeLine(0,CANVAS_HEIGHT, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}

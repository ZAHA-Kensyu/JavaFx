package com.example.stonepickinggame;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class StoneController {

    private Stone main;

    public void setMain(Stone main) {
        this.main = main;
    }

    //表示
    @FXML
    private Pane circlePane;

    //円のリスト
    private List<Circle> circles = new ArrayList<>();
    @FXML
    Label listNum;

    private UserRegisterController userRegisterController;

    @FXML
    private TextField removeStoneNum;

    @FXML
    private Button removeButton;

    @FXML
    private Button turnButton;

    @FXML
    Label removeLabel;
    @FXML
    Label errorRemoveLabel;

    public void setUserRegisterController(UserRegisterController userRegisterController) {
        this.userRegisterController = userRegisterController;
    }


    @FXML
    TextArea startBackgroundText;

    @FXML
    Button startButton;

    //スタートボタンが押されたらの処理
    @FXML
    private void GameInit() {
        StartItemRemove();
        Generation();//石生成
        updatePlayerTurn();//最初のターン表示
        maxRemoveStone =  (int)userRegisterController.getStoneRemove().getValue();
        removeLabel.setText("1~"+maxRemoveStone+"を入力してください。");

    }

    private void StartItemRemove() {
        Pane parent1 = (Pane) startBackgroundText.getParent();

        parent1.getChildren().remove(startBackgroundText);
        parent1.getChildren().remove(startButton);
    }

    public void Generation() {
        System.out.println(userRegisterController);
        for (int i = 0; i < userRegisterController.getStoneMax().getValue(); i++) {
            addCircle(i);
        }
    }


    int count = 0;

    public void addCircle(int i){
        Circle circle = new Circle(10); // 半径

        int x = i % 20;
        circle.setLayoutX(((x + 1) * 20)); //

        if(i % 20 == 0){
            count++;
            System.out.println(count);
        }
        int y  = 20 + (count * 20);
        circle.setLayoutY(y);

        circlePane.getChildren().add(circle); // Paneに追加
        circles.add(circle);//リストに追加
    }

    //勝者を格納する
    @FXML
    Pane victoryOrDefeatPane;
    int winner = 0;
    int loser = 0;
    boolean isGame = true;
    boolean rangRemoveFlg = true;

    int maxRemoveStone = 0;


    public void Remove() {
        errorRemoveLabel.setOpacity(0);
        if(Integer.parseInt(removeStoneNum.getText()) > 0 && Integer.parseInt(removeStoneNum.getText()) <= maxRemoveStone){
            rangRemoveFlg = true;
        }else{
            rangRemoveFlg = false;
        }

        //削除できる範囲か見る
        if (rangRemoveFlg) {
            for (int i = 0; i < Integer.parseInt(removeStoneNum.getText()); i++) {
                removeCircle();
            }

            if (circles.size() < 1) {
                isGame = false;
            }

            //ゲームが続いているか?
            if (isGame) {
                TurnChange();
            } else {
                //勝敗決める
                victoryOrDefeat();
                //勝者表示
                drawVictoryOrDefeat();
            }
        }else{
            errorRemoveLabel.setOpacity(1);
            errorRemoveLabel.setText("1~"+maxRemoveStone+"を入力してください");
            System.out.println("1 ~ "+ maxRemoveStone+"を入力してください");
        }

    }
    void drawVictoryOrDefeat(){
        TextArea textarea = new TextArea();
        Label winnerlabel = new Label();
        Label loserlabel = new Label();
        Font winnerFont= new Font(36);
        Font loserFont= new Font(36);

        winnerlabel.setFont(winnerFont);
        winnerlabel.setPrefSize(300,50);
        winnerlabel.setLayoutX(260);
        winnerlabel.setLayoutY(150);

        loserlabel.setFont(loserFont);
        loserlabel.setPrefSize(300,50);
        loserlabel.setLayoutX(260);
        loserlabel.setLayoutY(200);

        winnerlabel.setText("勝者 " + userRegisterController.getUserNames()[winner]);
        loserlabel.setText("敗者 "+ userRegisterController.getUserNames()[loser]);
        textarea.setPrefSize(600,360);

        victoryOrDefeatPane.getChildren().add(textarea);
        victoryOrDefeatPane.getChildren().add(winnerlabel);
        victoryOrDefeatPane.getChildren().add(loserlabel);
    }

    void victoryOrDefeat(){
        //敗北者
        loser = turnCurrent;

        //勝者
        //0になったら
        int tmp = loser - 1;
        //マイナス符号になったら
        if( tmp < 0){
            winner = userRegisterController.getUserNames().length - 1;
        }else{
            winner = tmp;
        }
    }

    //サークル削除
    public void removeCircle() {
        if (circles.size() > 0) { // リストに要素が1つ以上存在する場合
            Circle lastCircle = circles.remove(circles.size() - 1); // リストから最後に追加されたCircleを取得し、リストから削除
            circlePane.getChildren().remove(lastCircle); // Paneから最後に追加されたCircleを削除
        }
    }

    //サークル生成


    @FXML
    TextField turnText;
    int turnCurrent = 0;
    //Turn表示しようかなと思います。
    public void TurnChange(){
        turnCurrent++;

        if(turnCurrent >= userRegisterController.userNames.length){
            turnCurrent = 0;
        }

        updatePlayerTurn();
    }

    private void updatePlayerTurn(){
        turnText.setText(userRegisterController.getUserNames()[turnCurrent]);
    }
}

//    @FXML
//    private ListView<String> listView;
//
//    public void listUserName(){
//        listView.getItems().clear();//表示クリアしてから
//        listView.getItems().addAll(userNames);//リスト表示に追加
//    }

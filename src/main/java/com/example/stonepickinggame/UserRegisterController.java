package com.example.stonepickinggame;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserRegisterController {
    private Stone main;
    @FXML
    private TextField userName;

    String[] userNames;

    @FXML
    private TextField stoneMax;

    @FXML
    Slider stoneMaxSlider;

    @FXML
    Label maxRangeLabel;

    @FXML
    Slider stoneGetSlider;

    @FXML
    Label getRangeLabel;

    public Slider getStoneMax(){
        return stoneMaxSlider;
    }

    public Slider getStoneRemove(){
        return stoneGetSlider;
    }

    public void setMain(Stone main) {
        this.main = main;
    }

    public void initialize(){
    }

    public String[] getUserNames() {
        return userNames;
    }
    //ユーザーネーム登録処理
    public void UserNameRegister() throws IOException {
        String str = userName.getText();//UIで入力された文字列を受け取る。
        userNames = str.split(",");//カンマ区切りの文字列を、配列に格納する

        main.LoadSetting(this);//シーンチェンジ
    }
}

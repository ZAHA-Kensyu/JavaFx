package com.example.stonepickinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Stone extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        LoadUserRegister();
        stage.show();
    }

    //ユーザー登録画面
    private void LoadUserRegister() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserRegister.fxml"));
        Parent root = loader.load();
        UserRegisterController userRegisterController = loader.getController();
        userRegisterController.setMain(this);
        stage.setTitle("");
        stage.setScene(new Scene((root)));
    }

    public void LoadSetting(UserRegisterController userRegisterController) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stone.fxml"));
        Parent root = loader.load();
        StoneController stoneController = loader.getController();
        stoneController.setMain(this);
        stoneController.setUserRegisterController(userRegisterController);
        stage.setTitle("");
        stage.setScene(new Scene((root)));
    }


    //ゲーム設定画面

    //ゲームメイン

    public static void main(String[] args){
        launch(args);
    }
}
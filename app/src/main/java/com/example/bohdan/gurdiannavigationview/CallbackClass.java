package com.example.bohdan.gurdiannavigationview;

import java.util.List;

public class CallbackClass {

    interface Callback {

        Model callingBack(String string) throws InterruptedException;

        void callingBackSecondFr(String s);
        void callingBackButton();

    }

    public Callback callback;

    public void registerCallBack(Callback callback){
        this.callback = callback;
    }

    void loadObjectSecondFr(String s){
        callback.callingBackSecondFr(s);
    }

    Model loadObject(String string) throws InterruptedException {
        return callback.callingBack(string);
    }
    void buttonBack(){
        callback.callingBackButton();
    }

}


package com.ant.webview.mainprocess;

import android.os.RemoteException;
import android.util.Log;

import com.ant.webview.Command;
import com.ant.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.ant.webview.IWebviewProcessToMainProcessInterface;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandsManager extends IWebviewProcessToMainProcessInterface.Stub {
    private static final String TAG = "MainProcessCommandsMana";

    private static MainProcessCommandsManager sInstance;
    private static HashMap<String, Command> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                if (sInstance == null) {
                    sInstance = new MainProcessCommandsManager();
                }
            }
        }
        return sInstance;
    }

    private MainProcessCommandsManager() {
        ServiceLoader<Command> serviceLoader= ServiceLoader.load(Command.class);
        for (Command command:serviceLoader){
            if (!mCommands.containsKey(command.name())){
                mCommands.put(command.name(),command);
            }
        }
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainProcessToWebViewProcessInterface callback) throws RemoteException {
        if (mCommands.get(commandName)!=null){
            mCommands.get(commandName).execute(new Gson().fromJson(jsonParams, Map.class),callback);
        }else {
            Log.e(TAG, "handleWebCommand: commandName does not exist " );
        }
    }
}
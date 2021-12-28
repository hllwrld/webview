package com.hllwrld.webview.mainprocess

import com.google.gson.Gson
import com.hllwrld.common.Command
import com.hllwrld.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.hllwrld.common.IWebviewProcessToMainProcessInterface
import java.util.*


object MainProcessCommandsManager :IWebviewProcessToMainProcessInterface.Stub() {

    private val mCommands: HashMap<String, Command> = HashMap<String, Command>()


    init {
        val serviceLoader = ServiceLoader.load(Command::class.java)
        for (command in serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands[command.name()] = command
            }
        }
    }


    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        mCommands[commandName]?.execute(Gson().fromJson(jsonParams, MutableMap::class.java), callback)
    }
}

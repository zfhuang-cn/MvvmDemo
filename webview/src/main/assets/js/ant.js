var ant={};
ant.os={};
ant.os.isIOS= /iPhone|iPad|iPod|iOS|i.test(navigator.userAgent);
ant.os.isAndroid= /Android/i.test(navigator.userAgent);
ant.callbacks={}

ant.callback = function (callbackName response){
    var callbackObject = ant.callbacks[callbackName];
    if(callbackObject !== undefined){
        var ret = callbackObject(response);
        if(ret === false){
            return;
        }
        delete ant.callbacks[callbackName];
    }
}

ant.takeNativeAction = function(commandName, parameters){
    console.log("ant takeNativeAction");
    var request={};
    request.name=commandName;
    request.param=parameters;
    if(window.ant.os.isAndroid){
        window.antwebview.takeNativeAction(JSON.stringify(request));
    }else{
        window.webkit.messageHandlers.antwebview.postMessage(JSON.stringify(request));
    }
}

ant.takeNativeActionWithCallback=function(commandName, parameters,callback){
    var callbackName="js_callback_"+((new Date()).getTime()+"_"+Math.floor(Math.random()*1000));
    ant.callbacks[callbackName]=callback;

    var request = {};
    request.name = commandName;
    request.param = parameters;
    request.param.callbackName = callbackName;
    if(window.ant.os.isAndroid){
        window.antwebview.takeNativeAction(JSON.stringify(request));
    }else{
        window.webkit.messageHandlers.antwebview.postMessage(JSON.stringify(request));
    }
}

window.ant = ant;
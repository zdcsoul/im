var websocket;
$(document).ready(function(){
	
	$('#send').click(function(){
        sendMessage();
	});
	
	$('#send').keypress(function(e){
		if(e.which == 13) {
            sendMessage();
		}
	});
	
   	var i = 0;
	function add_message(username,msg,sendTime) {
		i = i + 1;
		var  inner = $('#chat-messages-inner');
		var id = 'msg-'+i;
		inner.append('<p id="'+id+'">'
										+'<span class="msg-block"><strong>'+username+'</strong><span class="time">'+sendTime+'</span>'
										+'<span class="msg">'+msg+'</span></span></p>');
		$('#'+id).hide().fadeIn(800);
		$('#chat-messages').animate({ scrollTop: inner.height() },0);
	}

    var host = window.location.host;
    if ('WebSocket' in window) {
        websocket = new ReconnectingWebSocket("ws://"
            + host + "/webSocketIMServer", null, {debug:true, maxReconnectAttempts:4});
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + host
            + "/webSocketIMServer");
    } else {
        websocket = new SockJS("http://" + host
            + "/webSocketIMServer");
    }
    websocket.onopen = function(evnt) {
        console.log("websocket连接上");
    };
    websocket.onmessage = function(evnt) {
        messageHandler(evnt.data);
    };
    websocket.onerror = function(evnt) {
        console.log("websocket错误");
    };
    websocket.onclose = function(evnt) {
        console.log("websocket关闭");
    }
    function messageHandler(data){
        var obj = $.parseJSON(data);
        if(obj.type == "target"){
            $("#line-status").html(obj.content);
        }else if(obj.type == "send"){
            add_message(obj.fromUser.username,obj.content,obj.sendTime);
        }
    }
    function sendMessage(){
        var message = {};
        var msg = $("#msg-box").val();
        message.content = msg;
        message.type = "send";
        message.fromUser = user;
        message.targetUser = targetUser;
        websocket.send(JSON.stringify(message));
    }
});
var goFrend = function (frendId,frendName,sendTime) {
    var msg = {
        type:"target",
        fromUser:user,
        targetUser:{userId:frendId,username:frendName},
        content:""
    };
    websocket.send(JSON.stringify(msg));
    targetUser = {userId:frendId,username:frendName};
}

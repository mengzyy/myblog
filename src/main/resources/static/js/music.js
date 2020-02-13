var musicNode = document.getElementsByClassName('aud')[0];
var  musicsrc =[
    'http://up_mp4.t57.cn/2016/1/12m/09/205091041376.m4a'
    ,'http://i.y.qq.com/rsc/fcgi-bin/fcg_pyq_play.fcg?songid=&songmid=003NRZIf12Rbsy&songtype=1&fromtag=50&uin=3233793689&code=405BF'
    ,'http://up_mp4.t57.cn/2018/1/03m/13/396131213056.m4a'
];
musicNode.src = musicsrc[0];
musicNode.onended =function(){//音乐播放下一曲
    var nextint = Math.random()*2;
    musicNode.src = musicsrc[nextint];
    musicNode.load();
    musicNode.play();

};
document.getElementsByClassName('music1')[0].onclick =function(){
        musicNode.src = musicsrc[0];
        musicNode.load();
        musicNode.play();
};
document.getElementsByClassName('music2')[0].onclick =function(){
    musicNode.src = musicsrc[1];
    musicNode.load();
    musicNode.play();
};
document.getElementsByClassName('music3')[0].onclick =function(){
    musicNode.src = musicsrc[2];
    musicNode.load();
    musicNode.play();
};


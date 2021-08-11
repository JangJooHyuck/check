function findWord(){

    //word 에 html안에서 id가 word 인 것이 가지고있는 값을 넣어준다 (dictionary.html 에 있음)
    var word = document.getElementById("word").value;

    // /dictionary?word= + word 값으로 이동
    window.location.href = "/dictionary?word=" + word;
}
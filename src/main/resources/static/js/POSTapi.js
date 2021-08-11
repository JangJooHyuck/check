async function ajaxPostAPI(){

        // html에서 email 값을 가져와서 getemail 에 넣기
        var getemail = document.getElementById("email").value;

        // html 에서 title 안에 있는 값을 가져와 title 에 넣기
        // header 에 <title></title> 안에있음
        var title = $("#title").text();


        //spinner 에 있는 display 값을 block으로 해준다.
        $("#spinner").css("display", "block");

        //ajax 통신
        $.ajax({
            //전송방식
            type: "POST",

            // 비동기처리
            async: true,

            // 호출 url
            url: "http://localhost:1233/api/post",

            contentType: "application/json",

            //전송 파라미터
            data: JSON.stringify({
                id : null,
                createdDate : null,
                modifiedDate : null,
                mail : getemail,
                userip : title,
                result : null
            })
            // 호출한 페이지의 return 형식
            ,dataType: "json" //

            //통신에 성공하였을 때
            ,success:function(data){

                //data 출력
               console.log(data);

               //만약 date의 result 값이 success 이면
               if(data.result == "success"){
                    document.getElementById("emailprint").innerText = "Entered Email:" + data.mail;
                    document.getElementById("result").innerText = data.result;

                 //아니면
                 }else{
                    document.getElementById("emailprint").innerText =  "Entered Email:" + data.mail;
                    document.getElementById("result").innerText = data.result;
                 }
                 //스피너 없애기
                 $("#spinner").css("display", "none");

                 }


       });
}

async function ajaxPostDic(){

   // html 에서 값을 getword에 저장함
   var getword = document.getElementById("word").value;
   var title = $("#title").text();

   //제이쿼리. spinner 에 있는 display 값을 block으로 해준다.

   $("#spinner").css("display", "block");

   //ajax 통신
   $.ajax({
       type: "POST",
       async: "async",
       url: "http://localhost:1233/api/postDic",
       contentType: "application/json",
       data: JSON.stringify({
           word : getword,
           content : ""
       })
       ,dataType: "json"
       ,success:function(data){
            console.log(data);

          document.getElementById("wordprint").innerText = data.word;
          document.getElementById("wordprint2").innerText = data.content;

            $("#spinner").css("display", "none");

            }


  });
}


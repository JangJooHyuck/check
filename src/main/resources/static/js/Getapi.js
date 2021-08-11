
//사용자가 입력한 이메일 출력
function ajaxGetAPI() {

     // email 에 id=email 안에 들어있는 값을 넣어줌
     var getemail = document.getElementById("email").value;
     var title = $("#title").text();

     // spinner 에 있는 display 값을 block으로 해준다.
     $("#spinner").css("display", "block");

     $.ajax({
          //전송방식
          type: "GET",

          // 비동기처리
          async: "async",

          //호출 url
          url: "http://localhost:1233/api/get?check=" + getemail + "&userIP=" + title,

          contentType: "application/json"

          // return 형식 (json)
          ,dataType: "json"

          // success = 통신에 성공
          ,success:function(data){

               //데이터 출력
               console.log(data);

               //만약 data 의 result 가 success 라면
               if(data.result == "success"){
                    document.getElementById("emailprint").innerText = "Entered Email:" + data.mail;
                    document.getElementById("result").innerText = data.result;

               //아니면
               }else{

                    document.getElementById("emailprint").innerText =  "Entered Email:" + data.mail;
                    document.getElementById("result").innerText = data.result;
               }
               //spinner 없애기
               $("#spinner").css("display", "none");

               }


               //통신 실패시 콘솔에 에러 출력
               ,error:function(data){
                    console.log("error");
                    $("#spinner").css("display", "none");
               }
     });

    }





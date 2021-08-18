var updateWord;
var deleteWord;

$(".updateBtn").click(function(){
    var checkBtn = $(this);
    var tr = checkBtn.parent().parent();
    var td = tr.children();
    updateWord = td.eq(1).text();
    console.log("update " + updateWord);
    $("#updateModal").modal("show");
    document.getElementById("editedWord").innerText = "내용을 수정할 단어 : " + updateWord;
})

$(".deleteBtn").click(function(){
    var checkBtn = $(this);
    var tr = checkBtn.parent().parent();
    var td = tr.children();
    deleteWord = td.eq(1).text();
    console.log("delete " + deleteWord);
    $("#deleteModal").modal("show");
    document.getElementById("deleteWord").innerText = "삭제하실 단어 : " + deleteWord;
})

function saveWord(){
    var updateContent = document.getElementById("editedContent").value;
    $.ajax({
        type : "POST",
        async : "async",
        url : "http://localhost:9000/api/updateword",
        contentType: "application/json",
        data : JSON.stringify({
            id : null,
            word : updateWord,
            content : updateContent
        }),
        dataType: "json",
        success:function(data){
            closeModal();
        }
        ,error:function(data){
            closeModal();
        }
    });
}

function delWord(){
    $.ajax({
        type : "POST",
        async : "async",
        url : "http://localhost:9000/api/deleteword",
        contentType: "application/json",
        data : JSON.stringify({
            id : null,
            word : deleteWord,
            content : null
        }),
        dataType: "json",
        success:function(data){
            closeModal();
            window.location.href = "/dictionaryfav";
        }
        ,error:function(data){
            closeModal();
            window.location.href = "/dictionaryfav";
        }
    });
}

function closeModal(){
    $(".modal").modal("hide");
}
$(document).ready(init);

function init() {
    query();
    $('#btnAddData').click(addData);
    $('#hideFormAdd').click(closeaddData);
    $('#btnAdd').click(sendTo);
    $('#btnUpdate').click(updateTo);
    $('#hideUpdatedata').click(closeupdate);
    $('#sec').click(querybyname);
}
function query(){
    $.ajax({
        type:"GET",
        url:"/findAll",
        dataType: "json",
        success:function(data){
            $("#tb").html("");
            $.each(data,function(i,n){
                var myid = data[i].studentId;
                var str="<tr>";
                str+="<th>"+n.studentId+"</th>";
                str+="<th>"+n.name+"</th>";
                str+="<th>"+n.age+"</th>";
                str+="<th><button onclick='myupdate("+ myid +")' id='myupdate'>"+"修改" +"</button>"+
                    "<button onclick='mydelete("+ myid + ")' id='mydelete'>"+"删除"+
                    "</button></th>";
                $("#tb").append(str);
                // "+ myid + ","+ myname +","+ myage +"
            });
        }
    });
}

function querybyname(){
    var name = $('#secbox').val();
    var obj = {};
    obj.name = name;
    $.ajax({
        type:"GET",
        data:obj,
        url:"/findStudentByName",
        dataType: "json",
        success:function(data){
            data = JSON.parse(data.data);
            $("#tb").html("");
            $.each(data,function(i,n){
                var myid = data[i].studentId;
                // alert(n.name+"333");
                var str="<tr>";
                str+="<th>"+n.studentId+"</th>";
                str+="<th>"+n.name+"</th>";
                str+="<th>"+n.age+"</th>";
                str+="<th><button onclick='myupdate("+ myid +")' id='myupdate'>"+"修改" +"</button>"+
                    "<button onclick='mydelete("+ myid + ")' id='mydelete'>"+"删除"+
                    "</button></th>";
                $("#tb").append(str);
                // "+ myid + ","+ myname +","+ myage +"
            });
        }
    });
}


var update_id;
function myupdate(id) {
    update_id = id;
    $('#formUpdate').show();
    $('#mymask').show();
}
function updateTo() {
    var name = $('#updatename').val();
    var age = $('#updateage').val();
    var obj = new Object();
    obj.studentId = update_id;
    obj.name = name;
    obj.age = age;
    $.ajax({
        type:"put",
        data:obj,
        url:"/updateStudent",
        success:function() {
            closeupdate();
            query();
        }

    })
}



function closeupdate() {
    $('#formUpdate').hide();
    $('#mymask').hide();
}

function addData(studentId) {
    $('#formAdd').show();
    $('#mymask').show();
}
function sendTo() {
    // var studentId = $("#mystudentId").val();
    var name = $("#myname").val();
    var age = $("#myage").val();
    var obj = new Object();
    // obj.studentId = studentId;
    obj.name = name;
    obj.age = age;
    $.ajax({
        url:"/addStudent",
        type:"post",
        data:obj,
        success:function () {
            closeaddData();
            query();
        }
    })
}
function closeaddData() {
    $('#formAdd').hide();
    $('#mymask').hide();
}


function mydelete(studentId){
    $.ajax({
        url :"/deleteStudent",
        type:"POST",
        data :{
            "studentId":studentId
        },
        dataType:"json",
        success : saveSuccess
        // function () {
        //     query();
        // }
    });
}

function saveSuccess(){
    query();
}

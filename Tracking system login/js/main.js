var projectStatus = ["Receiving Samples", "Sample Preprocessing", "Sample Injection", "Post Analysis","Finished"];

function getClickedProject(){
  document.querySelector('tbody').addEventListener('click',function(e) {


    sessionStorage.setItem('projectId',e.target.parentElement.querySelector('#projectID').innerText);
    console.log(e.target.parentElement.querySelector('#projectID').innerText);
    window.location.href ="status.html";
  });

}


function saveProjectsData(projects) {
  var projectList = JSON.stringify(projects);
   console.log(JSON.parse(projectList));
  sessionStorage.setItem("projects", projectList);
}

function drawTable(projects) {
  projects.forEach(function(project) {

    $(".table_body").append('	<tr> <td class="column1">' + project.projectName + '</td>' +
      '<td id="projectID" hidden>' + project.projectId + '</td>' +
      '<td class="column2">' + project.samplesNumber + '</td>' +
      '<td class="column3">' + project.startDate + '</td>' +
      '<td class="column4">' + ((project.endDate) ? project.endDate : 'Not determined yet') + '</td>' +
      '<td class="column5">' + project.projectType + '</td>' +
      '<td class="column6">' + projectStatus[project.status - 1] + '</td></tr>');

  });
}

function loadTable(projects) {
  $(".limiter").empty();
  $(".limiter").load('./table.html', function(responseTxt, statusTxt, xhr) {
    if (statusTxt == "success") {
      drawTable(projects);
      getClickedProject();
      saveProjectsData(projects);
    }
    if (statusTxt == "error")
      swal("OOPs!", "Some Server Error Happened Please Try Later!", "error");
  });
}

function login(username, password) {
  $.ajax({
    type: "POST",
    headers: {
      "email": username,
      "pass": password
    },
    url: 'http://localhost:8080/doctor/login',
    contentType: 'application/json',
    success: function(data) {
      console.log(data);
      if (data.id !== -1) {
        sessionStorage.setItem('email', data.userEmail);
        sessionStorage.setItem('password', data.password);
        loadTable(data.projects);
      } else {
        swal("OOPs!", "Wrong Email or Password!", "error");
      }

    },
    fail: function(jqXhr, textStatus, errorThrown) {
      swal("OOPs!", "Some Server Error Happened Please Try Later!", "error");
    },
    error: function(jqXhr, textStatus, errorThrown) {
      swal("OOPs!", "Some Client Side Error Happened Please Try Later!", "error");
    }
  });
}



function redirectIfLogedIn() {
  if (sessionStorage.getItem('email')) {
    login(sessionStorage.getItem('email'), sessionStorage.getItem('password'))
  } else {
    console.log('no user loged in');
    $(".limiter").empty();
    $(".limiter").load('./login.html');
    //$(".wrap-login100").removeAttr("hidden");
  }
}

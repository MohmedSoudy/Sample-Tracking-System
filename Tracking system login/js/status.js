(function($) {
  "use strict";
  var projectId = sessionStorage.getItem('projectId');
 
  var projects = JSON.parse(sessionStorage.getItem('projects'));
  var project;
  projects.forEach(function(pro) {
 
    project = (pro.projectId === projectId ? pro : project);
    
  });
   
   console.log(project);
  $("#projectNameVal").append(project.projectName);
  $("#samplesNumber").append(project.samplesNumber);

  var activeStep = '<li class="active step0"></li>';
  var notActiveStep = '<li class="step0"></li>';
  var stepsnumber = 4;
  var index = 0;
  for (var i = index; i <= project.status; i++) {
    $("#progressbar").append(activeStep);
    index++;
  }
  for (var i = index; i <= stepsnumber; i++) {
    $("#progressbar").append(notActiveStep);
    index++;
  }

  // <div class="text-center mt-4">
  //       <a class="btn btn-info d-inline" href="undefined">Downlaod You Mother fucker</a></div>
  if(project.status == 4){
    let statusCard = document.querySelector('.card');
    statusCard.classList.add('expandCard');
    let df = document.createDocumentFragment();
    let downloadDiv = document.createElement('div');
    downloadDiv.classList.add('text-center');
    downloadDiv.classList.add('mt-4');
    let downloadLink  = document.createElement('a');
    downloadLink.classList.add('btn');
    downloadLink.classList.add('downloadLink');
    downloadLink.classList.add('d-inline');
    downloadLink.setAttribute('target',"_blank")
    downloadLink.textContent = 'Downlaod Project Data';
    downloadLink.setAttribute('href',project.projectData);
    downloadDiv.appendChild(downloadLink);
    df.appendChild(downloadDiv);
    statusCard.appendChild(df);
  }
})(jQuery);

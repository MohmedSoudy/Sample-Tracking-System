(function($) {
  "use strict";
  var projectId = sessionStorage.getItem('projectId');
  // console.log(projectId);
  var projects = JSON.parse(sessionStorage.getItem('projects'));
  var project;
  projects.forEach(function(pro) {
    // console.log(pro);
    // console.log(pro.projectId === projectId);
    project = (pro.projectId === projectId ? pro : project);
    //console.log(project);
  });
   // console.log(sessionStorage.getItem('projectId'));
   // console.log(projects);
   console.log(project);
  $("#projectNameVal").append(project.projectName);
  $("#samplesNumber").append(project.samplesNumber);

  var activeStep = '<li class="active step0"></li>';
  var notActiveStep = '<li class="step0"></li>';
  var stepsnumber = 3;
  var index = 0;
  for (var i = index; i <= project.status; i++) {
    $("#progressbar").append(activeStep);
    index++;
  }
  for (var i = index; i <= stepsnumber; i++) {
    $("#progressbar").append(notActiveStep);
    index++;
  }
})(jQuery);

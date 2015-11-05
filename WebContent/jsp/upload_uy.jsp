<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Daniel.uy - Online Code Demos</title>

    <!-- Bootstrap -->
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="/css/uploader/uploader.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/uploader/demo.css" rel="stylesheet" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body role="document">

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="demo-nav">
      <div class="container">
          <ul class="nav navbar-nav">
            <li><a href="http://www.daniel.com.uy/" class="home">daniel.uy</a></li>
            <li><a href="http://www.daniel.com.uy/en/about">About</a></li>
            <li><a href="http://blog.daniel.com.uy">Blog</a></li>
            <li><a href="http://www.daniel.com.uy/en/contact">Contact</a></li>
            <li class="active"><a href="http://danielm.herokuapp.com/demos/">Demos</a></li>
          </ul>
      </div>
    </div>

    <div class="container demo-wrapper">
      <div class="page-header">
        <h1>Demo <small>JQuery Drag and Drop Files</small></h1>
      </div>
    
      <div class="row demo-columns">
        <div class="col-md-6">
          <!-- D&D Zone-->
          <div id="drag-and-drop-zone" class="uploader">
            <div>Drag &amp; Drop Images Here</div>
            <div class="or">-or-</div>
            <div class="browser">
              <label>
                <span>Click to open the file Browser</span>
                <input type="file" name="file_upload[]" multiple="multiple" title='Click to add Files'>
              </label>
            </div>
          </div>
          <!-- /D&D Zone -->

          <!-- Debug box -->
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Debug</h3>
            </div>
            <div class="panel-body demo-panel-debug">
              <ul id="demo-debug">
              </ul>
            </div>
          </div>
          <!-- /Debug box -->
        </div>
        <!-- / Left column -->

        <div class="col-md-6">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Uploads</h3>
            </div>
            <div class="panel-body demo-panel-files" id='demo-files'>
              <span class="demo-note">No Files have been selected/droped yet...</span>
            </div>
          </div>
        </div>
        <!-- / Right column -->
      </div>

      <div class="alert alert-info">
        Read the Blog Article and Source code for this Example here: 
        <a href="#">http://blog.daniel.com.uy/2014/01/31/jquery-drag-and-drop-progress-files.html</a>
      </div>

      <div class="demo-footer">
        <p>&copy; Daniel Morales 2014</p>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script> -->

    <script type="text/javascript" src="/js/demo.min.js"></script>
    <script type="text/javascript" src="/js/dmuploader.min.js"></script>

    <script type="text/javascript">
      $('#drag-and-drop-zone').dmUploader({
        url: '/upload_service.do',
        dataType: 'json',
        allowedTypes: 'image/*',
        /*extFilter: 'jpg;png;gif',*/
        onInit: function(){
          $.danidemo.addLog('#demo-debug', 'default', 'Plugin initialized correctly');
        },
        onBeforeUpload: function(id){
          $.danidemo.addLog('#demo-debug', 'default', 'Starting the upload of #' + id);

          $.danidemo.updateFileStatus(id, 'default', 'Uploading...');
        },
        onNewFile: function(id, file){
          $.danidemo.addFile('#demo-files', id, file);
        },
        onComplete: function(){
          $.danidemo.addLog('#demo-debug', 'default', 'All pending tranfers completed');
        },
        onUploadProgress: function(id, percent){
          var percentStr = percent + '%';

          $.danidemo.updateFileProgress(id, percentStr);
        },
        onUploadSuccess: function(id, data){
          $.danidemo.addLog('#demo-debug', 'success', 'Upload of file #' + id + ' completed');

          $.danidemo.addLog('#demo-debug', 'info', 'Server Response for file #' + id + ': ' + JSON.stringify(data));

          $.danidemo.updateFileStatus(id, 'success', 'Upload Complete');

          $.danidemo.updateFileProgress(id, '100%');
        },
        onUploadError: function(id, message){
          $.danidemo.updateFileStatus(id, 'error', message);

          $.danidemo.addLog('#demo-debug', 'error', 'Failed to Upload file #' + id + ': ' + message);
        },
        onFileTypeError: function(file){
          $.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: must be an image');
        },
        onFileSizeError: function(file){
          $.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: size excess limit');
        },
        /*onFileExtError: function(file){
          $.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' has a Not Allowed Extension');
        },*/
        onFallbackMode: function(message){
          $.danidemo.addLog('#demo-debug', 'info', 'Browser not supported(do something else here!): ' + message);
        }
      });
    </script>

  </body>
</html>
    
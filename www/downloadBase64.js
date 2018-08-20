// Empty constructor
function DownloadBase64() {}

// The function that passes work along to native shells
// @param url        URL of the file from where it has to be downloaded
// @param folderName folderName, under which file has to be downloaded (under Download directory)
// @param fileName   Name of the file with which it has to be saved (filename.extension)
DownloadBase64.prototype.downloadFile = function(url, filePath, fileName, successCallback, errorCallback) {
  var options = {};
  options.url = url;
  options.filePath = filePath;
  options.fileName = fileName;
  cordova.exec(successCallback, errorCallback, 'DownloadBase64', 'downloadFile', [options]);
}

// Installation constructor that binds DownloadBase64 to window
DownloadBase64.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.downloadBase64 = new DownloadBase64();
  return window.plugins.downloadBase64;
};
cordova.addConstructor(DownloadBase64.install);

# Cordova-plugin-downloadBase64
An Android Cordova plugin to download large size(base64), image/pdf, file from internet.
 
## Installation

```
cordova plugin add https://github.com/Deejo/Cordova-plugin-downloadBase64
```

## Implementation

```javascript 
// Add the following 5 lines
window.plugins.downloadBase64.downloadFile(url, folderName, fileName, function() {
  console.log('Excelsior!');
}, function(err) {
  console.log('Uh oh... ' + err);
});
```

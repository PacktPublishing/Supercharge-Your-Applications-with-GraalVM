import firebase from 'firebase';

// Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  var firebaseConfig = {
    apiKey: "AIzaSyD9EF4m00iZYVTrDIj0Z0EYbiAlYZ1Biaw",
    authDomain: "bozo-book-library.firebaseapp.com",
    projectId: "bozo-book-library",
    storageBucket: "bozo-book-library.appspot.com",
    messagingSenderId: "330987868039",
    appId: "1:330987868039:web:ee290d7833d8f46b780d22",
    measurementId: "G-7X34ZG7VCE"
  };
  // Initialize Firebase
  const fire = firebase.initializeApp(firebaseConfig);
  
  export default fire;
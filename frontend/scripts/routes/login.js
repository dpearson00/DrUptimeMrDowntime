const { default: axios } = require("axios");
var session = require('express-session');

  /*
  Login route
  */
  const login = (req, res) => {
          res.render('login.ejs');
        //   axios.get(`http://lambdathings/${req.params.id}`)
  };

  /*
  Auth route (POST from /login)
  */
  const auth = (req, res) => {
    const daniel = "Daniel";
    const user = "Tommy";
    const pass = "test";
    if (req.body.username == user && req.body.password == pass) {
    req.session.regenerate(function(){
        req.session.user = user;
        req.session.success = 'Successfully authenticated as ' + user
        res.redirect('/dash');
      });
    } else {
      req.session.error = 'Authentication failed, please provide valid user details.';
    res.render('info.ejs', {title: `Failure!`, desc: `Your credentials are invalid!`});
    }
};

 /*
  Logout route
  */
  const logout = (req, res) => {
    req.session.destroy(function(){
        res.render('info.ejs', {title: `Successfully logged out!`, desc: `You will need to log in again to access your dashboard!`});
    });
  //   axios.get(`http://lambdathings/${req.params.id}`)
};

  module.exports = { login, auth, logout };
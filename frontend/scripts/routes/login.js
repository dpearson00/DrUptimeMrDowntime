const { default: axios } = require("axios");
var session = require("express-session");

/*
  Login route
*/
const login = (req, res) => {
  res.render("login.ejs");
};

/*
  Auth route (POST from /login)
*/
const auth = (req, res) => {
  axios
    .post(`${__apiLink}/users/auth`, {
      username: req.body.username,
      password: req.body.password,
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        req.session.regenerate(function () {
          req.session.user = req.body.username;
          req.session.success =
            "Successfully authenticated as " + req.body.username;
          res.redirect("/dash");
        });
      } else {
        req.session.error =
          "Authentication failed, please provide valid user details.";
        res.render("info.ejs", {
          title: `Failure!`,
          desc: `Your credentials are invalid!`,
        });
      }
    })
    .catch(function (error) {
      console.log(error);
    });
};

/*
  Logout route
*/
const logout = (req, res) => {
  req.session.destroy(function () {
    res.render("info.ejs", {
      title: `Successfully logged out!`,
      desc: `You will need to log in again to access your dashboard!`,
    });
  });
  //   axios.get(`http://lambdathings/${req.params.id}`)
};

module.exports = { login, auth, logout };

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
      email: req.body.email,
      password: req.body.password,
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        req.session.regenerate(function () {

          // TODO: user should use token and name should be the name of the person they are logged in as!
          req.session.userId = req.body.email;
          req.session.name = req.body.email;
          req.session.success =
            "Successfully authenticated as " + req.session.name;
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

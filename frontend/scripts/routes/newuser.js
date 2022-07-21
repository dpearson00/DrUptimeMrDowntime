const { default: axios } = require("axios");

/*
  View newuser route
*/
const newuser = (req, res) => {
  res.render("newuser.ejs");
};

/*
  POST from /newuser
*/
const postuser = (req, res) => {
  axios
    .post(`${__apiLink}/users/create`, {
      username: req.body.username,
      password: req.body.password,
      email: req.body.email,
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        req.session.regenerate(function () {
          req.session.user = req.body.username;
          req.session.success =
            "Successfully created and logged in as " + req.body.username;
          res.redirect("/dash");
        });
      } else {
        req.session.error =
          "Account creation failed, please provide valid user details.";
        res.render("info.ejs", {
          title: `Failure!`,
          desc: `An error occurred! Please contact the site administrator!`,
        });
      }
    })
    .catch(function (error) {
      console.log(error);
    });
};

module.exports = { newuser, postuser };
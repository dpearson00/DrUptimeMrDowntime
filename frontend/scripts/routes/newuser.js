const { default: axios } = require("axios");

/*
  View newuser route
*/
const newuser = (req, res) => {
  res.render("newuser.ejs");
};

/*
  POST from /postnewuser
*/
const postnewuser = (req, res) => {
  axios
    .post(`${__apiLink}/users/create`, {
      name: req.body.name,
      password: req.body.password,
      email: req.body.email,
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        req.session.regenerate(function () {
          // TODO: populate with userId
          req.session.userId = req.body.username;
          req.session.success =
            "Successfully created and logged in as " + req.session.name;
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

module.exports = { newuser, postnewuser };
const { default: axios } = require("axios");

/*
  View newuser route
*/
const newapp = (req, res) => {
  res.render("newapp.ejs", {name: req.session.name});
};

/*
  POST from /newapp
*/
const postnewapp = (req, res) => {
  axios
    .post(`${__apiLink}/apps/${req.session.userId}/create`, {
      name: req.body.name,
      description: req.body.description,
      url: req.body.url,
    })
    .then(function (response) {
      if (response.data[1] == "SUCCESS") {
        req.session.regenerate(function () {
          req.session.id = req.body.username;
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

module.exports = { newapp, postnewapp };
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
      name: req.body.title,
      description: req.body.description,
      url: req.body.url,
    })
    .then(function (response) {
      console.log(response.data);
      if (response.data[1] == "SUCCESS") {
          res.redirect("/dash");
        } else {
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
const { default: axios } = require("axios");
const { json } = require("express");
/*
  Dashboard route
*/
const dash = (req, res) => {
  axios
    .get(`${__apiLink}/users/${req.session.userId}/apps`)
    .then(function (response) {
      console.log(response.data);
      if (response.data.appNames.length == 0) {
        res.render("dash.ejs", {
          welcome: `Welcome to your dashboard!`,
          desc: `You have not created any applications yet!\n<a href="/dash/newapp"><button class="button">Enroll Application</button></a>`,
          bundles: [],
        });
      }
      var name = JSON.parse(JSON.stringify(response.data.appNames));
      var ids = JSON.parse(JSON.stringify(response.data.appIds));

      var count = Object.keys(ids).length;

      const bundles = new Array();

      for (var i = 0; i < count; i++) {
        bundles.push([name[i], ids[i]]);
      }

      res.render("dash.ejs", {
        welcome: `Welcome, ` + req.session.name + `!`,
        desc: `Services you monitor are below!`,
        bundles: bundles,
      });
    })
    .catch(function (error) {
      res.render("info.ejs", {
        title: `Error getting applications!`,
        desc: `The API may be down!`,
      });
    });
};

module.exports = { dash };

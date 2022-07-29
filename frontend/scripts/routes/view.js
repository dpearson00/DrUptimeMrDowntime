const { default: axios } = require("axios");

/*
  View route
*/
const view = (req, res) => {
  axios
    .get(`${__apiLink}/apps/${req.session.userId}/view/${req.params.id}`)
    .then(function (response) {
      console.log(response.data);
      if (res.statusCode === 200) {

        let issues = [];
        let oks = [];

        for (var i = 0; i < response.data.errorLogs.length; i++) {
          if (response.data.errorLogs[i][1] == "200") {
            oks.push(response.data.errorLogs[i][0]);
          } else {
            issues.push(response.data.errorLogs[i][0]);
          }
        }

        res.render("view.ejs", {
          name: response.data.application.name,
          url: response.data.application.appUrl,
          id: response.data.application.appId,
          issues: issues,
          oks: oks,
        });
      } else {
        res.render("info.ejs", {
          title: `Failure!`,
          desc: `You do not have permission to view ${req.params.id}!`,
        });
      }
    })
    .catch(function (error) {
      res.render("info.ejs", {
        title: `Failure!`,
        desc: `Service ${req.params.id} does not exist in this server's content datastore!`,
      });
    });
};

module.exports = { view };

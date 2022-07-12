const { default: axios } = require("axios");

  /*
  View route
  */
  const view = (req, res) => {
          res.render('info.ejs', {title: `Failure!`, desc: `Service ${req.params.id} does not exist in this server's content datastore!`});
        //   axios.get(`http://lambdathings/${req.params.id}`)
  };

  module.exports = { view };
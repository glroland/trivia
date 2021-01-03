const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api/gamemaster',
    createProxyMiddleware({
        target: process.env.REACT_APP_trivia_URL_gamemasterSVC || 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/api/gamemaster': '', // rewrite path
        },
      })
  );
};

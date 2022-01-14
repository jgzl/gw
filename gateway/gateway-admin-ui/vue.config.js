// eslint-disable-next-line @typescript-eslint/no-var-requires
const CompressionPlugin = require("compression-webpack-plugin")
console.log(process.env.NODE_ENV)
module.exports = {
  publicPath: process.env.NODE_ENV === 'development' ? '/' : "/",
  outputDir: "dist",
  assetsDir: "static",
  lintOnSave: true,
  productionSourceMap: false,
  devServer: {
    hot: true,
    port: 5567,
    open: false,
    https: false,
    proxy: {
      '/api': { //用/api代替服务端真实地址
        target: `http://localhost:8000`, //服务端真实地址
        changeOrigin: true, //运行跨域
        pathRewrite: {
          '^/api': '' //请求的时候，地址重写
        }
      }
    }
  },
  configureWebpack: {
    devtool: '#eval-source-map',
    module: {
      rules: [
        {
          test: /\.mjs$/,
          include: /node_modules/,
          type: 'javascript/auto'
        }
      ]
    }
  },
  chainWebpack(config) {
    if (process.env.NODE_ENV === 'production') {
      config.plugin('compressionPlugin')
        .use(new CompressionPlugin({
          filename: '[path].gz[query]',
          algorithm: 'gzip',
          test: /\.js$|\.html$|\.css/,
          threshold: 50240,
          deleteOriginalAssets: false
        }))
    }
  }
};

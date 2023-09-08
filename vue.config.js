const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  pages:{
    index:{
      entry: './src/main/vue/src/main.js'
    }
  },
  outputDir: "./src/main/resources/static",
  indexPath: "./static/index.html",
  devServer: {
    port: 9090,
    proxy: "http://localhost:8080"
  },
  transpileDependencies: true
})

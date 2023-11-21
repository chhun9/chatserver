const {defineConfig} = require('@vue/cli-service')
const path = require('path')
module.exports = defineConfig({
    pages: {
        index: {
            entry: './src/main/vue/src/main.js'
        }
    },
    outputDir: "./src/main/resources/static",
    indexPath: "./static/index.html",
    devServer: {
        port: 9090,
        proxy: 'http://localhost:8080'
    },
    transpileDependencies: true,
    configureWebpack: {
        resolve: {
            alias: {
                '@': path.join(__dirname, 'src/main/vue/src/')
            }
        }
    }
})

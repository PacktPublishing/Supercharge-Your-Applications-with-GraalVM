const express = require('express')
const app = express()

app.get('/plot', function (req, res) {
  var text = ""
  text += Polyglot.eval('R',
    `svg();
     require(lattice);
     data <- read.csv("heart.csv", header = TRUE)
     print(barchart(data$age~data$chol, main="Age vs Cholestral levels"))
     grDevices:::svg.off()
    `);
  res.send(text)

})

app.listen(3000, function () {
  console.log('Plot with R -  listening on port 3000!')
})




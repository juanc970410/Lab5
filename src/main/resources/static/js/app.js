/* global apimock */

var app = (function () {
  var _author;
  var _print;
  var update = function (author) {
      _author = author;
      apimock.getBlueprintsByAuthor(_author,function (lista){
          var blueprint = lista.map(function (k){
              return{
                  "name":k.name,"pointlen":k.points.length
              };
          });
          var b2 = blueprint.map(function (j){
                return "<tr> <td>"+j.name+"</td> <td>"+j.pointlen+"</td> <td><button type='button' onclick= app.draw(document.getElementById('author').value,'"+j.name+"')>bp</button></td>";
          });
          $("table").append(b2);
      });
  };
  var drawPrint = function (author, print){
      _author = author;
      _print = print;
      console.log(author);
      console.log(print);
      apimock.getBlueprintsByNameAndAuthor(_author,_print,function (dr){
          var points = dr.map(function (g){
                return g.points;
          });
          var c = document.getElementById("myCanvas");
          var ctx = c.getContext("2d");
          ctx.moveTo(points[0][0],points[0][1]);
          ctx.lineTo(points[1][0],points[1][1]);
          ctx.stroke();
      });
  };
  
  return {
    setAuthor: function (author) {
        update(author);
    },
    draw: function (author, print){
        drawPrint(author,print);
    }
  };

})();
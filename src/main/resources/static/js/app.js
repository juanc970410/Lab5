/* global apimock, apiclient */

var app = (function () {
  var _author;
  var _print;
  var api = apiclient;
  var _points;
  var ctx;
  var update = function (author) {
      _author = author;
      var tpoints = parseInt("0");
      api.getBlueprintsByAuthor(_author,function (lista){
          var blueprint = lista.map(function (k){
              return{
                  "name":k.name,"pointlen":k.points.length
              };
          });
          var b2 = blueprint.map(function (j){
              tpoints = tpoints + parseInt(j.pointlen);
              
                return "<tr> <td>"+j.name+"</td> <td>"+j.pointlen+"</td> <td><button type='button' class='btn btn-default' onclick= app.draw(document.getElementById('author').value,'"+j.name+"')>draw</button></td>";
          });
          document.getElementById("aut").innerHTML = _author+"'s blueprints";
          $("table").empty();
          $("table").append("<tr><th>Plano</th> <th>Longitud</th> <th>Dibujar</th> </tr>");
          $("table").append(b2);
          document.getElementById("tpoints").innerHTML = "Total user points: "+tpoints;
      });
  };
  var drawPrint = function (author, print){
      _author = author;
      _print = print;
      api.getBlueprintsByNameAndAuthor(_author,_print,function (dr){
          var points = dr.points;
          _points = points;
          document.getElementById("myCanvas").getContext("2d").clearRect(0,0,500,400);
          ctx.beginPath();
          ctx.moveTo(points[0].x,points[0].y);
          for (i=1; i<points.length; i++){
              ctx.lineTo(points[i].x,points[i].y);
          }
          ctx.stroke();
          ctx.closePath();
          document.getElementById("cbp").innerHTML = "Current blueprint: "+print;
      });
  };
  
  return {
    setAuthor: function (author) {
        update(author);
    },
    draw: function (author, print){
        drawPrint(author,print);
    },
    init:function(){
      ctx = document.getElementById("myCanvas").getContext("2d");
      var rect = document.getElementById("myCanvas").getBoundingClientRect();
      console.log(rect.left);
      //if PointerEvent is suppported by the browser:
      if(window.PointerEvent) {
        document.getElementById("myCanvas").addEventListener("pointerdown", function(event){
          var x = parseInt(event.pageX)-parseInt(rect.left);
          var y = parseInt(event.pageY)-parseInt(rect.top);
          if(_print.toString()!=="undefined"){
              _points[_points.length]={"x": x, "y": y};
              ctx.beginPath();
              ctx.moveTo(_points[_points.length-2].x,_points[_points.length-2].y);
              ctx.lineTo(_points[_points.length-1].x,_points[_points.length-1].y);
              ctx.stroke();
              ctx.closePath();
          }
        });
      }
      else {
        document.getElementById("myCanvas").addEventListener("mousedown", function(event){
                    alert('mousedown at '+event.clientX+','+event.clientY);  

          }
        );
      }
    }
  };

})();
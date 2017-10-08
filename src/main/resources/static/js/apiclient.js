var apiclient = (function(){
    return{
        getBlueprintsByAuthor: function (authname, callback){
           $.get("/blueprints/"+authname,callback);
        },
        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {
            $.get("/blueprints/"+authname+"/"+bpname,callback);
        },
        putBlueprint: function(blueprint){
            return $.ajax({type: "PUT", url: "/blueprints/" +blueprint.author+ "/"+ blueprint.name, data: JSON.stringify(blueprint), contentType: "application/json"});
        },
        postBlueprint: function (blueprint){
            return $.ajax({type: "POST", url: "/blueprints/", data: JSON.stringify(blueprint), contentType: "application/json"});
        },
        deleteBlueprint: function (author,print){
            return $.ajax({type: "DELETE", url: "/blueprints/"+author+"/"+print});
        }
    };
})();
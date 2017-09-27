var apiclient = (function(){
    return{
        getBlueprintsByAuthor: function (authname, callback){
           $.get("/blueprints/"+authname,callback);
        },
        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {
            $.get("/blueprints/"+authname+"/"+bpname,callback);
        }
    };
})();
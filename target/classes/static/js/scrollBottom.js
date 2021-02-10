
 var end = document.getElementById("bottom");



window.addEventListener('scroll',function(){
    
   
    console.log(window.scrollY*2.2);
    console.log(document.documentElement.offsetHeight);
    
    if(window.scrollY*2.2 > document.documentElement.offsetHeight){
        end.style.transform="translateY(0px)";
    }
    
    if(window.scrollY*2.2<document.documentElement.offsetHeight-250){
        end.style.transform="translateY(386px)";
    }
    

});
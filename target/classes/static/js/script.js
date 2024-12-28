console.log("Script loaded");


let CurrentTheme = getTheme();
//initial
document.addEventListener("DOMContentLoaded" ,() => {
    changeTheme();
});


//TODO
function changeTheme(){
    //set to web page
    document.querySelector("html").classList.add(CurrentTheme);

    //set the lissener to change theme botoon
    const changeThemeButton = document.querySelector("#theme_change_button");
      //change the text of button
      changeThemeButton.querySelector("span").textContent = 
      CurrentTheme == "light" ?   "Dark" : "light";



    changeThemeButton.addEventListener("click" , (event) => {
        const oldTheme = CurrentTheme;
        console.log("chenged theme button clicked");
        if(CurrentTheme === "dark"){
            //theme to light
            CurrentTheme =  "light";
                }else{
                    //theme to dark
                    CurrentTheme = "dark";  
                }
                //localstorage mai update karage
                setTheme(CurrentTheme);
                //remove the current theme
                document.querySelector('html').classList.remove(oldTheme);

                //ste the current theme
                document.querySelector('html').classList.add(CurrentTheme);
                 //change the text of button
                 changeThemeButton.querySelector("span").textContent = 
                 CurrentTheme == "light" ?   "Dark" : "Light";
               
    });
}


//set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}



// get theme from localstorage
function getTheme(){
    let theme = localStorage.getItem("theme");
    if(theme) return theme;
    else return "light";
}
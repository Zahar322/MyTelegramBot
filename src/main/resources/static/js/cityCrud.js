let status;
let url;
let method;
let inputCityName=document.getElementById("cityName");
let inputCityDesc=document.getElementById("cityDesc");
let inputCityId=document.getElementById("cityId");
let actions=document.querySelectorAll(".actions");
let updateByNameElement=document.getElementById("updateName");
let nameSearchUpdate=document.getElementById("name");
let crudButton=document.getElementById("crudButton");
let hideElementsWhenDelete=document.querySelectorAll(".hideWhenDelete");

window.onload=()=>{
    actions.forEach(action=>{
        action.onclick=()=>{
            status=action.getAttribute("url")+"d";
            setCrudParameters(action.getAttribute("url"),action.getAttribute("method"),action.innerHTML);
        }
    });
    updateByNameElement.onblur=findCityByName;
}
function findCityByName(){
    let stateUrl=url;
    setCrudParameters(`/city/${updateByNameElement.value}`,"GET");
    crudOperation();
    url=stateUrl;
    status="PUT";
}

function setCity(json) {
    let city=JSON.parse(json);
    inputCityName.value=city.cityName;
    inputCityDesc.value=city.cityDescription;
    inputCityId.value=city.id;
}


function crudOperation(){
    let request=new XMLHttpRequest();
    let data=new City(inputCityName.value,inputCityDesc.value);
    request.open(method,url);
    request.onload=function(){
        if(request.status==200&&method==="GET"){
            setCity(request.responseText);
            method="PUT";
            status="got";
        }
    };
    if(method==="PUT")data.id=inputCityId.value;
    request.setRequestHeader("Content-Type","application/json");
    request.send(JSON.stringify(data));
    alert(status);
    formReset();
}

function formReset() {
    inputCityName.value="";
    inputCityDesc.value="";
    inputCityId.value="";
    updateByNameElement.value="";
}

function setCrudParameters(currentUrl,currentMethod,buttonText="Get"){
    url=`${window.location.protocol}//${window.location.host}/rest/${currentUrl}`;
    method=currentMethod;
    if(buttonText!="Get")crudButton.innerHTML=buttonText;
    if(method==="PUT"||method==="GET"){
        nameSearchUpdate.style.cssText='display:block';

    } else nameSearchUpdate.style.cssText='display:none';
    if(method==="DELETE"){
        hideElementsWhenDelete.forEach(element=>{element.style.cssText="display:none"});
        return;
    }
    hideElementsWhenDelete.forEach(element=>element.style.cssText="display:block");
}

function City(cityName, cityDescription) {
    this.cityName=cityName;
    this.cityDescription=cityDescription;
}